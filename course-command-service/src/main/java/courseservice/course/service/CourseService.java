package courseservice.course.service;

import courseservice.course.dto.*;
import courseservice.course.model.Course;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CourseService {

    private CourseRepository courseRepository;

    private CourseMapper courseMapper;

    // Valós életben ezt jobb volna úgy, hogy itt application eventet dobunk és egy gateway osztályban forgatjuk át stream bridge-re
    private StreamBridge streamBridge;

    public CourseView createCourse(CreateCourseCommand command) {
        var course =  Course.announceCourse(command);
        courseRepository.save(course);

        // topic névkonvenciók: my-service-request / -response / -events
        streamBridge.send("course-command-events", courseMapper.toCourseHasBeenCreatedEvent(course));
        return courseMapper.toView(course);
    }

    @Transactional(readOnly = true)
    public CourseDetailsView findCourseById(long id) {
        var course = courseRepository.findById(id).orElseThrow();
        return courseMapper.toDetailsView(course);
    }

    public List<CourseView> findAllCourses() {
        return courseRepository.findAllView();
    }

    @Transactional
    public EnrollmentResult enroll(EnrollCommand command) {
        var course = courseRepository.findById(command.getCourseId()).orElseThrow();
        var result = course.enroll(command);

        return result;
    }


}
