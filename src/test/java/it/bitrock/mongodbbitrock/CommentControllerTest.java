package it.bitrock.mongodbbitrock;

import it.bitrock.mongodbbitrock.controller.CommentController;
import it.bitrock.mongodbbitrock.dto.CommentDTO;
import it.bitrock.mongodbbitrock.service.ServiceUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentControllerTest {

    @Autowired
    CommentController commentController;


    public CommentDTO createCommentDTO(){
        return new CommentDTO("max","maximo@gmail.com","5a9427648b0beebeb6957ba5", ServiceUtils.transformStringToLocalDateTime("01-05-1994"),"jswisjiwjdijidjeijidjij");
    }

    @Test
    void findCommentPositiveTest(){

    }


    @Test
    void saveCommentPositiveTest(){
        CommentDTO commentDTO =  commentController.saveComment(createCommentDTO()).getBody();
        Assertions.assertTrue(commentController.findCommentByEmail("maximo@gmail.com").getBody().get(0).getEmail().equalsIgnoreCase(commentDTO.getEmail()));
    }


    @Test
    void removeCommentByEmailPositiveTest(){
      Assertions.assertTrue(commentController.removeCommentByEmail("maximo@gmail.com").getBody().equalsIgnoreCase("Hai cancellato " + 1 + " commenti"));

    }
}
