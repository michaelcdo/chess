package michael.oliveira.chess.core.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import michael.oliveira.chess.core.PieceDTO;
import michael.oliveira.chess.core.PieceBaseDTO;
import michael.oliveira.chess.enums.ColunaEnum;
import michael.oliveira.chess.enums.TeamEnum;

public class KingDTO extends PieceBaseDTO implements PieceDTO{

	public KingDTO(String position, TeamEnum team) {
		super(position, team);
	}

	@Override
	public String getIconUrl() {
		return getIconsPath() + "/" + team.getPath() + "/king.png";
	}

	@Override
	public List<String> getPossibleMove(Map<String, PieceDTO> board) {
		String coluna = this.getPosition().substring(0,1);
		Integer colunaOrdinal = ColunaEnum.getByDescricao(coluna).getOrdinal();
		int linha = Integer.parseInt(this.getPosition().substring(1,2));
		
		List<Integer[]> possibleCoords = new ArrayList<Integer[]>();
		possibleCoords.add(new Integer[]{linha, colunaOrdinal + 1});
		possibleCoords.add(new Integer[]{linha, colunaOrdinal - 1});
		possibleCoords.add(new Integer[]{linha + 1, colunaOrdinal});
		possibleCoords.add(new Integer[]{linha - 1, colunaOrdinal});
		possibleCoords.add(new Integer[]{linha + 1, colunaOrdinal + 1});
		possibleCoords.add(new Integer[]{linha + 1, colunaOrdinal - 1});
		possibleCoords.add(new Integer[]{linha - 1, colunaOrdinal + 1});
		possibleCoords.add(new Integer[]{linha - 1, colunaOrdinal - 1});
		
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
		
		List<String> attackedSquares = board.values().stream()
				.filter(piece -> piece.getTeam() != this.getTeam() && !piece.getClass().equals(KingDTO.class))
				.flatMap(piece -> piece.getPossibleMoveIgnoringOposeKing(board).stream()).collect(Collectors.toList());
		
		PieceDTO enemyKing = board.values().stream()
				.filter(piece -> piece.getTeam() != this.getTeam() && piece.getClass().equals(KingDTO.class))
				.findFirst().orElse(null);
		
		possibleMoves = possibleMoves.stream().filter(move -> attackedSquares.stream().noneMatch(attack -> attack.equalsIgnoreCase(move))).collect(Collectors.toList());
		
		possibleMoves = possibleMoves.stream().filter(move -> getEnemyKingPossibleSquares(enemyKing).stream().noneMatch(attack -> attack.equalsIgnoreCase(move))).collect(Collectors.toList());
		
		return possibleMoves;
	}

	private List<String> getEnemyKingPossibleSquares(PieceDTO enemyKing) {
		if(enemyKing == null) {
			return new ArrayList<String>();
		}
		String coluna = enemyKing.getPosition().substring(0,1);
		Integer colunaOrdinal = ColunaEnum.getByDescricao(coluna).getOrdinal();
		int linha = Integer.parseInt(enemyKing.getPosition().substring(1,2));
		
		List<Integer[]> possibleCoords = new ArrayList<Integer[]>();
		possibleCoords.add(new Integer[]{linha, colunaOrdinal + 1});
		possibleCoords.add(new Integer[]{linha, colunaOrdinal - 1});
		possibleCoords.add(new Integer[]{linha + 1, colunaOrdinal});
		possibleCoords.add(new Integer[]{linha - 1, colunaOrdinal});
		possibleCoords.add(new Integer[]{linha + 1, colunaOrdinal + 1});
		possibleCoords.add(new Integer[]{linha + 1, colunaOrdinal - 1});
		possibleCoords.add(new Integer[]{linha - 1, colunaOrdinal + 1});
		possibleCoords.add(new Integer[]{linha - 1, colunaOrdinal - 1});
		
		List<String> possibleMoves = new ArrayList<>();
		for (Integer[] coordenadas : possibleCoords) {
			Integer row = coordenadas[0];
			Integer col = coordenadas[1];
			ColunaEnum colunaEnum = ColunaEnum.getByOrdinal(col);
			if(colunaEnum != null) {
				possibleMoves.add(colunaEnum.getDescricao() + row);
			}
		}
		
		return possibleMoves;
		
	}

	@Override
	public List<String> getPossibleMoveIgnoringOposeKing(Map<String, PieceDTO> board) {
		return getPossibleMove(board);
	}
}
