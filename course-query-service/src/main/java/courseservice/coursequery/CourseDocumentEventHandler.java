package courseservice.coursequery;

import courseservice.course.CourseHasBeenCreatedEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@AllArgsConstructor
@Slf4j
class CourseDocumentEventHandler {

    private final CourseDocumentRepository repository;
    private final CourseDocumentMapper mapper;

    @Bean
    public Consumer<CourseHasBeenCreatedEvent> eventHandler() {
        return event -> {
            log.info("Event received: {}", event);
            var document = mapper.toCourseDocument(event);
            repository.save(document);
        };
    }
}
