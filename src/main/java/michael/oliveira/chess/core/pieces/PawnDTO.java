package michael.oliveira.chess.core.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import michael.oliveira.chess.core.PieceDTO;
import michael.oliveira.chess.core.PieceBaseDTO;
import michael.oliveira.chess.enums.ColunaEnum;
import michael.oliveira.chess.enums.LinhaEnum;
import michael.oliveira.chess.enums.TeamEnum;

public class PawnDTO extends PieceBaseDTO implements PieceDTO{
	
	public PawnDTO(String position, TeamEnum team) {
		super(position, team);
	}
	
	@Override
	public String getIconUrl() {
		return getIconsPath() + "/" + team.getPath() + "/pawn.png";
	}

	@Override
	public List<String> getPossibleMove(Map<String, PieceDTO> board) {
		return getPossibleMove(board, false);
	}

	@Override
	public List<String> getPossibleMoveIgnoringOposeKing(Map<String, PieceDTO> board) {
		return getPossibleMove(board, true);
	}
	
	public List<String> getPossibleMove(Map<String, PieceDTO> board, boolean isKingMove) {
		int moveDirection = +1;
		if(this.getTeam() == TeamEnum.BLACK) {
			moveDirection = -1;
		}
		String coluna = this.getPosition().substring(0,1);
		int linha = Integer.parseInt(this.getPosition().substring(1,2));
		int linhaCapture = linha + moveDirection;
		
		List<String> possibleMoves = new ArrayList<>();
		
		ColunaEnum colunaCapture = ColunaEnum.getByDescricao(coluna);
		if(colunaCapture.getOrdinal() > 1) {
			if((board.get(ColunaEnum.getByOrdinal(colunaCapture.getOrdinal()-1).getDescricao() + linhaCapture) != null &&
			board.get(ColunaEnum.getByOrdinal(colunaCapture.getOrdinal()-1).getDescricao() + linhaCapture).getTeam() != this.getTeam()) || isKingMove) {
				possibleMoves.add(ColunaEnum.getByOrdinal(colunaCapture.getOrdinal()-1).getDescricao() + linhaCapture);
			}
		}
		
		if(colunaCapture.getOrdinal() < 8) {
			if((board.get(ColunaEnum.getByOrdinal(colunaCapture.getOrdinal()+1).getDescricao() + linhaCapture) != null &&
			board.get(ColunaEnum.getByOrdinal(colunaCapture.getOrdinal()+1).getDescricao() + linhaCapture).getTeam() != this.getTeam()) || isKingMove) {
				possibleMoves.add(ColunaEnum.getByOrdinal(colunaCapture.getOrdinal()+1).getDescricao() + linhaCapture);
			}
		}
		
		if(!isKingMove) {
			linha += moveDirection;
			if(board.get(coluna + linha) == null) {
				possibleMoves.add(coluna + linha);
				if (isFirstMove()) {
					linha += moveDirection;
					if(board.get(coluna + linha) == null) {
						possibleMoves.add(coluna + linha);
					}
				}
			}
		}
		return possibleMoves;
	}
	
	private boolean isFirstMove() {
		if((this.getTeam() == TeamEnum.WHITE && this.getPosition().substring(1,2).equalsIgnoreCase(LinhaEnum.LINHA_2.getDescricao())) ||
			(this.getTeam() == TeamEnum.BLACK && this.getPosition().substring(1,2).equalsIgnoreCase(LinhaEnum.LINHA_7.getDescricao()))) {
			return true;
		}
		return false;
	}

}
