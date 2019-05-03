package util.security;

import java.io.Serializable;

/***
 * The class that identifies a user, to be sent as the payload of a JWS.
 */
public class Payload implements Serializable {
    private String email;
    private String name;
    private String profileUuid;
    private String roleName;

    public Payload(String email, String name, String profileUuid, String roleName) {
        this.email = email;
        this.profileUuid = profileUuid;
        this.name = name;
        this.roleName = roleName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfileUuid() {
        return profileUuid;
    }

    public void setProfileUuid(String profileUuid) {
        this.profileUuid = profileUuid;
    }
}
