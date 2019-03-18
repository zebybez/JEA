package api;


import business.interfaces.AccountService;
import domain.Account;
import util.annotations.Secured;
import util.security.Role;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Secured
@Provider
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    private String profileName;
    @Context
    private ResourceInfo resourceInfo;

    @Context
    private SecurityContext securityContext;

    @Inject
    private AccountService accountService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        List<Role> classRoles = extractRoles(resourceInfo.getResourceClass());
        List<Role> methodRoles = extractRoles(resourceInfo.getResourceMethod());

        profileName = securityContext.getUserPrincipal().getName();

        try {
            if (methodRoles.isEmpty()) {
                checkPermissions(classRoles);
            } else {
                checkPermissions(methodRoles);
            }

        } catch (Exception e) {
            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).build());
        }
    }

    private List<Role> extractRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new ArrayList<Role>();
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new ArrayList<Role>();
            } else {
                Role[] allowedRoles = secured.value();
                return Arrays.asList(allowedRoles);
            }
        }
    }

    private void checkPermissions(List<Role> allowedRoles) throws SecurityException {
        // Check if the user contains one of the allowed roles
        // Throw an Exception if the user has not permission to execute the method
        if(allowedRoles.isEmpty()){
            return;
        }
        Account account = accountService.getAccountByProfileName(profileName);
        if(!allowedRoles.contains(account.getRole())){
            throw new SecurityException("user not in role");
        }
    }
}
