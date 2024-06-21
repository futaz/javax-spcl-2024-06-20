package courseservice.course;

// Eventeknek jellemzően ott kell lenni, ahol dobjuk
public record CourseHasBeenCreatedEvent(long id, String name, String description, String syllabus) {
}
