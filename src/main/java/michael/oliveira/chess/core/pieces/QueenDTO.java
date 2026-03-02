package michael.oliveira.chess.core.pieces;

import java.util.List;
import java.util.Map;

import michael.oliveira.chess.core.PieceDTO;
import michael.oliveira.chess.core.PieceBaseDTO;
import michael.oliveira.chess.enums.TeamEnum;

public class QueenDTO extends PieceBaseDTO implements PieceDTO{

	public QueenDTO(String position, TeamEnum team) {
		super(position, team);
	}

	@Override
	public String getIconUrl() {
		return getIconsPath() + "/" + team.getPath() + "/queen.png";
	}

	@Override
	public List<String> getPossibleMove(Map<String, PieceDTO> board) {
		List<String> possibleMoves = getPossibleDiagonalMove(board, false);
		possibleMoves.addAll(getPossibleLineMove(board, false));
		
		return possibleMoves;
	}

	@Override
	public List<String> getPossibleMoveIgnoringOposeKing(Map<String, PieceDTO> board) {
		List<String> possibleMoves = getPossibleDiagonalMove(board, true);
		possibleMoves.addAll(getPossibleLineMove(board, true));
		
		return possibleMoves;
	}

}
