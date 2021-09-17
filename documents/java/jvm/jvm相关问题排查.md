1. 如何查找到占用cpu最高的Java线程
   1. top：找到占用cpu最高的java进程pid   shift+p按照cpu占用率进行排序
   2. top -Hp pid：找到pid进程中占用cpu最高的线程tid
   3. printf "%x" tid：将tid转化为十六进制
   4. jstack pid > pid.log：将pid进程的线程快照输出到pid.log中，在这个log文件中用tid的十六进制表示搜索这个线程

