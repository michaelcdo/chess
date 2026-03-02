package michael.oliveira.chess.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import michael.oliveira.chess.enums.GameWinnerEnum;

@Converter(autoApply = false)
public class GameWinnerConverter 
        implements AttributeConverter<GameWinnerEnum, String> {

    @Override
    public String convertToDatabaseColumn(GameWinnerEnum attribute) {
        return attribute == null ? null : attribute.getCode();
    }

    @Override
    public GameWinnerEnum convertToEntityAttribute(String dbData) {
        return GameWinnerEnum.fromCode(dbData);
    }
}