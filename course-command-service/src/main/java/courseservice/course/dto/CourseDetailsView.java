package courseservice.course.dto;

import lombok.Data;

import java.util.List;

@Data
public class CourseDetailsView {

    private Long id;

    private String name;

    private String description;

    private String syllabus;

    private int limit;

    private List<Long> enrolledEmployees;

    private List<Long> completedEmployees;

}
