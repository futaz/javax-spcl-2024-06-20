package careerservice.assignedskill.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record LeveledSkill(long skillId, int level) {

    public LeveledSkill levelUp(LeveledSkill newLevel) {
        if (skillId() != newLevel.skillId()) {
            throw new IllegalArgumentException("Skill id mismatch");
        }

        if (level() < newLevel.level()) {
            return newLevel;
        }

        return this;
    }
}
