package domain;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
public class Account implements Serializable {

    private String uuid;

    private Profile profile;
    private String email;
    private String passwordHash;

    public Account() {
    }

    public Account(String uuid, Profile profile, String email, String passwordHash) {
        this.uuid = uuid;
        this.profile = profile;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
