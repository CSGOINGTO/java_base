package design_patterns.adapter.type2;

/**
 * China适配器
 */
public class ChinaPowerAdapter implements DC5Adapter {

    public final int voltage = 220;

    @Override
    public boolean support(AC ac) {
        return voltage == ac.outputAc();
    }

    @Override
    public int outputAC5V(AC ac) {
        int outputAc = ac.outputAc();
        int adapterOutput = outputAc / 44;
        System.out.println("使用ChinaPowerAdapter变压适配器，输入AC：" + outputAc + "V, 输出DC：" + adapterOutput);
        return adapterOutput;
    }
}
