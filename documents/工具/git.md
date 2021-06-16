1. 将A项目中的某个分支单独拉出一个B项目
   1. git remote add -f A项目名 A项目的绝对地址
   2. git checkout -b 分支名 A项目名/基于A项目的分支
   3. git push origin 分支名
   4. 将A项目中的分支删除
2. 撤销commit记录
   1. 查询所要回退的git记录号：git log
   2. 强制撤销commit记录：git reset --hard 记录号 
   3. 远程推送：git push --forced 