package pl.mkan.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.mkan.persistence.model.GameHistory;

@Repository
public interface GameRepository extends JpaRepository<GameHistory, Long> {
}
