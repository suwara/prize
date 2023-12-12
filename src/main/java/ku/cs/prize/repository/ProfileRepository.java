package ku.cs.prize.repository;

import ku.cs.prize.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {

    Profile findByMember_Id(UUID id);
    Profile findByEmail(String email);

    void deleteByMember_Id(UUID id);
}
