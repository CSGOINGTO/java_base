package base_knowledge.rpc.discovery.impl;

import base_knowledge.rpc.discovery.ServiceInfo;
import base_knowledge.rpc.discovery.ServiceInfoDiscoverer;
import base_knowledge.rpc.utils.PropertiesUtils;
import base_knowledge.zookeeper.MyZkSerializer;
import com.google.gson.Gson;
import org.I0Itec.zkclient.ZkClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class ZookeeperServiceInfoDiscoverer implements ServiceInfoDiscoverer {

    ZkClient zkClient;

    private static final String centerRootPath = "/Rpc-framework";

    public ZookeeperServiceInfoDiscoverer() {
        String addr = PropertiesUtils.getProperties("zk.address");
        zkClient = new ZkClient(addr);
        zkClient.setZkSerializer(new MyZkSerializer());
    }

    @Override
    public List<ServiceInfo> getServiceInfo(String name) {
        String servicePath = centerRootPath + "/" + name + "/service";
        final List<String> childrenList = zkClient.getChildren(servicePath);
        List<ServiceInfo> serviceInfoList = new ArrayList<>();
        Gson gson = new Gson();
        for (String children : childrenList) {
            try {
                final String decode = URLDecoder.decode(children, "UTF-8");
                final ServiceInfo serviceInfo = gson.fromJson(decode, ServiceInfo.class);
                serviceInfoList.add(serviceInfo);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return serviceInfoList;
    }
}
