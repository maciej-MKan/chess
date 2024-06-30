package pl.mkan.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mkan.controller.dto.UserDTO;
import pl.mkan.persistance.model.UserEntity;
import pl.mkan.persistance.repository.UserRepository;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public boolean saveIfNewUser(UserDTO user) {
        userRepository.save(new UserEntity(user.name()));
        return true;
    }
}
