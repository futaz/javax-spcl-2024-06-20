package careerservice.skill.controller;

import careerservice.assignedskills.command.AssignSkillsToEmployeeCommand;
import careerservice.skill.command.CreateSkillCommand;
import careerservice.skill.service.SkillService;
import careerservice.assignedskills.view.EmployeeSkillsView;
import careerservice.skill.view.SkillView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class SkillController {

    private SkillService skillService;

    @GetMapping("/api/skills")
    public List<SkillView> listSkills() {
        return skillService.listSkills();
    }

    @PostMapping("/api/skills")
    public SkillView create(@RequestBody CreateSkillCommand command) {
        return skillService.create(command);
    }

}
