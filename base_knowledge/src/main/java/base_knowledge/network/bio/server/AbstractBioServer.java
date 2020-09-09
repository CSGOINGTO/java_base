package base_knowledge.network.bio.server;

import base_knowledge.network.TeaMessage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
public abstract class AbstractBioServer {

    public static final int DEFAULT_PORT = 8888;

    public static final String DEFAULT_IP = "127.0.0.1";

    public static final int DEFAULT_CLIENT_NUMS = 50;

    int port;

    String ip;

    int clientNums;

    ServerSocket serverSocket;

    InetAddress address;

    volatile LinkedBlockingQueue<Socket> clients = new LinkedBlockingQueue<>();

    AbstractBioServer(String ip, int port, int clientNums) {
        try {
            this.ip = ip;
            this.port = port;
            this.clientNums = clientNums;
            address = InetAddress.getByName(ip);
            serverSocket = new ServerSocket(port, clientNums, address);
            log.info("BIO server has started in {} listening port {}", ip, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove(Socket client) {
        if (client == null) return;
        boolean remove = clients.remove(client);
        if (remove && !client.isClosed()) {
            try {
                client.close();
                log.warn("移除client {} : {}", client.getInetAddress().getHostAddress(), client.getPort());
            } catch (IOException e) {
                e.printStackTrace();
                log.warn("{}", e.getMessage());
            }
        }
    }

    protected abstract void listen() throws IOException;

    protected abstract void provideService(Socket client);

    protected abstract TeaMessage readRequest(Socket client) throws IOException;

    protected abstract void dispatch(TeaMessage message);
}
