package api;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import util.annotations.Secured;
import util.security.JWTHelper;
import util.security.RoleType;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.nio.file.attribute.UserPrincipal;
import java.security.Principal;
import java.util.HashMap;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final String AUTHENTICATION_SCHEME = "Bearer";
    JWTHelper jwtHelper = JWTHelper.getInstance();

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        try {// Get the Authorization header from the request
            String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

            // Extract the token from the Authorization header
            String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

            // Validate the token
            HashMap userMap = validateToken(token);

            final SecurityContext securityContext = requestContext.getSecurityContext();
            requestContext.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return new UserPrincipal() {
                        @Override
                        public String getName() {
                            return (String) userMap.get("name");
                        }
                    };
                }

                @Override
                public boolean isUserInRole(String role) {
                    RoleType userRole = (RoleType) userMap.get("role");
                    if(userRole.toString() == role){
                        return true;
                    }
                    return false;
                }

                @Override
                public boolean isSecure() {
                    return securityContext.isSecure();
                }

                @Override
                public String getAuthenticationScheme() {
                    return securityContext.getAuthenticationScheme();
                }
            });
        } catch (MalformedJwtException e) {
            requestContext.abortWith(Response.status(Response.Status.BAD_REQUEST).build());
        } catch (ExpiredJwtException e) {
            requestContext.abortWith(Response.status(Response.Status.GATEWAY_TIMEOUT).build());
        } catch (Exception e) {
            abortWithUnauthorized(requestContext);
        }
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext) {
        // Abort the filter chain with a 401 status code response
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .build());
    }

    private HashMap validateToken(String token) {
        return jwtHelper.claimKey(token);
    }
}
