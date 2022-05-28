# V1

**you only look once:unified, real-time object detection**

## 思想



1)将一幅图像分成SxS个网格(grid cell),如果某个object的中心落在这个网格中，则这个网格就负责预测这个object。

2)每个网格要预测B个bounding box,每个bounding box除了要预测位置之外，还要附带预测一个confidence值。每个网格还要预测C个类别的分数。

![quicker_009f6375-cf72-45a5-8c9a-71d462a9fca2.png](https://s2.loli.net/2022/03/30/fmPys3jO1xXeaS4.png)

![quicker_e565eedf-5844-4a6d-9f72-9f5d292438b9.png](https://s2.loli.net/2022/04/07/Q28uDgZbSz59Kf3.png)

B=2， 7\*7\*30包含了坐标、置信度、类别结果

![quicker_8554cab6-43ed-43ba-b576-e57ea2ae429a.png](https://s2.loli.net/2022/03/30/vt8dCxbKTMXD3jo.png)



## 网络结构

![quicker_bbfc17a0-fd55-46e7-a924-608320874e3c.png](https://s2.loli.net/2022/04/07/wGxvH52dr4uq3jQ.png)





![quicker_ddc8f262-fece-4ee4-aeb9-276fb623a62d.png](https://s2.loli.net/2022/05/08/sl2un1bEIVZgktB.png)

## 后处理





![quicker_d32d2664-49e5-4c50-9f54-c8b28e313f25.png](https://s2.loli.net/2022/03/30/Q8WsNpUHTr7S6xC.png)

## 损失函数



![1648653112944](C:\Users\WENCHAO\AppData\Roaming\Typora\typora-user-images\1648653112944.png)

宽和高开根号，是因为偏移相同的距离，对小目标影响更大

![quicker_fe20384e-9a44-4c4b-b1d2-8ac93ab34f7b.png](https://s2.loli.net/2022/05/08/MdCFshx5IwDUYty.png)



## 缺陷

![quicker_ea58d9a6-762d-431b-84da-54c0e2cabe7a.png](https://s2.loli.net/2022/05/08/uASTXq365lkvEjF.png)

群体小目标效果差：grid cell只预测两个bbox，两个bbox同类别

目标出现新的尺寸或配置

主要错误原因来源于定位不准确



# v2



Yolo9000: Better,Faster,Stronger



YOLOv2中的各种尝试

## Batch Normalization

![quicker_a21a2608-2876-4a16-80b6-8d6a59448be0.png](https://s2.loli.net/2022/05/08/lV83pBA7FsOHE6G.png)

## High Resolution Classifier

![quicker_cc2c9f30-c06a-4066-8cad-1c85007659c8.png](https://s2.loli.net/2022/05/08/7xGFfyglPTtpber.png)

## Convolutional With Anchor Boxes

![quicker_ebf88288-5a3f-4e1d-8ac0-1acd1551802a.png](https://s2.loli.net/2022/05/08/OQAohyxdTgPZ1Ir.png)

## Dimension Clusters

![quicker_2940e48e-7b55-4a37-89cd-55562f7f9846.png](https://s2.loli.net/2022/05/08/jSmZx9cCOErkywX.png)

## Direct location prediction

![quicker_eddfe836-9897-4e0a-ae21-04ae470c5e8c.png](https://s2.loli.net/2022/05/08/642gsR7lVd9nf1x.png)

## Fine-Grained Features

![1652019960723](C:\Users\WENCHAO\AppData\Roaming\Typora\typora-user-images\1652019960723.png)

 

<img src='https://s2.loli.net/2022/05/08/e3TdWYOHxlI2QDA.png' title='quicker_e58852f3-aa65-4471-af7e-881bbeae12b1.png' />

## Multi-Scale Training

![quicker_ee5ea3b8-9757-4c4e-ac61-4d1be128c63e.png](https://s2.loli.net/2022/05/08/dPnTxls9EhVo4FM.png)





## 模型结构



![quicker_e071e5e8-99ff-40ab-9c03-b511a0572d92.png](https://s2.loli.net/2022/05/08/cENjmszArXtovaY.png)

![quicker_4ff8f074-ade7-42a8-b122-cb0c80d438d9.png](https://s2.loli.net/2022/05/08/HnX14fM7NuaCwmV.png)







![quicker_be167748-486f-4193-a962-45a55c4fa985.png](https://s2.loli.net/2022/05/08/zmoA9DNHcVFRkeg.png)



# V3

[YOLOv3](https://so.csdn.net/so/search?q=YOLOv3&spm=1001.2101.3001.7020): An Incremental Improvement

## 模型结构

有53个convolutional layers，所以叫做Darknet-53$$（ 2 + 1*2 + 1 + 2*2 + 1 + 8*2 + 1 + 8*2 + 1 + 4*2 + 1 = 53 $$按照顺序数，最后的Connected是全连接层也算卷积层，一共53个）。卷积的strides默认为（1，1），padding默认为same，当strides为（2，2）时padding为valid。上图是以输入图像256 x 256进行预训练来进行介绍的，常用的尺寸是416 x 416，都是32的倍数。

![quicker_63fb0c5f-b01b-48ea-8421-3db82da2b5a3.png](https://s2.loli.net/2022/05/08/Q1AWzDPpg4sr2jC.png)





**原Darknet53中的尺寸是在图片分类训练集上训练的，所以输入的图像尺寸是256x256，下图是以YOLO v3 416模型进行绘制的，所以输入的尺寸是416x416，预测的三个特征层大小分别是52，26，13。**

![quicker_1e5320d6-33b2-4bdf-8484-555e43228621.png](https://s2.loli.net/2022/05/08/6vFiPaYE5kXMQDZ.png)

在上图中我们能够很清晰的看到三个预测层分别来自的什么地方，以及Concatenate层与哪个层进行深度方向拼接（FPN对应维度上相加）。**注意Convolutional是指Conv2d+BN+LeakyReLU，和Darknet53图中的一样，而生成预测结果的最后三层都只是Conv2d。**



## 目标边界框的预测



![quicker_07c7df41-0dfc-4598-ac25-dfff4392091f.png](https://s2.loli.net/2022/05/08/rPwdmlf4nOvXDjU.png)

## 损失函数的计算

![1652025879465](C:\Users\WENCHAO\AppData\Roaming\Typora\typora-user-images\1652025879465.png)

# V3 SPP

Mosaic 图像增强

- 增加数据的多样性
- 增加目标个数
- BN能一次性统计多张图片的参数





## 模型结构

第一个预测特征层添加了SPP（Spatial Pyramid Pooling）模块，实现了不同尺度的特征融合
注意：这里的SPP和SPPnet中的SPP结构不一样



![quicker_fe49099b-15ac-424c-9b14-b2762dd28151.png](https://s2.loli.net/2022/05/10/hIOFb3QvTr712ge.png)



Iou

优点
1.能够更好的反应重合程度
2.具有尺度不变性
缺点
1.当不相交时loss为0



![quicker_f2a1c960-af71-468b-bfc6-c196f271d4d4.png](https://s2.loli.net/2022/05/10/cMQ7X5CHvRzV3Td.png)



 ![quicker_d779df84-222e-4f51-87b6-e2e1e3583caf.png](https://s2.loli.net/2022/05/10/dAV71qUPY5lxB4c.png)



![quicker_bcd1a1aa-c1da-49de-b991-af043f653965.png](https://s2.loli.net/2022/05/10/kSZh1FtTiXdPD3n.png)

![quicker_2c053b0c-571c-4387-ba12-401ae87fc02a.png](https://s2.loli.net/2022/05/10/ON9KcadtPpgiB2m.png)







![quicker_977a2f54-dd1e-4b67-b1f9-466469773554.png](https://s2.loli.net/2022/05/10/E3Nco6ZrItFwkXH.png)

![quicker_e2f1075b-de9f-4f15-8a23-3422ade2c0bf.png](https://s2.loli.net/2022/05/10/189pHGQJjEdsLgn.png)

![quicker_06f8056f-fbea-4bdd-a52d-8953becc5e3e.png](https://s2.loli.net/2022/05/10/lyKXE8CjkSIPe7o.png)





# V4

[YOLOv4](https://so.csdn.net/so/search?q=YOLOv4&spm=1001.2101.3001.7020): Optimal Speed and Accuracy of Object Detection

## 网络结构

在论文3.4章节中介绍了YOLOv4网络的具体结构：

Backbone: CSPDarknet53
Neck: SPP，PAN
Head: YOLOv3
相比之前的YOLOv3，改进了下Backbone，在Darknet53中引入了CSP模块（来自CSPNet）。在Neck部分，采用了SPP模块（Ultralytics版的YOLOv3 SPP就使用到了）以及PAN模块（来自PANet）。Head部分没变还是原来的检测头。

相比之前的`YOLOv3`，改进了下Backbone，在`Darknet53`中引入了`CSP`模块（来自`CSPNet`）。在Neck部分，采用了`SPP`模块（`Ultralytics`版的`YOLOv3 SPP`就使用到了）以及`PAN`模块（来自`PANet`）。Head部分没变还是原来的检测头。



![quicker_8cde5fd8-3a9f-4f86-9c73-c7ce21b21452.png](https://s2.loli.net/2022/05/09/zFAsxfuporB3hX4.png)





## 优化策略

有关训练Backbone时采用的优化策略就不讲了有兴趣自己看下论文的`4.2`章节，这里直接讲下训练检测器时作者采用的一些方法。在论文`4.3`章节，作者也罗列了一堆方法，并做了部分消融实验。这里我只介绍确实在代码中有使用到的一些方法。

![quicker_ec7b98a6-e801-41ca-abaa-593108f91593.png](https://s2.loli.net/2022/05/09/NxXor2OA1mbRiWT.png)



## CSPDarknet53网络结构

CSPDarknet53就是将CSP结构融入了Darknet53中。CSP结构是在CSPNet（Cross Stage Partial Network）论文中提出的，CSPNet作者说在目标检测任务中使用CSP结构有如下好处：

Strengthening learning ability of a CNN
Removing computational bottlenecks
Reducing memory costs
**即减少网络的计算量以及对显存的占用，同时保证网络的能力不变或者略微提升。**CSP结构的思想参考原论文中绘制的CSPDenseNet，**进入每个stage（一般在下采样后）先将数据划分成俩部分**，如下图所示的Part1和Part2。但具体怎么划分呢，**在CSPNet中是直接按照通道均分**，但在**YOLOv4网络中是通过两个1x1的卷积层来实现的**。**在Part2后跟一堆Blocks然后在通过1x1的卷积层（图中的Transition），接着将两个分支的信息在通道方向进行Concat拼接，最后再通过1x1的卷积层进一步融合**（图中的Transition）。



![quicker_9c3da51b-5d06-4c84-a90b-c08a371a0edf.png](https://s2.loli.net/2022/05/09/drBFhsqePQcnuYi.png)



CSPDarknet53详细结构（以输入图片大小为416 × 416 × 3 为例）

- 注意，`CSPDarknet53` Backbone中所有的激活函数都是`Mish`激活函数

  ![quicker_d078c76a-ae00-4272-b93a-f4a5a4944b02.png](https://s2.loli.net/2022/05/09/mlPByM4hdgoIS71.png)



## 网络结构

![quicker_e0ec51d7-d578-4fb5-8a36-e590863d705f.png](https://s2.loli.net/2022/05/09/S7vgi5XTmFNOM21.png)

# V5

网络结构主要由以下几部分组成：

Backbone: New CSP-Darknet53
Neck: SPPF, New CSP-PAN
Head: YOLOv3 Head

YOLOv5针对不同大小（n, s, m, l, x）的网络整体架构都是一样的，只不过会在每个子模块中采用不同的深度和宽度，分别应对yaml文件中的depth_multiple和width_multiple参数。还需要注意一点，官方除了n, s, m, l, x版本外还有n6, s6, m6, l6, x6，区别在于后者是针对更大分辨率的图片比如1280x1280，当然结构上也有些差异，**后者会下采样64倍，采用4个预测特征层，而前者只会下采样到32倍且采用3个预测特征层**。本博文只讨论前者。

![quicker_d52e935b-94ae-4c77-b7b2-d2bd508eb31f.png](https://s2.loli.net/2022/05/09/kCZgA5jshbKy7lv.png)



和YOLOv4对比，其实YOLOv5在Backbone部分没太大变化。但是YOLOv5在v6.0版本后相比之前版本有一个很小的改动，把网络的第一层（原来是Focus模块）换成了一个6x6大小的卷积层。两者在理论上其实等价的，但是对于现有的一些GPU设备（以及相应的优化算法）使用6x6大小的卷积层比使用Focus模块更加高效。详情可以参考这个issue #4825。下图是原来的Focus模块(和之前Swin Transformer中的Patch Merging类似)，**将每个2x2的相邻像素划分为一个patch，然后将每个patch中相同位置（同一颜色）像素给拼在一起就得到了4个feature map，然后在接上一个3x3大小的卷积层。这和直接使用一个6x6大小的卷积层等效。**

![quicker_1d6266e5-514d-4ce5-b18b-609bcb1bda92.png](https://s2.loli.net/2022/05/09/8I3kFMYjO2RhL6e.png)



在Neck部分的变化还是相对较大的，首先是将SPP换成成了SPPF（Glenn Jocher自己设计的），这个改动我个人觉得还是很有意思的，**两者的作用是一样的，但后者效率更高**。`SPPF`比`SPP`计算速度快了不止两倍。SPP结构如下图所示，是将输入并行通过多个不同大小的MaxPool，然后做进一步融合，能在一定程度上解决目标多尺度问题。



`SPPF`结构是将输入串行通过多个`5x5`大小的`MaxPool`层，这里需要注意的是**串行两个`5x5`大小的`MaxPool`层是和一个`9x9`大小的`MaxPool`层计算结果是一样的，串行三个`5x5`大小的`MaxPool`层是和一个`13x13`大小的`MaxPool`层计算结果是一样的。**

![quicker_20886403-6055-4761-a782-afdf76d1476b.png](https://s2.loli.net/2022/05/09/ugM3fX7L6myd9ZN.png)

![quicker_99ea837a-ba94-4c1c-86e1-39582eab5c6b.png](https://s2.loli.net/2022/05/09/SiqLO4nXUDtrak9.png)



在**Neck**部分另外一个不同点就是`New CSP-PAN`了，在YOLOv4中，**Neck**的`PAN`结构是没有引入`CSP`结构的，但在YOLOv5中作者在`PAN`结构中加入了`CSP`。每个`C3`模块里都含有`CSP`结构。在**Head**部分，YOLOv3, v4, v5都是一样的



## 数据增强

这里简单罗列部分方法：

- **Mosaic**，将四张图片拼成一张图片，讲过很多次了
- **Random affine(Rotation, Scale, Translation and Shear)**，随机进行仿射变换，但根据配置文件里的超参数发现只使用了`Scale`和`Translation`即缩放和平移。
- **MixUp**，就是将两张图片按照一定的透明度融合在一起，具体有没有用不太清楚，毕竟没有论文，也没有消融实验。代码中只有较大的模型才使用到了`MixUp`，而且每次只有10%的概率会使用到。
- **Albumentations**，主要是做些滤波、直方图均衡化以及改变图片质量等等，我看代码里写的只有安装了`albumentations`包才会启用，但在项目的`requirements.txt`文件中`albumentations`包是被注释掉了的，所以默认不启用。
- **Augment HSV(Hue, Saturation, Value)**，随机调整色度，饱和度以及明度。
- **Random horizontal flip**，随机水平翻转

## 训练策略

在YOLOv5源码中使用到了很多训练的策略，这里简单总结几个我注意到的点，还有些没注意到的请大家自己看下源码：

- Multi-scale training(0.5~1.5x)，多尺度训练，假设设置输入图片的大小为640 × 640，训练时采用尺寸是在0.5 × 640 ∼ 1.5 × 640之间随机取值，注意取值时取得都是32的整数倍（因为网络会最大下采样32倍）。
- AutoAnchor(For training custom data)，训练自己数据集时可以根据自己数据集里的目标进行重新聚类生成Anchors模板。
- Warmup and Cosine LR scheduler，训练前先进行Warmup热身，然后在采用Cosine学习率下降策略。
- EMA(Exponential Moving Average)，可以理解为给训练的参数加了一个动量，让它更新过程更加平滑。
- Mixed precision，混合精度训练，能够减少显存的占用并且加快训练速度，前提是GPU硬件支持。
- Evolve hyper-parameters，超参数优化，没有炼丹经验的人勿碰，保持默认就好。

![quicker_696f7eaa-9fc9-4263-ae6a-59a9b40f18c9.png](https://s2.loli.net/2022/05/09/UhNwK1bSXdei8RA.png)

