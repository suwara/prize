package ku.cs.prize.service;

import jakarta.transaction.Transactional;
import ku.cs.prize.entity.Member;
import ku.cs.prize.model.MemberRequest;
import ku.cs.prize.repository.EducationRepository;
import ku.cs.prize.repository.MemberRepository;
import ku.cs.prize.repository.PrizeRepository;
import ku.cs.prize.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserDetailsServiceImp implements UserDetailsService {

    @Autowired
    private MemberRepository userRepository;

    @Autowired
    private PrizeRepository prizeRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private ProfileRepository profileRepository;

    private Member currentMember;

    public List<Member> getAllMember() {
        return userRepository.findAll();
    }

    public Member getOneById(UUID id){
        return userRepository.findById(id).get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        this.setMember(user);

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }

    public Member update(MemberRequest request){
        Member record = userRepository.findById(request.getId()).get();
        record.setUsername(request.getUsername());
        if (request.getRole().equals("Admin")) {
            record.setRole("ROLE_ADMIN");
        } else {
            record.setRole("ROLE_USER");
        }
        userRepository.save(record);
        return record;
    }
    @Transactional
    public void delete(UUID id) {
        educationRepository.deleteByMember_id(id);
        prizeRepository.deleteByMember_Id(id);
        profileRepository.deleteByMember_Id(id);
        userRepository.deleteById(id);
    }

    public void setMember(Member member){
        this.currentMember = member;
    }
    public Member getMember() { return this.currentMember; }
}
