package ku.cs.prize.service;

import ku.cs.prize.entity.Education;
import ku.cs.prize.entity.Member;
import ku.cs.prize.entity.Prize;
import ku.cs.prize.model.EducationRequest;
import ku.cs.prize.repository.EducationRepository;
import ku.cs.prize.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.UUID;

@Service
public class EducationService {
    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private MemberRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    public List<Education> getAllEducation() { return educationRepository.findAll();}

    public List<Education> getAllById() {
        UUID id = userDetailsServiceImp.getMember().getId();
        return educationRepository.findByMember_Id(id);
    }

    public Education getOneById(UUID uuid){
        return educationRepository.findById(uuid).get();
    }

    public void addEducation(EducationRequest education){
       Education record = modelMapper.map(education, Education.class);
       Member member = repository.findById(userDetailsServiceImp.getMember().getId()).get();
       record.setMember(member);
       educationRepository.save(record);
    }

    public Education update(EducationRequest request){
        UUID id = request.getId();
        Education record = educationRepository.findById(id).get();
        record.setNameOfAcademy(request.getNameOfAcademy());
        record.setYearsStart(request.getYearsStart());
        record.setYearsEnd(request.getYearsEnd());
        educationRepository.save(record);
        return record;
    }


    public void delete(UUID id){
        educationRepository.deleteById(id);
    }
}
