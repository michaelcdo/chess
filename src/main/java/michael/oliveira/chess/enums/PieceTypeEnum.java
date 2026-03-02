package michael.oliveira.chess.enums;

public enum PieceTypeEnum {
	KING("K"),
	QUEEN("Q"),
	KNIGHT("N"),
	BISHOP("B"),
	TOWER("T"),
	PAWN("P");
	
	private final String code;

	PieceTypeEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static PieceTypeEnum fromCode(String code) {
		if (code == null) {
			return null;
		}

		for (PieceTypeEnum winner : values()) {
			if (winner.code.equals(code)) {
				return winner;
			}
		}

		throw new IllegalArgumentException("Invalid GameWinner code: " + code);
	}
}
