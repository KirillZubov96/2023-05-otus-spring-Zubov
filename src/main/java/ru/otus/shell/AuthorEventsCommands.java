package ru.otus.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.dao.author.AuthorDao;

@ShellComponent
@AllArgsConstructor
public class AuthorEventsCommands {
    private final AuthorDao authorDao;

    @ShellMethod(value = "Count Authors", key = {"count-author", "CA"})
    public void countAuthor() {
        System.out.println(authorDao.count());
    }
}
