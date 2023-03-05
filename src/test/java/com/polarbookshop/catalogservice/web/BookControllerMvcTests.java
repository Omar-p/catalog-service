package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.domain.BookNotFoundException;
import com.polarbookshop.catalogservice.domain.BookRepository;
import com.polarbookshop.catalogservice.domain.BookService;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookController.class)
class BookControllerMvcTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BookService bookService;


  @Test
  void whenGetBookNotExist_then404() throws Exception {
    String isbn = "9780321146533";

    given(bookService.viewBookDetails(isbn))
      .willThrow(BookNotFoundException.class);


    mockMvc.perform(get("/books/{isbn}", isbn))
      .andExpect(status().isNotFound());

  }


}