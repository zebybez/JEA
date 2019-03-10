package util.security;

import java.io.Serializable;

/***
 * The class that identifies a user, to be sent as the payload of a JWS.
 */
public class Payload implements Serializable {
    private String email;
    private String name;
    private String ProfileUuid;
    private Role Role;

    public Payload(String email, String name, String profileUuid, Role role) {
        this.email = email;
        ProfileUuid = profileUuid;
        Role = role;
        this.name = name;
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
        return ProfileUuid;
    }

    public void setProfileUuid(String profileUuid) {
        ProfileUuid = profileUuid;
    }

    public Role getRole() {
        return Role;
    }

    public void setRole(Role role) {
        Role = role;
    }
}
