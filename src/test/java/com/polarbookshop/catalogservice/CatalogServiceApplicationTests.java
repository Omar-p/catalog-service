package com.polarbookshop.catalogservice;

import com.polarbookshop.catalogservice.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

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
		Book book = new Book("9780321146533", "A valid title", "A valid author", 10.0);
		webTestClient
				.post()
				.uri("/books")
				.bodyValue(book)
				.exchange()
				.expectStatus().isCreated()
				.expectBody(Book.class)
				.isEqualTo(book);
	}
}
