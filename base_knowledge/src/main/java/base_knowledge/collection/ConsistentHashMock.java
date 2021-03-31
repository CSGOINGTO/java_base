package base_knowledge.collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class ConsistentHashMock {
    /**
     * 假设我们一共初始化有8个节点(可以是ip, 就理解为ip吧);
     * 把 1024个虚拟节点跟 8个资源节点相对应
     */
    public static Map<Integer, String> realNodeMap = new HashMap<>();
    public static int V_NODES = 1024; // 假设我们的环上有1024个虚拟节点
    static TreeMap<Integer, String> virtualNodeMap = new TreeMap<>();
    private static final Integer REAL_NODE_COUNT = 8;

    static {
        realNodeMap.put(0, "node_0");
        realNodeMap.put(1, "node_1");
        realNodeMap.put(2, "node_2");
        realNodeMap.put(3, "node_3");
        realNodeMap.put(4, "node_4");
        realNodeMap.put(5, "node_5");
        realNodeMap.put(6, "node_6");
        realNodeMap.put(7, "node_7");

        for (Integer i = 0; i < V_NODES; i++) {
            // 每个虚拟节点跟其取模的余数的 nodeMap 中的key相对应;
            // 下面删除虚拟节点的时候, 就可以根据取模规则来删除 TreeMap中的节点了;
            virtualNodeMap.put(i, realNodeMap.get(i % REAL_NODE_COUNT));
        }
    }


    /**
     * 输入一个id
     *
     * @param value
     * @return
     */
    public static String getRealServerNode(String value) {
        // 1. 传递来一个字符串, 得到它的hash值
        Integer vnode = value.hashCode() % 1024;
        // 2.找到对应节点最近的key的节点值
        String realNode = virtualNodeMap.ceilingEntry(vnode).getValue();

        return realNode;
    }

    /**
     * 模拟删掉一个物理可用资源节点, 其他资源可以返回其他节点
     *
     * @param nodeName
     */
    public static void dropBadNode(String nodeName) {
        int nodek = -1;
        // 1. 遍历 nodeMap 找到故障节点 nodeName对应的key;
        for (Map.Entry<Integer, String> entry : realNodeMap.entrySet()) {
            if (nodeName.equalsIgnoreCase(entry.getValue())) {
                nodek = entry.getKey();
                break;
            }
        }
        if (nodek == -1) {
            System.err.println(nodeName + "在真实资源节点中无法找到, 放弃删除虚拟节点!");
            return;
        }

        // 2. 根据故障节点的 key, 对应删除所有 chMap中的虚拟节点
        Iterator<Map.Entry<Integer, String>> iter = virtualNodeMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, String> entry = iter.next();
            int key = entry.getKey();
            String value = entry.getValue();
            if (key % REAL_NODE_COUNT == nodek) {
                System.out.println("删除节点虚拟节点: [" + key + " = " + value + "]");
                iter.remove();
            }
        }
    }

    public static void main(String[] args) {
        // 1. 一个字符串请求(比如请求字符串存储到8个节点中的某个实际节点);
        String requestValue = "hello";
        // 2. 打印虚拟节点和真实节点的对应关系;
        System.out.println(virtualNodeMap);
        // 3. 核心: 传入请求信息, 返回实际调用的节点信息
        System.out.println(getRealServerNode(requestValue));
        // 4. 删除某个虚拟节点后
        System.out.println("==========删除 node_2 之后: ================");
        dropBadNode("node_2");
        System.out.println("===============删除之后的虚拟节点map: ===========");
        System.out.println(virtualNodeMap);
        System.out.println("==============删除之后, 获取节点的真正node节点对应者: ");
        System.out.println(getRealServerNode(requestValue));

    }
}
