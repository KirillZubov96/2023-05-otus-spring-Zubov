package ru.otus.domain.author;

import jakarta.persistence.*;

@Entity
@Table(name = "AUTHOR")
public class Author {

    @Id
    @Column(name = "ID", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AUTHORNAME")
    private String name;

    public Author(String name) {
        this.name = name;
    }


    public Author() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}