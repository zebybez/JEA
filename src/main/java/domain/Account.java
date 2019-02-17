package domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Account")
@NamedQueries({
        @NamedQuery(name = "account.findByEmail", query = "SELECT a FROM Account a WHERE a.email = :email"),
        @NamedQuery(name = "account.count", query = "SELECT COUNT(a) FROM Account a")})
public class Account implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String uuid;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Profile profile;
    @Column(unique = true)
    private String email;
    private String passwordHash;
    private String salt;
    public Account() {
    }
    public Account(String uuid, Profile profile, String email, String passwordHash) {
        this.uuid = uuid;
        this.profile = profile;
        this.email = email;
        this.passwordHash = passwordHash;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
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
