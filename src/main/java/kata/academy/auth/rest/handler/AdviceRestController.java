package kata.academy.auth.rest.handler;

import kata.academy.auth.api.Error;
import kata.academy.auth.exception.AccountIsNotEnabledException;
import kata.academy.auth.exception.EntityNotFoundException;
import kata.academy.auth.exception.FeignRequestException;
import kata.academy.auth.exception.TokenValidationException;
import kata.academy.auth.exception.WrongPasswordException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(annotations = RestController.class)
public class AdviceRestController {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> onGlobalException(Exception exception) {
        ResponseEntity.internalServerError().body(exception.getMessage());
        return ResponseEntity.internalServerError().body(Error.of(exception.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Error> onMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(Error.of("В теле запроса допущены ошибки в следующих полях: " + exception.getBindingResult().getFieldErrors().stream().map(FieldError::getField).toList()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    public ResponseEntity<Error> onMissingServletRequestParameterException(MissingServletRequestParameterException exception) {
        return ResponseEntity.badRequest().body(Error.of("В запросе не указан параметр: " + exception.getParameterName()));
    }

    @ResponseBody
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> onHttpMessageNotReadableException() {
        return ResponseEntity.badRequest().body(Error.of("В запросе не указано тело"));
    }

    @ResponseBody
    @ExceptionHandler(FeignRequestException.class)
    public ResponseEntity<Error> onFeignRequestException(FeignRequestException exception) {
        return ResponseEntity.internalServerError().body(Error.of(exception.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Error> onEntityNotFoundException(EntityNotFoundException exception) {
        return ResponseEntity.badRequest().body(Error.of(exception.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(WrongPasswordException.class)
    public ResponseEntity<Error> onWrongPasswordException(WrongPasswordException exception) {
        return ResponseEntity.badRequest().body(Error.of(exception.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(AccountIsNotEnabledException.class)
    public ResponseEntity<Error> onAccountIsNotEnabledException(AccountIsNotEnabledException exception) {
        return ResponseEntity.badRequest().body(Error.of(exception.getMessage()));
    }

    @ResponseBody
    @ExceptionHandler(TokenValidationException.class)
    public ResponseEntity<Error> onTokenValidationException(TokenValidationException exception) {
        return ResponseEntity.badRequest().body(Error.of(exception.getMessage()));
    }
}
