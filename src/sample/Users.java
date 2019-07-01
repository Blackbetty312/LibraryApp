package sample;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id_user", nullable = false)
    private int idUser;
    private String email;
    private String password;
    private Byte permission;


    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 64)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "permission", nullable = true)
    public Byte getPermission() {
        return permission;
    }

    public void setPermission(Byte permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return idUser == users.idUser &&
                Objects.equals(email, users.email) &&
                Objects.equals(password, users.password) &&
                Objects.equals(permission, users.permission);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, email, password, permission);
    }
}
