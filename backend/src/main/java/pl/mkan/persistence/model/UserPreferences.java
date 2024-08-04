package pl.mkan.persistence.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.mkan.controller.dto.enums.PieceColor;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String defaultColor;

    public UserPreferences(User user, String defaultColor) {
        this.user = user;
        this.defaultColor = defaultColor;
    }

    public UserPreferences(User user, PieceColor defaultColor) {
        this.user = user;
        this.defaultColor = defaultColor.name();
    }
}
