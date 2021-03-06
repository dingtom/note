**图像处理中的坐标系，水平向右为x轴正方向，竖直向下为y轴正方向**。



安装OpenCV-Python,

```bash
pip install opencv-python==3.4.2.17
```

要利用SIFT和SURF等进行特征提取时，还需要安装：

```bash
pip install opencv-contrib-python==3.4.2.17
```

core、highgui、imgproc是最基础的模块，该课程主要是围绕这几个模块展开的，分别介绍如下：

- **core模块**实现了最核心的数据结构及其基本运算，如绘图函数、数组操作相关函数等。
- **highgui模块**实现了视频与图像的读取、显示、存储等接口。
- **imgproc模块**实现了图像处理的基础方法，包括图像滤波、图像的几何变换、平滑、阈值分割、形态学处理、边缘检测、目标检测、运动分析和对象跟踪等。

对于图像处理其他更高层次的方向及应用，OpenCV也有相关的模块实现

- **features2d模块**用于提取图像特征以及特征匹配，nonfree模块实现了一些专利算法，如sift特征。
- **objdetect模块**实现了一些目标检测的功能，经典的基于Haar、LBP特征的人脸检测，基于HOG的行人、汽车等目标检测，分类器使用Cascade Classification（级联分类）和Latent SVM等。
- **stitching模块**实现了图像拼接功能。
- **FLANN模块**（Fast Library for Approximate Nearest Neighbors），包含快速近似最近邻搜索FLANN 和聚类Clustering算法。
- **ml模块**机器学习模块（SVM，决策树，Boosting等等）。
- **photo模块**包含图像修复和图像去噪两部分。
- **video模块**针对视频处理，如背景分离，前景检测、对象跟踪等。
- **calib3d模块**即Calibration（校准）3D，这个模块主要是相机校准和三维重建相关的内容。包含了基本的多视角几何算法，单个立体摄像头标定，物体姿态估计，立体相似性算法，3D信息的重建等等。
- **G-API模块**包含超高效的图像处理pipeline引擎





 opencv 的接口使用BGR模式，而 matplotlib.pyplot 接口使用的是RGB模式

```PYTHON
b, g, r = cv2.split(srcImage)
srcImage_new = cv2.merge([r, g, b])
plt.imshow(srcImage_new)
# 通道变换之后对灰度图进行输出的图片颜色仍然为绿色,这是因为我们还是直接使用plt显示图像，它默认使用三通道显示图像
grayImage = cv2.cvtColor(img2, cv2.COLOR_BGR2GRAY)  # 灰度变换
plt.imshow(grayImage, cmap="gray")
```



# 图像处理

## 二值化

```python
# 全局阈值
thresh, dst = cv2.threshold(src, thresh, maxVal, type)
type: cv2.THRESH_BINARY 大于阈值的为maxVal,小于的为0  cv2.THRESH_BINARY_INV
# 自适应阈值
dst = cv2.adaptiveThreshold(src, maxVal, adaptiveMethod, type, blockSize, C)
#type:cv2.THRESH_BINARY
#adapttiveMethod:cv2.ADAPTIVE_THRESH_MEAN_C 
#thresh=blockSize*blockSize矩阵平均值灰度-C，大于thresh的为maxValue
```

# 寻找轮廓

```python
contours, hierarchy = cv2.findContours(image, mode, method)
# 轮廓检索模式
# Cv2.RETR_EXTERNAL检测外轮廓
# Cv2.RETR_TREE等级树结构的轮廓
# 轮廓近似方法
# Cv2.CHAIN_APPROX_NONE所有点
# Cv2.CHAIN_APPROX_SIMPLE直线两端点
# contours：list结构，列表中每个元素代表一个边沿信息。每个元素是(x,1,2)的三维向量，x表示该条边沿里共有多少个像素点，第三维的那个“2”表示每个点的横、纵坐标；
# 注意：如果输入选择cv2.CHAIN_APPROX_SIMPLE，则contours中一个list元素所包含的x点之间应该用直线连接起来，这个可以用cv2.drawContours()函数观察一下效果。
# hierarchy：返回类型是(x,4)的二维ndarray。x和contours里的x是一样的意思。如果输入选择cv2.RETR_TREE，则以树形结构组织输出，hierarchy的四列分别对应下一个轮廓编号、上一个轮廓编号、父轮廓编号、子轮廓编号，该值为负数表示没有对应项。
iamge = cv2.drawContours(image, contours, i, color, thickness)
# i：列表中第几个轮廓，-1所有；color：绘制颜色；thickness：线条粗细，-1填充
```







## 几何变换

```python
# 读取图像
img = cv2.imread('messi5.jpg',0)
参数：要读取的图像；读取方式的标志
cv2.IMREAD*COLOR：以彩色模式加载图像，任何图像的透明度都将被忽略。这是默认参数。
cv2.IMREAD*GRAYSCALE：以灰度模式加载图像
cv2.IMREAD_UNCHANGED：包括alpha通道的加载图像模式。
可以使用1、0或者-1来替代上面三个标志


# 显示图像
# opencv中显示
cv2.imshow('image',img)
cv2.waitKey(0)
# matplotlib中展示
plt.imshow(img[:,:,::-1])

参数：显示图像的窗口名称，以字符串类型表示，要加载的图像
注意：在调用显示图像的API后，要调用cv2.waitKey()给图像绘留下时间，否则窗口会出现无响应情况，并且图像无法显示出来

# 保存图像
cv2.imwrite('messigray.png',img)
参数：文件名，要保存在哪里；要保存的图像

# 向图像中添加文字
cv2.putText(img,text,station, font, fontsize,color,thickness,cv2.LINE_AA)
参数：
img: 图像
text：要写入的文本数据
station：文本的放置位置
font：字体
Fontsize :字体大小

    
通过行和列的坐标值获取该像素点的像素值。对于BGR图像，它返回一个蓝，绿，红值的数组。对于灰度图像，仅返回相应的强度值。使用相同的方法对像素值进行修改。   
img = cv2.imread('messi5.jpg')
# 获取某个像素点的值
px = img[100,100]
# 仅获取蓝色通道的强度值
blue = img[100,100,0]
# 修改某个位置的像素值
img[100,100] = [255,255,255]
# 通道拆分
b,g,r = cv2.split(img)
# 通道合并
img = cv2.merge((b,g,r))
# 色彩空间的改变
cv2.cvtColor(image，flag)
cv2.COLOR_BGR2GRAY : BGR↔Gray
cv2.COLOR_BGR2HSV: BGR→HSV
# 图像的加法
OpenCV加法和Numpy加法之间存在差异。OpenCV的加法是饱和操作，而Numpy添加是模运算。
尽量使用 OpenCV 中的函数。
>>> x = np.uint8([250])
>>> y = np.uint8([10])
>>> print( cv2.add(x,y) ) # 250+10 = 260 => 255
>>> print( x+y )          # 250+10 = 260 % 256 = 4

# 图像的混合
这其实也是加法，但是不同的是两幅图像的权重不同，这就会给人一种混合或者透明的感觉。图像混合的计算公式如下：dst = α⋅img1 + β⋅img2 + γ
img3 = cv2.addWeighted(img1,α,img2,β,γ)

    
   
```

### 图像旋转

<img src='https://s2.loli.net/2022/04/28/WyLYjn6C1Z2OBh4.png' title='quicker_d8c220b4-a82b-432e-a585-c7a8ac798020.png' />



### 仿射变换

变换前后满足**平直性**（变换前是直线变换后还是直线）和**平行性**（变换前平行的线变换后依旧平行）

![quicker_f637ed85-6bd2-48b6-9c3c-b1c33325db57.png](https://s2.loli.net/2022/04/28/hTgPDuieE3BClxt.png)

### 透射变换

![quicker_c19ebd8f-9b86-42d7-b372-dbc223a209a4.png](https://s2.loli.net/2022/04/28/awiPEVFmDq3LJsc.png)



###  图像金字塔

![quicker_01b001f5-46b8-430d-a549-0fa2507eae71.png](https://s2.loli.net/2022/04/29/HYBu8aq1Q4wFAcZ.png)







```python

# 图像缩放
cv2.resize(src,dsize,fx=0,fy=0,interpolation=cv2.INTER_LINEAR)
src : 输入图像
dsize: 绝对尺寸，直接指定调整后图像的大小 (2*cols,2*rows)
fx,fy: 相对尺寸，将dsize设置为None，(img1,None,fx=0.5,fy=0.5)
interpolation：插值方法，
cv2.INTER_LINEAR  双线性插值法
cv2.INTER_NEAREST 最临近插值
cv2.INTER_AREA 像素区域重采样{默认}
cv2.INTER_CUBIC 双三次插值

# 图像平移
M = np.float32([[1,0,100],[0,1,50]])# 将图像的像素点移动(50,100)的距离：
dst = cv2.warpAffine(img1,M,dsize=(cols,rows)，borderValue=(0,0,0))
img: 输入图像
M： 2*∗3移动矩阵
dsize: 输出图像的大小，它应该是(宽度，高度)的形式。请记住,width=列数，height=行数。
borderValue为边界填充颜色（注意是BGR顺序，( 0 , 0 , 0 ) (0,0,0)(0,0,0)代表黑色）:


#  图像旋转
旋转中图像仍保持这原始尺寸。图像旋转后图像的水平对称轴、垂直对称轴及中心坐标原点都可能会发生变换，因此需要对图像旋转中的坐标进行相应转换。
# 生成旋转矩阵
M = cv2.getRotationMatrix2D((cols/2,rows/2),90,1)
# center：旋转中心；angle：旋转角度；scale：缩放比例
# 进行旋转变换
dst = cv2.warpAffine(img,M,(cols,rows))


# 仿射变换
涉及到图像的形状位置角度的变化，是深度学习预处理中常到的功能,仿射变换主要是对图像的缩放，旋转，翻转和平移等操作的组合。

pts1 = np.float32([[50,50],[200,50],[50,200]])# 2.1 创建变换矩阵
pts2 = np.float32([[100,100],[200,50],[100,250]])
M = cv2.getAffineTransform(pts1,pts2)
dst = cv2.warpAffine(img,M,(cols,rows))# 2.2 完成仿射变换

# 透射变换

pts1 = np.float32([[56,65],[368,52],[28,387],[389,390]]) # 2.1 创建变换矩阵
pts2 = np.float32([[100,145],[300,100],[80,290],[310,300]])
T = cv2.getPerspectiveTransform(pts1,pts2)
# 2.2 进行变换
dst = cv2.warpPerspective(img,T,(cols,rows))


# 图像金字塔
cv2.pyrUp(img)      #对图像进行上采样
cv2.pyrDown(img)        #对图像进行下采样

```



## 形态学操作

### 连通性

![quicker_9780cacd-c3f0-4aec-a8c1-35543bc82d5c.png](https://s2.loli.net/2022/04/28/XwCNFQxH5EhgUAs.png)



![quicker_1f6186c4-8aed-459e-9ae3-ae3af2b09f3c.png](https://s2.loli.net/2022/04/28/UmGAk7bd5sRrO41.png)

### 腐蚀、膨胀

![quicker_d0033f1b-8a50-4897-ae0e-ee437855f6db.png](https://s2.loli.net/2022/04/28/FcCt2RTbWafueKZ.png)



![quicker_6cd09d96-3223-411c-a481-0003cdd07376.png](https://s2.loli.net/2022/04/29/zxWv4Vn1meaZ9ic.png)

开闭运算

![quicker_2a338865-a7ea-4e44-8dfb-9a02908053b4.png](https://s2.loli.net/2022/04/29/c9spWqvybUdoLBE.png)





礼帽和黑帽

礼帽：噪音提取

黑帽：空洞提取

![quicker_e0e407cf-b766-48e8-9661-db5b0dcd19b8.png](https://s2.loli.net/2022/04/29/3C5dLUcVKSgHjsq.png)

![quicker_758b62e3-23d8-40d4-a7b1-a0c95a19af9d.png](https://s2.loli.net/2022/06/22/iDaXEGzA9Z3wSuR.png)



```python
# 腐蚀、膨胀
cv2.erode(img,kernel,iterations)
cv2.dilate(img,kernel,iterations)

# 开闭运算# 礼帽和黑帽

kernel = np.ones((10, 10), np.uint8)# 2 创建核结构
cvOpen = cv2.morphologyEx(img1,cv2.MORPH_OPEN,kernel) # 开运算
cvClose = cv2.morphologyEx(img2,cv2.MORPH_CLOSE,kernel)# 闭运算
cvOpen = cv2.morphologyEx(img1,cv2.MORPH_TOPHAT,kernel) # 礼帽运算
cvClose = cv2.morphologyEx(img2,cv2.MORPH_BLACKHAT,kernel)# 黑帽运算



```









## 图像平滑

### 图像噪声

**椒盐噪声**也称为脉冲噪声，是图像中经常见到的一种噪声，它是一种**随机出现的白点或者黑点**，可能是亮的区域有黑色像素或是在暗的区域有白色像素（或是两者皆有）。椒盐噪声的成因可能是影像讯号受到突如其来的强烈干扰而产生、类比数位转换器或位元传输错误等。例如失效的感应器导致像素值为最小值，饱和的感应器导致像素值为最大值。



**高斯噪声**是指噪声密度函数服从高斯分布的一类噪声。由于高斯噪声在空间和频域中数学上的易处理性，这种噪声(也称为正态噪声)模型经常被用于实践中。高斯随机变量z的概率密度函数由下式给出：

![quicker_75363766-8572-412b-9888-e60f83e4a984.png](https://s2.loli.net/2022/05/01/PUKL18ZgFTefzrA.png)



图像平滑从信号处理的角度看就是去除其中的高频信息，保留低频信息。因此我们可以对图像实施低通滤波。低通滤波可以去除图像中的噪声，对图像进行平滑。





**均值滤波**的优点是**算法简单，计算速度较快**，缺点是**在去噪的同时去除了很多细节部分**，将图像变得模糊。

![quicker_71b104d0-8f95-4def-832d-3e3a73bdd548.png](https://s2.loli.net/2022/05/01/42dk7lqmVPBewFh.png)



**高斯平滑**在从图像中**去除高斯噪声方面非常有效**。

正态分布是一种钟形曲线，越接近中心，取值越大，越远离中心，取值越小。计算平滑结果时，只需要**将"中心点"作为原点，其他点按照其在正态曲线上的位置，分配权重**，就可以得到一个加权平均值。





**中值滤波**对**椒盐噪声（salt-and-pepper noise）来说尤其有用**，因为它不依赖于邻域内那些与典型值差别很大的值。是一种典型的非线性滤波技术，基本思想是**用像素点邻域灰度值的中值来代替该像素点的灰度值**。



![quicker_59bc7d70-51bf-45e4-9e48-e760963c175e.png](https://s2.loli.net/2022/05/01/wnAPQrxaGZ82Yty.png)



```python
# 均值滤波
cv2.blur(src, ksize, anchor, borderType)
src：输入图像
ksize：卷积核的大小
anchor：默认值 (-1,-1) ，表示核中心
borderType：边界类型
# 高斯滤波
cv2.GaussianBlur(src,ksize,sigmaX,sigmay,borderType)
src: 输入图像
ksize:高斯卷积核的大小，注意 ： 卷积核的宽度和高度都应为奇数，且可以不同
sigmaX: 水平方向的标准差
sigmaY: 垂直方向的标准差，默认值为0，表示与sigmaX相同
borderType:填充边界类型
# 中值滤波
cv2.medianBlur(src, ksize )
src：输入图像
ksize：卷积核的大小
```

## 直方图

图像直方图（Image Histogram）是用以表示数字图像中亮度分布的直方图，标绘了图像中**每个亮度值的像素个数**。这种直方图中，横坐标的左侧为较暗的区域，而右侧为较亮的区域。因此一张较暗图片的直方图中的数据多集中于左侧和中间部分，而整体明亮、只有少量阴影的图像则相反。

“直方图均衡化”是把原始图像的灰度直方图**从比较集中的某个灰度区间变成在更广泛灰度范围内的分布**。直方图均衡化就是对图像进行非线性拉伸，重新分配图像像素值，使一定灰度范围内的像素数量大致相同。

这种方法**提高图像整体的对比度**，特别是有用数据的像素值分布比较接近时，**在X光图像中使用广泛，可以提高骨架结构的显示**，另外在**曝光过度或不足**的图像中可以更好的突出细节。



![quicker_d57c8302-46c3-428d-8206-ca7e1476b7ab.png](https://s2.loli.net/2022/05/01/fuIqpZUwlSbF7N4.png)

上述的直方图均衡，我们考虑的是图像的全局对比度。 的确在进行完直方图均衡化之后，图片背景的对比度被改变了，在猫腿这里太暗，我们丢失了很多信息，所以在许多情况下，这样做的效果并不好。

需要使用自适应的直方图均衡化

整幅图像会被分成很多小块，这些小块被称为“tiles”（在 OpenCV 中 tiles 的 大小默认是 8x8），然后再对**每一个小块分别进行直方图均衡化**。 所以在每一个的区域中， 直方图会集中在某一个小的区域中）。**如果有噪声的话，噪声会被放大。为了避免这种情况的出现要使用对比度限制**。对于每个小块来说，**如果直方图中的 bin 超过对比度的上限的话，就把其中的像素点均匀分散到其他 bins 中，然后在进行直方图均衡化。**最后，为了 去除每一个小块之间的边界，再使用双线性差值，对每一小块进行拼接。

```python
# 直方图
cv2.calcHist(images,channels,mask,histSize,ranges[,hist[,accumulate]])
images: 原图像。当传入函数时应该用中括号 [] 括起来，例如：[img]。
channels: 如果输入图像是灰度图，它的值就是 [0]；如果是彩色图像的话，传入的参数可以是 [0]，[1]，[2] 它们分别对应着通道 B，G，R。 　　
mask: 掩模图像。要统计整幅图像的直方图就把它设为 None。但是如果你想统计图像某一部分的直方图的话，你就需要制作一个掩模图像，并使用它。（后边有例子） 　　
histSize:BIN 的数目。也应该用中括号括起来，例如：[256]。 　　
ranges: 像素值范围，通常为 [0，256]

mask = np.zeros(img.shape[:2], np.uint8)# 2. 创建蒙版
mask[400:650, 200:500] = 255 # 查找直方图的区域上创建一个白色的掩膜图像，否则创建黑色
masked_img = cv2.bitwise_and(img,img,mask = mask)# 3.掩模
mask_histr = cv2.calcHist([img],[0],mask,[256],[1,256])    # 4. 统计掩膜后图像的灰度图

# 直方图均衡化
dst = cv2.equalizeHist(img)

# 自适应的直方图均衡化
cv2.createCLAHE(clipLimit, tileGridSize)
clipLimit: 对比度限制，默认是40
tileGridSize: 分块的大小，默认为8*88∗8
```

## 边缘检测

图像边缘检测大幅度地减少了数据量，并且剔除了可以认为不相关的信息，保留了图像重要的结构属性。有许多方法用于边缘检测，它们的绝大部分可以划分为两类：基于搜索和基于零穿越。



![quicker_8cfce94d-4575-4cec-bc4c-3a4cfd27d0eb.png](https://s2.loli.net/2022/05/01/y9czuDdmoPOR7bv.png)

**Sobel边缘检测算法**比较简单，实际应用中效率**比canny边缘检测效率要高**，但是边缘**不如Canny检测的准确**，但是很多实际应用的场合，sobel边缘却是首选，Sobel算子是**高斯平滑与微分操作的结合体，所以其抗噪声能力很强，用途较多**。尤其是效率要求较高，而对细纹理不太关心的时候。

![quicker_b8444d61-1a79-48a9-9df9-ec4a3cfd7d7f.png](https://s2.loli.net/2022/05/01/FR6ZhUeb8flJop1.png)

**Laplacian是利用二阶导数来检测边缘 。**

![quicker_cb380bdc-d81c-4ac5-a4c5-6e83439c8e8d.png](https://s2.loli.net/2022/05/02/xs7ztkwRfuWP1Qy.png)



**Canny 边缘检测算法被认为是最优的边缘检测算法**。

![quicker_39c15296-346c-42e6-a6ff-4590df34bf5d.png](https://s2.loli.net/2022/05/01/9w4cPjUBeXKZ1np.png)





```python
# sobel边缘检测
Sobel_x_or_y = cv2.Sobel(src, ddepth, dx, dy, dst, ksize, scale, delta, borderType)
src：传入的图像
ddepth: 图像的深度
dx和dy: 指求导的阶数，0表示这个方向上没有求导，取值为0、1。
ksize: 是Sobel算子的大小，即卷积核的大小，必须为奇数1、3、5、7，默认为3。注意：如果ksize=-1，就演变成为3x3的Scharr算子。
scale：缩放导数的比例常数，默认情况为没有伸缩系数。
borderType：图像边界的模式，默认值为cv2.BORDER_DEFAULT。

Sobel函数求完导数后会有负值，还有会大于255的值。而原图像是uint8，即8位无符号数，所以Sobel建立的图像位数不够，会有截断。因此要使用16位有符号的数据类型，即cv2.CV_16S。处理完图像后，再使用cv2.convertScaleAbs()函数将其转回原来的uint8格式，否则图像无法显示。Sobel算子是在两个方向计算的，最后还需要用cv2.addWeighted( )函数将其组合起来

x = cv2.Sobel(img, cv2.CV_16S, 1, 0)# 2 计算Sobel卷积结果
y = cv2.Sobel(img, cv2.CV_16S, 0, 1)
Scale_absX = cv2.convertScaleAbs(x)  # convert 转换  scale 缩放
Scale_absY = cv2.convertScaleAbs(y)
result = cv2.addWeighted(Scale_absX, 0.5, Scale_absY, 0.5, 0)# 4 结果合成

# laplacian算子
cv2.Laplacian(src, ddepth[, dst[, ksize[, scale[, delta[, borderType]]]]])
Src: 需要处理的图像，
Ddepth: 图像的深度，-1表示采用的是原图像相同的深度，目标图像的深度必须大于等于原图像的深度；
ksize：算子的大小，即卷积核的大小，必须为1,3,5,7。

result = cv2.Laplacian(img,cv2.CV_16S)
Scale_abs = cv2.convertScaleAbs(result)


# canny检测
canny = cv2.Canny(image, threshold1, threshold2)
image:灰度图，
threshold1: minval，较小的阈值将间断的边缘连接起来
threshold2: maxval，较大的阈值检测图像中明显的边缘

lowThreshold = 0
max_lowThreshold = 100
canny = cv2.Canny(img, lowThreshold, max_lowThreshold) 

    
```



## 模板匹配和霍夫变换



模板匹配，就是在给定的图片中查找和模板最相似的区域，该算法的输入包括模板和图片，整个任务的思路就是按照滑窗的思路不断的移动模板图片，计算其与图像中对应区域的匹配度，最终将匹配度最高的区域选择为最终的结果。



霍夫变换常用来提取图像中的直线和圆等几何形状

```python
res = cv2.matchTemplate(img,template,method)
img: 要进行模板匹配的图像
Template ：模板
method：实现模板匹配的算法，主要有：
平方差匹配(CV_TM_SQDIFF)：利用模板与图像之间的平方差进行匹配，最好的匹配是0，匹配越差，匹配的值越大。
相关匹配(CV_TM_CCORR)：利用模板与图像间的乘法进行匹配，数值越大表示匹配程度较高，越小表示匹配效果差。
利用相关系数匹配(CV_TM_CCOEFF)：利用模板与图像间的相关系数匹配，1表示完美的匹配，-1表示最差的匹配。
完成匹配后，使用cv2.minMaxLoc()方法查找最大值所在的位置即可。如果使用平方差作为比较方法，则最小值位置是最佳匹配位置。

res = cv2.matchTemplate(img, template, cv2.TM_CCORR)# 2.1 模板匹配
min_val, max_val, min_loc, max_loc = cv2.minMaxLoc(res)# 2.2 返回图像中最匹配的位置，确定左上角的坐标，并将匹配位置绘制在图像上
# top_left = min_loc# 使用平方差时最小值为最佳匹配位置
top_left = max_loc
bottom_right = (top_left[0] + w, top_left[1] + h)
cv2.rectangle(img, top_left, bottom_right, (0,255,0), 2)



# 霍夫线检测
cv2..HoughLines(img, rho, theta, threshold)
img: 检测的图像，要求是二值化的图像，所以在调用霍夫变换之前首先要进行二值化，或者进行Canny边缘检测
rho、theta: \rhoρ 和\thetaθ的精确度
threshold: 阈值，只有累加器中的值高于该阈值时才被认为是直线。
    
img =cv2..imread('./image/rili.jpg')# 1.加载图片，转为二值图
gray =cv2..cvtColor(img,cv2..COLOR_BGR2GRAY)
edges =cv2..Canny(gray, 50, 150)
lines =cv2..HoughLines(edges, 0.8, np.pi / 180, 150)# 2.霍夫直线变换
for line in lines:# 3.将检测的线绘制在图像上（注意是极坐标噢）
    rho, theta = line[0]
    a = np.cos(theta)
    b = np.sin(theta)
    x0 = a * rho
    y0 = b * rho
    x1 = int(x0 + 1000 * (-b))
    y1 = int(y0 + 1000 * (a))
    x2 = int(x0 - 1000 * (-b))
    y2 = int(y0 - 1000 * (a))
   cv2..line(img, (x1, y1), (x2, y2), (0, 255, 0))
plt.figure(figsize=(10,8),dpi=100)
plt.imshow(img[:,:,::-1]),plt.title('霍夫变换线检测')
plt.xticks([]), plt.yticks([])
plt.show()    
```









# 图像特征提取和描述

模板匹配不适用于尺度变换，视角变换后的图像，这时我们就要使用关键点匹配算法，比较经典的关键点检测算法包括SIFT和SURF等，主要的思路是首先通过关键点检测算法获取模板和测试图片中的关键点；然后使用关键点匹配算法处理即可，这些关键点可以很好的处理尺度变化、视角变换、旋转变化、光照变化等，具有很好的不变性。







## 角点特征

在角点的地方，无论你向哪个方向移动小图，结果都会有很大的不同。所以可以把它们当 成一个好的特征。

## Harris和Shi-Tomas算法

**Harris**

优点：

- **旋转不变性**，椭圆转过一定角度但是其形状保持不变（特征值保持不变）
- 对于图像灰度的仿射变化具有部分的不变性，由于仅仅使用了图像的一介导数，对于图像灰度平移变化不变；对于图像灰度尺度变化不变

缺点：

- **对尺度很敏感**，不具备几何尺度不变性。
- 提取的角点是像素级的

**Shi-Tomasi**

对Harris算法的改进，能够更好地检测角点

![quicker_9e35dfbc-4202-42a3-870a-64675311f339.png](https://s2.loli.net/2022/05/04/NOYH29EvTesRbBJ.png)





```python
#Hariis检测使用的API是：
dstcv2..cornerHarris(src, blockSize, ksize, k)
img：数据类型为 ﬂoat32 的输入图像。
blockSize：角点检测中要考虑的邻域大小。
ksize：sobel求导使用的核大小
k ：角点检测方程中的自由参数，取值参数为 [0.04，0.06].


# 1 读取图像，并转换成灰度图像
img =cv2..imread('./image/chessboard.jpg')
gray =cv2..cvtColor(img,cv2..COLOR_BGR2GRAY)
# 2 角点检测
# 2.1 输入图像必须是 float32
gray = np.float32(gray)
# 2.2 最后一个参数在 0.04 到 0.05 之间
dst =cv2..cornerHarris(gray,2,3,0.04)
# 3 设置阈值，将角点绘制出来，阈值根据图像进行选择
img[dst>0.001*dst.max()] = [0,0,255]
# 4 图像显示
plt.figure(figsize=(10,8),dpi=100)
plt.imshow(img[:,:,::-1]),plt.title('Harris角点检测')
plt.xticks([]), plt.yticks([])
plt.show()

# Shi-Tomasi
corners = cv2.goodFeaturesToTrack ( image, maxcorners, qualityLevel, minDistance )
Image: 输入灰度图像
maxCorners : 获取角点数的数目。
qualityLevel：该参数指出最低可接受的角点质量水平，在0-1之间。
minDistance：角点之间最小的欧式距离，避免得到相邻特征点。
返回：
Corners: 搜索到的角点，在这里所有低于质量水平的角点被排除掉，然后把合格的角点按质量排序，然后将质量较好的角点附近（小于最小欧式距离）的角点删掉，最后找到maxCorners个角点返回。

import numpy as np 
import cv2 as cv
import matplotlib.pyplot as plt
# 1 读取图像
img =cv2..imread('./image/tv.jpg') 
gray =cv2..cvtColor(imgcv2..COLOR_BGR2GRAY)
# 2 角点检测
corners =cv2..goodFeaturesToTrack(gray,1000,0.01,10)  
# 3 绘制角点
for i in corners:
    x,y = i.ravel()
   cv2..circle(img,(x,y),2,(0,0,255),-1)
# 4 图像展示
plt.figure(figsize=(10,8),dpi=100)
plt.imshow(img[:,:,::-1]),plt.title('shi-tomasi角点检测')
plt.xticks([]), plt.yticks([])
plt.show()    
```



## SIFT/SURF算法

Harris和Shi-Tomasi角点检测算法，这两种算法具有旋转不变性，但不具有尺度不变性



**SIFT算法的实质是在不同的尺度空间上查找关键点(特征点)，并计算出关键点的方向。SIFT所查找到的关键点是一些十分突出，不会因光照，仿射变换和噪音等因素而变化的点，如角点、边缘点、暗区的亮点及亮区的暗点**，但并不完美，仍然存在实时性不高，有时特征点较少，对边缘光滑的目标无法准确提取特征点等缺陷



SIFT原理：

- 尺度空间极值检测：构建高斯金字塔，高斯差分金字塔，检测极值点。
- 关键点定位：去除对比度较小和边缘对极值点的影响。
- 关键点方向确定：利用梯度直方图确定关键点的方向。
- 关键点描述：对关键点周围图像区域分块，计算块内的梯度直方图，生成具有特征向量，对关键点信息进行描述。



使用 SIFT 算法进行关键点检测和描述的执行速度比较慢， 需要速度更快的算法。 2006 年 Bay提出了 SURF 算法，是SIFT算法的增强版，它的计算量小，运算速度快，提取的特征与SIFT几乎相同，将其与SIFT算法对比如下：

![quicker_cdef8f3f-84d9-4b0e-aa28-dd880ba6aab0.png](https://s2.loli.net/2022/05/04/f1HINV4cnmzRDKp.png)



```python
# 实例化sift
sift =cv2..xfeatures2d.SIFT_create()
# 利用sift.detectAndCompute()检测关键点并计算
kp,des = sift.detectAndCompute(gray,None)
gray: 进行关键点检测的图像，注意是灰度图像
返回：
kp: 关键点信息，包括位置，尺度，方向信息
des: 关键点描述符，每个关键点对应128个梯度信息的特征向量
# 将关键点检测结果绘制在图像上
cv2..drawKeypoints(image, keypoints, outputimage, color, flags)
image: 原始图像
keypoints：关键点信息，将其绘制在图像上
outputimage：输出图片，可以是原始图像
color：颜色设置，通过修改（b,g,r）的值,更改画笔的颜色，b=蓝色，g=绿色，r=红色。
flags：绘图功能的标识设置
cv2.DRAW_MATCHES_FLAGS_DEFAULT：创建输出图像矩阵，使用现存的输出图像绘制匹配对和特征点，对每一个关键点只绘制中间点
cv2.DRAW_MATCHES_FLAGS_DRAW_OVER_OUTIMG：不创建输出图像矩阵，而是在输出图像上绘制匹配对
cv2.DRAW_MATCHES_FLAGS_DRAW_RICH_KEYPOINTS：对每一个特征点绘制带大小和方向的关键点图形
cv2.DRAW_MATCHES_FLAGS_NOT_DRAW_SINGLE_POINTS：单点的特征点不被绘制


# 1 读取图像
img =cv2..imread('./image/tv.jpg')
gray=cv2..cvtColor(imgcv2..COLOR_BGR2GRAY)
# 2 sift关键点检测
# 2.1 实例化sift对象
sift =cv2..xfeatures2d.SIFT_create()
# 2.2 关键点检测：kp关键点信息包括方向，尺度，位置信息，des是关键点的描述符
kp,des=sift.detectAndCompute(gray,None)
# 2.3 在图像上绘制关键点的检测结果
cv2..drawKeypoints(img,kp,img,flagscv2..DRAW_MATCHES_FLAGS_DRAW_RICH_KEYPOINTS)
# 3 图像显示
plt.figure(figsize=(8,6),dpi=100)
plt.imshow(img[:,:,::-1]),plt.title('sift检测')
plt.xticks([]), plt.yticks([])
plt.show()

```





# 视频操作

```python
import numpy as np
import cv2 as cv
# 1.获取视频对象
cap =cv2..VideoCapture('DOG.wmv')
# 获取视频属性
# retval = cap.get(propId)
#0.cv2.CAP_PROP POS MSEC视频文件的当前位置(ms)
#1.cv2.CAP_PROP POS FRAMES从0开始索引帧，帧位置
#2.cv2.CAP_PROP_POS AVI RATIO视频文件的相对位置(0表示开始，1表示结束)
#3.cv2.CAP_PROP FRAME WIDTH视频流的帧宽度
#4.cv2.CAP PROP FRAME HEIGHT视频流的帧高度
#5.cv2.CAP PROP FPS帧率
#6.cv2.CAP PROP FOURCC编解码器四字符代码
#7.cv2.CAP PROP FRAME COUNT视频文件的帧
# 修改视频的属性信息
# cap.set(propId，value)

# 2.判断是否读取成功
while(cap.isOpened()):
    # 3.获取每一帧图像
    ret, frame = cap.read()
    #ret: 若获取成功返回True，获取失败，返回False
	#Frame: 获取到的某一帧的图像
    # 4. 获取成功显示图像
    if ret == True:
       cv2..imshow('frame',frame)
    # 5.每一帧间隔为25ms
    ifcv2..waitKey(25) & 0xFF == ord('q'):
        break
# 6.释放视频对象
cap.release()
cv2..destoryAllwindows()




# 1. 读取视频
cap =cv2..VideoCapture("DOG.wmv")

# 3. 创建保存视频的对象，设置编码格式，帧率，图像的宽高等
out =cv2..VideoWriter('outpy.avi'cv2..VideoWriter_fourcc('M','J','P','G'), 10, (frame_width,frame_height))

#ilename：视频保存的位置
#fourcc：指定视频编解码器的4字节代码cv2.VideoWriter_fourcc( c1, c2, c3, c4 ) c1,c2,c3,c4: 是视频编解码器的4字节代码，在fourcc.org中找到可用代码列表，与平台紧密相关
#fps：帧率
#frameSize：帧大小


while(True):
    # 4.获取视频中的每一帧图像
    ret, frame = cap.read()
    if ret == True: 
        # 5.将每一帧图像写入到输出文件中
        out.write(frame)
    else:
        break 

# 6.释放资源
cap.release()
out.release()
cv2..destroyAllWindows()
```

## 视频追踪

图像是一个矩阵信息，如何在一个视频当中使用meanshift算法来追踪一个运动的物体呢？ 大致流程如下：

1. 首先在图像上选定一个目标区域

2. 计算选定区域的**直方图分布**，一般是HSV色彩空间的直方图。

3. 对下一帧图像b同样计算直方图分布。

4. 计算图像b当中与选定区域直方图分布最为相似的区域，使用meanshift算法将选定区域沿着最为相似的部分进行移动，直到找到最相似的区域，便完成了在图像b中的目标追踪。

5. 重复3到4的过程，就完成整个视频目标追踪。

   通常情况下我们使用直方图反向投影得到的图像和第一帧目标对象的起始位置，当目标对象的移动会反映到直方图反向投影图中，meanshift 算法就把我们的窗口移动到反向投影图像中灰度密度最大的区域了。

直方图反向投影的流程是：

假设我们有一张100x100的输入图像，有一张10x10的模板图像，查找的过程是这样的：

1. 从输入图像的左上角(0,0)开始，切割一块(0,0)至(10,10)的临时图像；
2. 生成临时图像的直方图；
3. 用临时图像的直方图和模板图像的直方图对比，对比结果记为c；
4. 直方图对比结果c，就是结果图像(0,0)处的像素值；
5. 切割输入图像从(0,1)至(10,11)的临时图像，对比直方图，并记录到结果图像；
6. 重复1～5步直到输入图像的右下角，就形成了直方图的反向投影。



```python
import numpy as np
import cv2 as cv
# 1.获取图像
cap =cv2..VideoCapture('DOG.wmv')

# 2.获取第一帧图像，并指定目标位置
ret,frame = cap.read()
# 2.1 目标位置（行，高，列，宽）
r,h,c,w = 197,141,0,208  
track_window = (c,r,w,h)
# 2.2 指定目标的感兴趣区域
roi = frame[r:r+h, c:c+w]

# 3. 计算直方图
# 3.1 转换色彩空间（HSV）
hsv_roi = cv2..cvtColor(roi,cv2..COLOR_BGR2HSV)
# 3.2 去除低亮度的值
# mask =cv2..inRange(hsv_roi, np.array((0., 60.,32.)), np.array((180.,255.,255.)))
# 3.3 计算直方图
roi_hist =cv2..calcHist([hsv_roi],[0],None,[180],[0,180])
# 3.4 归一化
cv2..normalize(roi_hist,roi_hist,0,255cv2..NORM_MINMAX)

# 4. 目标追踪
# 4.1 设置窗口搜索终止条件：最大迭代次数，窗口中心漂移最小值
term_crit = (cv2..TERM_CRITERIA_EPS |cv2..TERM_CRITERIA_COUNT, 10, 1 )

while(True):
    # 4.2 获取每一帧图像
    ret ,frame = cap.read()
    if ret == True:
        # 4.3 计算直方图的反向投影
        hsv =cv2..cvtColor(frame,cv2..COLOR_BGR2HSV)
        dst =cv2..calcBackProject([hsv],[0],roi_hist,[0,180],1)

        # 4.4 进行meanshift追踪
        ret, track_window =cv2..meanShift(dst, track_window, term_crit)

        # 4.5 将追踪的位置绘制在视频上，并进行显示
        x,y,w,h = track_window
        img2 =cv2..rectangle(frame, (x,y), (x+w,y+h), 255,2)
       cv2..imshow('frame',img2)

        ifcv2..waitKey(60) & 0xFF == ord('q'):
            break        
    else:
        break
# 5. 资源释放        
cap.release()
cv2..destroyAllWindows()
```

## 视频追踪

meanshift算法除了应用在视频追踪当中，在聚类，平滑等等各种涉及到数据以及非监督学习的场合当中均有重要应用，是一个应用广泛的算法。

图像是一个矩阵信息，如何在一个视频当中使用meanshift算法来追踪一个运动的物体呢？ 大致流程如下：

1. 首先在图像上选定一个目标区域

2. 计算选定区域的直方图分布，一般是HSV色彩空间的直方图。

3. 对下一帧图像b同样计算直方图分布。

4. 计算图像b当中与选定区域直方图分布最为相似的区域，使用meanshift算法将选定区域沿着最为相似的部分进行移动，直到找到最相似的区域，便完成了在图像b中的目标追踪。

5. 重复3到4的过程，就完成整个视频目标追踪。

   通常情况下我们使用直方图反向投影得到的图像和第一帧目标对象的起始位置，当目标对象的移动会反映到直方图反向投影图中，meanshift 算法就把我们的窗口移动到反向投影图像中灰度密度最大的区域了。

直方图反向投影的流程是：

假设我们有一张100x100的输入图像，有一张10x10的模板图像，查找的过程是这样的：

1. 从输入图像的左上角(0,0)开始，切割一块(0,0)至(10,10)的临时图像；
2. 生成临时图像的直方图；
3. 用临时图像的直方图和模板图像的直方图对比，对比结果记为c；
4. 直方图对比结果c，就是结果图像(0,0)处的像素值；
5. 切割输入图像从(0,1)至(10,11)的临时图像，对比直方图，并记录到结果图像；
6. 重复1～5步直到输入图像的右下角，就形成了直方图的反向投影。



```python
# Meanshift的API是：
cv2..meanShift(probImage, window, criteria)
probImage: ROI区域，即目标的直方图的反向投影
window： 初始搜索窗口，就是定义ROI的rect
criteria: 确定窗口搜索停止的准则，主要有迭代次数达到设置的最大值，窗口中心的漂移值大于某个设定的限值等。

import numpy as np
import cv2 as cv
# 1.获取图像
cap =cv2..VideoCapture('DOG.wmv')

# 2.获取第一帧图像，并指定目标位置
ret,frame = cap.read()
# 2.1 目标位置（行，高，列，宽）
r,h,c,w = 197,141,0,208  
track_window = (c,r,w,h)
# 2.2 指定目标的感兴趣区域
roi = frame[r:r+h, c:c+w]

# 3. 计算直方图
# 3.1 转换色彩空间（HSV）
hsv_roi = cv2..cvtColor(roi,cv2..COLOR_BGR2HSV)
# 3.2 去除低亮度的值
# mask =cv2..inRange(hsv_roi, np.array((0., 60.,32.)), np.array((180.,255.,255.)))
# 3.3 计算直方图
roi_hist =cv2..calcHist([hsv_roi],[0],None,[180],[0,180])
# 3.4 归一化
cv2..normalize(roi_hist,roi_hist,0,255cv2..NORM_MINMAX)

# 4. 目标追踪
# 4.1 设置窗口搜索终止条件：最大迭代次数，窗口中心漂移最小值
term_crit = (cv2..TERM_CRITERIA_EPS |cv2..TERM_CRITERIA_COUNT, 10, 1 )

while(True):
    # 4.2 获取每一帧图像
    ret ,frame = cap.read()
    if ret == True:
        # 4.3 计算直方图的反向投影
        hsv =cv2..cvtColor(frame,cv2..COLOR_BGR2HSV)
        dst =cv2..calcBackProject([hsv],[0],roi_hist,[0,180],1)

        # 4.4 进行meanshift追踪
        ret, track_window =cv2..meanShift(dst, track_window, term_crit)

        # 4.5 将追踪的位置绘制在视频上，并进行显示
        x,y,w,h = track_window
        img2 =cv2..rectangle(frame, (x,y), (x+w,y+h), 255,2)
       cv2..imshow('frame',img2)

        ifcv2..waitKey(60) & 0xFF == ord('q'):
            # cv2.waitKey(1)在有按键按下的时候返回按键的ASCII值，否则返回-1
		   # & 0xFF的按位与操作只取cv2.waitKey(1)返回值最后八位，因为有些系统cv2.waitKey(1)的返回值不止八位
            # ord(‘q’)表示q的ASCII值
            # 总体效果：按下q键后break
    else:
        break
# 5. 资源释放        
cap.release()
cv2..destroyAllWindows()
```

meanshift检测的窗口的大小是固定的，而狗狗由近及远是一个逐渐变小的过程，固定的窗口是不合适的。所以我们需要根据目标的大小和角度来对窗口的大小和角度进行修正。CamShift可以帮我们解决这个问题。

CamShift算法全称是“Continuously Adaptive Mean-Shift”（连续自适应MeanShift算法），是对MeanShift算法的改进算法，可随着跟踪目标的大小变化实时调整搜索窗口的大小，具有较好的跟踪效果。

Camshift算法首先**应用meanshift，一旦meanshift收敛，它就会更新窗口的大小，还计算最佳拟合椭圆的方向，从而根据目标的位置和大小更新搜索窗口。**

```python
#进行camshift追踪
ret, track_window =cv2..CamShift(dst, track_window, term_crit)

# 绘制追踪结果
pts =cv2..boxPoints(ret)
pts = np.int0(pts)
img2 =cv2..polylines(frame,[pts],True, 255,2)
```

# 人脸识别

Haar 特征会被使用，就像我们的卷积核，每一个特征是一 个值，这个值等于黑色矩形中的像素值之后减去白色矩形中的像素值之和。

Haar特征值反映了图像的灰度变化情况。例如：脸部的一些特征能由矩形特征简单的描述，眼睛要比脸颊颜色要深，鼻梁两侧比鼻梁颜色要深，嘴巴比周围颜色要深等。

Haar特征可用于于图像任意位置，大小也可以任意改变，所以矩形特征值是矩形模版类别、矩形位置和矩形大小这三个因素的函数。故类别、大小和位置的变化，使得很小的检测窗口含有非常多的矩形特征。

![quicker_383542f0-ddd8-4efe-b52d-9ca4003ccad6.png](https://s2.loli.net/2022/05/06/UkcsVhFrXRaQoq2.png)

```python
# 训练好的检测器，包括面部，眼睛，猫脸等，都保存在XML文件中，我们可以通过以下程序找到他们：
import cv2 as cv
printcv2..__file__)

# 实例化人脸和眼睛检测的分类器对象
# 实例化级联分类器
classifier cv2..CascadeClassifier( "haarcascade_frontalface_default.xml" ) 
# 加载分类器
classifier.load('haarcascade_frontalface_default.xml')
# 进行人脸和眼睛的检测

rect = classifier.detectMultiScale(gray, scaleFactor, minNeighbors, minSize,maxsize)
Gray: 要进行检测的人脸图像
scaleFactor: 前后两次扫描中，搜索窗口的比例系数
minneighbors：目标至少被检测到minNeighbors次才会被认为是目标
minsize和maxsize: 目标的最小尺寸和最大尺寸
    
import cv2 as cv
import matplotlib.pyplot as plt
# 1.以灰度图的形式读取图片
img =cv2..imread("16.jpg")
gray =cv2..cvtColor(imgcv2..COLOR_BGR2GRAY)

# 2.实例化OpenCV人脸和眼睛识别的分类器 
face_cas =cv2..CascadeClassifier( "haarcascade_frontalface_default.xml" ) 
face_cas.load('haarcascade_frontalface_default.xml')

eyes_cas =cv2..CascadeClassifier("haarcascade_eye.xml")
eyes_cas.load("haarcascade_eye.xml")

# 3.调用识别人脸 
faceRects = face_cas.detectMultiScale( gray, scaleFactor=1.2, minNeighbors=3, minSize=(32, 32)) 
for faceRect in faceRects: 
    x, y, w, h = faceRect 
    # 框出人脸 
   cv2..rectangle(img, (x, y), (x + h, y + w),(0,255,0), 3) 
    # 4.在识别出的人脸中进行眼睛的检测
    roi_color = img[y:y+h, x:x+w]
    roi_gray = gray[y:y+h, x:x+w]
    eyes = eyes_cas.detectMultiScale(roi_gray) 
    for (ex,ey,ew,eh) in eyes:
       cv2..rectangle(roi_color,(ex,ey),(ex+ew,ey+eh),(0,255,0),2)
# 5. 检测结果的绘制
plt.figure(figsize=(8,6),dpi=100)
plt.imshow(img[:,:,::-1]),plt.title('检测结果')
plt.xticks([]), plt.yticks([])
plt.show()    



# 我们也可在视频中对人脸进行检测：

import cv2 as cv
import matplotlib.pyplot as plt
# 1.读取视频
cap =cv2..VideoCapture("movie.mp4")
# 2.在每一帧数据中进行人脸识别
while(cap.isOpened()):
    ret, frame = cap.read()
    if ret==True:
        gray =cv2..cvtColor(frame,cv2..COLOR_BGR2GRAY)
        # 3.实例化OpenCV人脸识别的分类器 
        face_cas =cv2..CascadeClassifier( "haarcascade_frontalface_default.xml" ) 
        face_cas.load('haarcascade_frontalface_default.xml')
        # 4.调用识别人脸 
        faceRects = face_cas.detectMultiScale(gray, scaleFactor=1.2, minNeighbors=3, minSize=(32, 32)) 
        for faceRect in faceRects: 
            x, y, w, h = faceRect 
            # 框出人脸 
           cv2..rectangle(frame, (x, y), (x + h, y + w),(0,255,0), 3) 
       cv2..imshow("frame",frame)
        ifcv2..waitKey(1) & 0xFF == ord('q'):
            break
# 5. 释放资源
cap.release()  
cv2..destroyAllWindows()
```

