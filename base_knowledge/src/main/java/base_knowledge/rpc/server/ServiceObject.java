package base_knowledge.rpc.server;

import lombok.Data;

@Data
public class ServiceObject {

    public ServiceObject(String name, Class<?> interf, Object obj) {
        this.name = name;
        this.interf = interf;
        this.obj = obj;
    }

    private String name;

    private Class<?> interf;

    private Object obj;
}
