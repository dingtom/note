# 在jupyter中添加tensorflow核
conda install  -n tensorflow ipykernel
python -m ipykernel install --user --name tensorflow --display-name tensorflow

conda install -n pytorch ipykernel
python -m ipykernel install --user --name pytorch --display-name pytorch

#　tensorflow环境中没有安装 jupyter
conda install ipython
conda install jupyter