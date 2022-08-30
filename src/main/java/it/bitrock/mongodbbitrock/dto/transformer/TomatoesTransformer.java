package it.bitrock.mongodbbitrock.dto.transformer;


import it.bitrock.mongodbbitrock.dto.TomatoesDTO;
import it.bitrock.mongodbbitrock.dto.ViewerDTO;
import it.bitrock.mongodbbitrock.model.Tomatoes;

public class TomatoesTransformer {

    private TomatoesTransformer(){

    }
    public static TomatoesDTO fromTomatoesToTomatoesDTO(Tomatoes tomatoes){
        if (tomatoes != null) {
            return new TomatoesDTO(new ViewerDTO(tomatoes.getViewer().getRating(), tomatoes.getViewer().getNumReviews()), tomatoes.getLastUpdated());
        }
        return new TomatoesDTO();
    }
}
