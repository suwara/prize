package ku.cs.prize.model;

import lombok.Data;

import java.util.UUID;

@Data
public class MemberRequest {
    private UUID id;
    private String username;
    private String password;
    private String role;
}
