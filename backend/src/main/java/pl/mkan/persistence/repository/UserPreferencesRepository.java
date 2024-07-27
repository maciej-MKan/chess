package pl.mkan.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mkan.persistence.model.User;
import pl.mkan.persistence.model.UserPreferences;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserPreferencesRepository extends JpaRepository<UserPreferences, Long> {

    Optional<UserPreferences> findByUser(User user);

    @Query("SELECT up.defaultColor FROM UserPreferences up WHERE up.user.userId = :userId")
    String findDefaultColorByUserId(@Param("userId") UUID userId);
}
