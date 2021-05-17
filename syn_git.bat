@echo off   % 关闭回显%
python toc.py
	    ::echo off将echo状态设置为off表示关闭其他所有命令(不包括本身这条命令)的回显%
                    ::@的作用就是关闭紧跟其后的一条命令的回显%
                    ::C:\Users\liang\Desktop> 就是echo off 命令的回显%
git status
git add .
git commit -m %date:~0,8%
                  ::%time:~0,8%
git push
pause


