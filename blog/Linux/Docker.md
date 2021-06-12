- [ 容器生命周期管理](#head1)
	- [ 创建一个新的容器并运行一个命令：run](#head2)
	- [ 暂停/恢复容器中进程：pause/unpause](#head3)
	- [ 创建容器：create](#head4)
	- [ 进入容器：exec](#head5)
	- [ inspect](#head6)
	- [ top](#head7)
	- [ attach](#head8)
	- [ events](#head9)
	- [ logs](#head10)
	- [ wait](#head11)
	- [ export](#head12)
	- [ port](#head13)
- [ 容器rootfs命令](#head14)
	- [ 从容器创建一个新的镜像：commit](#head15)
	- [ push](#head16)
	- [ 推送镜像到仓库](#head17)
	- [ search](#head18)
- [ 本地镜像管理](#head19)
	- [ 查看镜像列表：images](#head20)
	- [ tag](#head21)
	- [ 使用Dockerfile构建镜像：build](#head22)
	- [ 从tar加载镜像：load](#head23)
- [Add the package repositories](#head24)



![](https://upload-images.jianshu.io/upload_images/18339009-a16ddde40bfc6f50.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

ubuntu安装 Docker 的时候需在管理员的账号下操作。
```wget -qO- https://get.docker.com/ | sh```
安装完成之后，以 sudo 启动 Docker 服务。
``` sudo service docker start```

### <span id="head1"> 容器生命周期管理</span>

#### <span id="head2"> 创建一个新的容器并运行一个命令：run</span>
https://www.runoob.com/docker/docker-run-command.html
```docker run -d(后台运行不阻塞shell） -p 80:80（指定容器端口映射，内部：外部） <mirror name>:<version>```

>- -d：后台运行容器
>- -h: 指定容器的hostname；
>  -p：主机端口号:容器端口号```容器内外端口映射
>  --name name：指定容器名称
>  -v <外部 dir>:< 内部 dir>：目录映射1
>  <mirror name>:<version>：指定映像版本
>  -it：在容器启动后直接进入，-i：交互式操作，一个是 -t 终端  后面加/bin/bash
>  --link：将另一个容器映射到本容器，进行通信
>  --restart=always：因某种错误而停止运行时，能够自动重启
>
>`--rm`：这个参数是说容器退出后随之将其删除
>
>`bash`：放在镜像名后的是 **命令**，这里我们希望有个交互式 Shell，因此用的是 `bash`。
>
>

#### 启动/停止/重启容器：start/stop/restart
https://www.runoob.com/docker/docker-start-stop-restart-command.html
#### 杀掉容器：kill
https://www.runoob.com/docker/docker-kill-command.html
快速停止容器



#### 删除容器：rm
https://www.runoob.com/docker/docker-rm-command.html
```docker rm -f container_id```

如果容器还在运行，则会删除失败，应先结束掉容器：



#### <span id="head3"> 暂停/恢复容器中进程：pause/unpause</span>
https://www.runoob.com/docker/docker-pause-unpause-command.html
如果只是希望容器暂停工作一段时间，比如对容器的文件系统大打个快照，或者 docker host 需要使用 CPU，这是可以执行 docker pause 将其暂停。处于暂停状态的容器不会占用 CPU，直到通过 docker unpause 恢复运行。

#### <span id="head4"> 创建容器：create</span>
https://www.runoob.com/docker/docker-create-command.html



#### <span id="head5"> 进入容器：exec</span>
https://www.runoob.com/docker/docker-exec-command.html
```docker exec -it container_id bash```



### 容器操作

#### 查看正在运行的容器：ps
https://www.runoob.com/docker/docker-ps-command.html
```docker ps```

#### <span id="head6"> inspect</span>
https://www.runoob.com/docker/docker-inspect-command.html
#### <span id="head7"> top</span>
https://www.runoob.com/docker/docker-top-command.html
#### <span id="head8"> attach</span>
https://www.runoob.com/docker/docker-attach-command.html
#### <span id="head9"> events</span>
https://www.runoob.com/docker/docker-events-command.html
#### <span id="head10"> logs</span>
https://www.runoob.com/docker/docker-logs-command.html
#### <span id="head11"> wait</span>
https://www.runoob.com/docker/docker-wait-command.html
#### <span id="head12"> export</span>
https://www.runoob.com/docker/docker-export-command.html
#### <span id="head13"> port</span>
https://www.runoob.com/docker/docker-port-command.html

### <span id="head14"> 容器rootfs命令</span>

#### <span id="head15"> 从容器创建一个新的镜像：commit</span>
https://www.runoob.com/docker/docker-commit-command.html)
```docker commit container_id  mirror_name```我们修改过后的容器保存成镜像，重新运行，它可以保持这个变化
>-a :提交的镜像作者；
-c :使用Dockerfile指令来创建镜像；
-m :提交时的说明文字；
-p :在commit时，将容器暂停。
注意：通过commint的形式保存现场为一个新的镜像虽然也能直观的达到构建新镜像的目的，但是实际操作中，并不推荐这种形式，因为1.commit操作不仅会把有用的修改保存下来，对一些无关的修改也会保存下来（每一个命令行操作都会生成存储如ls操作）就会导致镜像比较臃肿；2.因为commit操作属于黑箱操作，后续如果有什么问题维护起来会比较麻烦。**建议commit仅作为保留现场的手段，然后通过修改dockerfile构建镜像。**

#### cp
https://www.runoob.com/docker/docker-cp-command.html
#### diff
https://www.runoob.com/docker/docker-diff-command.html

### 镜像仓库

#### login
https://www.runoob.com/docker/docker-login-command.html
#### 拉取或者更新镜像：pull
https://www.runoob.com/docker/docker-pull-command.html
```docker pull nginx（等效于nginx:latest）```

#### <span id="head16"> push</span>
https://www.runoob.com/docker/docker-push-command.html

#### <span id="head17"> 推送镜像到仓库</span>

```
docker push registry.cn-shanghai.aliyuncs.com/test/pytorch:myversion
```

#### <span id="head18"> search</span>
https://www.runoob.com/docker/docker-search-command.html
命令可以搜索 Docker Hub 中的镜像。

### <span id="head19"> 本地镜像管理</span>

#### <span id="head20"> 查看镜像列表：images</span>
https://www.runoob.com/docker/docker-images-command.html

```docker images ```
#### 删除镜像：rmi
https://www.runoob.com/docker/docker-rmi-command.html

```docker rmi mirror_name```
#### <span id="head21"> tag</span>
https://www.runoob.com/docker/docker-tag-command.html

有时需要对临时版本，或者节点版本做一个标记保留，打TAG标签非常好用，并不会额外占用空间

```
docker tag registry.cn-shanghai.aliyuncs.com/test/pytorch:myversion my_tmp_version:0.1
```

#### <span id="head22"> 使用Dockerfile构建镜像：build</span>
https://www.runoob.com/docker/docker-build-command.html
```docker build -t mirror_name 存放Dockerfile的文件夹```

注意一般文件名命名为Dockerfile 无后缀名，如果命名为其他名字，构建时需要额外指定文件名

如要指定dockerfile :

```
docker build -f ./dockerfile -t registry.cn-shanghai.aliyuncs.com/target:test .
registry.~~~是你在阿里云容器仓库开通的镜像仓库的公网地址，用自己仓库地址替换。地址后面的：1.0为自己指定的版本号，用于区分每次build的镜像。最后的.是构建镜像的路径，不可以省掉
```

- **--build-arg=[] :**设置镜像创建时的变量；

- **--cpu-shares :**设置 cpu 使用权重；

- **--cpu-period :**限制 CPU CFS周期；

- **--cpu-quota :**限制 CPU CFS配额；

- **--cpuset-cpus :**指定使用的CPU id；

- **--cpuset-mems :**指定使用的内存 id；

- **--disable-content-trust :**忽略校验，默认开启；

- **-f :**指定要使用的Dockerfile路径；

- **--force-rm :**设置镜像过程中删除中间容器；

- **--isolation :**使用容器隔离技术；

- **--label=[] :**设置镜像使用的元数据；

- **-m :**设置内存最大值；

- **--memory-swap :**设置Swap的最大值为内存+swap，"-1"表示不限swap；

- **--no-cache :**创建镜像的过程不使用缓存；

- **--pull :**尝试去更新镜像的新版本；

- **--quiet, -q :**安静模式，成功后只输出镜像 ID；

- **--rm :**设置镜像成功后删除中间容器；

- **--shm-size :**设置/dev/shm的大小，默认值是64M；

- **--ulimit :**Ulimit配置。

- **--squash :**将 Dockerfile 中所有的操作压缩为一层。

- **--tag, -t:** 镜像的名字及标签，通常 name:tag 或者 name 格式；可以在一次构建中为一个镜像设置多个标签。

- **--network:** 默认 default。在构建期间设置RUN指令的网络模式

#### Dockerfile

  ```
## 从天池基础镜像构建(from的base img 根据自己的需要更换，建议使用天池open list镜像链接：https://tianchi.aliyun.com/forum/postDetail?postId=67720)
FROM registry.cn-shanghai.aliyuncs.com/tcc-public/pytorch:1.4-cuda10.1-py3

##安装依赖包,pip包请在requirements.txt添加
#RUN pip install --no-cache-dir -r requirements.txt -i https://pypi.tuna.tsinghua.edu.cn/simple

## 把当前文件夹里的文件构建到镜像的//workspace目录下,并设置为默认工作目录
ADD math.py /workspace
ADD run.sh /workspace
WORKDIR /workspace

## 镜像启动后统一执行 sh run.sh
CMD ["sh", "run.sh"]
  ```



#### history

https://www.runoob.com/docker/docker-history-command.html
#### 镜像保存为tar：save
https://www.runoob.com/docker/docker-save-command.html

```docker save mirror_name > .tar```
#### <span id="head23"> 从tar加载镜像：load</span>
https://www.runoob.com/docker/docker-load-command.html
```docker load <  .tar```

#### import
https://www.runoob.com/docker/docker-import-command.html

### info|version

#### info
https://www.runoob.com/docker/docker-info-command.html
#### version
https://www.runoob.com/docker/docker-version-command.html

# 杂

## 如果你本地有gpu，请继续执行如下命令以支持gpu调用：

```
# <span id="head24">Add the package repositories</span>
distribution=$(. /etc/os-release;echo $ID$VERSION_ID)
curl -s -L https://nvidia.github.io/nvidia-docker/gpgkey | sudo apt-key add -
curl -s -L https://nvidia.github.io/nvidia-docker/$distribution/nvidia-docker.list | sudo tee /etc/apt/sources.list.d/nvidia-docker.list
sudo apt-get update && sudo apt-get install -y nvidia-container-toolkit
sudo systemctl restart docker
```



<!-- more -->