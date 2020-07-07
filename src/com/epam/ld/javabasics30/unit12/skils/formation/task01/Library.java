package com.epam.ld.javabasics30.unit12.skils.formation.task01;

import com.epam.ld.javabasics30.unit12.skils.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Library {
    private List<Book> books = new ArrayList<>(); // В условии список, возможно уместнее было бы использовать Set
    // Да, в условии список, т.к. для начинающих проце все начать с ArrayList, а сеты в голову сразу не влазят)))

    public Library() {
    }

    public Library add(Book book) {
        books.add(book);
        return this;
    }

    public Library add(Library library) {
        books.addAll(library.books);
        return this;
    }

    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }
}
