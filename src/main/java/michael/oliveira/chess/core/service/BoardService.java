package michael.oliveira.chess.core.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import michael.oliveira.chess.converter.PieceConverter;
import michael.oliveira.chess.core.PieceDTO;
import michael.oliveira.chess.core.jpa.model.Game;
import michael.oliveira.chess.core.jpa.model.Piece;
import michael.oliveira.chess.core.pieces.BishopDTO;
import michael.oliveira.chess.core.pieces.KingDTO;
import michael.oliveira.chess.core.pieces.KnightDTO;
import michael.oliveira.chess.core.pieces.PawnDTO;
import michael.oliveira.chess.core.pieces.QueenDTO;
import michael.oliveira.chess.core.pieces.TowerDTO;
import michael.oliveira.chess.enums.ColunaEnum;
import michael.oliveira.chess.enums.LinhaEnum;
import michael.oliveira.chess.enums.TeamEnum;

@Service
public class BoardService{
	@Autowired
	private GameService gameService;
	
	public Integer createStartingBoard(){
		List<PieceDTO> board = new ArrayList<PieceDTO>();
		
		board.add(new KingDTO("E1", TeamEnum.WHITE));
		board.add(new QueenDTO("D1", TeamEnum.WHITE));
		board.add(new BishopDTO("C1", TeamEnum.WHITE));
		board.add(new BishopDTO("F1", TeamEnum.WHITE));
		board.add(new KnightDTO("G1", TeamEnum.WHITE));
		board.add(new KnightDTO("B1", TeamEnum.WHITE));
		board.add(new TowerDTO("A1", TeamEnum.WHITE));
		board.add(new TowerDTO("H1", TeamEnum.WHITE));
		board.add(new PawnDTO("A2", TeamEnum.WHITE));
		board.add(new PawnDTO("B2", TeamEnum.WHITE));
		board.add(new PawnDTO("C2", TeamEnum.WHITE));
		board.add(new PawnDTO("D2", TeamEnum.WHITE));
		board.add(new PawnDTO("E2", TeamEnum.WHITE));
		board.add(new PawnDTO("F2", TeamEnum.WHITE));
		board.add(new PawnDTO("G2", TeamEnum.WHITE));
		board.add(new PawnDTO("H2", TeamEnum.WHITE));
		
		board.add(new KingDTO("E8", TeamEnum.BLACK));
		board.add(new QueenDTO("D8", TeamEnum.BLACK));
		board.add(new BishopDTO("C8", TeamEnum.BLACK));
		board.add(new BishopDTO("F8", TeamEnum.BLACK));
		board.add(new KnightDTO("G8", TeamEnum.BLACK));
		board.add(new KnightDTO("B8", TeamEnum.BLACK));
		board.add(new TowerDTO("A8", TeamEnum.BLACK));
		board.add(new TowerDTO("H8", TeamEnum.BLACK));
		board.add(new PawnDTO("A7", TeamEnum.BLACK));
		board.add(new PawnDTO("B7", TeamEnum.BLACK));
		board.add(new PawnDTO("C7", TeamEnum.BLACK));
		board.add(new PawnDTO("D7", TeamEnum.BLACK));
		board.add(new PawnDTO("E7", TeamEnum.BLACK));
		board.add(new PawnDTO("F7", TeamEnum.BLACK));
		board.add(new PawnDTO("G7", TeamEnum.BLACK));
		board.add(new PawnDTO("H7", TeamEnum.BLACK));	

		Map<String, PieceDTO> startingBoard = board.stream().collect(Collectors.toMap(PieceDTO::getPosition, p -> p));
		return saveInitialBoard(startingBoard);
	}
	
	public Integer saveInitialBoard(Map<String, PieceDTO> board) {
		Game game = new Game();
		for (PieceDTO pieceDTO : board.values()) {
			game.addPieces(PieceConverter.pieceDtoToPiece(pieceDTO, game));
		}
		
		return gameService.save(game);
	}
	
	public Map<String, PieceDTO> getCurrentBoard(Integer gameId){
		Game game = gameService.findById(gameId);
		if(game == null) {
			throw new RuntimeException("Game not found.");
		}
		List<PieceDTO> pieces = game.getPieces().stream().map(piece -> PieceConverter.pieceToPieceDto(piece)).collect(Collectors.toList());
		
		return pieces.stream().collect(Collectors.toMap(PieceDTO::getPosition, p -> p));
	}
	
	public TeamEnum getCurrentPlayer(Integer gameId) {
		Game game = gameService.findById(gameId);
		if(game == null) {
			throw new RuntimeException("Game not found.");
		}
		return game.getCurrentPlayer();
	}
	
	public List<String> getPossibleMovesByPosition(String position, Map<String, PieceDTO> board, TeamEnum currentPlayer){
		if(currentPlayer == null) {
			return null;
		}
		if(board == null) {
			return null;
		}
		PieceDTO piece = board.get(position);
		if(piece == null) {
			return null;
		}
		if(piece.getTeam() != currentPlayer) {
			return null;
		}
		
		List<String> allPossibleMoves = piece.getPossibleMove(board);
		
		return removeImpossibleMoves(allPossibleMoves);
	}
	
	public void move(Integer gameId, PieceDTO piece, String position) {
		Game game = gameService.findById(gameId);
		if(game == null) {
			throw new RuntimeException("Game not found.");
		}
		
		//Remove a peça capturada
		Piece targetPiece = game.getPieces().stream().filter(p -> p.getPosition().equalsIgnoreCase(position)).findFirst().orElse(null);
		if(targetPiece != null) {
			game.removePieces(targetPiece);
		}
		
		//Move a peça
		Piece currentPiece = game.getPieces().stream().filter(p -> p.getPosition().equalsIgnoreCase(piece.getPosition())).findFirst().orElse(null);
		if(currentPiece == null) {
			throw new RuntimeException("Current Piece not found.");
		}
		currentPiece.setPosition(position);
		
		//Altera o player
		TeamEnum currentPlayer = game.getCurrentPlayer();
		if(currentPlayer == TeamEnum.BLACK) {
			currentPlayer = TeamEnum.WHITE;
		}else {
			currentPlayer = TeamEnum.BLACK;
		}
		game.setCurrentPlayer(currentPlayer);
		
		gameService.save(game);
	}
	
	private List<String> removeImpossibleMoves(List<String> allPossibleMoves){
		List<String> colunas = Arrays.asList(ColunaEnum.values()).stream().map(ColunaEnum::getDescricao).collect(Collectors.toList());
		List<String> linhas = Arrays.asList(LinhaEnum.values()).stream().map(LinhaEnum::getDescricao).collect(Collectors.toList());
		List<String> possibleMoves = allPossibleMoves.stream().filter(position -> colunas.contains(position.substring(0, 1))).collect(Collectors.toList());
		
		possibleMoves = possibleMoves.stream().filter(position -> linhas.contains(position.substring(1, 2))).collect(Collectors.toList());

		return possibleMoves;
	}
}
