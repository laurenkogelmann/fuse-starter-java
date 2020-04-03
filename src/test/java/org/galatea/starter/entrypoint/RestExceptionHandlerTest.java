package org.galatea.starter.entrypoint;

import static java.util.Collections.singleton;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

import com.fasterxml.jackson.core.JsonProcessingException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.galatea.starter.entrypoint.exception.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;

public class RestExceptionHandlerTest {

  private RestExceptionHandler handler;

  @Before
  public void setUp() {
    handler = new RestExceptionHandler();
  }

  @Test
  public void handleEntityNotFound() {
    EntityNotFoundException exception = new EntityNotFoundException(Object.class, "id");
    ResponseEntity<Object> response = handler.handleEntityNotFound(exception);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
  }

  @Test
  public void handleHttpMessageNotReadable() {
    HttpMessageNotReadableException exception = new HttpMessageNotReadableException("msg");
    ResponseEntity<Object> response = handler.handleHttpMessageNotReadable(exception);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }

  @Test
  public void handleDataAccessException() {
    DataAccessException exception = new DataAccessException("msg") {};
    ResponseEntity<Object> response = handler.handleDataAccessException(exception);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
  }

  @Test
  public void handleJsonProcessingException() {
    JsonProcessingException exception = new JsonProcessingException("msg") {};
    ResponseEntity<Object> response = handler.handleJsonProcessingException(exception);
    assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
  }

  @Test
  public void handleOptimisticLockException() {
    ObjectOptimisticLockingFailureException exception = new ObjectOptimisticLockingFailureException(Object.class, "id") {};
    ResponseEntity<Object> response = handler.handleOptimisticLockException(exception);
    assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
  }
}
