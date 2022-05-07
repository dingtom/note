





![](https://upload-images.jianshu.io/upload_images/18339009-61f9cf3c7b3600d2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

# 回归
## RMSE（Root Mean Square Error）均方根误差
衡量观测值与真实值之间的偏差。常用来作为机器学习模型预测结果衡量的标准。**如果存在个别偏离程度非常大的离群点（ Outlier）时，即使离群点数量非常少，也会让RMSE指标变得很差。**
 $RMSE = \sqrt{\frac{1}{m} 
 \sum_{i=1}^{m}(\hat{y_i}-y_i)^2} $ 

## MSE（Mean Square Error）均方误差
通过平方的形式便于求导，所以常被用作线性回归的损失函数。
$MSE = \frac{1}{m} 
  \sum_{i=1}^{m} (\hat{y_i}-y_i)^2 $ 
>**L2 loss对异常敏感**，用了MSE为代价函数的模型因为要最小化这个异常值带来的误差，就会尽量贴近异常值，也就是对outliers（异常值）赋予更大的权重。这样就会影响总体的模型效果。

## MAE（Mean Absolute Error）平均绝对误差
是绝对误差的平均值。可以更好地反映预测值误差的实际情况。
$MAE = \frac{1}{m} 
  \sum_{i=1}^{m} |\hat{y_i}-y_i| $ 
>相比MSE来说，MAE在数据里有不利于预测结果**异常值的情况下鲁棒性更好**。

## SD（Standard Deviation）标准差
方差的算术平均根。用于衡量**一组数值的离散程度**。
$SD = \sqrt{\frac{1}{m} 
  \sum_{i=1}^{m} (avg(x)-x_i)^2} $ 
## R2(R- Square）拟合优度
R2=SSR/SST=1-SSE/SST
其中：SST=SSR+SSE，

SST(total sum of squares)为总离差平方和，$S S_{\text {tot}}=\sum\left(y_{i}-\overline{y}_{i}\right)^{2}$
SSR(regression sum of squares)为回归平方和，$S S_{\text {reg}}=\sum\left(\hat{y_{i}}-\overline{y}_{i}\right)^{2}$
SSE(error sum of squares) 为残差平方和，$S S_{\text {res}}=\sum\left(y_{i}-\hat{y}_{i}\right)^{2}$

其中$\overline{y}$表示$y$的平均值得到$R^2$表达式为：
$R^{2}=1-\frac{S S_{\text {res}}}{S S_{\text {tot}}}=1-\frac{\sum\left(y_{i}-\hat{y}_{i}\right)^{2}}{\sum\left(y_{i}-\overline{y}\right)^{2}}$

**$R^2$因变量的变异能通过回归关系被由自変量解释的比例取值范国是0~1，R越近1表明回归平方和占总平方和的比例越大回归线与各观则点越接近**，回归的拟合程度就越好。所以R也称为拟合优度（ Goodness of Fit）的统计量


# Error = Bias + Variance
Error反映的是整个模型的准确度，Bias反映的是模型在样本上的输出与真实值之间的误差，即模型本身的精准度，Variance反映的是模型**每一次输出结果与模型输出期望之间的误差**，即模型的稳定性。
# 分类

![](https://upload-images.jianshu.io/upload_images/18339009-c549d3e0714b4c33.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



![](https://upload-images.jianshu.io/upload_images/18339009-7de651cf33176560.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
第一个字母T或F，代表这个分类结果是否正确，第二个字母P或N，代表分类器认为是正例还是负例。


# 1.准确率（accuracy）
所有预测正确的样本/总的样本 = （TP+TN）/总

```
from sklearn.metrics import accuracy
accuracy = accuracy_score(y_test, y_predict)
```

# 2.查准率（precision)
**预测为正的样本中有多少是真的正样本**。两种可能，一种就是把正类预测为正类(TP)，另一种就是把负类预测为正类(FP)
$TPR = \frac{TP}{TP+FP}$

```
from sklearn.metrics import precision_score
precision = precision_score(y_test, y_predict)
```
# 3.查全率/召回率(recall)
**样本中的正样本有多少被预测正确了**。两种可能，一种是把原来的正类预测成正类(TP)，另一种就是把原来的正类预测为负类(FN)：
$FPR = \frac{TP}{TP+FN}$

```
from sklearn.metrics import recall_score
recall = recall_score(y_test, y_predict)
#recall得到的是一个list，是每一类的召回率
```
# 4.F1
是**准确率和召回率**的调和平均
$$
F_{1}=2 \cdot \frac{\text { precision } \cdot \text {recall}}{\text {precision}+\text {recall}}
$$

$F1=\frac{ 2TP }{ 2TP+FP+FN }$
![](https://upload-images.jianshu.io/upload_images/18339009-68c5cfb068834c48.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
```
from sklearn.metrics import f1_score
f1_score(y_test, y_predict)
```
>在一个总样本中，正样本占90%，负样本占10%，样本是严重不平衡的，只需要将全部样本预测为正样本
>准确率为90%
查准率为90%
召回率100%
F1 为18/19


#　5.PR曲线

>ROC-AUC兼顾正负例，正负不均衡时可以用，鲁棒
>PR曲线只关注正例
>**正负样本数量往往很不均衡。P-R曲线的变化就会非常大，而ROC曲线则能够更加稳定地反映模型本身的好坏。**
如果研究者希望更多地看到模型在**特定数据集上的表现，P-R曲线则能够更直观地反映其性能。**

PR曲线是准确率和召回率的点连成的线。
![曲线越靠近右上角性能越好](https://upload-images.jianshu.io/upload_images/18339009-354497cc47a449f2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

PR曲线与ROC曲线的相同点是都采用了TPR (Recall)，都可以用AUC来衡量分类器的效果。不同点是ROC曲线使用了FPR，而PR曲线使用了Precision，
**因此PR曲线的两个指标都聚焦于正例。类别不平衡问题中由于主要关心正例**，所以在此情况下PR曲线被广泛认为优于ROC曲线。


# 6.ROC(Receiver Operating Characteristic）曲线，又称接受者操作特征曲线
通过动态地调整截断点，从最高的得分开始（实际上是从正无穷开始，对应着ROC曲线的零点），逐渐调整到最低得分，每一个截断点都会对应一个FPR和TPR，在ROC图上绘制出每个截断点对应的位置再连接所有点就得到最终的ROC曲线。

ROC的含义为概率曲线，AUC的含义为**正负类可正确分类的程度**。
![左上角最好](https://upload-images.jianshu.io/upload_images/18339009-41fc89a5e72e229c.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

>- TPR(True Positive Rate)真正例率/查准率
真实的正例中，被预测为正例的比例：TPR = TP/(TP+FN)。
>- FPR(False Positive Rate)假正例率
真实的反例中，被预测为正例的比例：FPR = FP/(TN+FP)。
>- **理想分类器TPR=1，FPR=0。ROC曲线越接近左上角，代表模型越好，即ACU接近1**
>- **截断点thresholds**
指的就是**区分正负预测结果的阈值**

# 7.AUC

**AUC值为ROC曲线所覆盖的区域面积，显然,AUC越大,分类器分类效果越好。**

AUC = 1，是完美分类器，采用这个预测模型时，不管设定什么阈值都能得出完美预测。绝大多数预测的场合，不存在完美分类器。0.5 < AUC < 1，优于随机猜测。AUC = 0.5，跟随机猜测一样。AUC < 0.5，比随机猜测还差。

计算：分别随机从正负样本集中抽取一个正样本，一个负样本，**正样本的预测值大于负样本的概率。**
>例题：对于样本 (A, B, C, D, E) , 
已知其对应的label为 (0, 1, 1 ,0 ,1)，
模型A的预估值为 (0.2, 0.4, 0.7, 0.3, 0.5), 
模型 B 的预估值为(0.1, 0.3, 0.9, 0.2, 0.5)，
模型 A 和 模型 B 的 AUC 一样
本题样本对（一个正样本，一个负样本组成一个样本对）共有3*2=6个，
分别是（B，A）（B，D）（C，A）（C，D）（E，A）（E，D）。
模型A对应概率为(0.4,0.2)，(0.4,0.3)，(0.7,0.2)，(0.7,0.3)，(0.5,0.2)，(0.5,0.3)，
可得其对应AUC为：(1+1+1+1+1+1)/6 = 1。同理，模型B也等于1。



## Binary-class classification
```
import numpy as np
np.random.seed(10)
import matplotlib.pyplot as plt
from sklearn.datasets import make_classification
from sklearn.preprocessing import label_binarize
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import roc_curve

X, y = make_classification(n_samples=80000)
# print(X[0], y[0])
# (80000, 20) (80000,)

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.5)

X_train, X_train_lr, y_train, y_train_lr = train_test_split(X_train,                                                            y_train,                                                       test_size=0.5)

from keras.models import Sequential
from keras.layers import Dense
from sklearn.metrics import auc

model = Sequential()
model.add(Dense(20, input_dim=20, activation='relu'))
model.add(Dense(40, activation='relu'))
model.add(Dense(1, activation='sigmoid'))
model.compile(loss='binary_crossentropy', optimizer='adam', metrics=['accuracy'])
model.fit(X_train, y_train, epochs=5, batch_size=100, verbose=1)

y_pred = model.predict(X_test).ravel()
print(y_pred.shape)

fpr, tpr, thresholds = roc_curve(y_test, y_pred)

roc_auc = auc(fpr, tpr)



plt.figure(1)
plt.plot([0, 1], [0, 1], 'k--')
plt.plot(fpr, tpr, label='Keras (area = {:.3f})'.format(roc_auc))
plt.xlabel('False positive rate')
plt.ylabel('True positive rate')
plt.title('ROC curve')
plt.legend(loc='best')
plt.show()
# Zoom in view of the upper left corner.
plt.figure(2)
plt.xlim(0, 0.2)
plt.ylim(0.8, 1)
plt.plot([0, 1], [0, 1], 'k--')
plt.plot(fpr, tpr, label='Keras (area = {:.3f})'.format(roc_auc))
plt.xlabel('False positive rate')
plt.ylabel('True positive rate')
plt.title('ROC curve (zoomed in at top left)')
plt.legend(loc='best')
plt.show()


# (Optional) Prediction probability density function(PDF)

import numpy as np
from scipy.interpolate import UnivariateSpline
from matplotlib import pyplot as plt

def plot_pdf(y_pred, y_test, name=None, smooth=500):
    positives = y_pred[y_test == 1]
    negatives = y_pred[y_test == 0]
    N = positives.shape[0]
    n = N//smooth
    s = positives
    p, x = np.histogram(s, bins=n) # bin it into n = N//10 bins
    x = x[:-1] + (x[1] - x[0])/2   # convert bin edges to centers
    f = UnivariateSpline(x, p, s=n)
    plt.plot(x, f(x))

    N = negatives.shape[0]
    n = N//smooth
    s = negatives
    p, x = np.histogram(s, bins=n) # bin it into n = N//10 bins
    x = x[:-1] + (x[1] - x[0])/2   # convert bin edges to centers
    f = UnivariateSpline(x, p, s=n)
    plt.plot(x, f(x))
    plt.xlim([0.0, 1.0])
    plt.xlabel('density')
    plt.ylabel('density')
    plt.title('PDF-{}'.format(name))
    plt.show()
plot_pdf(y_pred, y_test, 'Keras')
```
# 宏平均（Macro-averaging）和微平均（Micro-averaging）：

用途：用于多个类别的分类
宏平均：先计算每一类的F1，然后3类求平均。

微平均：先计算出所有类别总共的TP，FP和FN，然后按公式求。

![](https://upload-images.jianshu.io/upload_images/18339009-f1c02a2540993c29.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
![](https://upload-images.jianshu.io/upload_images/18339009-4ab30c4df81575e1.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](https://i.loli.net/2021/05/18/wUd9HlnC1jNpfim.png)

## Multi-class classification

```

from sklearn.datasets import make_classification
from sklearn.preprocessing import label_binarize
from keras.models import Sequential
from keras.layers import Dense
import numpy as np
from scipy import interp
import matplotlib.pyplot as plt
from itertools import cycle
from sklearn.model_selection import train_test_split
from sklearn.metrics import roc_curve, auc

# 标签共三类
n_classes = 3

X, y = make_classification(n_samples=80000, n_features=20, n_informative=3, n_redundant=0, n_classes=n_classes,
    n_clusters_per_class=2)
# print(X.shape, y.shape)
# print(X[0], y[0])
# (80000, 20) (80000,)
# [-1.90920853 -1.30052757 -0.76903467 -3.2546519  -0.02947816  0.14105006
#   0.43556031 -0.81300607 -0.94553296 -0.92774495  1.49041451 -0.4443121
#  -1.16342165 -0.32997815 -1.02907045 -0.39950447 -0.711287    0.51382424
#   2.88822258 -2.0935274 ] 
# 1

# Binarize the output相当于one_hot
y = label_binarize(y, classes=[0, 1, 2])
# print(y.shape, y[0])
# (80000, 3) [0 1 0]

X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.5)
model = Sequential()
model.add(Dense(20, input_dim=20, activation='relu'))
model.add(Dense(40, activation='relu'))
model.add(Dense(3, activation='softmax'))
model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])
model.fit(X_train, y_train, epochs=1, batch_size=100, verbose=1)

y_pred = model.predict(X_test)
# print(y_pred.shape)
# (40000, 3)

# Compute ROC curve and ROC area for each class
fpr = dict()
tpr = dict()
roc_auc = dict()
for i in range(n_classes):
    # scores = np.array([0.1, 0.4, 0.35, 0.8])
    # fpr, tpr, thresholds = metrics.roc_curve(y, scores, pos_label=2)
    # y 就是标准值，scores 是每个预测值对应的阳性概率，比如0.1就是指第一个数预测为阳性的概率为0.1，很显然，
    # y 和 socres应该有相同多的元素，都等于样本数。pos_label=2 是指在y中标签为2的是标准阳性标签，其余值是阴性。
    # 接下来选取一个阈值计算TPR/FPR,阈值的选取规则是在scores值中从大到小的以此选取，于是第一个选取的阈值是0.8
    # label=[1,1,2,2] scores=[0.1,0.4,0.35,0.8] thresholds=[0.8,0.4,0.35,0.1] 以threshold为0.8为例，将0.8与
    # scores 中所有值比较大小得到预测值，[0,0,0,1].对于label中两个1，其概率分别为0.1，0.4，小于阈值0.8，判定为
    # 负样本，而他们的label是1，说明他们确实是负样本，判断正确，是两个TN；两个2，对应概率为0.35，0.8，0.35小于
    # 0.8，判定为负样本，但是label是2，应该是个正样本，所以这是个FN；最后0.8>=0.8,这是个TP，所以最后的结果是
    # ：1个TP，2个TN，1个FN，0个FP
    fpr[i], tpr[i], thresholds = roc_curve(y_test[:, i], y_pred[:, i])  # (40000,)
    # print(fpr[i].shape)# (5491,)# (6562,)# (4271,)
    roc_auc[i] = auc(fpr[i], tpr[i])
    

# 计算microROC曲线和ROC面积 
# .ravel()将多维数组转换为一维数组
fpr["micro"], tpr["micro"]  , thresholds = roc_curve(y_test.ravel(), y_pred.ravel())  #  (120000,)
roc_auc["micro"] = auc(fpr["micro"], tpr["micro"])

# 计算macroROC曲线和ROC面积
# 首先，汇总所有的假阳性率
# np.unique() 该函数是去除数组中的重复数字，并进行排序之后输出。
# print(np.concatenate([fpr[i] for i in range(n_classes)]).shape) (16324,)
all_fpr = np.unique(np.concatenate([fpr[i] for i in range(n_classes)]))  # (7901,)
# 然后插值所有的ROC曲线在这一点
# np.zeros_like() 这个函数的意思就是生成一个和你所给数组a相同shape的全0数组。
mean_tpr = np.zeros_like(all_fpr)
for i in range(n_classes):
    mean_tpr += interp(all_fpr, fpr[i], tpr[i])
    
# 最后求平均值并计算AUC
mean_tpr /= n_classes
fpr["macro"] = all_fpr
tpr["macro"] = mean_tpr
roc_auc["macro"] = auc(fpr["macro"], tpr["macro"])

# Plot all ROC curves
plt.figure(1)
plt.plot(fpr["micro"], tpr["micro"], color='deeppink', linestyle=':', linewidth=4,
         label='micro-average ROC curve (area = {0:0.2f})'.format(roc_auc["micro"]))

plt.plot(fpr["macro"], tpr["macro"],color='navy', linestyle=':', linewidth=4,
         label='macro-average ROC curve (area = {0:0.2f})'.format(roc_auc["macro"]))

colors = cycle(['aqua', 'darkorange', 'cornflowerblue'])
for i, color in zip(range(n_classes), colors):
    plt.plot(fpr[i], tpr[i], color=color, linewidth=2,
             label='ROC curve of class {0} (area = {1:0.2f})'.format(i, roc_auc[i]))

plt.plot([0, 1], [0, 1], 'k--', linewidth=2)
plt.xlim([0.0, 1.0])
plt.ylim([0.0, 1.05])
plt.xlabel('False Positive Rate')
plt.ylabel('True Positive Rate')
plt.title('Some extension of Receiver Operating Characteristic to multi-class')
plt.legend(loc='best')
plt.show()


# Zoom in view of the upper left corner.
plt.figure(2)
plt.xlim(0, 0.2)
plt.ylim(0.8, 1)
plt.plot(fpr["micro"], tpr["micro"],color='deeppink', linestyle=':', linewidth=4,
         label='micro-average ROC curve (area = {0:0.2f})'.format(roc_auc["micro"]))

plt.plot(fpr["macro"], tpr["macro"],color='navy', linestyle=':', linewidth=4,
         label='macro-average ROC curve (area = {0:0.2f})'.format(roc_auc["macro"]))

colors = cycle(['aqua', 'darkorange', 'cornflowerblue'])
for i, color in zip(range(n_classes), colors):
    plt.plot(fpr[i], tpr[i], color=color, linewidth=2,
             label='ROC curve of class {0} (area = {1:0.2f})'.format(i, roc_auc[i]))

plt.plot([0, 1], [0, 1], 'k--', linewidth=2)
plt.xlabel('False Positive Rate')
plt.ylabel('True Positive Rate')
plt.title('ROC curve (zoomed in at top left)')
plt.legend(loc='best')
plt.show()
```


# 8.混淆矩阵confusion matrix：

![混淆矩阵的每一列代表了**预测类别**每一行代表了数据的**真实类别**](https://upload-images.jianshu.io/upload_images/18339009-5e76761a1c380552.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](https://upload-images.jianshu.io/upload_images/18339009-8a1b811436e18079?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

```
def plot_confusion_matrix(title, y_true, y_pred, labels):
    import matplotlib.pyplot as plt
    from sklearn.metrics import confusion_matrix
    
    cm = confusion_matrix(y_true, y_pred)
    
    # np.newaxis的作用就是在这一位置增加一个一维，这一位置指的是np.newaxis所在的位置，比较抽象，需要配合例子理解。
    # x1 = np.array([1, 2, 3, 4, 5])
    # the shape of x1 is (5,)
    # x1_new = x1[:, np.newaxis]
# now, the shape of x1_new is (5, 1)


    cm_normalized = cm.astype('float') / cm.sum(axis=1)[:, np.newaxis]
    # print (cm, '\n\n', cm_normalized)
    # [[1 0 0 0 0]                           
    #  [0 1 0 0 0]
    #  [0 0 1 0 0]
    #  [0 0 0 1 0]
    #  [0 0 0 0 1]]

    #  [[1. 0. 0. 0. 0.]
    #  [0. 1. 0. 0. 0.]
    #  [0. 0. 1. 0. 0.]
    #  [0. 0. 0. 1. 0.]
    #  [0. 0. 0. 0. 1.]]
    tick_marks = np.array(range(len(labels))) + 0.5
    #  [0.5 1.5 2.5 3.5 4.5 5.5]
    np.set_printoptions(precision=2)
    
    plt.figure(figsize=(10, 8), dpi=120)
    ind_array = np.arange(len(labels))
    x, y = np.meshgrid(ind_array, ind_array)
    # print(ind_ａrray, '\n\n', x, '\n\n', y)
    # [0 1 2 3 4 5] 

    #  [[0 1 2 3 4 5]
    #  [0 1 2 3 4 5]
    #  [0 1 2 3 4 5]
    #  [0 1 2 3 4 5]
    #  [0 1 2 3 4 5]
    #  [0 1 2 3 4 5]] 

    #  [[0 0 0 0 0 0]
    #  [1 1 1 1 1 1]
    #  [2 2 2 2 2 2]
    #  [3 3 3 3 3 3]
    #  [4 4 4 4 4 4]
    #  [5 5 5 5 5 5]]
    intFlag = 0 # 标记在图片中对文字是整数型还是浮点型
    for x_val, y_val in zip(x.flatten(), y.flatten()):
        # plt.text()函数用于设置文字说明。

        if (intFlag):
            c = cm[y_val][x_val]
            plt.text(x_val, y_val, "%d" % (c,), color='red', fontsize=8, va='center', ha='center')

        else:
            c = cm_normalized[y_val][x_val]
            if (c > 0.01):
                plt.text(x_val, y_val, "%0.2f" % (c,), color='red', fontsize=7, va='center', ha='center')
            else:
                plt.text(x_val, y_val, "%d" % (0,), color='red', fontsize=7, va='center', ha='center')
    cmap = plt.cm.binary
    if(intFlag):
        plt.imshow(cm, interpolation='nearest', cmap=cmap)
    else:
        plt.imshow(cm_normalized, interpolation='nearest', cmap=cmap)
    plt.gca().set_xticks(tick_marks, minor=True)
    plt.gca().set_yticks(tick_marks, minor=True)
    plt.gca().xaxis.set_ticks_position('none')
    plt.gca().yaxis.set_ticks_position('none')
    plt.grid(True, which='minor', linestyle='-')
    plt.gcf().subplots_adjust(bottom=0.15)
    plt.title(title)
    plt.colorbar()
    xlocations = np.array(range(len(labels)))
    plt.xticks(xlocations, labels, rotation=90)
    plt.yticks(xlocations, labels)
    plt.ylabel('Index of True Classes')
    plt.xlabel('Index of Predict Classes')
    plt.savefig('confusion_matrix.jpg', dpi=300)
    plt.show()
title='Confusion Matrix'
labels = ['A', 'B', 'C', 'F', 'G']
y_true = [1, 2, 3, 4, 5]# np.loadtxt(r'/home/dingtom/a.txt')
y_pred = [1, 2, 3, 4, 5]# np.loadtxt(r'/home/dingtom/b.txt')
plot＿confusion_matrix(title, y_true,y_pred, labels)
```
参考：
[https://github.com/Tony607/ROC-Keras/blob/master/ROC-Keras.ipynb](https://github.com/Tony607/ROC-Keras/blob/master/ROC-Keras.ipynb)



# 图像



![quicker_a44be6fb-20d3-40fa-9159-8216b432de21.png](https://s2.loli.net/2022/04/13/O41uKwk9roFP7jN.png)



## IOU（交并比）

它是模型所**预测**的检测框(bbox)和**真实**的检测框(ground truth)的**交集和并集**之间的比例。用于比较有限样本集之间的相似性与差异性。**Jaccard值越大，样本相似度越高**。

![quicker_981e58bd-cd83-4bcb-97d3-b59e6a823416.png](https://s2.loli.net/2022/03/31/YXvqJbEAxoIruaf.png)

```python
def iou_score(output, target):
    '''计算IoU指标'''
	  intersection = np.logical_and(target, output) 
    union = np.logical_or(target, output) 
    return np.sum(intersection) / np.sum(union)

# 生成随机两个矩阵测试
target = np.random.randint(0, 2, (3, 3))
output = np.random.randint(0, 2, (3, 3))

d = iou_score(output, target)
# ----------------------------
target = array([[1, 0, 0],
       			[0, 1, 1],
			    [0, 0, 1]])
output = array([[1, 0, 1],
       			[0, 1, 0],
       			[0, 0, 0]])
d = 0.4
```

## Dice系数

一种集合相似度度量指标,通常用于**计算两个样本的相似度**,值的范围0~1 ,分割结果最好时值为1 ,最差时值为0 。**Dice相似系数对mask的内部填充比较敏感**。

![quicker_ac9f04ae-c215-40f7-bffd-bf9bbfec8003.png](https://s2.loli.net/2022/03/31/JmXVnbEeHgiPsCK.png)



Dice系数与分类评价指标中的F1 score很相似

![quicker_8d5bd778-0023-4880-b554-309808a8581e.png](https://s2.loli.net/2022/04/13/7iv3pCDURLOaxqV.png)

所以，Dice系数不仅在直观上体现了target与prediction的相似程度，同时其本质上还隐含了精确率和召回率两个重要指标。

![quicker_e2a97b9b-e737-4043-93d0-76a00e22716e.png](https://s2.loli.net/2022/03/31/TwyK6WUlzjRfvu1.png)

```python
import numpy as np

def dice(output, target):
    '''计算Dice系数'''
    smooth = 1e-6 # 避免0为除数
    intersection = (output * target).sum()
    return (2. * intersection + smooth) / (output.sum() + target.sum() + smooth)

# 生成随机两个矩阵测试
target = np.random.randint(0, 2, (3, 3))
output = np.random.randint(0, 2, (3, 3))

d = dice(output, target)
# ----------------------------
target = array([[1, 0, 0],
       			[0, 1, 1],
			    [0, 0, 1]])
output = array([[1, 0, 1],
       			[0, 1, 0],
       			[0, 0, 0]])
d = 0.5714286326530524
```

## BCE损失函数

BCE损失函数（Binary Cross-Entropy Loss）是交叉熵损失函数（Cross-Entropy Loss）的一种特例，BCE Loss只应用在二分类任务中。针对分类问题，单样本的交叉熵损失为：

![quicker_46e51217-3a10-40a0-a80b-a74c7eb0ee93.png](https://s2.loli.net/2022/04/01/hbuasvVTqPFCcop.png)

```python
import torch
import torch.nn as nn

bce = nn.BCELoss()
bce_sig = nn.BCEWithLogitsLoss()

input = torch.randn(5, 1, requires_grad=True)
target = torch.empty(5, 1).random_(2)
pre = nn.Sigmoid()(input)

loss_bce = bce(pre, target)
loss_bce_sig = bce_sig(input, target)

# 同时，pytorch还提供了已经结合了Sigmoid函数的BCE损失：torch.nn.BCEWithLogitsLoss()，相当于免去了实现进行Sigmoid激活的操作。


input = tensor([[-0.2296],
        		[-0.6389],
        		[-0.2405],
        		[ 1.3451],
        		[ 0.7580]], requires_grad=True)
output = tensor([[1.],
        		 [0.],
        		 [0.],
        		 [1.],
        		 [1.]])
pre = tensor([[0.4428],
        	  [0.3455],
        	  [0.4402],
        	  [0.7933],
        	  [0.6809]], grad_fn=<SigmoidBackward>)

print(loss_bce)
tensor(0.4869, grad_fn=<BinaryCrossEntropyBackward>)

print(loss_bce_sig)
tensor(0.4869, grad_fn=<BinaryCrossEntropyWithLogitsBackward>)
```

## Focal Loss

Focal loss最初是出现在目标检测领域，主要是为了解决正负样本比例失调的问题。那么对于分割任务来说，如果存**在数据不均衡的情况，也可以借用focal loss来进行缓解**。Focal loss函数公式如下所示：

![quicker_b30327b2-4641-40a9-b9d2-53e0ed7c1ad2.png](https://s2.loli.net/2022/04/01/96vqcofAp4JxL71.png)



```python
import torch.nn as nn
import torch
import torch.nn.functional as F

class FocalLoss(nn.Module):
    def __init__(self, alpha=1, gamma=2, logits=False, reduce=True):
        super(FocalLoss, self).__init__()
        self.alpha = alpha
        self.gamma = gamma
        self.logits = logits    # 如果BEC带logits则损失函数在计算BECloss之前会自动计算softmax/sigmoid将其映射到[0,1]
        self.reduce = reduce

    def forward(self, inputs, targets):
        if self.logits:
            BCE_loss = F.binary_cross_entropy_with_logits(inputs, targets, reduce=False)
        else:
            BCE_loss = F.binary_cross_entropy(inputs, targets, reduce=False)
        pt = torch.exp(-BCE_loss)
        F_loss = self.alpha * (1-pt)**self.gamma * BCE_loss

        if self.reduce:
            return torch.mean(F_loss)
        else:
            return F_loss


FL1 = FocalLoss(logits=False)
FL2 = FocalLoss(logits=True)

inputs = torch.randn(5, 1, requires_grad=True)
targets = torch.empty(5, 1).random_(2)
pre = nn.Sigmoid()(inputs)

f_loss_1 = FL1(pre, targets)
f_loss_2 = FL2(inputs, targets)


print('inputs:', inputs)
inputs: tensor([[-1.3521],
        [ 0.4975],
        [-1.0178],
        [-0.3859],
        [-0.2923]], requires_grad=True)

print('targets:', targets)
targets: tensor([[1.],
        [1.],
        [0.],
        [1.],
        [1.]])

print('pre:', pre)
pre: tensor([[0.2055],
        [0.6219],
        [0.2655],
        [0.4047],
        [0.4274]], grad_fn=<SigmoidBackward>)

print('f_loss_1:', f_loss_1)
f_loss_1: tensor(0.3375, grad_fn=<MeanBackward0>)

print('f_loss_2', f_loss_2)
f_loss_2 tensor(0.3375, grad_fn=<MeanBackward0>)
```





## Lovász-Softmax

IoU是评价分割模型分割结果质量的重要指标，因此很自然想到能否用1−IoU（即Jaccard loss）来做损失函数，但是它是一个离散的loss，不能直接求导，所以无法直接用来作为损失函数。为了克服这个离散的问题，可以采用**Lovász extension将离散的Jaccard loss 变得连续，从而可以直接求导，使得其作为分割网络的loss function**。Lovász-Softmax相比于交叉熵函数具有更好的效果。



![quicker_b818041f-ffa0-4458-b1b3-988c1d4b3dd6.png](https://s2.loli.net/2022/04/01/JwYkyMQp19mqhRa.png)

```python
import torch
from torch.autograd import Variable
import torch.nn.functional as F
import numpy as np
try:
    from itertools import  ifilterfalse
except ImportError: # py3k
    from itertools import  filterfalse as ifilterfalse
    
# --------------------------- MULTICLASS LOSSES ---------------------------
def lovasz_softmax(probas, labels, classes='present', per_image=False, ignore=None):
    """
    Multi-class Lovasz-Softmax loss
      probas: [B, C, H, W] Variable, class probabilities at each prediction (between 0 and 1).
              Interpreted as binary (sigmoid) output with outputs of size [B, H, W].
      labels: [B, H, W] Tensor, ground truth labels (between 0 and C - 1)
      classes: 'all' for all, 'present' for classes present in labels, or a list of classes to average.
      per_image: compute the loss per image instead of per batch
      ignore: void class labels
    """
    if per_image:
        loss = mean(lovasz_softmax_flat(*flatten_probas(prob.unsqueeze(0), lab.unsqueeze(0), ignore), classes=classes)
                          for prob, lab in zip(probas, labels))
    else:
        loss = lovasz_softmax_flat(*flatten_probas(probas, labels, ignore), classes=classes)
    return loss


def lovasz_softmax_flat(probas, labels, classes='present'):
    """
    Multi-class Lovasz-Softmax loss
      probas: [P, C] Variable, class probabilities at each prediction (between 0 and 1)
      labels: [P] Tensor, ground truth labels (between 0 and C - 1)
      classes: 'all' for all, 'present' for classes present in labels, or a list of classes to average.
    """
    if probas.numel() == 0:
        # only void pixels, the gradients should be 0
        return probas * 0.
    C = probas.size(1)
    losses = []
    class_to_sum = list(range(C)) if classes in ['all', 'present'] else classes
    for c in class_to_sum:
        fg = (labels == c).float() # foreground for class c
        if (classes is 'present' and fg.sum() == 0):
            continue
        if C == 1:
            if len(classes) > 1:
                raise ValueError('Sigmoid output possible only with 1 class')
            class_pred = probas[:, 0]
        else:
            class_pred = probas[:, c]
        errors = (Variable(fg) - class_pred).abs()
        errors_sorted, perm = torch.sort(errors, 0, descending=True)
        perm = perm.data
        fg_sorted = fg[perm]
        losses.append(torch.dot(errors_sorted, Variable(lovasz_grad(fg_sorted))))
    return mean(losses)


def flatten_probas(probas, labels, ignore=None):
    """
    Flattens predictions in the batch
    """
    if probas.dim() == 3:
        # assumes output of a sigmoid layer
        B, H, W = probas.size()
        probas = probas.view(B, 1, H, W)
    B, C, H, W = probas.size()
    probas = probas.permute(0, 2, 3, 1).contiguous().view(-1, C)  # B * H * W, C = P, C
    labels = labels.view(-1)
    if ignore is None:
        return probas, labels
    valid = (labels != ignore)
    vprobas = probas[valid.nonzero().squeeze()]
    vlabels = labels[valid]
    return vprobas, vlabels


def xloss(logits, labels, ignore=None):
    """
    Cross entropy loss
    """
    return F.cross_entropy(logits, Variable(labels), ignore_index=255)

# --------------------------- HELPER FUNCTIONS ---------------------------
def isnan(x):
    return x != x
    
def mean(l, ignore_nan=False, empty=0):
    """
    nanmean compatible with generators.
    """
    l = iter(l)
    if ignore_nan:
        l = ifilterfalse(isnan, l)
    try:
        n = 1
        acc = next(l)
    except StopIteration:
        if empty == 'raise':
            raise ValueError('Empty mean')
        return empty
    for n, v in enumerate(l, 2):
        acc += v
    if n == 1:
        return acc
    return acc / n
```

# 