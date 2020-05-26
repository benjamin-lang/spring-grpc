package de.grid.springgrpc.adapter.grpc;

import de.grid.grpcdemo.adapter.grpc.service.CalculatePrimeFactorsRequest;
import de.grid.grpcdemo.adapter.grpc.service.CalculatePrimeFactorsResponse;
import de.grid.grpcdemo.adapter.grpc.service.CalculatorServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GRpcService
public class CalculatorServiceGrpcImpl extends CalculatorServiceGrpc.CalculatorServiceImplBase
{
    private final Logger LOGGER = LoggerFactory.getLogger(CalculatorServiceGrpcImpl.class);

    @Override
    public void calculatePrimeFactors(CalculatePrimeFactorsRequest request, StreamObserver<CalculatePrimeFactorsResponse> responseObserver)
    {
        LOGGER.info("calculate prime factors ...");

        int input = request.getInput();
        int k = 2;

        try
        {
            while (input > 1)
            {
                if (input % k == 0)
                {
                    CalculatePrimeFactorsResponse.Builder builder = CalculatePrimeFactorsResponse.newBuilder();
                    LOGGER.info(String.valueOf(k));
                    responseObserver.onNext(builder.setPrimeFactor(k).build());
                    input = input / k;
                    Thread.sleep(1000);
                } else
                    k = k + 1;
            }
        } catch (InterruptedException exception)
        {
            LOGGER.error("Thread interrupted.", exception);
        } finally
        {
            responseObserver.onCompleted();
        }


        LOGGER.info("prime factors calculated.");
    }
}
