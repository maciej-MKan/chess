package pl.mkan.persistance.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.mkan.service.tools.UserIdFactory;

@Slf4j
@Getter
@EqualsAndHashCode(of = "userId")
@NoArgsConstructor
@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int userId;
    private String username;

    public UserEntity(String username) {
        this.username = username;
        this.userId = UserIdFactory.generateId().getUserId();
        log.info("Created user with id {}", userId);
    }
}
