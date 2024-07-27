package pl.mkan.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mkan.controller.dto.UserDTO;
import pl.mkan.controller.dto.enums.PieceColor;
import pl.mkan.persistence.model.User;
import pl.mkan.persistence.repository.UserPreferencesRepository;
import pl.mkan.persistence.repository.UserRepository;

import java.util.UUID;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserPreferencesRepository userPreferences;

    public boolean saveIfNewUser(UserDTO user) {
        User newUser = new User(user.name());
        User storedUser = userRepository.findByUserId(newUser.getUserId());
        if (storedUser == null) {
            userRepository.save(newUser);
            return true;
        }
//        log.info((userPreferences.findByUser(storedUser).orElse(new UserPreferences(0L, newUser, "Not specified"))).getDefaultColor());
        return false;
    }

    public PieceColor getUserColor(UUID userId) {
        return PieceColor.valueOf(userPreferences.findDefaultColorByUserId(userId));
    }

    public User getUserByName(String userName) {
        return userRepository.findByUsername(userName);
    }
}
