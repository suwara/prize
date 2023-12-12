package ku.cs.prize.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Member {
    @Id
    @GeneratedValue
    private UUID id;

    private String username;
    private String password;
    private String role;

    @OneToMany(mappedBy = "member")
    List<Prize> prizes;

    @OneToMany(mappedBy = "member")
    List<Education> educations;

}
