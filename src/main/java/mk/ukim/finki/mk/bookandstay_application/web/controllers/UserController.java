package mk.ukim.finki.mk.bookandstay_application.web.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import mk.ukim.finki.mk.bookandstay_application.dto.CreateUserDto;
import mk.ukim.finki.mk.bookandstay_application.dto.DisplayUserDto;
import mk.ukim.finki.mk.bookandstay_application.dto.LoginResponseDto;
import mk.ukim.finki.mk.bookandstay_application.dto.LoginUserDto;
import mk.ukim.finki.mk.bookandstay_application.model.exceptions.InvalidArgumentsException;
import mk.ukim.finki.mk.bookandstay_application.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.mk.bookandstay_application.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.mk.bookandstay_application.repository.UserRepository;
import mk.ukim.finki.mk.bookandstay_application.service.application.UserApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mk.ukim.finki.mk.bookandstay_application.model.domain.User;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User API", description = "Endpoints for user authentication and registration")
public class UserController {
    private final UserApplicationService userApplicationService;
    private final UserRepository userRepository;

    public UserController(UserApplicationService userApplicationService, UserRepository userRepository) {
        this.userApplicationService = userApplicationService;
        this.userRepository = userRepository;
    }

    @Operation(summary = "Register a new user", description = "Creates a new user account")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User registered successfully"
            ), @ApiResponse(
                    responseCode = "400", description = "Invalid input or passwords do not match"
            )}
    )
    @PostMapping("/register")
    public ResponseEntity<DisplayUserDto> register(@RequestBody CreateUserDto createUserDto) {
        try {
            return userApplicationService.register(createUserDto)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException exception) {
            return ResponseEntity.badRequest().build();
        }
    }
//
//    @Operation(summary = "User login", description = "Authenticates a user and starts a session")
//    @ApiResponses(
//            value = {@ApiResponse(
//                    responseCode = "200",
//                    description = "User authenticated successfully"
//            ), @ApiResponse(responseCode = "404", description = "Invalid username or password")}
//    )
//    @PostMapping("/login")
//    public ResponseEntity<DisplayUserDto> login(@RequestBody HttpServletRequest request) {
//        try {
//            DisplayUserDto displayUserDto = userApplicationService.login(
//                    new LoginUserDto(request.getParameter("username"), request.getParameter("password"))
//            ).orElseThrow(InvalidUserCredentialsException::new);
//
//            request.getSession().setAttribute("user", displayUserDto.toUser());
//            return ResponseEntity.ok(displayUserDto);
//        } catch (InvalidUserCredentialsException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }

    @Operation(summary = "User login", description = "Authenticates a user and generates a JWT")
    @ApiResponses(
            value = {@ApiResponse(
                    responseCode = "200",
                    description = "User authenticated successfully"
            ), @ApiResponse(responseCode = "404", description = "Invalid username or password")}
    )
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginUserDto loginUserDto) {
        try {
            return userApplicationService.login(loginUserDto)
                    .map(ResponseEntity::ok)
                    .orElseThrow(InvalidUserCredentialsException::new);
        } catch (InvalidUserCredentialsException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/no-temp-reservations")
    public List<User> getUsersWithoutTemporaryReservations() {
        return userRepository.findAllWithoutTemporaryReservations();
    }

    @Operation(summary = "User logout", description = "Ends the user's session")
    @ApiResponse(responseCode = "200", description = "User logged out successfully")
    @GetMapping("/logout")
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

}