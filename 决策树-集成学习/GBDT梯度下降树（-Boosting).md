GBDT（Gradient Boosting Decision Tree）是一种迭代的决策树算法，该算法由多棵决策树组成。GBDT 是被公认的泛化能力较强的算法。

# 思想

GBDT 由三个概念组成：Regression Decision Tree（即 DT）、Gradient Boosting（即 GB），和 Shrinkage（一个重要演变）

## 回归树（Regression Decision Tree）

如果认为 GBDT 有很多分类树那就大错特错了（虽然调整后也可以分类）。对于**分类树而言，其值加减无意义**（如性别），而对于**回归树而言，其值加减才是有意义的**（如说年龄）。GBDT 的**核心在于累加所有树的结果作为最终结果，所以GBDT 中的树都是回归树，不是分类树**，这一点相当重要。

回归树在分枝时会穷举每一个特征的每个阈值以找到最好的分割点，衡量标准是最小化均方误差。

## 梯度迭代（Gradient Boosting）

上面说到 GBDT 的核心在于累加所有树的结果作为最终结果，**GBDT 的每一棵树都是以之前树得到的残差来更新目标值，这样每一棵树的值加起来即为 GBDT 的预测值**。
![](https://upload-images.jianshu.io/upload_images/18339009-b961b7d246e7ed7e.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

![](https://upload-images.jianshu.io/upload_images/18339009-a101b0f0d5942da4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



GBDT 的 Boosting 不同于 Adaboost 的 Boosting，**GBDT 的每一步残差计算其实变相地增大了被分错样本的权重，而对于分对样本的权重趋于 0**，这样后面的树就能专注于那些被分错的样本。

## 缩减（Shrinkage）

Shrinkage 的思想认为，每走**一小步逐渐逼近结果的效果要比每次迈一大步很快逼近结果的方式更容易避免过拟合**。即它并不是完全信任每一棵残差树。

![](https://upload-images.jianshu.io/upload_images/18339009-a67276ac07fd47c4.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


Shrinkage 不直接用残差修复误差，而是只修复一点点，把大步切成小步。本质上 **Shrinkage 为每棵树设置了一个 weight，累加时要乘以这个 weight，当 weight 降低时，基模型数会配合增大**。

# 优缺点

## 优点
1.  可以**自动进行特征组合**，拟合非线性数据；
2.  可以灵活处理各种类型的数据。

## 缺点
1. **对异常点敏感**。

# 与 Adaboost 的对比

## 相同：

1.  都是**Boosting 家族成员，使用弱分类器**；
2.  都使用**前向分布算法**；

##　不同：

1.  **迭代思路不同**：Adaboost 是通过**提升错分数据点的权重来弥补模型的不足（利用错分样本）**，而 GBDT 是通过**算梯度来弥补模型的不足（利用残差）**；
2.  **损失函数不同**：AdaBoost 采用的是**指数损失**，GBDT 使用的是**绝对损失或者 Huber 损失函数**；
