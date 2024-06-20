package careerservice.assignedskill.command;

import careerservice.assignedskill.model.LeveledSkill;

import java.util.List;

public record AssignSkillsToEmployeeCommand(long employeeId, List<LeveledSkill> skills) {
}
