@echo off   % �رջ���%
python toc.py
	    ::echo off��echo״̬����Ϊoff��ʾ�ر�������������(������������������)�Ļ���%
                    ::@�����þ��ǹرս�������һ������Ļ���%
                    ::C:\Users\liang\Desktop> ����echo off ����Ļ���%
title GITһ���ύ
color 3
echo ��ǰĿ¼�ǣ�%cd%
echo;

:: �����ύ����
set /p commit_msg=�����ύ commit:

echo ��������֧��ȡԶ������֧��git pull origin master
git pull origin master
echo;

echo ��ʼ��ӱ����git add .
git add .
echo;

set /p declation=�����ύ��commit��Ϣ:
git commit -m "%declation%"
echo;
 
echo ���������ύ��Զ���Լ���֧��git push
git push
echo;
				
echo 2���Ӻ�ػ�
shutdown -s -t 120


