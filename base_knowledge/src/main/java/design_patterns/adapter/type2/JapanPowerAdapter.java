package design_patterns.adapter.type2;

/**
 * 日本Adapter
 */
public class JapanPowerAdapter implements DC5Adapter {

    public final int voltage = 110;

    @Override
    public boolean support(AC ac) {
        return voltage == ac.outputAc();
    }

    @Override
    public int outputAC5V(AC ac) {
        int outputAc = ac.outputAc();
        int adapterOutput = outputAc / 22;
        System.out.println("使用JapanPowerAdapter变压适配器，输入AC：" + outputAc + "V，输入DC：" + adapterOutput);
        return adapterOutput;
    }
}
