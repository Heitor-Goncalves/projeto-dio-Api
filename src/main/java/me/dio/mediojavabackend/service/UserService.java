package me.dio.mediojavabackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.dio.mediojavabackend.dto.UserDto;
import me.dio.mediojavabackend.model.User;
import me.dio.mediojavabackend.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean createUser(UserDto userDto) {
        String email = userDto.getEmail();

        // Verifica se o email já está em uso
        if (userRepository.existsByEmail(email)) {
            return false; // Retorna false se o email já existe
        }

        // Cria um novo usuário
        User user = new User();
        user.setEmail(email);
        user.setSenha(userDto.getSenha());

        userRepository.save(user); // Salva o novo usuário
        return true;
    }

    public boolean validateLogin(LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getSenha();

        // Verifica se o usuário existe e a senha está correta
        return userRepository.findByEmailAndPassword(email, password).isPresent();
    }
}
