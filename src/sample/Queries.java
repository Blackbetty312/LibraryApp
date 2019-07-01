package sample;

import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class Queries {

    DatabaseConnect dbconnect = null;

    Queries(DatabaseConnect dbconn) {
        this.dbconnect = dbconn;
        this.dbconnect.startSession();
    }

    public void getAllBookWithAuthorsNames() {
        String hql = "FROM Book";
        Query query = dbconnect.getSession().createQuery(hql);
        List list = query.list();
        Book b = null;
        Author a = null;
        int i = 1;
        for(Object o : list) {
            b = (Book) o;
            System.out.print(b.getTitle() + " - ");
            for (BooksAuthors ba : b.getBooksAuthorsByIdBook()) {
                if(i == 1) {
                    System.out.print(ba.getAuthorByAuthorIdAuthor().getFirstName() + " " + ba.getAuthorByAuthorIdAuthor().getLastName());
                } else {
                    System.out.print(", " + ba.getAuthorByAuthorIdAuthor().getFirstName() + " " + ba.getAuthorByAuthorIdAuthor().getLastName());
                }
                i++;
            }
            i = 1;
            System.out.println();
        }
    }

    public int createBook(Book b, int id) {
        Transaction tx = dbconnect.getSession().beginTransaction();
        Query query = dbconnect.getSession().createSQLQuery("INSERT INTO book (isbn, title, description, release_year, publishing_id_publishing) VALUES (:isbn, :title, :desc, :year, :id)");
        query.setParameter("isbn", b.getIsbn())
                .setParameter("title", b.getTitle())
                .setParameter("desc", b.getDescription())
                .setParameter("year", b.getReleaseYear())
                .setParameter("id", id);
        int result;
        result = query.executeUpdate();
        tx.commit();
        return result;
    }

    public List getAllPublishing() {
        String hql = "FROM Publishing";
        Query query = dbconnect.getSession().createQuery(hql);
        List list = query.list();
        return list;
    }
    public int getPublishingFromName(String name) {
        String hql = "FROM Publishing WHERE name = :name";
        Query query = dbconnect.getSession().createQuery(hql);
        query.setParameter("name", name);
        Publishing publishing = (Publishing) query.list().get(0);
        return publishing.getIdPublishing();
    }

    public List getAllAuthors() {
        String hql = "FROM Author";
        Query query = dbconnect.getSession().createQuery(hql);
        List list = query.list();
        return list;
    }
    public List getAuthorFromId(int id) {
        String hql = "FROM Author WHERE id_author = :id";
        Query query = dbconnect.getSession().createQuery(hql);
        query.setParameter("id", id);
        List list = query.list();
        return list;
    }
    public List getAllBooks() {
        String hql = "select b from Book b inner join b.publishingByPublishingIdPublishing";
        Query query = dbconnect.getSession().createQuery(hql);
        List list = query.list();
        return list;
    }
    public String getAuthorInfo(Integer num) {
        String hql = "FROM Author WHERE id_author = ?";
        Query query = dbconnect.getSession().createQuery(hql);
        List list = query
                .setParameter(0, num)
                .list();
        Author a = (Author) list;
        return a.getBiography();
    }
    public void createReader(Reader reader) {
        Transaction tx = dbconnect.getSession().beginTransaction();
        Query query = dbconnect.getSession().createSQLQuery("INSERT INTO users (email, password, permission) VALUES (:email, :pass, 0)");
        query.setParameter("email", reader.getUsersByUsersIdUser().getEmail());
        query.setParameter("pass", reader.getUsersByUsersIdUser().getPassword());
        query.executeUpdate();

        query = dbconnect.getSession().createSQLQuery("INSERT INTO reader (users_id_user, name, last_name, telephone, personal_id) VALUES (:id, :name, :lname, :tel, :pesel)");
        query.setParameter("id", getIDUser(reader.getUsersByUsersIdUser()))
                .setParameter("name", reader.getName())
                .setParameter("lname", reader.getLastName())
                .setParameter("tel", reader.getTelephone())
                .setParameter("pesel", reader.getPersonalId());
        query.executeUpdate();
        tx.commit();
    }

    public int getIDUser(Users user) {
        Query query = dbconnect.getSession().createQuery("FROM Users WHERE email = :mail");
        query.setParameter("mail", user.getEmail());
        Users tmp = (Users) query.list().get(0);
        return tmp.getIdUser();
    }


    public Boolean existUser(Users user) {
        Query query = dbconnect.getSession().createQuery("FROM Users WHERE email = :mail");
        query.setParameter("mail", user.getEmail());
        return !query.list().isEmpty();
    }
    // 0 - czytelnik | 1 - pracownik | 2 - brak uzytkownika
    public int checkLogin(Users user) {
        Query query = dbconnect.getSession().createQuery("SELECT permission FROM Users WHERE email = :mail AND password = :pass");
        query.setParameter("mail", user.getEmail())
                .setParameter("pass", user.getPassword());
        List list = query.list();
        if(query.list().isEmpty()) {
            return 2;
        }
        Byte tmp = (Byte) list.get(0);
        return tmp;
    }
    public int deleteAuthor(int id) {
        Transaction tx = dbconnect.getSession().beginTransaction();
        Query query = dbconnect.getSession().createQuery("DELETE FROM Author WHERE id_author = :id");
        query.setParameter("id", id);
        int result = query.executeUpdate();
        tx.commit();
        return result;
    }
    public void createAuthor(Author a) {
        Transaction tx = dbconnect.getSession().beginTransaction();
        Query query = dbconnect.getSession().createSQLQuery("INSERT INTO author (first_name, last_name, biography, birth_date) VALUES (:name, :lname, :desc, :bday)");
        query.setParameter("name", a.getFirstName())
                .setParameter("lname", a.getLastName())
                .setParameter("desc", a.getBiography())
                .setParameter("bday", a.getBirthDate());
        query.executeUpdate();
        tx.commit();
    }
}
