package ru.otus.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.author.Author;
import ru.otus.domain.book.Book;
import ru.otus.domain.genre.Genre;

import java.util.List;

@Transactional
@SuppressWarnings("JpaQlInspection")
@Repository
public class LibraryJdbcRepository implements LibraryDao {

    @PersistenceContext
    private EntityManager em;

    @Transactional
    @Override
    public void storeBook(Book book) {

        TypedQuery<Author> queryAuthor = em
                .createQuery("SELECT a FROM Author a WHERE a.name = :authorName", Author.class);
        queryAuthor.setParameter("authorName", book.getAuthor().getName());
//newBook -t title -g genre -a author
        Author foundedAuthor;
        try {
            foundedAuthor = queryAuthor.getSingleResult();
            book.setAuthor(foundedAuthor);
        } catch (NoResultException ex) {
            em.persist(book.getAuthor());
        }
        TypedQuery<Genre> queryGenre = em.createQuery("SELECT g FROM Genre g WHERE g.title = :genreTitle", Genre.class);
        queryGenre.setParameter("genreTitle", book.getGenre().getTitle());
        Genre foundedGenre;
        try {
            foundedGenre = queryGenre.getSingleResult();
            book.setGenre(foundedGenre);
        } catch (NoResultException ex) {
            em.persist(book.getGenre());
        }

        em.merge(book);
    }

    @Override
    public List<Book> getAllBooks() {
        TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b",
                Book.class);
        return query.getResultList();
    }

    @Override
    public Book getBook(Book book) {
        TypedQuery<Book> searchBook = em.createQuery(
                "SELECT b FROM Book b, Author a, Genre g, Comment c WHERE b.title = :bookTitle AND a.name = :authorName AND g.title = :genreTitle",
                Book.class);
        searchBook.setParameter("bookTitle", book.getTitle());
        searchBook.setParameter("authorName", book.getAuthor().getName());
        searchBook.setParameter("genreTitle", book.getGenre().getTitle());
        Book foundedBook;
        try {
            foundedBook = searchBook.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
        return foundedBook;
    }

    @Override
    public Book getBookByTitle(String title) {
        TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b WHERE b.title = :title",
                Book.class);
        query.setParameter("title", title);
        return query.getSingleResult();
    }

    @Override
    public Author getAuthorByName(String name) {
        TypedQuery<Author> query = em.createQuery(
                "SELECT a FROM AUTHOR a WHERE a.AUTHORNAME = :name",
                Author.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public Genre getGenreByName(String name) {
        TypedQuery<Genre> query = em.createQuery(
                "SELECT g FROM Book g WHERE g.GENRETITLE = :name",
                Genre.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public Book getBookByID(Long id) {
        return em.find(Book.class, id);
    }

    @Override
    public List<Genre> getAllGenres() {
        TypedQuery<Genre> query = em.createQuery(
                "SELECT g FROM Genre g",
                Genre.class);
        return query.getResultList();
    }

    @Override
    public void removeBookById(Long id) {
        Book book = em.find(Book.class, id);
        em.remove(book);
    }
}
