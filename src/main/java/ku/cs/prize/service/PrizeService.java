package ku.cs.prize.service;

import ku.cs.prize.entity.Member;
import ku.cs.prize.entity.Prize;
import ku.cs.prize.model.PrizeRequest;
import ku.cs.prize.repository.MemberRepository;
import ku.cs.prize.repository.PrizeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PrizeService {
    @Autowired
    private PrizeRepository prizeRepository;

    @Autowired
    private MemberRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserDetailsServiceImp userDetailsServiceImp;

    public List<Prize> getAllPrizes() {return prizeRepository.findAll();}

    public List<Prize> getAllById() {
        UUID id = userDetailsServiceImp.getMember().getId();
        return prizeRepository.findByMember_Id(id);
    }

    public List<Prize> getPrizeByMemberId(UUID id) {
        return prizeRepository.findByMember_Id(id);
    }

    public Prize getOneById(UUID id){return prizeRepository.findById(id).get();}

    public void createNewPrize(PrizeRequest prize){
        Prize record = modelMapper.map(prize, Prize.class);
        Member member = repository.findById(userDetailsServiceImp.getMember().getId()).get();
        record.setMember(member);

        record.setDayToGet(prize.ConvertStringToDate(prize.getDayToGet()));

        prizeRepository.save(record);
//        2023-12-31 form Date
    }

    public List<Prize> getPrizeBySource(String source) {
        return prizeRepository.findBySources(source);
    }

    public Prize update(PrizeRequest request){
        Prize record = prizeRepository.findById(request.getId()).get();
        record.setType(request.getType());
        record.setSources(request.getSources());
        record.setTournaments(request.getTournaments());

        record.setDayToGet(request.ConvertStringToDate(request.getDayToGet()));
        record.setDescription(request.getDescription());
        record.setLevel(request.getLevel());
        record.setNameGiver(request.getNameGiver());
        record = prizeRepository.save(record);
        return record;
    }

    public void delete(UUID id) {
        prizeRepository.deleteById(id);
    }
}
