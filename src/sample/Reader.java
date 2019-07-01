package sample;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reader", nullable = false)
    private int idReader;
    private String name;
    private String lastName;
    private String telephone;
    private String personalId;
    private Users usersByUsersIdUser;


    public int getIdReader() {
        return idReader;
    }

    public void setIdReader(int idReader) {
        this.idReader = idReader;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 100)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "telephone", nullable = true, length = 25)
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Basic
    @Column(name = "personal_id", nullable = true, length = 11)
    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return idReader == reader.idReader &&
                Objects.equals(name, reader.name) &&
                Objects.equals(lastName, reader.lastName) &&
                Objects.equals(telephone, reader.telephone) &&
                Objects.equals(personalId, reader.personalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idReader, name, lastName, telephone, personalId);
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
