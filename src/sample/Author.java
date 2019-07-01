package sample;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

@Entity
public class Author {
    private int idAuthor;
    private String firstName;
    private String lastName;
    private String biography;
    private Date birthDate;

    @Id
    @Column(name = "id_author", nullable = false)
    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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
    @Column(name = "biography", nullable = true, length = -1)
    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    @Basic
    @Column(name = "birth_date", nullable = true)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        if(birthDate == null)
            return "Brak danych";
        else
            return dateFormat.format(birthDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return idAuthor == author.idAuthor &&
                Objects.equals(firstName, author.firstName) &&
                Objects.equals(lastName, author.lastName) &&
                Objects.equals(biography, author.biography) &&
                Objects.equals(birthDate, author.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAuthor, firstName, lastName, biography, birthDate);
    }

    @Override
    public String toString() {
        String s = getIdAuthor() + " " + getFirstName() + " " + getLastName() + " " + getBiography() + " " + getBirthDate();
        return s;
    }
}
