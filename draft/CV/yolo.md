

you only look once:unified, real-time object detection





![quicker_82e096d5-3fb4-4dbd-a986-73d8ed8f454a.png](https://s2.loli.net/2022/03/30/vsYdArPIS2lMBwK.png)











1)将一幅图像分成SxS个网格(grid cell),如果某个object的中心落在这个网格中，则这个网格就负责预测这个object。

2)每个网格要预测B个bounding box,每个bounding box除了要预测位置之外，还要附带预测一个confidence值。每个网格还要预测C个类别的分数。





![quicker_009f6375-cf72-45a5-8c9a-71d462a9fca2.png](https://s2.loli.net/2022/03/30/fmPys3jO1xXeaS4.png)

![quicker_e565eedf-5844-4a6d-9f72-9f5d292438b9.png](https://s2.loli.net/2022/04/07/Q28uDgZbSz59Kf3.png)

B=2， 7\*7\*30包含了坐标、置信度、类别结果

![quicker_8554cab6-43ed-43ba-b576-e57ea2ae429a.png](https://s2.loli.net/2022/03/30/vt8dCxbKTMXD3jo.png)







![quicker_bbfc17a0-fd55-46e7-a924-608320874e3c.png](https://s2.loli.net/2022/04/07/wGxvH52dr4uq3jQ.png)



# 后处理





![quicker_d32d2664-49e5-4c50-9f54-c8b28e313f25.png](https://s2.loli.net/2022/03/30/Q8WsNpUHTr7S6xC.png)





![1648653112944](C:\Users\WENCHAO\AppData\Roaming\Typora\typora-user-images\1648653112944.png)

![quicker_bf88c438-8822-4859-b331-f2c50dc798c4.png](https://s2.loli.net/2022/04/07/ASuN6sZTQob98gW.png)





群体小目标效果差：grid cell只预测两个bbox，两个bbox同类别

目标出现新的尺寸或配置

主要错误原因来源于定位不准确+ 









































