package careerservice.skill.service;

import careerservice.assignedskill.command.AssignSkillsToEmployeeCommand;
import careerservice.assignedskill.service.AssignedSkillsRepository;
import careerservice.skill.command.CreateSkillCommand;
import careerservice.skill.command.LeveledSkill;
import careerservice.skill.model.AssignedSkill;
import careerservice.skill.model.Skill;
import careerservice.assignedskill.view.EmployeeSkillsView;
import careerservice.skill.view.SkillView;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

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
