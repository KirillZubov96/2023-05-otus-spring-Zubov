package ru.otus.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.dao.author.AuthorDao;
import ru.otus.dao.book.BookDao;
import ru.otus.dao.genre.GenreDao;
import ru.otus.domain.author.Author;
import ru.otus.domain.book.Book;

@ShellComponent
@AllArgsConstructor
public class BooksEventsCommands {

    private final BookDao bookDao;

    @ShellMethod(value = "Count Books", key = {"count-book", "BA"})
    public void countBooks() {
        System.out.println(bookDao.count());
    }
    @ShellMethod(value = "Add new book", key = {"add-book"})
    public void insertBook(String name, long authorId, long genreId) {
        bookDao.insert(name, authorId, genreId);
        System.out.println(String.format("Книга с именем %s добавлена", name));
    }
    @ShellMethod(value = "Get book by id", key = {"get-book-by-id"})
    public void getBookById(long id) {
        Book book = bookDao.getById(id);
        System.out.println(String.format("id: %s, Название книги: %s, Имя автора: %s, Жанр: %s", book.getId(), book.getName(), book.getAuthor().getName(), book.getGenre().getName()));
    }
    @ShellMethod(value = "Get all books", key = {"get-all-books"})
    public void getAllBooks() {
        bookDao.getAll().stream().forEach(book ->
                System.out.println(String.format("id: %s, Название книги: %s, Имя автора: %s, Жанр: %s", book.getId(), book.getName(), book.getAuthor().getName(), book.getGenre().getName())));
    }

    @ShellMethod(value = "Delete book by id", key = {"delete-book-by-id"})
    public void deleteBookById(long id) {
        bookDao.deleteById(id);
        System.out.println(String.format("Книга с id = %s удалена", id));
    }
}
