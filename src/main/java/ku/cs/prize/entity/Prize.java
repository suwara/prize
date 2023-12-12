package ku.cs.prize.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Prize {
    @Id
    @GeneratedValue
    private UUID id;

    private String type;
    private String sources;
    private String level;
    private String nameGiver;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dayToGet;

    private String tournaments;
    private String description;
//    private Image imgPrize;
    @ManyToOne
    private Member member;

}
