package me.dio.mediojavabackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import me.dio.mediojavabackend.dto.UserDto;
import me.dio.mediojavabackend.service.LoginRequest;
import me.dio.mediojavabackend.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody UserDto userDto) {
        boolean created = userService.createUser(userDto);
        if (created) {
            return ResponseEntity.ok("Usuário criado com sucesso!");
        } else {
            return ResponseEntity.badRequest().body("O email já está em uso.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean valid = userService.validateLogin(loginRequest);
        if (valid) {
            return ResponseEntity.ok("Login bem-sucedido!");
        } else {
            return ResponseEntity.badRequest().body("Credenciais inválidas.");
        }
    }
}
