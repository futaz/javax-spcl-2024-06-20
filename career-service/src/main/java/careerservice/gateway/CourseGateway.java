package careerservice.gateway;

import careerservice.enrollment.model.EnrollCommand;
import lombok.AllArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CourseGateway {

    private final StreamBridge streamBridge;

    public void sendEnrollRequest(EnrollCommand command) {
        // Mivel nem publish-subscribe van, hiszen orkesztrálunk, ezért a címzett bemenő topicját nevezzük meg, vagyis ez egy request-response típusú üzenetküldés
        streamBridge.send("courses-request", command);
    }
}
