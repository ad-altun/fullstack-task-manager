package org.example.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ToDoNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String ToDoNotFoundException(ToDoNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(InvalidIdRequestedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String BadRequestException(InvalidIdRequestedException e) {
        return e.getMessage();
    }

//    @ExceptionHandler(ToDoNotFoundException.class)
//    @ResponseStatus(HttpStatus.NOT_FOUND) // This annotation maps the exception to a 404 status
//    public ResponseEntity<Object> ToDoNotFoundException(
//            ToDoNotFoundException ex) {
//
//        Map<String, Object> body = new LinkedHashMap<>();
//        body.put("timestamp", LocalDateTime.now());
//        body.put("status", HttpStatus.NOT_FOUND.value());
//        body.put("error", "Not Found"); // Or a more specific error code
//        body.put("message", ex.getMessage());
//
//        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
//    }
}
