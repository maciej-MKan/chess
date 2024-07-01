package pl.mkan.persistence.model;

import jakarta.persistence.*;
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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private int userId;

    @Column(nullable = false)
    private String username;

    public User(String username) {
        this.username = username;
        this.userId = UserIdFactory.generateId().getUserId();
        log.info("Created user with id {}", userId);
    }
}
