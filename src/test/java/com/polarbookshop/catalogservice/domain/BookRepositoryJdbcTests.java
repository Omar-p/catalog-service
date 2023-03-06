package com.polarbookshop.catalogservice.domain;

import com.polarbookshop.catalogservice.config.DataConfig;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.data.jdbc.core.JdbcAggregateTemplate;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest(

)
@Import(DataConfig.class)
@AutoConfigureTestDatabase(
  replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("integration")
class BookRepositoryJdbcTests {

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private JdbcAggregateTemplate jdbcAggregateTemplate;


  @BeforeEach
  void beforeAll() {
    bookRepository.deleteAll();
  }


  @Test
  void findBookByIsbnWhenExists() {
    Book book = Book.of("9780321146533", "A valid title", "A valid author", 10.0);
    jdbcAggregateTemplate.insert(book);

    assertThat(bookRepository.findByIsbn("9780321146533"))
        .isPresent();
  }

  @Test
  void deleteBookByIsbnWhenExists() {
    Book book = Book.of("9780321146533", "A valid title", "A valid author", 10.0);
    jdbcAggregateTemplate.insert(book);

    bookRepository.deleteByIsbn("9780321146533");

    assertThat(bookRepository.findByIsbn("9780321146533"))
        .isEmpty();
  }
}