package base_knowledge.network.bio.server;

import base_knowledge.network.TeaMessage;
import base_knowledge.network.bio.TeaChartRoomProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * BIO--Server
 */
public class BioServer extends AbstractBioServer {

    private static Logger log = LoggerFactory.getLogger(BioServer.class);


    private ExecutorService threadPool = Executors.newCachedThreadPool();

    BioServer(String ip, int port, int clientNums) {
        super(ip, port, clientNums);
    }

    /**
     * 创建一个默认的聊天室服务端
     *
     * @return
     * @throws IOException
     */
    public static BioServer createServerByDefault() throws IOException {
        return new BioServer(DEFAULT_IP, DEFAULT_PORT, DEFAULT_CLIENT_NUMS);
    }

    @Override
    protected void listen() throws IOException {
        while (true) {
            Socket client = serverSocket.accept();
            clients.add(client);
            System.out.println(client.getInetAddress().getHostAddress() + "加入了聊天室");
            System.out.println("当前在线人数：" + clients.size());
            log.info("{}加入了聊天室", client.getInetAddress().getHostAddress());
            log.info("当前在线人数：{}", clients.size());
            threadPool.execute(() -> provideService(client));
        }
    }

    @Override
    protected void provideService(Socket client) {
        while (true) {
            TeaMessage message = null;
            try {
                message = readRequest(client);
                if (clients.size() == 0) break;
                else dispatch(message);
            } catch (IOException e) {
                log.warn(e.getMessage());
                remove(client);
                break;
            }
        }
    }

    @Override
    protected TeaMessage readRequest(Socket client) throws IOException {
        return TeaChartRoomProtocol.getInstance().read(client.getInputStream());
    }

    @Override
    protected void dispatch(TeaMessage message) {
        for (Socket client : clients) {
            try {
                System.out.println("转发消息至" + message.getTeaHeader().toString());
                log.info("转发消息至{}", message.getTeaHeader().toString());
                TeaChartRoomProtocol.getInstance().write(client.getOutputStream(), message);
            } catch (IOException e) {
                e.printStackTrace();
                log.info(e.getMessage());
                remove(client);
            }
        }
    }

    public static void main(String[] args) {
        try {
            BioServer server = BioServer.createServerByDefault();
            server.listen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
