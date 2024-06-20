package careerservice.assignedskills.model;

import jakarta.persistence.Embeddable;

@Embeddable
public record LeveledSkill(long skillId, int level) {

    public LeveledSkill levelUp(LeveledSkill another) {
        if (this.skillId != another.skillId) {
            throw new IllegalArgumentException("Different skills");
        }
        if (this.level < another.level) {
            return another;
        } else {
            return this;
        }
    }
}
