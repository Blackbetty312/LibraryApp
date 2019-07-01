package sample;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Worker {
    private int idWorker;
    private Role roleByRoleIdRole;
    private Users usersByUsersIdUser;

    @Id
    @Column(name = "id_worker", nullable = false)
    public int getIdWorker() {
        return idWorker;
    }

    public void setIdWorker(int idWorker) {
        this.idWorker = idWorker;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Worker worker = (Worker) o;
        return idWorker == worker.idWorker;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idWorker);
    }

    @ManyToOne
    @JoinColumn(name = "role_id_role", referencedColumnName = "id_role", nullable = false)
    public Role getRoleByRoleIdRole() {
        return roleByRoleIdRole;
    }

    public void setRoleByRoleIdRole(Role roleByRoleIdRole) {
        this.roleByRoleIdRole = roleByRoleIdRole;
    }

    @ManyToOne
    @JoinColumn(name = "users_id_user", referencedColumnName = "id_user", nullable = false)
    public Users getUsersByUsersIdUser() {
        return usersByUsersIdUser;
    }

    public void setUsersByUsersIdUser(Users usersByUsersIdUser) {
        this.usersByUsersIdUser = usersByUsersIdUser;
    }
}
