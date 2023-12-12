package ku.cs.prize.model;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Data
public class PrizeRequest {

    private UUID id;
    private String type;
    private String sources;
    private String level;
    private String nameGiver;
    private String dayToGet;

    private String tournaments;
    private String description;

    public LocalDate ConvertStringToDate(String date){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date , format);

        return localDate;
    }
}
