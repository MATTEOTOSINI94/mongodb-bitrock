package it.bitrock.mongodbbitrock.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ViewerDTO {

    private Float rating;
    private Long numReviews;
}
