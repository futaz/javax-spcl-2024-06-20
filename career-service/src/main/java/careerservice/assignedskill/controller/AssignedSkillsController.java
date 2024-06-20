package careerservice.assignedskill.controller;

import careerservice.assignedskill.command.AssignSkillsToEmployeeCommand;
import careerservice.assignedskill.service.AssignedSkillsService;
import careerservice.assignedskill.view.EmployeeSkillsView;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assigned-skills")
@AllArgsConstructor
public class AssignedSkillsController {

    private final AssignedSkillsService service;

    @PostMapping
    public EmployeeSkillsView assignSkillsToEmployee(@RequestBody AssignSkillsToEmployeeCommand command) {
        return service.assignSkillsToEmployee(command);
    }

    @GetMapping
    public EmployeeSkillsView getAssignedSkills(@RequestParam int employeeId) {
        return service.getAssignedSkills(employeeId);
    }
}
