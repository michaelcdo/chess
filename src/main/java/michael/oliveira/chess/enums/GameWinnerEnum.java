package michael.oliveira.chess.enums;

public enum GameWinnerEnum {
	WHITE("W"),
    BLACK("B"),
    DRAW("D");

    private final String code;

    GameWinnerEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static GameWinnerEnum fromCode(String code) {
        if (code == null) {
            return null;
        }

        for (GameWinnerEnum winner : values()) {
            if (winner.code.equals(code)) {
                return winner;
            }
        }

        throw new IllegalArgumentException("Invalid GameWinner code: " + code);
    }
}
