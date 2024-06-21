package courseservice.course.controller;

import courseservice.course.dto.*;
import courseservice.course.service.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
public class CourseController {

    private CourseService courseService;

    @GetMapping
    public List<CourseView> findAllCourses() {
        return courseService.findAllCourses();
    }

    @GetMapping("/{id}")
    public CourseDetailsView findCourseById(@PathVariable("id") long id) {
        return courseService.findCourseById(id);
    }

    @PostMapping // nem idempotens
    @ResponseStatus(HttpStatus.CREATED)
    public CourseView create(@RequestBody CreateCourseCommand command) {
        return courseService.createCourse(command);
    }

    @PostMapping("/{id}/enrollments")
    @ResponseStatus(HttpStatus.CREATED)
    public EnrollmentResult enroll(@PathVariable("id") long id, @RequestBody EnrollCommand command) {
        if (command.getCourseId() != id) {
            throw new IllegalArgumentException("Ids not match");
        }
//        command.setCourseId(id);
        return courseService.enroll(command);
    }

}
