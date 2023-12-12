package ku.cs.prize.controller;

import ku.cs.prize.entity.Profile;
import ku.cs.prize.model.ProfileRequest;
import ku.cs.prize.service.EducationService;
import ku.cs.prize.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private EducationService educationService;

    @GetMapping
    public String getProfile(Model model) {
        if (profileService.getProfile() == null){
            return "redirect:/profiles/add";
        } else {
            model.addAttribute("profiles", profileService.getProfile());
            model.addAttribute("educations", educationService.getAllById());
            profileService.calculateBirthDay(profileService.getProfile());
            return "profile-view";
        }

    }
    @GetMapping("/add")
    public String getAddProfile(Model model){
        return "profile-add";
    }

    @PostMapping("/add")
    public String createProfile(@ModelAttribute ProfileRequest profile, Model model) {
        profileService.createProfile(profile);
        model.addAttribute("profiles", profileService.getProfile());
        return "redirect:/profiles";
    }
    @GetMapping("/edit")
    public String editProfilePage(Model model){
        Profile profile = profileService.getProfile();
        model.addAttribute("profiles", profile);
        return "profile-edit";
    }

    @PostMapping("/edit")
    public String update(@ModelAttribute ProfileRequest profile, Model model) {
        profileService.update(profile);
        return "redirect:/profiles";
    }
}
