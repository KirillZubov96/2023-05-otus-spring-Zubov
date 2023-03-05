package ru.otus.shell;

import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.dao.genre.GenreDao;

@ShellComponent
@AllArgsConstructor
public class GenreEventsCommands {

    private final GenreDao genreDao;

    @ShellMethod(value = "Count Genres", key = {"count-genre", "GA"})
    public void countGenres() {
        System.out.println(genreDao.count());
    }
}
