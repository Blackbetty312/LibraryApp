package sample;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Publishing {
    private int idPublishing;
    private String name;

    @Id
    @Column(name = "id_publishing", nullable = false)
    public int getIdPublishing() {
        return idPublishing;
    }

    public void setIdPublishing(int idPublishing) {
        this.idPublishing = idPublishing;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Publishing that = (Publishing) o;
        return idPublishing == that.idPublishing &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPublishing, name);
    }

    @Override
    public String toString() {
        return name;
    }
}
