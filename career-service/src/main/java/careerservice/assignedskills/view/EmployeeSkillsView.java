package careerservice.assignedskills.view;

import careerservice.assignedskills.model.LeveledSkill;

import java.util.List;

public record EmployeeSkillsView(long employeeId, List<LeveledSkill> skills) {

}
