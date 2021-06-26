### 思路
核心就是判断到同一个点的其他点的个数
+ 在每次遍历时，判断到达当前点距离相同的个数
+ 每次遍历完一个点之后，将所有的距离遍历一下，然后将距离个数*（距离个数-1），加到结果中

### 代码
```java
    public int numberOfBoomerangs(int[][] points) {
        if (points.length <= 2) {
            return 0;
        }
        int res = 0;
        Map<Double, Integer> ijMap = new HashMap<>();
        for (int[] point_i : points) {
            for (int[] point_j : points) {
                if (point_i != point_j) {
                    double i_j_len = Math.pow((point_j[0] - point_i[0]), 2) + Math.pow((point_j[1] - point_i[1]), 2);
                    ijMap.put(i_j_len, ijMap.getOrDefault(i_j_len, 0) + 1);
                }
            }
            for (Integer value : ijMap.values()) {
                res += value * (value - 1);
            }
            ijMap.clear();
        }
        return res;
    }
```

### 复杂度
+ 时间复杂度：O(n2)
+ 空间复杂度：O(n)