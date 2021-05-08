```pip install jupyterthemes```
查看可用的 Jupyter 主题
```jt -l```
更换 Jupyter 主题
```jt -t onedork -f fira -fs 13 -cellw 90% -ofs 11 -dfs 11 -T -T -m 10```
-t 主题 -f(字体)  -fs(字体大小) -cellw(占屏比或宽度)  -ofs(输出段的字号)  -T(显示工具栏)  -T(显示自己主机名)

 恢复 Jupyter 默认风格
```jt -r```

画图时
```
from jupyterthemes import jtplot
jtplot.style() 
```

![](https://upload-images.jianshu.io/upload_images/18339009-8515131bfec37dc8.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

