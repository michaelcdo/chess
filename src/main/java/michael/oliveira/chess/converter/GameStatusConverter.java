package michael.oliveira.chess.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import michael.oliveira.chess.enums.GameStatusEnum;

@Converter(autoApply = false)
public class GameStatusConverter 
        implements AttributeConverter<GameStatusEnum, String> {

    @Override
    public String convertToDatabaseColumn(GameStatusEnum attribute) {
        return attribute == null ? null : attribute.getCode();
    }

    @Override
    public GameStatusEnum convertToEntityAttribute(String dbData) {
        return GameStatusEnum.fromCode(dbData);
    }
}