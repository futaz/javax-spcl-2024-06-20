package careerservice.enrollment;

public record EnrollResponse(long employeeId, long courseId, Result result) {

    public enum Result {
        SUCCESS, NO_SPACE_LEFT
    }
}
