### 编程技巧
#### 细节
1. 两数交换可以使用异或操作（相同为0，不同为1），比如交换a和b：  
    a = a ^ b;  
    b = a ^ b;  
    a = b ^ a;  
任何数和全0异或都是自身。
    