- [ Spark](#head1)
- [ Spark四大特点](#head2)
- [ RDD](#head3)
- [ 算子](#head4)
	- [ Transformation算子](#head5)
- [ 加入环境变量，让pycharmi直接提交yarn的时候，知道nadoop的配置在哪，可以去读取yarn的信息](#head6)
- [如果提交到集群运行, 除了主代码以外, 还依赖了其它的代码文件](#head7)
- [需要设置一个参数, 来告知spark ,还有依赖文件要同步上传到集群中](#head8)
- [参数叫做: spark.submit.pyFiles](#head9)
- [参数的值可以是 单个.py文件,   也可以是.zip压缩包(有多个依赖文件的时候可以用zip压缩后上传)](#head10)
	- [ Action算子](#head11)


# <span id="head1"> Spark</span>

**Spark是一款分布式内存计算的统一分析引擎。其特点就是对任意类型的数据进行自定义计算。**Spark的适用面非常广泛，所以，被称之为 **统一的（适用面广）的分析引擎（数据处理）**



Spark可以计算：结构化、半结构化、非结构化等各种类型的数据结构，同时也支持使用Python、Java、Scala、R以及SQL语言去开发应用程序计算数据。



RDD 是一种分布式内存抽象，其使得程序员能够在大规模集群中做内存运算，并且有一定的容错方式。而这也
是整个 Spark 的核心数据结构，Spark 整个平台都围绕着RDD进行。


| |Hadoop| Spark|
|:-:|:-:|:-:|
|类型 |基础平台, 包含计算, 存储, 调度 |纯计算工具（分布式）|
|场景 |海量数据批处理（磁盘迭代计算）|海量数据的批处理（内存迭代计算、交互式计算）、海量数据流计算|
|价格 |对机器要求低, 便宜 |对内存有要求, 相对较贵|
|编程范式 |Map+Reduce, API 较为底层, 算法适应性差| RDD组成DAG有向无环图, API 较为顶层, 方便使用|
|数据存储结构| MapReduce中间计算结果在HDFS磁盘上, 延迟大 |RDD中间运算结果在内存中 , 延迟小|
|运行方式| Task以进程方式维护, 任务启动慢 |Task以线程方式维护, 任务启动快，可批量创建提高并行能力|

尽管Spark相对于Hadoop而言具有较大优势，但Spark并不能完全替代Hadoop
- 在计算层面，Spark相比较MR（MapReduce）有巨大的性能优势，但至今仍有许多计算工具基于MR构架，比如非常成熟的Hive

- Spark仅做计算，而Hadoop生态圈不仅有计算（MR）也有存储（HDFS）和资源管理调度（YARN），HDFS和YARN仍是许多大数据体系的核心架构。

  


# <span id="head2"> Spark四大特点</span>
- 速度快

  由于Apache Spark支持内存计算，并且通过DAG（有向无环图）执行引擎支持无环数据流，所以官方宣称其在内存中的运算速度要比Hadoop的MapReduce快100倍，在硬盘中要快10倍。

  Spark 借鉴了 MapReduce 思想发展而来，保留了其分布式并行计算的优点并改进了其明显的缺陷。让中间数据存储在内存中提高了运行速度、并提供丰富的操作数据的API提高了开发速度。

- 易于使用

  Spark 的版本已经更新到 Spark 3.2.0（截止日期2021.10.13），支持了包括 Java、Scala、Python 、R和SQL语言在内的多种语言。为了兼容Spark2.x企业级应用场景，Spark仍然持续更新Spark2版本。

- 通用性强

  在 Spark 的基础上，Spark 还提供了包括Spark SQL、Spark Streaming、MLib 及GraphX在内的多个工具库，我们可以在一个应用中无缝
  地使用这些工具库。

-  运行方式

  Spark 支持多种运行方式，包括在 Hadoop 和 Mesos 上，也支持 Standalone的独立运行模式，同时也可以运行在云Kubernetes（Spark 2.3开始支持）上。对于数据源而言，Spark 支持从HDFS、HBase、Cassandra 及 Kafka 等多种途径获取数据。

  



Hadoop的基于进程的计算和Spark基于线程方式优缺点？

> 答案：Hadoop中的MR中每个map/reduce task都是一个java进程方式运行，好处在于进程之间是互相独立的，每个task独享进程资源，没有互相干扰，监控方便，但是问题在于task之间不方便共享数据，执行效率比较低。比如多个map task读取不同数据源文件需要将数据源加载到每个map task中，造成重复加载和浪费内存。而基于线程的方式计算是为了数据共享和提高执行效率，Spark采用了线程的最小的执行单位，但缺点是线程之间会有资源竞争。
>
> - 线程是CPU的基本调度单位
> - 一个进程一般包含多个线程, 一个进程下的多个线程共享进程的资源
> - 不同进程之间的线程相互不可见
> - 线程不能独立执行
> - 一个线程可以创建和撤销另外一个线程

Spark解决什么问题？

> 海量数据的计算，可以进行离线批处理以及实时流计算

Spark有哪些模块？

> 核心SparkCore、SQL计算（SparkSQL）、流计算（SparkStreaming）、图计算（GraphX）、机器学习（MLlib）
>
> 
>
> - Spark Core：Spark的核心，Spark核心功能均由Spark Core模块提供，是Spark运行的基础。Spark Core以RDD为数据抽象，提供Python、Java、Scala、R语言的API，可以编程进行海量离线数据批处理计算。
> - SparkSQL：基于SparkCore之上，提供结构化数据的处理模块。SparkSQL支持以SQL语言对数据进行处理，SparkSQL本身针对离线计算场景。同时基于SparkSQL，Spark提供了Structured Streaming模块，可以以SparkSQL为基础，进行数据的流式计算。
> - SparkStreaming：以SparkCore为基础，提供数据的流式计算功能。
> - MLlib：以SparkCore为基础，进行机器学习计算，内置了大量的机器学习库和API算法等。方便用户以分布式计算的模式进行机器学习计算。
> - GraphX：以SparkCore为基础，进行图计算，提供了大量的图计算API，方便用于以分布式计算模式进行图计算。



Spark特点有哪些？

> 速度快、使用简单、通用性强、多种模式运行

Spark的运行模式？

> • 本地模式
> • 集群模式（StandAlone、YARN、K8S）
> • 云模式
>
> 
>
> - 本地模式（单机）
>   本地模式就是以一个独立的进程，通过其内部的多个线程来模拟整个Spark运行时环境
> - Standalone模式（集群）
>   Spark中的各个角色以独立进程的形式存在，并组成Spark集群环境
> - Hadoop YARN模式（集群）
>   Spark中的各个角色运行在YARN的容器内部，并组成Spark集群环境
>
> - Kubernetes模式（容器集群）
> Spark中的各个角色运行在Kubernetes的容器内部，并组成Spark集群环境
> - 云服务模式（运行在云平台上）



Spark的运行角色（对比YARN）？

> > 从2个层面划分：
> > 资源管理层面：
> >
> > - 管理者： Spark是 Master角色，YARN是 Resourcemanager
> > - 工作中： Spark是 Workers角色，YARN是 Nodemanager
> >
> > 从任务执行层面：
> >
> > - 某任务管理者： Spark是 Driver角色，YARN是 Applicationmaster
> > - 某任务执行者： Spark是 Executor角色，YARN是容器中运行的具体工作进程
> >
> > 注：正常情况下Executor是干活的角色，不过在特殊场景下（Local模式）Driver可以即管理又干活
>
> 
>
> > YARN主要有4类角色，从2个层面去看：
> >
> > 资源管理层面
> >
> > - 集群资源管理者（Master）：ResourceManager
> >
> > - 单机资源管理者（Worker）：NodeManager
> >
> > 任务计算层面
> > - 单任务管理者（Master）：ApplicationMaster
> > - 单任务执行者（Worker）：Task（容器内计算框架的工作角色）



# <span id="head3"> RDD</span>

RDD（Resilienlt Distributed Dataset）叫做弹性分布式数据集，是Spark中最基本的数据抽象，代表一个**不可变、可分区、里面的元素可并行计算**的集合。

- Dataset：一个数据集合，用于存放数据的
- Distributed：RDD中的数据是分布式存储的，可用于分布式计算。
- Resilient：RDD中的数据可以存储在内存中或者磁盘中。
- 所有的运算以及操作都建立在 RDD 数据结构的基础之上。

RDD的五大特性

- RDD是有分区的

  RDD的分区是RDD数据存储的最小单位，l一份RDD的数据，本质上是分隔成了多个分区

- RDD的方法会作用在其所有的分区上

- RDD之间是有依赖关系(RDD有血缘关系)

- Key-Value型的RDD可以有分区器

  默认分区器：Hash分区规则，可以手动设置一个分区器(rdd.partitionBy的方法来设置)

- DD的分区规划，会尽量靠近数据所在的服务器

  在初始RDD（读取数据的时候）规划的时候分区会尽量规划到存储数据所在的服务器上因为这样可以走本地读取，避免网铬读取

# <span id="head4"> 算子</span>

分布式集合对象上的API称之为算子



## <span id="head5"> Transformation算子</span>

定义：RDD的算子，返回值仍旧是一个RDD的，称之为转换算子

特性：这类算子是Lazy懒加载的。如果没action.算子，Transformation算子是不工作的。







- flatMap：对rdd执行map操作，然后进行解除嵌套操作

- reduceByKey：针对KV型RDD,自动按照key分组，然后根据你提供的聚合逻辑，完成**组内数据**(valve)的聚合操作

![quicker_bf607a63-1c54-43cb-99a6-27b1a0b0cdcd.png](https://s2.loli.net/2022/02/23/WuPSmhzI9igHZb8.png)



- groupBy：将rdd的数据进行分组

拿到你的返回值后，将所有相同返回值的放入一个组中。每一个组是一个二元元组，key就是返回值，所有同组的数据放入一个迭代器对象中作为value

- groupByKey：针对KV型RDD,自动按照key分组
- filter：过滤想要的数据进行保留

- distinct：对RDD数据进行去重，返回新RDD

- union：2个rdd合并成1个rdd返回，只合并，不会去重

- join：对两个RDD执行JOIN操作(可实现SQL的内外连接)

**注意：join算子只能用于二元元组**

```
rdd.join(other._rdd)#内连接
rdd.LeftOuterJoin(other-rdd)#左外
rdd.right0 uterJoin(other_rdd)#右外
```

- glom：将RDD的数据，加上嵌套，这个嵌套按照分区来进行
  比如RDD数据[1,2,3,4,5]有2个分区。那么，被glom后，数据变成：[[1,2,3],[4,5]]

- sortBy：对RDD数据进行排序，基于你指定的排序依据。

- sortByKey：针对KV型RDD,按照key进行排序

  ```
  ascending:升序or降序，True升序，Falsel降序，默认是升序
  numPartitions:按照几个分区进行排序，如果全局有序，设置1
  keyfunc:排序前处理一下key，让Key以你处理的样子进行排序（不影响数据本身）
  ```

  将案例提交到YARN集群中运行
  

  ```python
  在PyCharml中直接执行
# <span id="head6"> 加入环境变量，让pycharmi直接提交yarn的时候，知道nadoop的配置在哪，可以去读取yarn的信息</span>
  os.environ['HADOOP_CONF_DIR'] = "/export/server/hadoop/etc/hadoop"
# <span id="head7">如果提交到集群运行, 除了主代码以外, 还依赖了其它的代码文件</span>
# <span id="head8">需要设置一个参数, 来告知spark ,还有依赖文件要同步上传到集群中</span>
# <span id="head9">参数叫做: spark.submit.pyFiles</span>
# <span id="head10">参数的值可以是 单个.py文件,   也可以是.zip压缩包(有多个依赖文件的时候可以用zip压缩后上传)</span>
  conf.set("spark.submit.pyFiles", "defs_19.py")
  
  在服务器上通过spark-submit提交到集群运行
  #--py-f1Les可以帮你指定你依赖的其它python代码，支持。z1p（一堆），也可以单个。py文件都行。
  /export/server/spark/bin/spark-submit --master yarn --py-files ./defs.zip ./main.py
  ```

 - 分区操作算子

   - mapPartitions：分区操作算子

   ​    一次被传递的是一整个分区的数据
   作为一个迭代器(一次性ist)对象传入过来

   - foreachPartition：和普通foreach一致，一次处理的是一整个分区数据(就是一个没有返回值的mapPartitions)

   - partitionBy：对RDD进行自定义分区操作

     ```
     rdd.partitionBy(参数1，参数2)
     -参数1重新分区后有几个分区
     -参数2自定义分区规则，函数传入
     -返回值是int分区编号从0开始
     ```

   - partition：对RDD的分区执行重新分区（仅数量

   - coalesce：对分区进行数量增减
   
     ```
     rdd.coalesce(参数1，参数2)
     参数1，分区数
     参数2,True or False。True表示允许shuffle,也就是可以加分区。False表示不允许shuffle,也就是不劭加分区，False是默认
     
     如果你改分区了
     会影响并行计算（内存迭代的并行管道数量）后面学
     分区如果增加，极大可能导致shuffle
     
     对比repartition,一般使用coalesce较多，因为加分区要写参数2这样避免写repartition的时候手抖了加分区了。
     ```
   
   - mapValues：针对二元元组RDD,对其内部的二元元组的Valve执行map操作
   
   - join：
   
   





## <span id="head11"> Action算子</span>

定义：返回值不是rdd的就是action.算子

- countByKey：统计key出现的次数(一般适用于KV型RDD)，返回字典

- collect：将RDD各个分区内的数据，统一收集到Driver中，形成一个List对象

- reduce：对RDD数据集按照你传入的逻辑进行聚合

- fold：和reduce一样，接受传入逻辑进行聚合，聚合是带有初始值的

  ```python
  这个初始值聚合，会作用在：分区内聚合、分区间聚合
  比如：[[1,2,3]，[4,5,6]，[7,8,9]]
  数据分布在3个分区
  分区1 123聚合的时候带上10作为初始值得到16
  分区2 456聚合的时候带上10作为初始值得到25
  分区3 789聚合的时候带上10作为初始值得到34
  3个分区的结果做聚合也带上初始值10，所以结果是：10+16+25+34=85
  ```

- first：取出RDD的第一个元素

- take：取RDD的前N个元素，组合成ist返回给你

- top：对RDD数据集进行降序排序，取前N个

- counts：计算RDD有多少条数据，返回值是一个数字

- takeSample：随机抽样RDD的数据

```
takeSample(参数1:True or False,参数2：采样数，参数3：随机数种子)
-参数1:TrUe表示运行取同一个数据，False表示不允许取同一个数据。和数据内容无关，是否重复表示的是同一个位置的数据
-参数2：抽样要几个
-参数3：随机数种子，这个参数传入一个数字即可，随意给
```

- takeOrdered：对RDD进行排序取前N个

```
rdd.take0 rdered(参数1，参数2)
-参数1要几个数据
-参数2对排序的数据进行更改(不会更改数据本身，只是在排序的时候换个样子)
```

- foreach：对RDD的每一个元素，执行你提供的逻辑的操作(和mp一个意思)，但是这个方法没有返回值

- saveAsTextFile：将RDD的数据写入文本文件中。支持本地写出，hdfs等文件系统。

  写出数据是跳过Driver的每个分区直接写出，每个分区所在的Executor]直接控制数据写出到目标文件系统中所以才会一个分区产生1个结果文件



**foreach、saveAsTextFile这两个算子是分区(Executor)直接执行的跳过Driver**,.由分区所在的Executorj直接执行。其余的Action.算子都会将结果发送至Driver