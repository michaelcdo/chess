package michael.oliveira.chess.enums;

public enum GameStatusEnum {
	RUNNING("R"), ENDED("E");

	private final String code;

	GameStatusEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public static GameStatusEnum fromCode(String code) {
		if (code == null) {
			return null;
		}

		for (GameStatusEnum winner : values()) {
			if (winner.code.equals(code)) {
				return winner;
			}
		}

		throw new IllegalArgumentException("Invalid GameWinner code: " + code);
	}
}
