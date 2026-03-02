package michael.oliveira.chess.core.jpa.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import michael.oliveira.chess.converter.GameStatusConverter;
import michael.oliveira.chess.converter.GameWinnerConverter;
import michael.oliveira.chess.converter.TeamConverter;
import michael.oliveira.chess.enums.GameStatusEnum;
import michael.oliveira.chess.enums.GameWinnerEnum;
import michael.oliveira.chess.enums.TeamEnum;

@Entity
@Table(name = "GAME", schema = "CHESS")
public class Game {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GAME_ID")
	private Integer id;

	@Convert(converter = GameWinnerConverter.class)
	@Column(name = "GAME_WINNER", length = 1)
	private GameWinnerEnum winner;
	
	@Convert(converter = GameStatusConverter.class)
	@Column(name = "GAME_STATUS", length = 1)
	private GameStatusEnum status;
	
	@Convert(converter = TeamConverter.class)
	@Column(name = "CURRENT_PLAYER ", length = 1)
    private TeamEnum currentPlayer;
	
	@OneToMany(mappedBy = "game",
	           fetch = FetchType.LAZY,
	           cascade = CascadeType.ALL,
	           orphanRemoval = true)
	private List<Piece> pieces;
	
	public Game() {
		this.setStatus(GameStatusEnum.RUNNING);
		this.setCurrentPlayer(TeamEnum.WHITE);
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public GameWinnerEnum getWinner() {
		return winner;
	}

	public void setWinner(GameWinnerEnum winner) {
		this.winner = winner;
	}

	public GameStatusEnum getStatus() {
		return status;
	}

	public void setStatus(GameStatusEnum status) {
		this.status = status;
	}

	public List<Piece> getPieces() {
		return pieces;
	}

	public void setPieces(List<Piece> pieces) {
		this.pieces = pieces;
	}
	public void addPieces(Piece piece) {
		if(this.pieces == null) {
			this.pieces = new ArrayList<>();
		}
		this.pieces.add(piece);
	}
	public void removePieces(Piece piece) {
		if(this.pieces == null) {
			this.pieces = new ArrayList<>();
		}
		this.pieces.remove(piece);
	}

	public TeamEnum getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(TeamEnum currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
}
