package careerservice.enrollment.model;

import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private long employeeId;

    private long courseId;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    private Enrollment() {
    }

    public static Enrollment enrollToCourse(EnrollCommand command) {
        var enrollment = new Enrollment();
        enrollment.employeeId = command.employeeId();
        enrollment.courseId = command.courseId();
        enrollment.status = EnrollmentStatus.STARTED;
        return enrollment;
    }

    public void complete() {
        status = status.complete();
    }

    public void cancel() {
        status = status.cancel();
    }
}
