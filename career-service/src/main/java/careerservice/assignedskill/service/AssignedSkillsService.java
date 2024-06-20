package careerservice.assignedskill.service;

import careerservice.NotFoundException;
import careerservice.assignedskill.command.AssignSkillsToEmployeeCommand;
import careerservice.assignedskill.model.AssignedSkills;
import careerservice.assignedskill.view.EmployeeSkillsView;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j //TODO ez mi?
// Tipikusan a perzisztens réteggel tartja a kapcsolatot és delegálja a hívásokat az entitások felé,
// esetleg áthív másik service-re, ha másik aggregate-tel is kell dolgozni, vagy áthív más rendszerekre

// Fontos, hogy körmentes hívás legyen! Ha az aggregate-ek szintekbe rendezhetők, ahol csak a felső szintűek hivatkoznak
// alsószintűre, nem lesz kör.
public class AssignedSkillsService {

    private AssignedSkillsRepository repository;

    @Transactional
    public EmployeeSkillsView assignSkillsToEmployee(AssignSkillsToEmployeeCommand command) {
        var mayBeAssignedSkills = repository.findByEmployeeId(command.employeeId());
        if (mayBeAssignedSkills.isEmpty()) {
            var assignedSkills = AssignedSkills.firstAssignedSkills(command.employeeId(), command.skills());
            repository.save(assignedSkills);
        } else {
            mayBeAssignedSkills.get().assignSkills(command.skills());
        }

        return getAssignedSkills(command.employeeId());
    }

    @Transactional(readOnly = true) // Join fetch helyett, viszont elég az olvasás
    public EmployeeSkillsView getAssignedSkills(long employeeId) {
        var assignedSkills = repository
                .findByEmployeeId(employeeId).map(AssignedSkills::getLeveledSkills)
                .orElseThrow(() -> new NotFoundException("Not found"));

        return new EmployeeSkillsView(employeeId, assignedSkills);
    }
}
