package leetcode.plusOne_66;

import org.junit.Test;

public class SolutionTest {

    /**
     * 测试System.arrayCopy(Object src,  int  srcPos,
     *                      Object dest, int destPos,
     *                      int length);
     */
    @Test
    public void testArrayCopy(){
        int[] src = new int[] {1,2,3,4,5};
        int[] desc = new int[3];
        // 复制自身（把3从src中删除）
        System.arraycopy(src, 3, src, 2, 2);
        for (int num : src) {
            System.out.println(num);
        }
        System.out.println("----------------------------------");
        // 复制src中下标为3,4,5的元素到desc数组中
        System.arraycopy(src, 2, desc, 0, desc.length);
        for (int num : desc) {
            System.out.println(num);
        }
    }
}