package sk.banik.examples.kubernetes.grpc;

import com.beust.jcommander.JCommander;
import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;
import java.io.IOException;
import java.net.InetSocketAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sk.banik.examples.kubernetes.grpc.config.Arguments;

public class ServiceBackend {

    final private static Logger LOG = LoggerFactory.getLogger(ServiceBackend.class);

    public static void main(String[] args) throws InterruptedException, IOException {
        LOG.info("SimpleService Backend init ...");
        Arguments arguments = new Arguments();
        JCommander.newBuilder()
                .addObject(arguments)
                .build()
                .parse(args);

        final SimpleServiceImpl simpleService = new SimpleServiceImpl();

        Server server = NettyServerBuilder.forAddress(
                new InetSocketAddress(arguments.getHost(), arguments.getPort()))
                .addService(simpleService)
                .build();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                LOG.info("SimpleService Backend shutting down ...");
                server.shutdown();
                LOG.info("SimpleService Backend shutdown.");
            }
        });

        LOG.info("SimpleService Backend, listening on {}:{}", arguments.getHost(), arguments.getPort());
        server.start();
        server.awaitTermination();
    }

}
