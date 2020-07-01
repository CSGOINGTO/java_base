package base;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class SetTest {

    @Test
    public void testSet(){
        Set<Table> set = Sets.newHashSet();
        Table table = new Table();
        Table table1 = new Table();
        table.setX(0);
        table1.setX(0);
        table.setY("x");
        table1.setY("y");
        set.add(table);

        if (set.contains(table1)) {
            set.removeIf(table2 -> table2.getX() == table1.getX());
        }
        set.add(table1);
        System.out.println(table.equals(table1));
        System.out.println(table.hashCode());
        System.out.println(table1.hashCode());
        System.out.println(table == table1);
        System.out.println(set.size());
        System.out.println(Arrays.toString(set.toArray()));
        set.forEach(System.out::println);
    }
}

class Table{
    private int x;

    private String y;

    public Table() {
    }

    public Table(int x, String y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(String y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public String getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Table table = (Table) o;
        return x == table.x;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x);
    }

    @Override
    public String toString() {
        return "Table{" +
                "x=" + x +
                ", y='" + y + '\'' +
                '}';
    }
}
