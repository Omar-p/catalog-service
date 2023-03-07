package com.polarbookshop.catalogservice.domain;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class BookValidationTests {

  private static Validator validator;

  @BeforeAll
  static void setUp() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    validator = factory.getValidator();
  }

  @Test
  void whenAllFieldsAreValid_thenNoConstraintViolations() {
    Book book = Book.of("9780321146533", "A valid title", "A valid author", 10.0, "O'Reilly");
    BDDAssertions.assertThat(validator.validate(book)).isEmpty();
  }

  @Test
  void whenIsbnIsBlank_thenConstraintViolation() {
    Book book = Book.of(" ", "A valid title", "A valid author", 10.0, "O'Reilly");
    BDDAssertions.assertThat(validator.validate(book)).hasSize(2);
  }

  @Test
  void whenIsbnIsInvalid_thenConstraintViolation() {
    Book book = Book.of("978032114653", "A valid title", "A valid author", 10.0, "O'Reilly");
    BDDAssertions.assertThat(validator.validate(book)).hasSize(1);
  }

}