package ku.cs.prize.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Education{
    @Id
    @GeneratedValue
    private UUID id;

    private String nameOfAcademy;
    private String level;
    private String yearsStart;
    private String yearsEnd;

    @ManyToOne
    private Member member;
}
