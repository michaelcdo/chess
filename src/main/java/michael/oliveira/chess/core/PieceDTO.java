package michael.oliveira.chess.core;

import java.util.List;
import java.util.Map;

import michael.oliveira.chess.enums.TeamEnum;

public interface PieceDTO {
	public String getIconUrl();
	public String getPosition();
	public void setPosition(String position);
	public TeamEnum getTeam();
	public void setTeam(TeamEnum team);
	public List<String> getPossibleMove(Map<String, PieceDTO> board);
	public List<String> getPossibleMoveIgnoringOposeKing(Map<String, PieceDTO> board);
}
