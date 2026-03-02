package michael.oliveira.chess.enums;

public enum TeamEnum {
	WHITE("White", "white", "W"), 
	BLACK("Black", "black", "B");

	private String descricao;
	private String path;
	private String code;

	private TeamEnum(String descricao, String path, String code) {
		this.descricao = descricao;
		this.path = path;
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getPath() {
		return path;
	}
	
	public static TeamEnum fromCode(String code) {
		if (code == null) {
			return null;
		}

		for (TeamEnum winner : values()) {
			if (winner.code.equals(code)) {
				return winner;
			}
		}

		throw new IllegalArgumentException("Invalid GameWinner code: " + code);
	}

}
