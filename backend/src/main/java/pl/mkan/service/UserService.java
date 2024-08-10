package pl.mkan.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.mkan.controller.dto.UserDTO;
import pl.mkan.controller.dto.enums.PieceColor;
import pl.mkan.persistence.model.User;
import pl.mkan.persistence.model.UserPreferences;
import pl.mkan.persistence.repository.UserPreferencesRepository;
import pl.mkan.persistence.repository.UserRepository;
import pl.mkan.service.tools.UserIdFactory;

import java.util.Optional;

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
        return false;
    }

    public Optional<PieceColor> getUserColor() {
        String defaultUserColor = userPreferences.findDefaultColorByUserId(UserIdFactory.generateId().getUserId());
        log.info("Found user color: {}", defaultUserColor);
        return defaultUserColor == null ? Optional.empty() : Optional.of(PieceColor.valueOf(defaultUserColor));
    }


    public void setDefaultColor(PieceColor color) {
        User user = userRepository.findByUserId(UserIdFactory.generateId().getUserId());
        UserPreferences preferences = user.getPreferences();

        if (preferences != null) {
            preferences.setDefaultColor(color.name());
        } else {
            preferences = new UserPreferences(user, color.name());
        }
        log.info("Setting default color {} for user {}",
                color,
                user.getUsername()
        );
        userPreferences.save(preferences);
    }
}
