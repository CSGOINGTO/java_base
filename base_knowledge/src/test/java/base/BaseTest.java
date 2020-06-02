package base;

import org.junit.Test;

import java.text.DecimalFormat;

public class BaseTest {
    @Test
    public void testDouble() {
        DecimalFormat df = new DecimalFormat("#0.00");
        System.out.println(String.valueOf(df.format(Double.parseDouble("1063004405.7599999905") / (1024 * 1024 * 1024))));
    }

    @Test
    public void testGetClass() {
        Number n = 0;
        Class<? extends Number> c = n.getClass();
        System.out.println(c);
    }
}
