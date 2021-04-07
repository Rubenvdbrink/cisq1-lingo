package nl.hu.cisq1.lingo.trainer.data;

import nl.hu.cisq1.lingo.trainer.domain.LingoGame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<LingoGame, Long> {
}
