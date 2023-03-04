package com.polarbookshop.catalogservice.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryBookRepository implements BookRepository {

    private final Map<String, Book> books = new HashMap<>();

    @Override
    public Iterable<Book> findAll() {
      return books.values();
    }

    @Override
    public Optional<Book> findByIsbn(String isbn) {
      return Optional.ofNullable(books.get(isbn));
    }

    @Override
    public boolean existsByIsbn(String isbn) {
        return books.containsKey(isbn);
    }

    @Override
    public Book save(Book book) {
      return books.put(book.isbn(), book);
    }

    @Override
    public void deleteByIsbn(String isbn) {
      books.remove(isbn);
    }
}
