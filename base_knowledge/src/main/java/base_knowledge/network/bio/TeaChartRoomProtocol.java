package base_knowledge.network.bio;

import base_knowledge.network.TeaHeader;
import base_knowledge.network.TeaMessage;

import java.io.*;

public class TeaChartRoomProtocol implements Protocol {

    private static volatile TeaChartRoomProtocol teaChartRoomProtocol;

    private TeaChartRoomProtocol() {
    }

    public static TeaChartRoomProtocol getInstance() {
        if (teaChartRoomProtocol != null) {
            return teaChartRoomProtocol;
        }
        synchronized (TeaChartRoomProtocol.class) {
            if (teaChartRoomProtocol == null) {
                teaChartRoomProtocol = new TeaChartRoomProtocol();
            }
        }
        return teaChartRoomProtocol;
    }


    @Override
    public TeaMessage read(InputStream inputStream) throws IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        TeaMessage teaMessage;
        try {
            if ((teaMessage = (TeaMessage) objectInputStream.readObject()) != null) {
                return teaMessage;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void write(OutputStream outputStream, TeaHeader header, String message) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(new TeaMessage(message, header));
    }

    @Override
    public void write(OutputStream outputStream, TeaMessage teaMessage) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(teaMessage);
    }
}
