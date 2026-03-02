package michael.oliveira.chess.core.pieces;

import java.util.List;
import java.util.Map;

import michael.oliveira.chess.core.PieceDTO;
import michael.oliveira.chess.core.PieceBaseDTO;
import michael.oliveira.chess.enums.TeamEnum;

public class TowerDTO extends PieceBaseDTO implements PieceDTO{

	public TowerDTO(String position, TeamEnum team) {
		super(position, team);
	}

	@Override
	public String getIconUrl() {
		return getIconsPath() + "/" + team.getPath() + "/tower.png";
	}

	@Override
	public List<String> getPossibleMove(Map<String, PieceDTO> board) {
		return getPossibleLineMove(board, false);
	}

	@Override
	public List<String> getPossibleMoveIgnoringOposeKing(Map<String, PieceDTO> board) {
		return getPossibleLineMove(board, true);
	}

}
