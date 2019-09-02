package sk.banik.examples.kubernetes.grpc.config;

import com.beust.jcommander.Parameter;

public class Arguments {

    @Parameter(names = { "-p", "--port" }, description = "Port to listen on.")
    private Integer port = 8989;

    @Parameter(names = { "-i", "--interface" }, description = "Interface address to bind listening socket.")
    private String host = "0.0.0.0";

    public Arguments() {
    }

    public Arguments(Integer port, String host) {
        this.port = port;
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public String getHost() {
        return host;
    }

}
