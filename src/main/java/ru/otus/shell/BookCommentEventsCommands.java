package ru.otus.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.dao.comment.BookCommentDao;
import ru.otus.domain.comment.BookComment;

@ShellComponent
@AllArgsConstructor
public class BookCommentEventsCommands {

    private final BookCommentDao bookCommentDao;

    @ShellMethod(value = "Count Comments", key = {"count-comments", "CC"})
    public void countBooks() {
        System.out.println(bookCommentDao.count());
    }

    @ShellMethod(value = "Add new book comment", key = {"add-book-comment"})
    public void insertBook(int bookId, String comment) {
        bookCommentDao.insert(bookId, comment);
        System.out.print("Комментарий к книге добавлен");
    }

    @ShellMethod(value = "Get book comment by id", key = {"get-book-comment-by-id"})
    public void getBookById(int id) {
        BookComment bookComment = bookCommentDao.getById(id);
        System.out.printf("Комментарий %s к книге с id = %s", bookComment.getComment(), bookComment.getBook().getId());
    }

    @ShellMethod(value = "Get all books comments", key = {"get-all-books-comments"})
    public void getAllBooks() {
        bookCommentDao.getAll().stream().forEach(book ->
                System.out.printf("Комментарий %s", book.getComment()));
    }

    @ShellMethod(value = "Delete book comment by id", key = {"delete-book-comment-by-id"})
    public void deleteBookById(int id) {
        bookCommentDao.deleteById(id);
        System.out.printf("комментарий с id = %s удален%n", id);
    }
}
