package ku.cs.prize.repository;

import jakarta.transaction.Transactional;
import ku.cs.prize.entity.Prize;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PrizeRepository extends JpaRepository<Prize, UUID> {
    List<Prize> findBySources(String source);
    List<Prize> findByMember_Id(UUID id);

    void deleteByMember_Id(UUID id);
}
