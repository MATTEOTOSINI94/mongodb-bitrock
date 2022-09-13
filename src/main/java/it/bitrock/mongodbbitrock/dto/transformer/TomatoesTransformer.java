package it.bitrock.mongodbbitrock.dto.transformer;


import io.vavr.control.Option;
import it.bitrock.mongodbbitrock.dto.TomatoesDTO;
import it.bitrock.mongodbbitrock.dto.ViewerDTO;
import it.bitrock.mongodbbitrock.model.Tomatoes;

public class TomatoesTransformer {

    private TomatoesTransformer(){

    }
    public static TomatoesDTO fromTomatoesToTomatoesDTO(Tomatoes tomatoes){
        return Option.of(tomatoes).map(tomatoesNotNull ->
                new TomatoesDTO(new ViewerDTO(tomatoesNotNull.getViewer().getRating(),
                        tomatoesNotNull.getViewer().getNumReviews()),
                        tomatoesNotNull.getLastUpdated())).getOrElse(new TomatoesDTO());
    }
}
