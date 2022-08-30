package it.bitrock.mongodbbitrock.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@Setter
@ToString
public class Tomatoes {

   private Viewer viewer;
   private LocalDateTime lastUpdated;

}
