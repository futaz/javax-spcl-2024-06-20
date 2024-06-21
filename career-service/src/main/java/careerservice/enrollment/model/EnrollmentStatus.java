package careerservice.enrollment.model;

public enum EnrollmentStatus {

    STARTED {
        @Override public EnrollmentStatus complete() { return COMPLETED; }
        @Override public EnrollmentStatus cancel() { return CANCELLED; }
    },

    COMPLETED,

    CANCELLED;

    // HTTP 200
    public EnrollmentStatus complete() {
        throw new IllegalArgumentException("Cannot complete this status");
    }

    public EnrollmentStatus cancel() {
        throw new IllegalArgumentException("Cannot cancel this status");
    }
}
