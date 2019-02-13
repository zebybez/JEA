package util.security;

import java.io.Serializable;

public class Payload implements Serializable {
    private String email;
    private String password;
    private String ProfileUuid;
    private String Role;

    public Payload(String email, String password, String profileUuid, String role) {
        this.email = email;
        this.password = password;
        ProfileUuid = profileUuid;
        Role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileUuid() {
        return ProfileUuid;
    }

    public void setProfileUuid(String profileUuid) {
        ProfileUuid = profileUuid;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }
}
