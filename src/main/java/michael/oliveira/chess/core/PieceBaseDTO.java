package michael.oliveira.chess.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import michael.oliveira.chess.core.pieces.KingDTO;
import michael.oliveira.chess.enums.ColunaEnum;
import michael.oliveira.chess.enums.LinhaEnum;
import michael.oliveira.chess.enums.TeamEnum;

public class PieceBaseDTO {
	private static final String ICONS_PATH = "/images/piece/";
	
	public String position;
	public TeamEnum team;
	
	public PieceBaseDTO(String position, TeamEnum team) {
		super();
		this.position = position;
		this.team = team;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public TeamEnum getTeam() {
		return team;
	}
	public void setTeam(TeamEnum team) {
		this.team = team;
	}
	public static String getIconsPath() {
		return ICONS_PATH;
	}
	
	public List<String> getPossibleDiagonalMove(Map<String, PieceDTO> board, boolean ignoreOposeKing){
		int boardSize = Math.max(ColunaEnum.values().length, LinhaEnum.values().length);
		String coluna = this.getPosition().substring(0,1);
		Integer colunaOrdinal = ColunaEnum.getByDescricao(coluna).getOrdinal();
		int linha = Integer.parseInt(this.getPosition().substring(1,2));
		boolean upRight = true, upLeft = true, downRight = true, downLeft = true;
		List<String> possibleMoves = new ArrayList<>();
		for (int i = 1; i <= boardSize; i++) {
			if(upRight || downRight) {
				ColunaEnum colunaUpEnum = ColunaEnum.getByOrdinal(colunaOrdinal + i);
				if(colunaUpEnum != null) {
					if(upRight) {
						upRight = checkPosition(colunaUpEnum, linha+i, board, possibleMoves, ignoreOposeKing);
					}
					
					if(downRight) {
						downRight = checkPosition(colunaUpEnum, linha-i, board, possibleMoves, ignoreOposeKing);
					}
				}else {
					upRight = false;
					downRight = false;
				}
			}
			if(upLeft || downLeft) {
				ColunaEnum colunaDownEnum = ColunaEnum.getByOrdinal(colunaOrdinal - i);
				if(colunaDownEnum != null) {
					if(upLeft) {
						upLeft = checkPosition(colunaDownEnum, linha+i, board, possibleMoves, ignoreOposeKing);
					}
					if(downLeft) {
						downLeft = checkPosition(colunaDownEnum, linha-i, board, possibleMoves, ignoreOposeKing);
					}
				}else {
					upLeft = false;
					downLeft = false;
				}
			}
		}
		
		return possibleMoves;
	}
	
	public List<String> getPossibleLineMove(Map<String, PieceDTO> board, boolean ignoreOposeKing){
		int boardSize = Math.max(ColunaEnum.values().length, LinhaEnum.values().length);
		String coluna = this.getPosition().substring(0,1);
		Integer colunaOrdinal = ColunaEnum.getByDescricao(coluna).getOrdinal();
		int linha = Integer.parseInt(this.getPosition().substring(1,2));
		boolean up = true, down= true, right = true, left = true;
		List<String> possibleMoves = new ArrayList<>();
		for (int i = 1; i <= boardSize; i++) {
			if(up) {
				ColunaEnum colunaUpEnum = ColunaEnum.getByOrdinal(colunaOrdinal);
				up = checkPosition(colunaUpEnum, linha+i, board, possibleMoves, ignoreOposeKing);

			}
			if(down) {
				ColunaEnum colunaUpEnum = ColunaEnum.getByOrdinal(colunaOrdinal);
				down = checkPosition(colunaUpEnum, linha-i, board, possibleMoves, ignoreOposeKing);

			}
			if(right) {
				ColunaEnum colunaUpEnum = ColunaEnum.getByOrdinal(colunaOrdinal + i);
				if(colunaUpEnum != null) {
					right = checkPosition(colunaUpEnum, linha, board, possibleMoves, ignoreOposeKing);
				}else {
					right = false;
				}
			}
			if(left) {
				ColunaEnum colunaUpEnum = ColunaEnum.getByOrdinal(colunaOrdinal - i);
				if(colunaUpEnum != null) {
					left = checkPosition(colunaUpEnum, linha, board, possibleMoves, ignoreOposeKing);
				}else {
					left = false;
				}
			}
		}
		return possibleMoves;
	}
	
	
	private boolean checkPosition(ColunaEnum coluna, int linha, Map<String, PieceDTO> board, List<String> possibleMoves, boolean ignoreOposeKing) {
		PieceDTO piece = board.get(coluna.getDescricao() + linha);
		if(piece == null) {
			possibleMoves.add(coluna.getDescricao() + linha);
			return true;
		}else if(piece.getTeam() != this.getTeam()){
			possibleMoves.add(coluna.getDescricao() + linha);
			if(ignoreOposeKing && piece.getClass().equals(KingDTO.class)) {
				return true;
			}
			return false;
		}else {
			return false;
		}
	}
	
}
