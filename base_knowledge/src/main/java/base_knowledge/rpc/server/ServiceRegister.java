package base_knowledge.rpc.server;

public interface ServiceRegister {

    void register(ServiceObject serviceObject, String protocol, int port);

    ServiceObject getServiceObject(String name);

}
