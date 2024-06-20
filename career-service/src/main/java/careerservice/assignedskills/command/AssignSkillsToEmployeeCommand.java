package careerservice.assignedskills.command;

import careerservice.assignedskills.model.LeveledSkill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public record AssignSkillsToEmployeeCommand(long employeeId, List<LeveledSkill> skills) {

}
