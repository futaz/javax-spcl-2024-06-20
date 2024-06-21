package courseservice.course.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResult {

    public enum Success {SUCCESSFULL, UNSUCCESSFULL}

    private Success success;

    public static EnrollmentResult successfull() {
        return new EnrollmentResult(Success.SUCCESSFULL);
    }

    public static EnrollmentResult unsuccessfull() {
        return new EnrollmentResult(Success.UNSUCCESSFULL);
    }
}
