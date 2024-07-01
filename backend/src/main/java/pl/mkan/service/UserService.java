package pl.mkan.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mkan.controller.dto.UserDTO;
import pl.mkan.persistence.model.User;
import pl.mkan.persistence.repository.UserRepository;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public boolean saveIfNewUser(UserDTO user) {
        User newUser = new User(user.name());
        if (userRepository.findByUserId(newUser.getUserId()) == null) {
            userRepository.save(newUser);
            return true;
        }
        return false;
    }
}
