package com.polarbookshop.catalogservice.demo;

import com.polarbookshop.catalogservice.domain.Book;
import com.polarbookshop.catalogservice.domain.BookRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile({"local", "testdata"})
public class BookDataLoader {

  private final BookRepository bookRepository;

  public BookDataLoader(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void load() {
    bookRepository.save(new Book("978-3-16-148410-0", "Refactoring", "Martin Fowler", 47.99));
    bookRepository.save(new Book("978-0-321-35668-0", "Clean Code", "Robert C. Martin", 43.99));
    bookRepository.save(new Book("978-0-13-235088-4", "The Pragmatic Programmer", "Andrew Hunt", 31.99));
  }
}
