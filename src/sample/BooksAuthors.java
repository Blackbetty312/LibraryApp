package sample;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class BooksAuthors {
    private int id;
    private Author authorByAuthorIdAuthor;
    private Book bookByBookIdBook;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BooksAuthors that = (BooksAuthors) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @ManyToOne
    @JoinColumn(name = "author_id_author", referencedColumnName = "id_author", nullable = false)
    public Author getAuthorByAuthorIdAuthor() {
        return authorByAuthorIdAuthor;
    }

    public void setAuthorByAuthorIdAuthor(Author authorByAuthorIdAuthor) {
        this.authorByAuthorIdAuthor = authorByAuthorIdAuthor;
    }

    @ManyToOne
    @JoinColumn(name = "book_id_book", referencedColumnName = "id_book", nullable = false)
    public Book getBookByBookIdBook() {
        return bookByBookIdBook;
    }

    public void setBookByBookIdBook(Book bookByBookIdBook) {
        this.bookByBookIdBook = bookByBookIdBook;
    }
}
