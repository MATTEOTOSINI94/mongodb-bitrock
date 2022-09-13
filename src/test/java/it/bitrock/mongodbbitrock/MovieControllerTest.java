package it.bitrock.mongodbbitrock;

import it.bitrock.mongodbbitrock.controller.MovieController;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;


@SpringBootTest
public class MovieControllerTest {


    @Autowired
    MovieController movieController;


    @Test
    void findMovieByIdPositiveTest(){
        Assertions.assertEquals(movieController.findMovieById("573a1390f29313caabcd5c0f").getBody().getTitle(),"Intolerance: Love's Struggle Throughout the Ages");
    }

    @Test
    void findMovieByIdNegativeTest(){
        Assertions.assertEquals(movieController.findMovieById("wraaongid").getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void findMovieByIdExceptionTest(){
         Assertions.assertEquals(movieController.findMovieById(null).getStatusCode(), HttpStatus.BAD_REQUEST);
    }

      @Test
    void findMovieByTitlePositiveTest(){
        Assertions.assertEquals(movieController.findMovieByTitle("Intolerance: Love's Struggle Throughout the Ages").getBody().getId(),"573a1390f29313caabcd5c0f");
    }

    @Test
    void findMovieByTitleNegativeTest(){
        Assertions.assertEquals(movieController.findMovieByTitle("IntoLerancE: Love's Struggle Throughout the Ages").getBody().getId(),"573a1390f29313caabcd5c0f");
    }

    @Test
    void findMovieByTitleExceptionTest(){
        Assertions.assertEquals(movieController.findMovieByTitle(null).getStatusCode(),HttpStatus.BAD_REQUEST);
    }

    @Test
    void findMoviesByCommentEmailPositiveTest(){
         Assertions.assertEquals(movieController.findMoviesByCommentEmail("shawn_mcCormick@fakegmail.com").getBody().size(),10);    }

    @Test
    void findMoviesByCommentEmailNegativeTest(){
        Assertions.assertEquals(movieController.findMoviesByCommentEmail("Shawn_mcCormick@faKeGmAiL.COM").getBody().size(),10);
    }

    @Test
    void findMoviesByCommentEmailExceptionTest(){
        Assertions.assertEquals(movieController.findMoviesByCommentEmail(null).getStatusCode(),HttpStatus.BAD_REQUEST);
    }

      @Test
    void findMovieByRatingNumReviewsPositiveTest(){
        Assertions.assertEquals(movieController.findMovieByRatingNumReviews(3d,2000d).getBody().size(),5);
    }

    @Test
    void findMovieByRatingNumReviewsNegativeTest(){
        Assertions.assertTrue(movieController.findMovieByRatingNumReviews(3000000000000000000000d,2000000000d).getBody().isEmpty());
    }

}
