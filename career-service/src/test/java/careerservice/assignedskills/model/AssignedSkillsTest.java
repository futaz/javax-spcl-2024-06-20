package careerservice.assignedskills.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AssignedSkillsTest {


    @Test
    void assignSkills() {
        var assignedSkills = AssignedSkills.firstAssignSkills(1L, List.of(
                new LeveledSkill(1, 4),
                new LeveledSkill(2, 3)
        ));

        assignedSkills.assignSkills(List.of(new LeveledSkill(1, 5)));

        assertThat(assignedSkills.getLeveledSkills())
                .containsExactlyInAnyOrder(new LeveledSkill(1, 5), new LeveledSkill(2, 3));
    }
}
