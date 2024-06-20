package careerservice.skill;

import careerservice.skill.command.CreateSkillCommand;
import careerservice.skill.view.SkillView;

import java.util.List;

public interface SkillServicePort {
    SkillView create(CreateSkillCommand command);

    List<SkillView> listSkills();
}
