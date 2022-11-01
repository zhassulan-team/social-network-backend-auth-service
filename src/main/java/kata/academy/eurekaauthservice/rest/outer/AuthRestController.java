package kata.academy.eurekaauthservice.rest.outer;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import kata.academy.eurekaauthservice.api.Data;
import kata.academy.eurekaauthservice.model.dto.UserDto;
import kata.academy.eurekaauthservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "AuthRestController", description = "Регистрация и аутентификация пользователя")
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestController {

    private final UserService userService;

    @ApiOperation(value = "register", notes = "Регистрация пользователя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешная регистрация пользователя"),
            @ApiResponse(code = 400, message = "Ошибка при регистрации пользователя, проверьте корректность полей ввода")})
    @PostMapping("/registration")
    public ResponseEntity<Void> register(@RequestBody @Valid UserDto dto) {
        userService.register(dto);
        return ResponseEntity.ok().build();
    }

    @ApiOperation(value = "authenticate", notes = "Аутентификация пользователя")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешная аутентификация пользователя"),
            @ApiResponse(code = 400, message = "Ошибка при аутентификации пользователя, проверьте корректность полей ввода")})
    @PostMapping("/login")
    public ResponseEntity<Data<String>> authenticate(@RequestBody @Valid UserDto dto) {
        return ResponseEntity.ok(Data.of(userService.authenticate(dto)));
    }
}
