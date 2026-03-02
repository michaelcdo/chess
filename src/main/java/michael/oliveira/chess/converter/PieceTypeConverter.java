package michael.oliveira.chess.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import michael.oliveira.chess.enums.PieceTypeEnum;

@Converter(autoApply = false)
public class PieceTypeConverter 
        implements AttributeConverter<PieceTypeEnum, String> {

    @Override
    public String convertToDatabaseColumn(PieceTypeEnum attribute) {
        return attribute == null ? null : attribute.getCode();
    }

    @Override
    public PieceTypeEnum convertToEntityAttribute(String dbData) {
        return PieceTypeEnum.fromCode(dbData);
    }
}