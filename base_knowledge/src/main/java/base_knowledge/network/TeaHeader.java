package base_knowledge.network;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 自定义消息头
 */
@Data
@AllArgsConstructor
public class TeaHeader implements Serializable {
    private String ip;

    private int port;

    private String name;
}
