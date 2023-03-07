package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile({"local", "testdata"})
public class BookDataLoader {

  private final BookRepository bookRepository;

  public BookDataLoader(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void load() {
    bookRepository.deleteAll();
    var books = List.of(
        Book.of("978-3-16-148410-0", "Refactoring", "Martin Fowler", 47.99, "Addison-Wesley Professional"),
        Book.of("978-0-321-35668-0", "Clean Code", "Robert C. Martin", 43.99, "Prentice Hall"),
        Book.of("978-0-13-235088-4", "The Pragmatic Programmer", "Andrew Hunt", 31.99, "Addison-Wesley Professional")
    );
    bookRepository.saveAll(books);
  }
}
