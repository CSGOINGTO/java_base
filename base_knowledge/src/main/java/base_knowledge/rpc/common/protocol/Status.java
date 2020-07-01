package base_knowledge.rpc.common.protocol;

/**
 * 消息状态
 */
public enum Status {
    SUCCESS(200, "SUCCESS"), ERROR(500, "ERROR"), NOT_FOUND(404, "NOT FOUND");

    private int code;

    private String message;

    private Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
