package careerservice.assignedskills.service;

import careerservice.NotFoundException;
import careerservice.assignedskills.command.AssignSkillsToEmployeeCommand;
import careerservice.assignedskills.model.AssignedSkills;
import careerservice.assignedskills.model.LeveledSkill;
import careerservice.assignedskills.view.EmployeeSkillsView;
import careerservice.skill.SkillHasBeenCreatedEvent;
import careerservice.skill.SkillServicePort;
import careerservice.skill.service.SkillService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class AssignedSkillsService {

    private AssignedSkillsRepository assignedSkillsRepository;

    private SkillServicePort skillService;


    @Transactional
    public EmployeeSkillsView assignSkillsToEmployee(AssignSkillsToEmployeeCommand command) {
        var mayBeAssignedSkills = assignedSkillsRepository.findByEmployeeId(command.employeeId());
        if (mayBeAssignedSkills.isEmpty()) {
            var assignedSkills = AssignedSkills.firstAssignSkills(command.employeeId(), command.skills());
            assignedSkillsRepository.save(assignedSkills);
        }
        else {
            mayBeAssignedSkills.get().assignSkills(command.skills());
        }
        return getAssignedSkills(command.employeeId());
    }

//    @Transactional(readOnly = true)
    public EmployeeSkillsView getAssignedSkills(long employeeId) {
        var assignedSkills = assignedSkillsRepository.findByEmployeeId(employeeId);

        return new EmployeeSkillsView(employeeId, assignedSkills
                .orElseThrow(() -> new NotFoundException("Not found")).getLeveledSkills());
    }

    @EventListener
    public void handleSkillHasBeenCreatedEvent(SkillHasBeenCreatedEvent event) {
        log.info("Skill has been created: {}", event);
    }
}
