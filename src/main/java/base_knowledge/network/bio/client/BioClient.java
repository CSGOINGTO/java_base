package base_knowledge.network.bio.client;

import base_knowledge.network.TeaHeader;
import base_knowledge.network.TeaMessage;
import base_knowledge.network.bio.TeaChartRoomProtocol;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * BIO--Client
 */
@Slf4j
public class BioClient {

    private final String ip;

    private final int port;

    private final String name;

    private final TeaHeader header;

    private Executor pool = Executors.newCachedThreadPool();

    private Socket socket;

    public BioClient(String ip, int port, String name) throws IOException {
        this.ip = ip;
        this.port = port;
        this.name = name;
        this.header = new TeaHeader(ip, port, name);
        socket = new Socket(ip, port);
        log.info("连接成功: {}:{}", ip, port);
    }

    public void sendMsg(String msg) throws IOException {
        //自定义协议去发送消息
        TeaChartRoomProtocol.getInstance().write(socket.getOutputStream(), header, msg);
    }

    public TeaMessage getResponse() throws IOException {
        //自定义协议去接收消息，返回消息对象
        return TeaChartRoomProtocol.getInstance().read(socket.getInputStream());
    }

    public void clear() {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void listen12n(MSGCallback callback) {
        pool.execute(() -> {
            while (true) {
                try {
                    TeaMessage message = getResponse();
                    //该回调面向用户，提供服务端发送的消息
                    callback.message(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    clear();
                    return;
                }
            }
        });

    }

    /**
     * 回调接口
     */
    public interface MSGCallback {
        void message(TeaMessage message);
    }

    public static void main(String[] args) {
        try {
            BioClient client = new BioClient("127.0.0.1", 8888, "你的");
            client.listen12n(System.out::println);
            Scanner in = new Scanner(System.in);
            String message;
            while (!(message = in.nextLine()).equals("再见")) {
                client.sendMsg(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


