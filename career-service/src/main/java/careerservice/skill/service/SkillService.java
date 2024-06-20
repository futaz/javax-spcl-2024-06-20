package careerservice.skill.service;

import careerservice.assignedskills.service.AssignedSkillsRepository;
import careerservice.skill.command.CreateSkillCommand;
import careerservice.skill.model.Skill;
import careerservice.skill.view.SkillView;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SkillService {

    private SkillRepository skillRepository;

    private AssignedSkillsRepository assignedSkillsRepository;

    private SkillMapper skillMapper;

    public SkillView create(CreateSkillCommand command) {
        var skill = new Skill(command.getName());
        skillRepository.save(skill);
        return skillMapper.toView(skill);
    }

    public List<SkillView> listSkills() {
        return skillMapper.toViews(skillRepository.findAll());
    }


}
