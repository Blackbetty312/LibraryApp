package sample;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Book {
    private int idBook;
    private String isbn;
    private String title;
    private String description;
    private Integer releaseYear;
    private Collection<BooksAuthors> booksAuthorsByIdBook;
    private Publishing publishingByPublishingIdPublishing;

    @Id
    @Column(name = "id_book", nullable = false)
    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    @Basic
    @Column(name = "isbn", nullable = true, length = 13)
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 150)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "release_year", nullable = true)
    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Transient
    public String getPublisherName() {
        return publishingByPublishingIdPublishing.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return idBook == book.idBook &&
                Objects.equals(isbn, book.isbn) &&
                Objects.equals(title, book.title) &&
                Objects.equals(description, book.description) &&
                Objects.equals(releaseYear, book.releaseYear);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBook, isbn, title, description, releaseYear);
    }

    @OneToMany(mappedBy = "bookByBookIdBook")
    public Collection<BooksAuthors> getBooksAuthorsByIdBook() {
        return booksAuthorsByIdBook;
    }

    public void setBooksAuthorsByIdBook(Collection<BooksAuthors> booksAuthorsByIdBook) {
        this.booksAuthorsByIdBook = booksAuthorsByIdBook;
    }

    @ManyToOne
    @JoinColumn(name = "publishing_id_publishing", referencedColumnName = "id_publishing", nullable = false)
    public Publishing getPublishingByPublishingIdPublishing() {
        return publishingByPublishingIdPublishing;
    }

    public void setPublishingByPublishingIdPublishing(Publishing publishingByPublishingIdPublishing) {
        this.publishingByPublishingIdPublishing = publishingByPublishingIdPublishing;
    }


}
