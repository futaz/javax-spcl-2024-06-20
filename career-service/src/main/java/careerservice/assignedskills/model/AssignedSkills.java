package careerservice.assignedskills.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AssignedSkills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long employeeId;

    @ElementCollection
    private List<LeveledSkill> leveledSkills;

    private AssignedSkills(long employeeId, List<LeveledSkill> leveledSkills) {
        if (leveledSkills == null || leveledSkills.isEmpty()) {
            throw new IllegalArgumentException("LeveledSkills must not be null or empty");
        }
        this.employeeId = employeeId;
        this.leveledSkills = leveledSkills;
    }

    public static AssignedSkills firstAssignSkills(long employeeId, List<LeveledSkill> leveledSkills) {
        return new AssignedSkills(employeeId, new ArrayList<>(leveledSkills));
    }

    public void assignSkills(List<LeveledSkill> skillsToAdd) {
        var skillsById = leveledSkills.stream().collect(Collectors.toMap(
                LeveledSkill::skillId, Function.identity()
        ));

        for (var skillToAdd: skillsToAdd) {
            var existingSkill = skillsById.get(skillToAdd.skillId());
            if (existingSkill == null) {
                leveledSkills.add(skillToAdd);
            }
            else {
                leveledSkills.remove(existingSkill);
                leveledSkills.add(existingSkill.levelUp(skillToAdd));
            }
        }
    }
}
