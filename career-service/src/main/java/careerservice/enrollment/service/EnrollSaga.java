package careerservice.enrollment.service;

import careerservice.enrollment.EnrollResponse;
import careerservice.enrollment.model.EnrollCommand;
import careerservice.enrollment.view.EnrollmentView;
import careerservice.gateway.CourseGateway;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component //TODO @Saga custom annotation
@AllArgsConstructor
public class EnrollSaga {

    private final EnrollmentService enrollmentService;
    private final CourseGateway gateway;

    @Transactional
    public EnrollmentView enroll(EnrollCommand command) {
        // Saját magunknál lerögzítjük a jelentkezést
        var view = enrollmentService.enroll(command);

        // Szólunk a courses rendszernek, hogy felhasználunk a jelentkezési limitből.
        gateway.sendEnrollRequest(command);

        return view;
    }

    @EventListener
    public void handleEnrollResponse(EnrollResponse response) {
        //TODO felcserélhetők a complete/cancel paraméterei -> value object
        switch (response.result()) {
            case SUCCESS -> enrollmentService.complete(response.courseId(), response.courseId());
            case NO_SPACE_LEFT -> enrollmentService.cancel(response.courseId(), response.courseId());
            default -> throw new IllegalStateException("Unexpected value: " + response.result());
        }
    }
}
