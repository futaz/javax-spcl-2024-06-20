package courseservice.coursequery;

import courseservice.course.CourseHasBeenCreatedEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
class CourseDocumentEventHandler {

    private final CourseDocumentRepository repository;
    private final CourseDocumentMapper mapper;

    @EventListener
    void handleCourseHasBeenCreatedEvent(CourseHasBeenCreatedEvent event) {
        var document = mapper.toCourseDocument(event);
        repository.save(document);
    }
}
