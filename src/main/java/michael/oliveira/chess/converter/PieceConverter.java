package michael.oliveira.chess.converter;

import michael.oliveira.chess.core.PieceDTO;
import michael.oliveira.chess.core.jpa.model.Game;
import michael.oliveira.chess.core.jpa.model.Piece;
import michael.oliveira.chess.core.pieces.BishopDTO;
import michael.oliveira.chess.core.pieces.KingDTO;
import michael.oliveira.chess.core.pieces.KnightDTO;
import michael.oliveira.chess.core.pieces.PawnDTO;
import michael.oliveira.chess.core.pieces.QueenDTO;
import michael.oliveira.chess.core.pieces.TowerDTO;
import michael.oliveira.chess.enums.PieceTypeEnum;

public class PieceConverter {
	public static Piece pieceDtoToPiece(PieceDTO pieceDto, Game game) {
		Piece piece = new Piece();
		piece.setGame(game);
		piece.setColor(pieceDto.getTeam());
		piece.setPosition(pieceDto.getPosition());
		PieceTypeEnum pieceType = null;
		
		if(pieceDto.getClass().equals(KingDTO.class)) {
			pieceType = PieceTypeEnum.KING;
		}else if(pieceDto.getClass().equals(QueenDTO.class)) {
			pieceType = PieceTypeEnum.QUEEN;
		}else if(pieceDto.getClass().equals(KnightDTO.class)) {
			pieceType = PieceTypeEnum.KNIGHT;
		}else if(pieceDto.getClass().equals(BishopDTO.class)) {
			pieceType = PieceTypeEnum.BISHOP;
		}else if(pieceDto.getClass().equals(TowerDTO.class)) {
			pieceType = PieceTypeEnum.TOWER;
		}else {
			pieceType = PieceTypeEnum.PAWN;
		}
		
		piece.setType(pieceType);
		
		return piece;
	} 
	
	public static PieceDTO pieceToPieceDto(Piece piece) {
		PieceDTO pieceDto = null;
		
		switch (piece.getType()) {
		case KING: {
			pieceDto = new KingDTO(piece.getPosition(), piece.getColor());
			break;
		}
		case QUEEN: {
			pieceDto = new QueenDTO(piece.getPosition(), piece.getColor());
			break;
		}
		case KNIGHT: {
			pieceDto = new KnightDTO(piece.getPosition(), piece.getColor());
			break;
		}
		case BISHOP: {
			pieceDto = new BishopDTO(piece.getPosition(), piece.getColor());
			break;
		}
		case TOWER: {
			pieceDto = new TowerDTO(piece.getPosition(), piece.getColor());
			break;
		}
		default:
			pieceDto = new PawnDTO(piece.getPosition(), piece.getColor());
		}	
		
		return pieceDto;
	} 
}
