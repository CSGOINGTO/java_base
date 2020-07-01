package base_knowledge.rpc.server;

import base_knowledge.rpc.discovery.ServiceInfo;
import base_knowledge.zookeeper.MyZkSerializer;
import com.google.gson.Gson;
import org.I0Itec.zkclient.ZkClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class ZkExportServiceRegister extends DefaultServiceRegister implements ServiceRegister{

    private String zkAddress;
    
    private ZkClient zkClient;

    private static final String CENTER_ROOT_PATH = "/Rpc-framework";

    public ZkExportServiceRegister(String zkAddress) {
        this.zkAddress = zkAddress;
        zkClient = new ZkClient(this.zkAddress);
        zkClient.setZkSerializer(new MyZkSerializer());
    }

    @Override
    public void register(ServiceObject serviceObject, String protocol, int port) {
        super.register(serviceObject, protocol, port);
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setAddress("127.0.0.1:" + port);
        serviceInfo.setName(serviceObject.getName());
        serviceInfo.setProtocol(protocol);
        exportService(serviceInfo);
    }

    @Override
    public ServiceObject getServiceObject(String name) {
        return super.getServiceObject(name);
    }

    private void exportService(ServiceInfo serviceInfo) {
        final String serviceName = serviceInfo.getName();
        final String uri = new Gson().toJson(serviceInfo);
        try {
            URLEncoder.encode(uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String servicePath = CENTER_ROOT_PATH + "/" + serviceName + "/service";
        if (!zkClient.exists(servicePath)) {
            zkClient.createPersistent(servicePath, true);
        }
        String uriPath = servicePath + "/" + uri;
        if (zkClient.exists(uriPath)) {
            zkClient.delete(uriPath);
        }
        zkClient.createEphemeral(uriPath);
    }


}
