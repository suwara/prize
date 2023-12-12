package ku.cs.prize.controller;

import ku.cs.prize.entity.Education;
import ku.cs.prize.model.EducationRequest;
import ku.cs.prize.service.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/edu")
public class EducationController {

    @Autowired
    public EducationService educationService;

    @GetMapping("/add")
    public String GetEducationPage(Model model) {return "education";}

    @PostMapping("/add")
    public String createAcademy(@ModelAttribute EducationRequest educationRequest, Model model) {
        educationService.addEducation(educationRequest);
        model.addAttribute("educations",educationService.getAllEducation());
        return "redirect:/profiles";
    }

    @GetMapping("/edit/{id}")
    public String editAcademy(@PathVariable UUID id, Model model) {
        Education education = educationService.getOneById(id);
        model.addAttribute("education", education);
        return "education-edit";
    }

    @PostMapping("edit")
    public String editedAcademy(@ModelAttribute EducationRequest request, Model model) {
        educationService.update(request);
        return "redirect:/profiles";
    }

    @PostMapping("/delete/{id}")
    public String deleteEducation(@PathVariable UUID id){
        educationService.delete(id);
        return "redirect:/profiles";
    }
}
