package base_knowledge.rpc.server;

public abstract class RpcServer {
    int port;

    String protocol;

    RequestHandler requestHandler;

    public RpcServer(int port, String protocol, RequestHandler requestHandler) {
        this.port = port;
        this.protocol = protocol;
        this.requestHandler = requestHandler;
    }

    public abstract void start();

    public abstract void stop();
}
