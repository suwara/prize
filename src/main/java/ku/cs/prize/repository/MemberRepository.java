package ku.cs.prize.repository;

import ku.cs.prize.entity.Member;
import ku.cs.prize.entity.Prize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MemberRepository extends JpaRepository<Member, UUID> {
    Member findByUsername(String username);

    void deleteById(UUID id);



}
