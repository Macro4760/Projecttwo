package data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChampionDto {
    private String id;
    private String name;
    private String title;
    private String blurb;
    private String image;
}