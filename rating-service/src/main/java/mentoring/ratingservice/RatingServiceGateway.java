package mentoring.ratingservice;

import io.grpc.stub.StreamObserver;
import mentoring.ratingservice.grpcgateway.RateRequest;
import mentoring.ratingservice.grpcgateway.RateResponse;
import mentoring.ratingservice.grpcgateway.RatingServiceGrpc;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class RatingServiceGateway extends RatingServiceGrpc.RatingServiceImplBase {

    @Override
    public void rate(RateRequest request, StreamObserver<RateResponse> responseObserver) {
        var response = RateResponse
                .newBuilder()
                .setStars(request.getStars())
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
