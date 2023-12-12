package ku.cs.prize.controller;

import ku.cs.prize.entity.Member;
import ku.cs.prize.service.SignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    @Autowired
    private SignupService signupService;

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }
    @PostMapping("/signup")
    public String signupUser (@ModelAttribute Member user, Model model) {
        if (signupService.isUsernameAvailable(user.getUsername())) {
            signupService.createUser(user);
            model.addAttribute("signupSuccess", "Success for Signup! Please Login");
            return "login";
        }else {
            model.addAttribute("signupError", "Username not available");
            return "signup";
        }

    }
 }
