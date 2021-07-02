package design_patterns.adapter.type2;

import java.util.LinkedList;
import java.util.List;

public class Main {

    private List<DC5Adapter> adapters = new LinkedList<>();

    {
        adapters.add(new ChinaPowerAdapter());
        adapters.add(new JapanPowerAdapter());
    }

    public static void main(String[] args) {
        Main main = new Main();
        // 1. 获取老的功能接口的实现
        AC ac220 = new AC220();
        // 2. 获取老的功能接口实现的适配器
        DC5Adapter powerAdapter = main.getPowerAdapter(ac220);
        // 3. 调用对应适配器的适配方法
        powerAdapter.outputAC5V(ac220);

        AC ac110 = new AC110();
        powerAdapter = main.getPowerAdapter(ac110);
        powerAdapter.outputAC5V(ac110);
    }

    public DC5Adapter getPowerAdapter(AC ac) {
        DC5Adapter adapter = null;
        for (DC5Adapter dc5Adapter : adapters) {
            if (dc5Adapter.support(ac)) {
                adapter = dc5Adapter;
                break;
            }
        }
        if (adapter == null) throw new IllegalArgumentException("没有找到合适的变压适配器！");
        return adapter;
    }
}
