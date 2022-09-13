package it.bitrock.mongodbbitrock.dto.transformer;

import io.vavr.control.Option;
import it.bitrock.mongodbbitrock.dto.AwardsDTO;
import it.bitrock.mongodbbitrock.model.Awards;


public class AwardsTransformer {

    private AwardsTransformer(){

    }

    public static AwardsDTO fromAwardsToAwardsDTO(Awards awards){
        return Option.of(awards).map(awardsNotNull->new AwardsDTO(awardsNotNull.getWins(),
                awardsNotNull.getNominations(), awardsNotNull.getText())).getOrElse(new AwardsDTO());
    }
}
