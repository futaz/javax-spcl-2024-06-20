package careerservice.assignedskill.view;

import careerservice.assignedskill.model.LeveledSkill;

import java.util.List;

public record EmployeeSkillsView(long employeeId, List<LeveledSkill> leveledSkills) {
}
