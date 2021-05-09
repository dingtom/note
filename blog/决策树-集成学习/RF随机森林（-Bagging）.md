- [ 思想](#head1)
- [ 优缺点](#head2)
	- [ 优点](#head3)
	- [ 缺点：](#head4)
- [ sklearn](#head5)
	- [ 导入数据集](#head6)
	- [ 将数据集拆分成训练集和测试集](#head7)
	- [ 特征缩放](#head8)
		- [ 调试训练集的随机森林](#head9)
	- [ 预测测试集结果](#head10)
Random Forest（随机森林），用**随机的方式建立一个森林**。RF 算法由很多决策树组成，每一棵决策树之间没有关联。建立完森林后，当有新样本进入时，每棵决策树都会**分别进行预测，然后基于投票法**给出分类结果。
>应用场景:
>1. 数据维度相对低（几十维），同时对准确性有较高要求时。
>2. 因为不需要很多参数调整就可以达到不错的效果，基本上不知道用什么方法的时候都可以先试一下随机森林。

# <span id="head1"> 思想</span>

Random Forest（随机森林）是**Bagging 的扩展变体**，它在以决策树为基学习器构建 Bagging 集成的基础上，进一步在决策树的训练过程中引入了**随机特征选择**，因此可以概括 RF 包括四个部分：

1.  **随机选择样本**（放回抽样）；
2.  **随机选择特征**；
3.  构建决策树；
4.  随机森林投票（平均）。

随机选择样本和 Bagging 相同，采用的是 **Bootstrap 自助采样法**；
随机选择特征是指在**每个节点在分裂过程中都是随机选择特征的**（区别与每棵树随机选择一批特征）。

这种随机性导致随机森林的**偏差会有稍微的增加**（相比于单棵不随机树），但是由于随机森林的“平均”特性，会使得它的**方差减小**，而且方差的减小补偿了偏差的增大，因此总体而言是更好的模型。

随机采样由于**引入了两种采样方法保证了随机性，所以每棵树都是最大可能的进行生长就算不剪枝也不会出现过拟合**。

# <span id="head2"> 优缺点</span>

## <span id="head3"> 优点</span>

1.  在数据集上表现良好，相对于其他算法有较大的优势，由于采用了随机采样，训练出的模型的**方差小，泛化能力强**
2.  易于**并行化**，在**大数据集上有很大的优势**；
3.  能够**处理高维度数据，不用做特征选择**(由于可以随机选择决策树节点划分特征)
4. 在训练后，可以**给出各个特征对于输出的重要性**
5. 相对于Boosting系列的Adaboost和GBDT， **RF实现比较简单**。
6. **对部分特征缺失不敏感。**

## <span id="head4"> 缺点：</span>
在某些噪音比较大的样本集上，**RF模型容易陷入过拟合。**
**取值划分比较多的特征容易对RF的决策产生更大的影响**，从而影响拟合的模型的效果。





# <span id="head5"> sklearn</span>
```sklearn.ensemble.RandomForestClassifier(n_estimators=100, criterion='gini', max_depth=None, min_samples_split=2, min_samples_leaf=1, min_weight_fraction_leaf=0.0, max_features='auto', max_leaf_nodes=None, min_impurity_decrease=0.0, min_impurity_split=None, bootstrap=True, oob_score=False, n_jobs=None, random_state=None, verbose=0, warm_start=False, class_weight=None)```
- n_estimators：森林中决策树的个数，	默认值100
- criterion：对特征的评价标准，分类对应的gini或信息增益；回归对应 msepumae
- max_depth：决策树最大深度，**值越大，模型能学习到的信息越多，越容易过拟合**
- min_samples_split：内部节点再划分所需最小样本数 如果某节点的样本数少于该值，则不会继续再尝试选择最优特征来划分，	默认值2，**值越大，决策树越简单，越不容易过拟合**
- min_samples_leaf：决策树叶结点最小样本数量，默认值1，**值越大，叶子节点越容易被被剪枝，决策树越简单越不易过拟合**
- min_weight_fraction_leaf：决策树叶结点最小加权样本数量，默认0,**样本较多缺失时，可适当增大，样本有缺失值或分布类别偏差很大时引入**
- max_features：划分时考虑的最大特征数，auto(defult): 划分时最多考虑$ \sqrt{N}$个特征，**值越大，模型能学习到的信息越多，越容易过拟合**
- max_leaf_nodes：最大叶子节点数，可以防止过拟合，**值越小，叶子节点个数越少，可以防止过拟合**，默认None,特征较多时，可通过交叉验证设置相应值
- min_impurity_decrease：节点划分最小不纯度，小于阈值时，该节点即为叶子节点，不用设置，默认值1e-7
- bootstrap：是否进行有放回取样
- oob_score：采用袋外样本来评估模型的好坏，默认值False。通过未参加训练的样本估计模型效果
- n_jobs：处理器数量
- random_state：随机种子
- verbose：控制输出
- warm_start：是否使用之前的输出
- class_weight：类别权重


```Python
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

## <span id="head6"> 导入数据集</span>

dataset = pd.read_csv('../datasets/Social_Network_Ads.csv')
X = dataset.iloc[:, [2, 3]].values
y = dataset.iloc[:, 4].values

## <span id="head7"> 将数据集拆分成训练集和测试集</span>

from sklearn.model_selection import train_test_split
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size = 0.25, random_state = 0)

## <span id="head8"> 特征缩放</span>

from sklearn.preprocessing import StandardScaler
sc = StandardScaler()
X_train = sc.fit_transform(X_train)
X_test = sc.transform(X_test)

### <span id="head9"> 调试训练集的随机森林</span>

from sklearn.ensemble import RandomForestClassifier
classifier = RandomForestClassifier(n_estimators = 10, criterion = 'entropy', random_state = 0)
classifier.fit(X_train, y_train)

## <span id="head10"> 预测测试集结果</span>
```python
y_pred = classifier.predict(X_test)

## 生成混淆矩阵，也称作误差矩阵
from sklearn.metrics import confusion_matrix
cm = confusion_matrix(y_test, y_pred)

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
plt.title('Random Forest Classification (Training set)')
plt.xlabel('Age')
plt.ylabel('Estimated Salary')
plt.legend()
plt.show()

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
plt.title('Random Forest Classification (Test set)')
plt.xlabel('Age')
plt.ylabel('Estimated Salary')
plt.legend()
plt.show()
```

