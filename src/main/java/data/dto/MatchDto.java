package data.dto;

import java.time.LocalDateTime;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Alias("matchDto")
public class MatchDto {
    private String summonerId; // summonerId
    private String matchId; // matchId
    private String gameMode;
    private String gameType;
    private int championId;
    private boolean win;
    private int kills;
    private int deaths;
    private int assists;
    private LocalDateTime date; // LocalDateTime으로 변경
}

