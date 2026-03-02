package michael.oliveira.chess.core.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import michael.oliveira.chess.core.PieceDTO;
import michael.oliveira.chess.core.PieceBaseDTO;
import michael.oliveira.chess.enums.ColunaEnum;
import michael.oliveira.chess.enums.TeamEnum;

public class KnightDTO extends PieceBaseDTO implements PieceDTO{

	public KnightDTO(String position, TeamEnum team) {
		super(position, team);
	}

	@Override
	public String getIconUrl() {
		return getIconsPath() + "/" + team.getPath() + "/knight.png";
	}

	@Override
	public List<String> getPossibleMove(Map<String, PieceDTO> board) {
		String coluna = this.getPosition().substring(0,1);
		Integer colunaOrdinal = ColunaEnum.getByDescricao(coluna).getOrdinal();
		int linha = Integer.parseInt(this.getPosition().substring(1,2));
		
		List<Integer[]> possibleCoords = new ArrayList<Integer[]>();
		possibleCoords.add(new Integer[]{linha + 2, colunaOrdinal + 1});
		possibleCoords.add(new Integer[]{linha + 2, colunaOrdinal - 1});
		possibleCoords.add(new Integer[]{linha + 1, colunaOrdinal - 2});
		possibleCoords.add(new Integer[]{linha + 1, colunaOrdinal + 2});
		possibleCoords.add(new Integer[]{linha - 2, colunaOrdinal + 1});
		possibleCoords.add(new Integer[]{linha - 2, colunaOrdinal - 1});
		possibleCoords.add(new Integer[]{linha - 1, colunaOrdinal - 2});
		possibleCoords.add(new Integer[]{linha - 1, colunaOrdinal + 2});
		
		List<String> possibleMoves = new ArrayList<>();
		for (Integer[] coordenadas : possibleCoords) {
			Integer row = coordenadas[0];
			Integer col = coordenadas[1];
			ColunaEnum colunaEnum = ColunaEnum.getByOrdinal(col);
			if(colunaEnum != null) {
				PieceDTO piece = board.get(colunaEnum.getDescricao() + row);
				if(piece == null || (piece != null && piece.getTeam() != this.getTeam())) {
					possibleMoves.add(colunaEnum.getDescricao() + row);
				}
			}
		}
		
		return possibleMoves;
	}

	@Override
	public List<String> getPossibleMoveIgnoringOposeKing(Map<String, PieceDTO> board) {
		return getPossibleMove(board);
	}

}
