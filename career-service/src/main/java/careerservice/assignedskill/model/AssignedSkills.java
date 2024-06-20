package careerservice.assignedskill.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE) // jpa szerint nem jó, hibernate viszi
public class AssignedSkills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long employeeId;

    @ElementCollection
    private List<LeveledSkill> leveledSkills;

    private AssignedSkills(long employeeId, List<LeveledSkill> leveledSkills) {
        if (leveledSkills == null || leveledSkills.isEmpty()) {
            throw new IllegalArgumentException("Leveled skills must not be null or empty");
        }

        this.employeeId = employeeId;
        this.leveledSkills = new ArrayList<>(leveledSkills);
    }

    public static AssignedSkills firstAssignedSkills(long employeeId, List<LeveledSkill> leveledSkills) {
        return new AssignedSkills(employeeId, leveledSkills);
    }

    public void assignSkills(List<LeveledSkill> skillsToAdd) {
        if (skillsToAdd == null || skillsToAdd.isEmpty()) {
            throw new IllegalArgumentException("Leveled skills must not be null or empty");
        }

        var skillsById = leveledSkills.stream().collect(Collectors.toMap(LeveledSkill::skillId, Function.identity()));

        for (LeveledSkill skillToAdd : skillsToAdd) {
            var existingSkill = skillsById.get(skillToAdd.skillId());
            if (existingSkill == null) {
                leveledSkills.add(skillToAdd);
            } else {
                leveledSkills.remove(existingSkill);
                leveledSkills.add(existingSkill.levelUp(skillToAdd));
            }
        }
    }
}
