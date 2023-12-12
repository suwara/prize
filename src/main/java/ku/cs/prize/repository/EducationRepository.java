package ku.cs.prize.repository;

import jakarta.transaction.Transactional;
import ku.cs.prize.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EducationRepository extends JpaRepository<Education, UUID> {
    List<Education> findByMember_Id(UUID id);

    void deleteByMember_id(UUID id);
}
