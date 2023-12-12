package ku.cs.prize.model;

import lombok.Data;

import java.util.UUID;

@Data
public class EducationRequest {
    private UUID id;
    private String nameOfAcademy;
    private String level;
    private String yearsStart;
    private String yearsEnd;
}
