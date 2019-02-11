package domain;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
public class Account {

    private String uuid;

    private User user;
    private String email;
    private String passwordHash;

    public Account() {
    }

    public Account(String uuid, User user, String email, String passwordHash) {
        this.uuid = uuid;
        this.user = user;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
