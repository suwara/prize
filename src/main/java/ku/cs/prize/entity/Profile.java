package ku.cs.prize.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
public class Profile {
    @Id
    @GeneratedValue
    private UUID id;

    private String firstName;
    private String lastName;
    private String email;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;

    private Integer age;

    @OneToOne
    private Member member;

}
