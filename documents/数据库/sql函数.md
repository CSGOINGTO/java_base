一、系统函数

1. 窗口函数

   1. **排名是连续且不重复的：row_number() over(partition by row1 order by row2)**

      order by row2：根据row2进行排序

      partition by row1：根据row1分组后再进行排序

      ![row_number over](..\image\数据库\row_number() over.png)

   2. **值相同的序号相同，排名不连续：rank() over(partition by row1 order by row2)**

      ![rank() over](..\image\数据库\rank() over.png)

   3. **值相同的序号相同，且排名连续：dense_rank() over(partition by row1 order by row2)**

      ![dense_rank() over](..\image\数据库\dense_rank() over.png)

   4. **将所有记录分成group_num个组：ntile(group_num) over(order by row1)**

      ![ntile(group_num) over](..\image\数据库\ntile(group_num) over.png)
   
2. 时间函数

   1. `datediff(date1, date2)`，date1和date2时间**天数的差值，如果date1在date2之前，则返回为正；date1在date2之后，返回为负** 

      ```mysql
      select datediff('2021-01-02 12:00:10', '2021-01-01 12:00:00'); // 1
      ```

   2. `date_add(date, 时间)`，在date的基础上加上一定的时间

      ```mysql
      SELECT DATE_ADD('2021-01-02 12:00:10',INTERVAL 1 DAY); // 2021-01-03 12:00:10
      ```

   3. `timestampdiff(时间类型，date1, date2)`，date1和date2在**以时间类型为单位上的时间差值，如果date1在date2之前，则返回负；date1在date2之后，返回正**

      ```mysql
      select timestampdiff(second , '2021-01-01 12:00:10', '2021-01-01 12:00:00'); // -10
      ```

二、自定义函数

1. 自定义函数的语法

   ```sql
   Create function 函数名（参数）
   Returns 返回值数据类型
   [with {Encryption | Schemabinding }]
   [as]
   begin
   SQL语句(必须有return 变量或值)
   End
   ```

   with选项为

   + `Encryption`时，表示对函数体进行加密
   + `Schemabinding`时，表示需要将创建的函数与引用的数据库绑定

2. 自定义函数分为：标量值函数或表值函数

   + 标量值函数

     如果RETURNS子句指定一种标量数据类型，则函数为标量值函数

   + 表值函数

     如果RETURNS子句指定table，则函数为表值函数

     + 内嵌表值函数（行内函数）

       如果RETURNS子句指定的Table不附带列的列表，则该函数为内嵌表值函数

     + 多语句函数

       如果RETURNS子句指定的Table类型带有列及其数据类型，则函数是多语句值函数

