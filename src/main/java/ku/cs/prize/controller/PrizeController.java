package ku.cs.prize.controller;

import ku.cs.prize.entity.Member;
import ku.cs.prize.entity.Prize;
import ku.cs.prize.model.PrizeRequest;
import ku.cs.prize.repository.MemberRepository;
import ku.cs.prize.service.PrizeService;
import ku.cs.prize.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/prizes")
public class PrizeController {
    @Autowired
    private PrizeService prizeService;

    @GetMapping
    public String getAllPrizeByMemberId(Model model) {
        model.addAttribute("prizes",prizeService.getAllById());
        return "prize-all";
    }
    @GetMapping("/as-{id}")
    public String getPrizeByMemberId(@PathVariable UUID id, Model model) {
        model.addAttribute("prizes", prizeService.getPrizeByMemberId(id));
        return "prize-all";
    }

    @GetMapping("/{id}")
    public String getOnePrize(@PathVariable UUID id, Model model) {
        Prize prize = prizeService.getOneById(id);
        model.addAttribute("prize", prize);
        return "prize-view";
    }

    @GetMapping("/add")
    public String getPrizeForm(Model model) {
        return "prize-add";
    }

    @PostMapping("/add")
    public String createPrize(@ModelAttribute PrizeRequest prize, Model model) {
        prizeService.createNewPrize(prize);
        model.addAttribute("prizes", prizeService.getAllPrizes());
        return "redirect:/prizes";
    }

    @GetMapping("/edit/{id}")
    public String getPrizeEditForm(@PathVariable UUID id,Model model) {
        Prize prize = prizeService.getOneById(id);
        model.addAttribute("prizes", prize);
        return "prize-edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute PrizeRequest prizes,Model model) {
        prizeService.update(prizes);
        return "redirect:/prizes";
    }

    @PostMapping("/delete/{id}")
    public String deletePrize(@PathVariable UUID id){
        prizeService.delete(id);
        return "redirect:/prizes";
    }
}
