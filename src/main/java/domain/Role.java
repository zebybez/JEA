package domain;

import util.security.RoleType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Role implements Serializable {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    int id;
    String name;

    public Role(){}

    public Role(RoleType role) {
        this.name = role.name();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
