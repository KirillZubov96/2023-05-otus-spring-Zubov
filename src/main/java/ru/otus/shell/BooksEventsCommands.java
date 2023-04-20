package ru.otus.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.dao.author.AuthorDao;
import ru.otus.dao.book.BookDao;
import ru.otus.dao.genre.GenreDao;
import ru.otus.domain.book.Book;

@ShellComponent
@AllArgsConstructor
public class BooksEventsCommands {

    private final BookDao bookDao;

    private AuthorDao authorDao;

    private GenreDao genreDao;

    @ShellMethod(value = "Count Books", key = {"count-book", "BA"})
    public void countBooks() {
        System.out.println(bookDao.count());
    }

    @ShellMethod(value = "Add new book", key = {"add-book"})
    public void insertBook(String name, int authorId, int genreId) {
        bookDao.insert(new Book(name, authorDao.getById(authorId), genreDao.getById(genreId)));
        System.out.printf("Книга с именем %s добавлена%n", name);
    }

    @ShellMethod(value = "Get book by id", key = {"get-book-by-id"})
    public void getBookById(int id) {
        Book book = bookDao.getById(id);
        System.out.printf("id: %s, Название книги: %s, Имя автора: %s, Жанр: %s%n", book.getId(), book.getName(), book.getAuthor().getName(), book.getGenre().getName());
    }

    @ShellMethod(value = "Get all books", key = {"get-all-books"})
    public void getAllBooks() {
        bookDao.getAll().stream().forEach(book ->
                System.out.printf("id: %s, Название книги: %s, Имя автора: %s, Жанр: %s%n", book.getId(), book.getName(), book.getAuthor().getName(), book.getGenre().getName()));
    }

    @ShellMethod(value = "Delete book by id", key = {"delete-book-by-id"})
    public void deleteBookById(int id) {
        bookDao.deleteById(id);
        System.out.printf("Книга с id = %s удалена%n", id);
    }
}
