package courseservice.course.service;

import courseservice.course.dto.CourseView;
import courseservice.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select new courseservice.course.dto.CourseView(c.id, c.name, c.limit) from Course c")
    List<CourseView> findAllView();

}
