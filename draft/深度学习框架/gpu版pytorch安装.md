# 安装anaconda
在 [清华大学开源软件镜像站](https://mirrors.tuna.tsinghua.edu.cn/help/anaconda/)下载
```ls```
```bash  Anaconda3-5.3.1-Linux-x86_64.sh```
一路回车


# 卸载
```rm -rf /home/txp/anaconda3```
打开终端并输入：
 ```sudo gedit ~/.bashrc```
在.bashrc文件末尾删除之前添加的路径：
export PATH=/home/lq/anaconda3/bin:$PATH
保存并关闭文件
```source ~/.bashrc```

   4.关闭终端，然后再重启一个新的终端
# 安装pytorch
```conda create -n pytorch```

>3070环境安装
CUDA11.1和Cudnn8.0.4从官网安装
使用```conda install pytorch torchvision cudatoolkit=11 -c pytorch-nightly```来安装pytorch
使用tensorflow的话 可以先```conda install cudatoolkit=11``` 然后再```pip install tensorflow-gpu==1.15```
先装好tf就无法安装cudatoolkit11，conda自带的是cudatoolkit10；
先装好cudatoolkit11，再用conda装tf可能会报错，用pip不会

添加Anaconda的清华镜像
```conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/msys2/```
```conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/conda-forge/```
```conda config --add channels https://mirrors.tuna.tsinghua.edu.cn/anaconda/cloud/pytorch/```
```conda config --set show_channel_urls yes```
1.点击[此处](https://developer.nvidia.com/cuda-gpus)查询显卡是否支持CUDA的安装


2.选择CUDA进行安装即可，点击[此处](https://developer.nvidia.com/cuda-toolkit-archive)

conda 安装cuda
```conda install cudatoolkit=10.1 -c https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/free/linux-64/```



3.点击[此处](https://developer.nvidia.com/cudnn)，查看对应的 cudnn版本
![](https://upload-images.jianshu.io/upload_images/18339009-532f67fce582eae0.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)



conda安装cudnn
```conda install cudnn=7.6.5 -c https://mirrors.tuna.tsinghua.edu.cn/anaconda/pkgs/main/linux-64/```
4.[此处](https://pytorch.org/)pytorch安装命令
```conda install pytorch torchvision cudatoolkit=10.1```


测试是否安装好
 ```
import torch
print(torch.__version__)
print(torch.cuda.is_available())
```
[手动下载安装具体步骤](https://codingchaozhang.blog.csdn.net/article/details/99688839?utm_medium=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param&depth_1-utm_source=distribute.pc_relevant_t0.none-task-blog-BlogCommendFromMachineLearnPai2-1.channel_param)


# 安装tensorflow

1、创建虚拟环境：
conda create -n tensorflow
复制环境
conda create -n tensorflow_new --clone Tensorflow_old
删除
conda remove -n rcnn --all
所有环境
删除没有用的包
conda clean -p    
删除tar包  
conda clean -t      
2、激活虚拟环境：
source activate tensorflow
PS:如果要退出：输入
source deactivate tensorflow
3、下面我们在虚拟环境里安装

安装CPU版本：
```
pip3 install -i https://pypi.tuna.tsinghua.edu.cn/simple/ --upgrade tensorflow     # CPU版本
pip3 install -i https://pypi.tuna.tsinghua.edu.cn/simple/ --upgrade tensorflow-gpu #  GPU版本
```
国内镜像网站
```
阿里云 
http://mirrors.aliyun.com/pypi/simple/
中国科技大学 
https://pypi.mirrors.ustc.edu.cn/simple/
豆瓣(douban) 
http://pypi.douban.com/simple/
清华大学 
https://pypi.tuna.tsinghua.edu.cn/simple/
中国科学技术大学 
http://pypi.mirrors.ustc.edu.cn/simple/
```


