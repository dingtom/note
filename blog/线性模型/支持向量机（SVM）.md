- [ SVM 原理](#head1)
- [SVM 为什么采用间隔最大化](#head2)
- [ SVM的目标（硬间隔）：](#head3)
- [ 求解目标（硬间隔）：](#head4)
- [为什么要将求解 SVM 的原始问题转换为其对偶问题](#head5)
- [为什么 SVM 要引入核函数](#head6)
- [ 为什么SVM对缺失数据敏感](#head7)
- [SVM 核函数之间的区别](#head8)
- [ SVM如何处理多分类问题？](#head9)
- [ SVM的特点](#head10)
- [ SVM和LR的区别](#head11)
	- [ 同](#head12)
	- [ 不同](#head13)
- [ sklearn.svm.SVC](#head14)
	- [ 导入库](#head15)
	- [ 导入数据](#head16)
	- [ 拆分数据集为训练集合和测试集合](#head17)
	- [ 特征量化](#head18)
	- [ 适配SVM到训练集合](#head19)
	- [ 预测测试集合结果](#head20)
	- [ 创建混淆矩阵](#head21)
	- [ 训练集合结果可视化](#head22)
	- [ 测试集合结果可视化](#head23)
支持向量机既~~可用于回归也可用于分类~~。
![](https://upload-images.jianshu.io/upload_images/18339009-178802060b021361?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
# <span id="head1"> SVM 原理</span>

SVM 是一种~~二类分类模型~~。它的基本模型是在特征空间中~~寻找间隔最大化的分离超平面~~的线性分类器。

*   当训练样本~~线性可分时，通过硬间隔最大化~~，学习一个线性分类器，即~~线性可分支持向量机~~；
*   当训练数据~~近似线性可分时，引入松弛变量，通过软间隔最大化~~，学习一个线性分类器，即~~线性支持向量机~~；
*   当训练数据~~线性不可分时，通过使用核技巧及软间隔最大化~~，学习~~非线性支持向量机~~。

以上各种情况下的数学推到应当掌握，硬间隔最大化（几何间隔）、学习的对偶问题、软间隔最大化（引入松弛变量）、非线性支持向量机（核技巧）。

# <span id="head2">SVM 为什么采用间隔最大化</span>

当训练数据线性可分时，~~存在无穷个分离超平面可以将两类数据正确分开~~。感知机利用误分类最小策略，求得分离超平面，不过此时的解有无穷多个。线性可分支持向量机~~利用间隔最大化求得最优分离超平面，这时，解是唯一的~~。另一方面，此时的分隔超平面所产生的分类结果是~~最鲁棒~~的，对未知实例的泛化能力最强。可以借此机会阐述一下几何间隔以及函数间隔的关系。

# <span id="head3"> SVM的目标（硬间隔）：</span>

有两个目标：第一个是~~使间隔最大化~~，第二个是使~~样本正确分类~~，由此推出目标函数：

# <span id="head4"> 求解目标（硬间隔）：</span>
从上面的公式看出，这是一个有约束条件的最优化问题，用拉格朗日函数来解决。



# <span id="head5">为什么要将求解 SVM 的原始问题转换为其对偶问题</span>

1. ~~对偶问题往往更易求解，把目标函数和约束全部融入一个新的函数，即拉格朗日函数，再通过这个函数来寻找最优点~~。当我们寻找约束存在时的最优点的时候，约束的存在虽然减小了需要搜寻的范围，但是却使问题变得更加复杂。为了使问题变得易于处理
2. ~~出现样本内积形式，可以自然引入核函数，进而推广到非线性分类问题~~。
3. 把与样本维度相关的求解转换为与样本数量有关（支持向量）

为什么求解对偶问题更加高效？
因为只用求解alpha系数，而alpha系数只有支持向量才非0，其他全部为0.

alpha系数有多少个？
样本点的个数

# <span id="head6">为什么 SVM 要引入核函数</span>

核函数的定义：$K(x,y)=<ϕ(x),ϕ(y)>$，即在特征空间的内积等于它们在原始样本空间中通过核函数 K 计算的结果。除了 SVM 之外，任何将计算表示为数据点的内积的方法，都可以使用核方法进行非线性扩展。
1. ~~数据变成了高维空间中线性可分的数据~~
2. ~~不需要求解具体的映射函数，只需要给定具体的核函数即可，这样使得求解的难度大大降低~~。

# <span id="head7"> 为什么SVM对缺失数据敏感</span>

这里说的缺失数据是指缺失某些特征数据，向量数据不完整。~~SVM 没有处理缺失值的策略。而 SVM 希望样本在特征空间中线性可分，所以特征空间的好坏对SVM的性能很重要~~。缺失特征数据将影响训练结果的好坏。

# <span id="head8">SVM 核函数之间的区别</span>

一般选择线性核和高斯核，也就是线性核与 RBF 核。
1.~~线性核：主要用于线性可分的情形，参数少，速度快，对于一般数据，分类效果已经很理想了。 RBF 核：主要用于线性不可分的情形，参数多，分类结果非常依赖于参数。~~有很多人是通过训练数据的交叉验证来寻找合适的参数，不过这个过程比较耗时。 
2.~~如果 Feature 的数量很大，跟样本数量差不多，这时候选用线性核的 SVM。 如果 Feature 的数量比较小，样本数量一般，不算大也不算小，选用高斯核的 SVM~~。

**大家一定要做到自己可以推导集合间隔、函数间隔以及对偶函数，并且理解对偶函数的引入对计算带来的优势。**


# <span id="head9"> SVM如何处理多分类问题？</span>

1. 直接法，直接在目标函数上修改，~~将多个分类面的参数求解合并到一个最优化问题里面~~。看似简单但是计算量却非常的大。
2. 间接法：~~对训练器进行组合。~~其中比较典型的有一对一，和一对多。
(1) 一对多，就是对~~每个类都训练出一个分类器~~，由svm是二分类，所以将此而分类器的两类设定为目标类为一类，其余类为另外一类。这样针对k个类可以训练出k个分类器，当有一个新的样本来的时候，~~用这k个分类器来测试，那个分类器的概率高~~，那么这个样本就属于哪一类。这种方法效果不太好，bias比较高。
(2) svm一对一法（one-vs-one），针对~~任意两个类训练出一个分类器，如果有k类，一共训练出$C^2_k$个分类器~~，这样当有一个新的样本要来的时候，用这$C^2_k$个分类器来测试，~~每当被判定属于某一类的时候，该类就加一，最后票数最多的类别被认定为该样本的类。~~

# <span id="head10"> SVM的特点</span>
1. 可以解决高维数据的问题（对偶问题把维度相关问题化为了和样本数有关）
2. 全局求解，避免了局部最优
3. 相当于自带L2正则
4. 但是对缺失数据敏感，噪声敏感（关键支持向量）
5. 输入数据需要归一化（涉及计算距离，归一化提高精度；用梯度下降计算的，归一化提高速度）
6. 复杂度为O(n2),大数据不合适


# <span id="head11"> SVM和LR的区别</span>
## <span id="head12"> 同</span>
1. LR和SVM都可以处理分类问题，且一般都用于处理线性二分类问题（在改进的情况下可以处理多分类问题）
2. 两个方法都可以增加不同的正则化项，如L1、L2等等。所以在很多实验中，两种算法的结果是很接近的。
3. LR和SVM都可以用来做非线性分类，只要加核函数就好。
4. LR和SVM都是线性模型，当然这里我们不要考虑核函数
5. 都属于判别模型
## <span id="head13"> 不同</span>
1. LR是参数模型，SVM是非参数模型。
2. 从损失函数来看，区别在于逻辑回归采用的是logistical loss（极大似然损失），SVM采用的是合页损失，这两个损失函数的目的都是增加对分类影响较大的数据点的权重，减少与分类关系较小的数据点的权重。
3. 逻辑回归相对来说模型更简单，好理解，特别是大规模线性分类时比较方便。而SVM的理解和优化相对来说复杂一些，SVM转化为对偶问题后,分类只需要计算与少数几个支持向量的距离,这个在进行复杂核函数计算时优势很明显,能够大大简化模型和计算。
4. SVM只考虑局部的边界线附近的点（支持向量），LR考虑全局，SVM不直接依赖数据分布，而LR则依赖整体数据分布，因为SVM只与支持向量那几个点有关系，而LR和所有点都有关系。
5. SVM依赖正则系数，实验中需要做CV
6. SVM的损失函数自带正则，SVM是结构风险最小化模型，而LR必须在损失函数外加正则项，是经验风险最小化模型
7. LR输出具有概率意义，而SVM没有，直接为1或-1










# <span id="head14"> sklearn.svm.SVC</span>
```(C=1.0, kernel='rbf', degree=3, gamma='auto', coef0=0.0, shrinking=True, probability=False,tol=0.001, cache_size=200, class_weight=None, verbose=False, max_iter=-1, decision_function_shape=None,random_state=None)```
参数：
-  C：惩罚参数默认值是1.0
C越大，相当于惩罚松弛变量，希望松弛变量接近0，即对误分类的惩罚增大，趋向于对训练集全分对的情况，这样对训练集测试时准确率很高，但泛化能力弱。C值小，对误分类的惩罚减小，允许容错，将他们当成噪声点，泛化能力较强。
-   kernel ：核函数，默认是rbf，可以是‘linear’, ‘poly’, ‘rbf’, ‘sigmoid’, ‘precomputed’ 
0 – 线性：u'v
1 – 多项式：(gamma*u'*v + coef0)^degree
2 – RBF函数：exp(-gamma|u-v|^2)
3 –sigmoid：tanh(gamma*u'*v + coef0)
-   degree ：多项式poly函数的维度，默认是3，选择其他核函数时会被忽略。
-  gamma ： ‘rbf’,‘poly’ 和‘sigmoid’的核函数参数。默认是’auto’，则会选择1/n_features
-   coef0 ：核函数的常数项。对于‘poly’和 ‘sigmoid’有用。
-   probability ：是否采用概率估计？.默认为False
-  shrinking ：是否采用shrinking heuristic方法，默认为true
-   tol ：停止训练的误差值大小，默认为1e-3
-   cache_size ：核函数cache缓存大小，默认为200
-   class_weight ：类别的权重，字典形式传递。设置第几类的参数C为weight*C(C-SVC中的C)
-  verbose ：允许冗余输出？
-  max_iter ：最大迭代次数。-1为无限制。
-  decision_function_shape ：‘ovo’, ‘ovr’ or None, default=None3
-   random_state ：数据洗牌时的种子值，int值

主要调节的参数有：C、kernel、degree、gamma、coef0。

```python
## <span id="head15"> 导入库</span>
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
## <span id="head16"> 导入数据</span>
dataset = pd.read_csv('Social_Network_Ads.csv')
X = dataset.iloc[:, [2, 3]].values
y = dataset.iloc[:, 4].values


## <span id="head17"> 拆分数据集为训练集合和测试集合</span>

from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.25, random_state = 0)


## <span id="head18"> 特征量化</span>

from sklearn.preprocessing import StandardScaler
sc = StandardScaler()
X_train = sc.fit_transform(X_train)
X_test = sc.fit_transform(X_test)


## <span id="head19"> 适配SVM到训练集合</span>

from sklearn.svm import SVC
classifier = SVC(kernel = 'linear', random_state = 0)
classifier.fit(X_train, y_train)

## <span id="head20"> 预测测试集合结果</span>

y_pred = classifier.predict(X_test)

## <span id="head21"> 创建混淆矩阵</span>

from sklearn.metrics import confusion_matrix
cm = confusion_matrix(y_test, y_pred)
## <span id="head22"> 训练集合结果可视化</span>

from matplotlib.colors import ListedColormap
X_set, y_set = X_train, y_train
X1, X2 = np.meshgrid(np.arange(start = X_set[:, 0].min() - 1, stop = X_set[:, 0].max() + 1, step = 0.01),
                     np.arange(start = X_set[:, 1].min() - 1, stop = X_set[:, 1].max() + 1, step = 0.01))
plt.contourf(X1, X2, classifier.predict(np.array([X1.ravel(), X2.ravel()]).T).reshape(X1.shape),
             alpha = 0.75, cmap = ListedColormap(('red', 'green')))
plt.xlim(X1.min(), X1.max())
plt.ylim(X2.min(), X2.max())
for i, j in enumerate(np.unique(y_set)):
    plt.scatter(X_set[y_set == j, 0], X_set[y_set == j, 1],
                c = ListedColormap(('red', 'green'))(i), label = j)
plt.title('SVM (Training set)')
plt.xlabel('Age')
plt.ylabel('Estimated Salary')
plt.legend()
plt.show()
## <span id="head23"> 测试集合结果可视化</span>
from matplotlib.colors import ListedColormap
X_set, y_set = X_test, y_test
X1, X2 = np.meshgrid(np.arange(start = X_set[:, 0].min() - 1, stop = X_set[:, 0].max() + 1, step = 0.01),
                     np.arange(start = X_set[:, 1].min() - 1, stop = X_set[:, 1].max() + 1, step = 0.01))
plt.contourf(X1, X2, classifier.predict(np.array([X1.ravel(), X2.ravel()]).T).reshape(X1.shape),
             alpha = 0.75, cmap = ListedColormap(('red', 'green')))
plt.xlim(X1.min(), X1.max())
plt.ylim(X2.min(), X2.max())
for i, j in enumerate(np.unique(y_set)):
    plt.scatter(X_set[y_set == j, 0], X_set[y_set == j, 1],
                c = ListedColormap(('red', 'green'))(i), label = j)
plt.title('SVM (Test set)')
plt.xlabel('Age')
plt.ylabel('Estimated Salary')
plt.legend()
plt.show()
```
