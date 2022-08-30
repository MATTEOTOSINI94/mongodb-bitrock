package it.bitrock.mongodbbitrock.dto;

import lombok.*;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TomatoesDTO {

    private ViewerDTO viewer;
    private LocalDateTime lastUpdated;
}
