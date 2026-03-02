package michael.oliveira.chess.core.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import michael.oliveira.chess.core.jpa.model.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer>{

}
