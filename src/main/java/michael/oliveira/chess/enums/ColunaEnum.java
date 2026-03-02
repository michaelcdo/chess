package michael.oliveira.chess.enums;

public enum ColunaEnum {
	COLUNA_A("A",1),
	COLUNA_B("B",2),
	COLUNA_C("C",3),
	COLUNA_D("D",4),
	COLUNA_E("E",5),
	COLUNA_F("F",6),
	COLUNA_G("G",7),
	COLUNA_H("H",8);
	
	private String descricao;
	private Integer ordinal;
	
	private ColunaEnum(String descricao, Integer ordinal) {
		this.descricao = descricao;
		this.ordinal = ordinal;
	}
	
	public static ColunaEnum getByDescricao(String descricao) {
		for (ColunaEnum coluna : ColunaEnum.values()) {
			if(coluna.getDescricao().equalsIgnoreCase(descricao)) {
				return coluna;
			}
		}
		return null;
	}
	
	public static ColunaEnum getByOrdinal(Integer ordinal) {
		for (ColunaEnum coluna : ColunaEnum.values()) {
			if(coluna.getOrdinal() == ordinal) {
				return coluna;
			}
		}
		return null;
	}
	public Integer getOrdinal() {
		return ordinal;
	}

	public String getDescricao() {
		return descricao;
	}
}
