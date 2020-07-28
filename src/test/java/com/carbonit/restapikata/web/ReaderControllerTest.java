package com.carbonit.restapikata.web;

import com.carbonit.restapikata.model.NewReaderDTO;
import com.carbonit.restapikata.model.ReaderDTO;
import com.carbonit.restapikata.service.ReaderService;
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

@WebMvcTest(ReaderController.class)
class ReaderControllerTest {

    @MockBean
    private ReaderService readerService;

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
    void should_return_reader_when_get_by_id() {
        // Arrange
        var readerDto = new ReaderDTO(UUID.randomUUID(), "Thomas", "Moore",
                45, "Philosopher");
        when(readerService.findOne(readerDto.getId())).thenReturn(readerDto);

        // Act & Assert
        restAssured.accept(ContentType.JSON)
                .get("/readers/" + readerDto.getId())
                .then()
                .assertThat()
                .status(HttpStatus.OK)
                .contentType(ContentType.JSON)
                .body("id", equalTo(readerDto.getId().toString()))
                .body("firstName", equalTo(readerDto.getFirstName()))
                .body("lastName", equalTo(readerDto.getLastName()))
                .body("age", equalTo(readerDto.getAge()))
                .body("educationLevel", equalTo(readerDto.getEducationLevel()));
    }

    @Test
    void should_return_http_status_NOT_FOUND_when_req_get_non_existing_reader() {
        // Arrange
        var id = UUID.randomUUID();
        when(readerService.findOne(id)).thenThrow(new ReaderDoesNotExistException());

        // Act & Assert
        restAssured.accept(ContentType.JSON)
                .get("/readers/" + id)
                .then()
                .assertThat()
                .status(HttpStatus.NOT_FOUND);
    }

    @Test
    void should_return_all_readers_when_get_all() {
        // Arrange
        var readers = Arrays.asList(
                new ReaderDTO(UUID.randomUUID(),
                        "Elvis", "Presley", 33, "Master in music"),
                new ReaderDTO(UUID.randomUUID(),
                        "Fryderyk", "Chopin", 39, "Virtuoso in music"));
        when(readerService.findAll()).thenReturn(readers);

        // Act & Assert
        restAssured
                .accept(ContentType.JSON)
                .get("/readers")
                .then()
                .assertThat()
                .status(HttpStatus.OK)
                .contentType(ContentType.JSON)
                .body("$.size()", equalTo(2));
    }

    @Test
    void should_create_new_reader_when_req_post() throws JsonProcessingException {

        // Arrange
        var newReaderDTO = new NewReaderDTO("Gustaw", "Holoubek", 98, "Professor in Theatre");
        var newReaderJson = objectMapper.writeValueAsString(newReaderDTO);
        var persistedReader = new ReaderDTO(UUID.randomUUID(), newReaderDTO.getFirstName(),
                newReaderDTO.getLastName(), newReaderDTO.getAge(), newReaderDTO.getEducationLevel());
        when(readerService.create(newReaderDTO)).thenReturn(persistedReader);

        // Act & Assert
        restAssured.accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(newReaderJson)
                .post("/readers")
                .then()
                .assertThat()
                .status(HttpStatus.CREATED)
                .contentType(ContentType.JSON)
                .body("id", equalTo(persistedReader.getId().toString()))
                .body("firstName", equalTo(persistedReader.getFirstName()))
                .body("lastName", equalTo(persistedReader.getLastName()))
                .body("age", equalTo(persistedReader.getAge()))
                .body("educationLevel", equalTo(persistedReader.getEducationLevel()));
    }

    @Test
    void should_update_a_reader_when_req_put() throws JsonProcessingException {

        // Arrange
        var id = UUID.randomUUID();
        var readerToUpdate = new ReaderDTO(id, "Gustaw", "Holoubek", 98,
                "Professor in Theatre");
        var readerToUpdateJson = objectMapper.writeValueAsString(readerToUpdate);
        var persistedReader = new ReaderDTO(readerToUpdate.getId(), readerToUpdate.getFirstName(),
                readerToUpdate.getLastName(), readerToUpdate.getAge(), readerToUpdate.getEducationLevel());
        when(readerService.update(id, readerToUpdate)).thenReturn(persistedReader);

        // Act & Assert
        restAssured.accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(readerToUpdateJson).put("/readers/" + id)
                .then()
                .assertThat()
                .status(HttpStatus.OK)
                .contentType(ContentType.JSON)
                .body("id", equalTo(persistedReader.getId().toString()))
                .body("firstName", equalTo(persistedReader.getFirstName()))
                .body("lastName", equalTo(persistedReader.getLastName()))
                .body("age", equalTo(persistedReader.getAge()))
                .body("educationLevel", equalTo(persistedReader.getEducationLevel()));
    }

    @Test
    void should_throw_exception_when_req_put_with_wrong_id() throws JsonProcessingException {
        // Arrange
        var wrongId = UUID.randomUUID();
        var readerToUpdate = new ReaderDTO(UUID.randomUUID(),"Gustaw", "Holoubek", 98,
                "Professor in Theatre");
        var readerToUpdateJson = objectMapper.writeValueAsString(readerToUpdate);

        // Act & Assert
        restAssured.accept(ContentType.JSON).contentType(ContentType.JSON)
                .body(readerToUpdateJson)
                .put("/readers/" + wrongId)
                .then()
                .assertThat()
                .status(HttpStatus.BAD_REQUEST);
    }

    @Test
    void should_return_status_NO_CONTENT_when_req_delete() {
        // Arrange
        var id = UUID.randomUUID();
        doNothing().when(readerService).delete(id);

        // Act & Assert
        restAssured.delete("/readers/" + id)
                .then()
                .assertThat()
                .status(HttpStatus.NO_CONTENT);
    }

}