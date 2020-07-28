package com.carbonit.restapikata.web;

import com.carbonit.restapikata.model.BookDTO;
import com.carbonit.restapikata.model.NewBookDTO;
import com.carbonit.restapikata.service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.specification.MockMvcRequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.UUID;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvcRequestSpecification restAssured;

    @BeforeEach
    void setup() {
        this.restAssured = given().mockMvc(mockMvc);
    }

    @Test
    void should_return_book_when_get_by_id() {
        // Arrange
        var book = new BookDTO(UUID.randomUUID(),
                "Siekierezada", "Edward Stachura", "342-fqdfq-234");
        when(bookService.findOne(book.getId())).thenReturn(book);

        // Act & Assert
        restAssured.accept(ContentType.JSON)
                .get("/books/" + book.getId())
                .then()
                .assertThat()
                .status(HttpStatus.OK)
                .contentType(ContentType.JSON)
                .body("id", equalTo(book.getId().toString()))
                .body("author", equalTo(book.getAuthor()))
                .body("title", equalTo(book.getTitle()))
                .body("isbn", equalTo(book.getIsbn()));
    }

    @Test
    void should_return_http_status_NOT_FOUND_when_req_get_non_existing_book() {
        // Arrange
        var id = UUID.randomUUID();
        when(bookService.findOne(id)).thenThrow(new BookDoesNotExistException());

        // Act & Assert
        restAssured.accept(ContentType.JSON)
                .get("/books/" + id)
                .then()
                .assertThat()
                .status(HttpStatus.NOT_FOUND);
    }

    @Test
    void should_return_all_books_when_get_all() {
        // Arrange
        var books = Arrays.asList(
                new BookDTO(UUID.randomUUID(),
                        "Siekierezada", "Edward Stachura", "342-fqdfq-234"),
                new BookDTO(UUID.randomUUID(),
                        "Transatlantyk", "Gombrowicz", "653-eeeq-234"));
        when(bookService.findAll()).thenReturn(books);

        // Act & Assert
        restAssured
                .accept(ContentType.JSON)
                .get("/books")
                .then()
                .assertThat()
                .status(HttpStatus.OK)
                .contentType(ContentType.JSON)
                .body("$.size()", equalTo(2));
    }

    @Test
    void should_create_new_book_when_req_post() throws JsonProcessingException {

        // Arrange
        var newBook = new NewBookDTO("Siekierezada", "Edward Stachura", "342-fqdfq-234");
        var newBookJson = objectMapper.writeValueAsString(newBook);
        var persistedBook = new BookDTO(UUID.randomUUID(),
                newBook.getTitle(), newBook.getAuthor(), newBook.getIsbn());
        when(bookService.create(newBook)).thenReturn(persistedBook);

        // Act & Assert
        restAssured.accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(newBookJson)
                .post("/books")
                .then()
                .assertThat()
                .status(HttpStatus.CREATED)
                .contentType(ContentType.JSON)
                .body("id", equalTo(persistedBook.getId().toString()))
                .body("author", equalTo(persistedBook.getAuthor()))
                .body("title", equalTo(persistedBook.getTitle()))
                .body("isbn", equalTo(persistedBook.getIsbn()));
    }

    @Test
    void should_update_a_book_when_req_put() throws JsonProcessingException {

        // Arrange
        var id = UUID.randomUUID();
        var bookToUpdate = new BookDTO(id, "Siekierezada", "Edward Stachura", "342-fqdfq-234");
        var bookToUpdateJson = objectMapper.writeValueAsString(bookToUpdate);
        var persistedBook = new BookDTO(bookToUpdate.getId(), bookToUpdate.getTitle(),
                bookToUpdate.getAuthor(), bookToUpdate.getIsbn());
        when(bookService.update(id, bookToUpdate)).thenReturn(persistedBook);

        // Act & Assert
        restAssured.accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(bookToUpdateJson).put("/books/" + id)
                .then()
                .assertThat()
                .status(HttpStatus.OK)
                .contentType(ContentType.JSON)
                .body("id", equalTo(persistedBook.getId().toString()))
                .body("author", equalTo(persistedBook.getAuthor()))
                .body("title", equalTo(persistedBook.getTitle()))
                .body("isbn", equalTo(persistedBook.getIsbn()));
    }

    @Test
    void should_throw_exception_when_req_put_with_wrong_id() throws JsonProcessingException {
        // Arrange
        var wrongId = UUID.randomUUID();
        var bookToUpdate = new BookDTO(UUID.randomUUID(),
                "Siekierezada", "Edward Stachura", "342-fqdfq-234");
        var bookToUpdateJson = objectMapper.writeValueAsString(bookToUpdate);

        // Act & Assert
        restAssured.accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(bookToUpdateJson)
                .put("/books/" + wrongId)
                .then()
                .assertThat()
                .status(HttpStatus.BAD_REQUEST);
    }

    @Test
    void should_return_status_NO_CONTENT_when_req_delete() {
        // Arrange
        var id = UUID.randomUUID();
        doNothing().when(bookService).delete(id);

        // Act & Assert
        restAssured.delete("/books/" + id)
                .then()
                .assertThat()
                .status(HttpStatus.NO_CONTENT);
    }


}