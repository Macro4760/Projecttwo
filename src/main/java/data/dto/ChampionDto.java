package data.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Alias("championDto")
public class ChampionDto {
    private String id;
    private String name;
    private String title;
    private String blurb;
    private String image;
    private String skillQ;
    private String skillQName;
    private String skillQDescription;
    private String skillQImage;

    private String skillW;
    private String skillWName;
    private String skillWDescription;
    private String skillWImage;

    private String skillE;
    private String skillEName;
    private String skillEDescription;
    private String skillEImage;

    private String skillR;
    private String skillRName;
    private String skillRDescription;
    private String skillRImage;

    private int rating;
    private List<String> comments;
    
    
}