package pl.mkan.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mkan.persistence.model.GameHistory;
import pl.mkan.persistence.model.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<GameHistory, Long> {
    GameHistory findByGameId(String gameId);

    Optional<List<GameHistory>> findAllByUser(User user);
}
