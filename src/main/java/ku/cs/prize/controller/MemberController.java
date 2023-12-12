package ku.cs.prize.controller;

import ku.cs.prize.entity.Member;
import ku.cs.prize.model.MemberRequest;
import ku.cs.prize.service.UserDetailsServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("admin/members")
public class MemberController {

    @Autowired
    private UserDetailsServiceImp memberService;

    @GetMapping
    public String getAllMembers(Model model) {
        model.addAttribute("members", memberService.getAllMember());
        return "member-all";
    }

    @GetMapping("/edit/{id}")
    public String getMemberForm(Model model, @PathVariable UUID id) {
        Member member = memberService.getOneById(id);
        model.addAttribute("member", member);
        return "member-edit";
    }

    @PostMapping("/edit")
    public String updateRole(@ModelAttribute MemberRequest request, Model model) {
        memberService.update(request);
        return "redirect:/admin/members";
    }

    @PostMapping("/delete/{id}")
    public String deleteMember(@PathVariable UUID id) {
        memberService.delete(id);
        return "redirect:/admin/members";
    }
}
