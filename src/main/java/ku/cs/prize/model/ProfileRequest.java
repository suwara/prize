package ku.cs.prize.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Data
public class ProfileRequest {
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private String birthDay;
    private Integer age;

    public LocalDate ConvertStringToDate(String date){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date , format);

        return localDate;
    }

    public Integer calculateBirthDay(LocalDate birthday){
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(birthday, currentDate);
        int age = period.getYears();
        return age;
    }
}
