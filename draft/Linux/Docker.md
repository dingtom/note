

![](https://upload-images.jianshu.io/upload_images/18339009-a16ddde40bfc6f50.PNG?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

ubuntu安装 Docker 的时候需在管理员的账号下操作。
```wget -qO- https://get.docker.com/ | sh```
安装完成之后，以 sudo 启动 Docker 服务。
``` sudo service docker start```

### 容器生命周期管理

#### [创建一个新的容器并运行一个命令：run](https://www.runoob.com/docker/docker-run-command.html)
```docker run -d(后台运行不阻塞shell） -p 80:80（指定容器端口映射，内部：外部） nginx```
>-d：后台运行容器
-h: 指定容器的hostname；
-p：主机端口号:容器端口号```容器内外端口映射
--name name：指定容器名称
-v <外部 dir>:< 内部 dir>：目录映射
<mirror name>:<version>：指定映像版本
-it：在容器启动后直接进入
--link：将另一个容器映射到本容器，进行通信
--restart=always：因某种错误而停止运行时，能够自动重启

#### [启动/停止/重启容器：start/stop/restart](https://www.runoob.com/docker/docker-start-stop-restart-command.html)
#### [杀掉容器：kill](https://www.runoob.com/docker/docker-kill-command.html)
快速停止容器
#### [删除容器：rm](https://www.runoob.com/docker/docker-rm-command.html)

```docker rm -f container_id```
#### [暂停/恢复容器中进程：pause/unpause](https://www.runoob.com/docker/docker-pause-unpause-command.html)
如果只是希望容器暂停工作一段时间，比如对容器的文件系统大打个快照，或者 docker host 需要使用 CPU，这是可以执行 docker pause 将其暂停。处于暂停状态的容器不会占用 CPU，直到通过 docker unpause 恢复运行。

#### [创建容器：create](https://www.runoob.com/docker/docker-create-command.html)
#### [进入容器：exec](https://www.runoob.com/docker/docker-exec-command.html)
```docker exec -it container_id bash```
### 容器操作

#### [查看正在运行的容器：ps](https://www.runoob.com/docker/docker-ps-command.html)
```docker ps```
#### [inspect](https://www.runoob.com/docker/docker-inspect-command.html)
#### [top](https://www.runoob.com/docker/docker-top-command.html)
#### [attach](https://www.runoob.com/docker/docker-attach-command.html)
#### [events](https://www.runoob.com/docker/docker-events-command.html)
#### [logs](https://www.runoob.com/docker/docker-logs-command.html)
#### [wait](https://www.runoob.com/docker/docker-wait-command.html)
#### [export](https://www.runoob.com/docker/docker-export-command.html)
#### [port](https://www.runoob.com/docker/docker-port-command.html)

### 容器rootfs命令

#### [从容器创建一个新的镜像：commit](https://www.runoob.com/docker/docker-commit-command.html)
```docker commit container_id  mirror_name```我们修改过后的容器保存成镜像，重新运行，它可以保持这个变化
>-a :提交的镜像作者；
-c :使用Dockerfile指令来创建镜像；
-m :提交时的说明文字；
-p :在commit时，将容器暂停。
#### [cp](https://www.runoob.com/docker/docker-cp-command.html)
#### [diff](https://www.runoob.com/docker/docker-diff-command.html)

### 镜像仓库

#### [login](https://www.runoob.com/docker/docker-login-command.html)
#### [拉取或者更新镜像：pull](https://www.runoob.com/docker/docker-pull-command.html)
```docker pull nginx（等效于nginx:latest）```

#### [push](https://www.runoob.com/docker/docker-push-command.html)
#### [search](https://www.runoob.com/docker/docker-search-command.html)
命令可以搜索 Docker Hub 中的镜像。
### 本地镜像管理

#### [查看镜像列表：images](https://www.runoob.com/docker/docker-images-command.html)

```docker images ```
#### [删除镜像：rmi](https://www.runoob.com/docker/docker-rmi-command.html)

```docker rmi mirror_name```
#### [tag](https://www.runoob.com/docker/docker-tag-command.html)
#### [使用Dockerfile构建镜像：build](https://www.runoob.com/docker/docker-build-command.html)
```docker build -t mirror_name 存放Dockerfile的文件夹```
#### [history](https://www.runoob.com/docker/docker-history-command.html)
#### [镜像保存为tar：save](https://www.runoob.com/docker/docker-save-command.html)

```docker save mirror_name > .tar```
#### [从tar加载镜像：load](https://www.runoob.com/docker/docker-load-command.html)
```docker load <  .tar```
#### [import](https://www.runoob.com/docker/docker-import-command.html)

### info|version

#### [info](https://www.runoob.com/docker/docker-info-command.html)
#### [version](https://www.runoob.com/docker/docker-version-command.html)

<!-- more -->
