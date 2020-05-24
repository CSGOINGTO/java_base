package base_knowledge.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

public class MasterSelection {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("139.199.67.139:2181", 40000, watchedEvent -> {
            if (watchedEvent.getType() == Watcher.Event.EventType.NodeCreated){
                System.out.println(watchedEvent.getPath());
            }
        });

        Thread.sleep(80000);
        final String helloWorld = "helloWorld";
        zooKeeper.create("/helloWorld", helloWorld.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        final byte[] data = zooKeeper.getData("/helloWorld", null, null);
        System.out.println(new String(data));
    }
}
