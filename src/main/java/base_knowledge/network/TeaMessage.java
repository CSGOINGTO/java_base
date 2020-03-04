package base_knowledge.network;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 聊天消息
 */
@Data
@AllArgsConstructor
public class TeaMessage implements Serializable {
    private String msg;

    private TeaHeader teaHeader;
}
