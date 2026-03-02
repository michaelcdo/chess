package michael.oliveira.chess.enums;

public enum LinhaEnum {
	LINHA_8("8",8),
	LINHA_7("7",7),
	LINHA_6("6",6),
	LINHA_5("5",5),
	LINHA_4("4",4),
	LINHA_3("3",3),
	LINHA_2("2",2),
	LINHA_1("1",1);
	
	private String descricao;
	private Integer ordinal;
	
	private LinhaEnum(String descricao, Integer ordinal) {
		this.descricao = descricao;
		this.ordinal = ordinal;
	}

	public Integer getOrdinal() {
		return ordinal;
	}

	public String getDescricao() {
		return descricao;
	}
}
