### 编程技巧
#### 细节
1. 两数交换可以使用异或操作（相同为0，不同为1），比如交换a和b：  
    a = a ^ b;  
    b = a ^ b;  
    a = b ^ a;  
任何数和全0异或都是自身。

2. 二重数组
```java
    int[][] max = new int[2][10];
```
相当于有10个长度为2的int数组。 
对应的矩阵为10行2列。  
当对行进行遍历时：
```java
for(int i = 0; i < 10; i++) {
    for (int j = 0; j < 2; j++) {
        System.out.println(max[j][i]);
    }
}  
```
当对列进行遍历时：
```java
for(int i = 0; i < 2; i++) {
    for (int j = 0; j < 10; j++) {
        System.out.println(max[i][j]);
    }
}
```
    