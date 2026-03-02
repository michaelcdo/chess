package michael.oliveira.chess.converter;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import michael.oliveira.chess.enums.TeamEnum;

@Converter(autoApply = false)
public class TeamConverter 
        implements AttributeConverter<TeamEnum, String> {

    @Override
    public String convertToDatabaseColumn(TeamEnum attribute) {
        return attribute == null ? null : attribute.getCode();
    }

    @Override
    public TeamEnum convertToEntityAttribute(String dbData) {
        return TeamEnum.fromCode(dbData);
    }
}