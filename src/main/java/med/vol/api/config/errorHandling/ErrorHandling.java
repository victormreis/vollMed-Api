package med.vol.api.config.errorHandling;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class ErrorHandling {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity notFoundError() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity invalidFields(MethodArgumentNotValidException err){
        var errors = err.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(FieldsErrorValidation::new).toList());
    }

    @ExceptionHandler(AppointmentValidationEx.class)
    public ResponseEntity serviceErrorHandler(AppointmentValidationEx ex){

        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity duplicatedData(SQLIntegrityConstraintViolationException err){

        return ResponseEntity.badRequest().body(err.getLocalizedMessage());
    }


    private record FieldsErrorValidation(String field, String message) {
        public FieldsErrorValidation(FieldError err) {
            this(err.getField(), err.getDefaultMessage());
        }
    }



}
