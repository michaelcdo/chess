package michael.oliveira.chess.core.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import michael.oliveira.chess.converter.TeamConverter;
import michael.oliveira.chess.converter.PieceTypeConverter;
import michael.oliveira.chess.enums.PieceTypeEnum;
import michael.oliveira.chess.enums.TeamEnum;

@Entity
@Table(name = "PIECE", schema = "CHESS")
public class Piece {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PIECE_ID")
    private Long id;

    @Convert(converter = PieceTypeConverter.class)
	@Column(name = "PIECE_TYPE", length = 1)
    private PieceTypeEnum type;

	@Convert(converter = TeamConverter.class)
	@Column(name = "PIECE_COLOR", length = 1)
    private TeamEnum color;

    @Column(name = "PIECE_POSITION", length = 2, nullable = false)
    private String position;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "GAME_ID", nullable = false)
    private Game game;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public PieceTypeEnum getType() {
		return type;
	}

	public void setType(PieceTypeEnum type) {
		this.type = type;
	}

	public TeamEnum getColor() {
		return color;
	}

	public void setColor(TeamEnum color) {
		this.color = color;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
    
    
}
