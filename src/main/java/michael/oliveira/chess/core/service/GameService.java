package michael.oliveira.chess.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import michael.oliveira.chess.core.jpa.model.Game;
import michael.oliveira.chess.core.jpa.repository.GameRepository;

@Service
public class GameService {
	@Autowired
	private GameRepository gameRepository;
	
	public Integer save(Game game) {
		game = gameRepository.save(game);
		
		return game.getId();
	}
	
	public Game findById(Integer gameId) {
		return gameRepository.findById(gameId).orElse(null);
	}	
}
