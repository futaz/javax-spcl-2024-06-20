package careerservice.assignedskill.model;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class AssignedSkillsTest {

    @Test
    void assingSkills() {
        final AssignedSkills assignedSkills = AssignedSkills.firstAssignedSkills(1L, List.of(
                new LeveledSkill(1, 4),
                new LeveledSkill(2, 3)
        ));

        assignedSkills.assignSkills(List.of(new LeveledSkill(1, 5)));

        Assertions.assertThat(assignedSkills.getLeveledSkills())
                .containsExactlyInAnyOrder(
                        new LeveledSkill(2, 3),
                        new LeveledSkill(1, 5)
                );
    }
}
