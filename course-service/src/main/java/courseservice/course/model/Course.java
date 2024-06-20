package courseservice.course.model;

import courseservice.course.dto.CreateCourseCommand;
import courseservice.course.dto.EnrollCommand;
import courseservice.course.dto.EnrollmentResult;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Lob
    private String description;

    @Lob
    private String syllabus;

    @Column(name = "attendee_limit")
    private int limit;

    @ElementCollection
    private List<Long> enrolledEmployees;

    @ElementCollection
    private List<Long> completedEmployees;


    public static Course announceCourse(CreateCourseCommand command) {
        var course = new Course();
        course.setName(command.getName());
        course.setDescription(command.getDescription());
        course.setSyllabus(command.getSyllabus());
        course.setLimit(command.getLimit());
        return course;
    }

    public EnrollmentResult enroll(EnrollCommand command) {
        if (alreadyEnrolled(command)) {
            return EnrollmentResult.successfull();
        }
        if (isFull()) {
            return EnrollmentResult.unsuccessfull();
        }
        else {
            enrolledEmployees.add(command.getEmployeeId());
//            events.add(new EmployeeHasBeenEnrolled(command.getCourseId(), command.getEmployeeId()));
            return EnrollmentResult.successfull();
        }
    }

    private boolean alreadyEnrolled(EnrollCommand command) {
        return enrolledEmployees.contains(command.getEmployeeId());
    }

    private boolean isFull() {
        return enrolledEmployees.size() >= limit;
    }

}
