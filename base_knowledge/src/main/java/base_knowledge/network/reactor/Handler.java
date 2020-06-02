package base_knowledge.network.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class Handler implements Runnable {

    SocketChannel socketChannel;

    SelectionKey sk;
    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);
    static final int READING = 0, SENDING = 1;
    int state = READING;

    public Handler(Selector selector, SocketChannel socketChannel) throws IOException {
        this.socketChannel = socketChannel;
        this.socketChannel.configureBlocking(false);
        this.sk = socketChannel.register(selector, READING);
        this.sk.attach(this);
        this.sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }

    boolean inputIsComplete() {
        /* ... */
        return true;
    }

    boolean outputIsComplete() {
        /* ... */
        return true;
    }

    void process() { /* ... */ }

    void read() throws IOException {
        this.socketChannel.read(input);
        if (inputIsComplete()) {
            process();
            // Normally also do first write now
            state = SENDING;
            this.sk.interestOps(SelectionKey.OP_WRITE);
        }
    }

    void send() throws IOException {
        this.socketChannel.write(output);
        if (outputIsComplete()) {
            sk.cancel();
        }
    }

    @Override
    public void run() {
        try {
            if (state == READING) {
                read();
            } else if (state == SENDING) {
                send();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
