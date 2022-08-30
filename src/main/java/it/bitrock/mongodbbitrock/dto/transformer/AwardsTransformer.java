package it.bitrock.mongodbbitrock.dto.transformer;

import it.bitrock.mongodbbitrock.dto.AwardsDTO;
import it.bitrock.mongodbbitrock.model.Awards;


public class AwardsTransformer {

    private AwardsTransformer(){

    }

    public static AwardsDTO fromAwardsToAwardsDTO(Awards awards){
        if(awards!=null) {
            return new AwardsDTO(awards.getWins(), awards.getNominations(), awards.getText());
        }
        return new AwardsDTO();
    }
}
