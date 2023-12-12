package ku.cs.prize.service;

import ku.cs.prize.entity.Member;
import ku.cs.prize.entity.Profile;
import ku.cs.prize.model.MemberRequest;
import ku.cs.prize.model.ProfileRequest;
import ku.cs.prize.repository.MemberRepository;
import ku.cs.prize.repository.ProfileRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.UUID;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MemberRepository repository;

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    public Profile getOneById(UUID id) {return profileRepository.findById(id).get();}

    public void createProfile(ProfileRequest profile) {
        Profile record = modelMapper.map(profile, Profile.class);
        Member member = repository.findById(userDetailsServiceImp.getMember().getId()).get();

        record.setBirthDay(profile.ConvertStringToDate(profile.getBirthDay()));

        record.setMember(member);
        profileRepository.save(record);
    }

    public Profile update(ProfileRequest requestBody) {
        UUID id = userDetailsServiceImp.getMember().getId();
        Profile record = profileRepository.findByMember_Id(id);
        record.setFirstName(requestBody.getFirstName());
        record.setLastName(requestBody.getLastName());
        record.setEmail(requestBody.getEmail());

        record.setBirthDay(requestBody.ConvertStringToDate(requestBody.getBirthDay()));

        record = profileRepository.save(record);
        return record;
    }

    public void calculateBirthDay(Profile request){
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(request.getBirthDay(), currentDate);
        int age = period.getYears();

        request.setAge(age);
        profileRepository.save(request);
    }

    public Profile getProfile() {

        UUID id = userDetailsServiceImp.getMember().getId();
        return profileRepository.findByMember_Id(id);
    }
}
