package mk.ukim.finki.mk.bookandstay_application.service.application;


import mk.ukim.finki.mk.bookandstay_application.dto.CreateUserDto;
import mk.ukim.finki.mk.bookandstay_application.dto.DisplayUserDto;
import mk.ukim.finki.mk.bookandstay_application.dto.LoginResponseDto;
import mk.ukim.finki.mk.bookandstay_application.dto.LoginUserDto;

import java.util.Optional;

public interface UserApplicationService {

    Optional<DisplayUserDto> register(CreateUserDto createUserDto);

    Optional<LoginResponseDto> login(LoginUserDto loginUserDto);

    Optional<DisplayUserDto> findByUsername(String username);
}
