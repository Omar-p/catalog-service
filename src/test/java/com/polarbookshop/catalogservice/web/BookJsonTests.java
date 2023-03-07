package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.time.Instant;

import static org.assertj.core.api.BDDAssertions.assertThat;

@JsonTest
public class BookJsonTests {

  @Autowired
  private JacksonTester<Book> json;

  @Test
  void testSerialize() throws Exception {
    Book book = new Book(1L, "9780321146533", "A valid title", "A valid author", 10.0, "O'Reilly", Instant.now(), Instant.now(), 1);
    var jsonContent = json.write(book);

    assertThat(jsonContent).extractingJsonPathValue("@.id")
        .isEqualTo(1);
    assertThat(jsonContent).extractingJsonPathValue("@.isbn")
        .isEqualTo("9780321146533");
    assertThat(jsonContent).extractingJsonPathValue("@.title")
        .isEqualTo("A valid title");
    assertThat(jsonContent).extractingJsonPathValue("@.author")
        .isEqualTo("A valid author");
    assertThat(jsonContent).extractingJsonPathValue("@.price")
        .isEqualTo(10.0);
    assertThat(jsonContent).extractingJsonPathValue("@.version")
        .isEqualTo(1);
  }

  @Test
  void testDeserialize() throws Exception {
    String content = """
        {
          "id": 1,
          "isbn": "9780321146533",
          "title": "A valid title",
          "author": "A valid author",
          "price": 10.0,
          "publisher": "O'Reilly",
          "version": 1
        }
        """;

    assertThat(json.parse(content))
        .usingRecursiveComparison()
        .isEqualTo(new Book(1L, "9780321146533", "A valid title", "A valid author", 10.0, "O'Reilly", null, null,1));
  }
}
