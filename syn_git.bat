@echo off   % �رջ���%
python toc.py
	    ::echo off��echo״̬����Ϊoff��ʾ�ر�������������(������������������)�Ļ���%
                    ::@�����þ��ǹرս�������һ������Ļ���%
                    ::C:\Users\liang\Desktop> ����echo off ����Ļ���%
git status
git pull
git add .
git commit -m %date:~0,8%_%time:~1,1%:%time:~3,2%:%time:~6,2%
::%date:~0,8%
                  ::%time:~0,8%
git push

shutdown -s -t 600


