package com.epam.ld.javabasics30.unit12.skils.consolidation.task01;

import com.epam.ld.javabasics30.unit12.skils.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Library {
    private static final Logger LOG = LoggerFactory.getLogger(Library.class);

    public static final Comparator<Book> COMPARATOR_TITLE_STARTS_WITH = (book1, book2) -> book1.getTitle().startsWith(book2.getTitle()) ? 0 : -1;
    public static final Comparator<Book> COMPARATOR_TITLE_COMPARE_IGNORE_CASE = Comparator.comparing(book -> book.getTitle().toLowerCase());

    private List<Book> books = new ArrayList<>(); // В условии список, возможно уместнее было бы использовать Set

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

    public Library remove(Book book) {
        if (books.remove(book)) {
            LOG.warn("Book {} has not been found.", book);
        } else {
            LOG.info("Book {} has been removed.", book);
        }
        return this;
    }

    public Library removeAll(Library library) {
        for (Book book : library.books) {
            remove(book);
        }
        return this;
    }

    public Book getFirstBookByNameStartsWith(String name) {
        Book toFind = new Book(name, null, 0, 0);
        return getFirstBookByCustomSearch(COMPARATOR_TITLE_STARTS_WITH, toFind);
    }

    public Library getBooksByNameStartsWith(String name) {
        Book toFind = new Book(name, null, 0, 0);
        return getBooksByCustomSearch(COMPARATOR_TITLE_STARTS_WITH, toFind, false);
    }

    public Book getFirstBookByName(String name) {
        Book toFind = new Book(name, null, 0, 0);
        return getFirstBookByCustomSearch(COMPARATOR_TITLE_COMPARE_IGNORE_CASE, toFind);
    }

    public Library getBooksByName(String name) {
        Book toFind = new Book(name, null, 0, 0);
        return getBooksByCustomSearch(COMPARATOR_TITLE_COMPARE_IGNORE_CASE, toFind, false);
    }

    public Book getFirstBookByCustomSearch(Comparator<Book> comparator, Book toFind) {
        Library library = getBooksByCustomSearch(comparator, toFind, true);
        if (library.books.size() == 1) {
            return library.books.get(0);
        }
        return null;
    }

    public Library getBooksByCustomSearch(Comparator<Book> comparator, Book toFind) {
        return getBooksByCustomSearch(comparator, toFind, false);
    }

    private Library getBooksByCustomSearch(Comparator<Book> comparator, Book toFind, Boolean firstOnly) {
        Library newLibrary = new Library();
        for (Book book : books) {
            if (comparator.compare(book, toFind) == 0) {
                newLibrary.add(book);
                if (firstOnly) {
                    return newLibrary;
                }
            }
        }
        return newLibrary;
    }

}
