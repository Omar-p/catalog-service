package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestPropertySource(properties = {
		"spring.cloud.config.fail-fast=false",
		"spring.cloud.config.request-connect-timeout=0",
})
class CatalogServiceApplicationTests {

	@Autowired
	private WebTestClient webTestClient;


	@Test
	void whenPostBook_thenBookIsAdded() {
		Book book = Book.of("9780321146533", "A valid title", "A valid author", 10.0);
		webTestClient
				.post()
				.uri("/books")
				.bodyValue(book)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class)
				.value(responseBook -> {
					assertThat(responseBook.id()).isNotNull();
					assertThat(responseBook.isbn()).isEqualTo(book.isbn());
					assertThat(responseBook.title()).isEqualTo(book.title());
					assertThat(responseBook.author()).isEqualTo(book.author());
					assertThat(responseBook.price()).isEqualTo(book.price());
					assertThat(responseBook.createdDate()).isNotNull();
					assertThat(responseBook.lastModifiedDate()).isNotNull();
					assertThat(responseBook.version()).isEqualTo(1);
				});
	}
}
