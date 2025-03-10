package data.dto;

import java.util.List;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Alias("summonerDto")
public class SummonerDto {
    private String summonerId; // id -> summonerId로 변경
    private String summonerName; // name -> summonerName으로 변경
    private String puuid;
    private int profileIconId; // profileImg -> profileIconId로 변경
    private long revisionDate;
    private int summonerLevel;
    private String region; // region 추가 (필요시)

    public String getProfileImgUrl() {
        return "http://ddragon.leagueoflegends.com/cdn/13.24.1/img/profileicon/" + profileIconId + ".png";
    }
}
