import sys
import os
import argparse

headline_dic={'#':0,'##':1,'###':2,'####':3,'#####':4,'######':5}
suojin={0:-1,1:-1,2:-1,3:-1,4:-1,5:-1,6:-1}
def writefile(fp, str=None):
    with open(fp,'w') as f:
        f.write(str)

def detectHeadLines(f):
    '''detact headline and return inserted string.
    params:
        f: Markdown file
    '''
    f.seek(0)

    insert_str=""
    org_str=""

    last_status = -1
    c_status=-1

    headline_counter=0
    iscode=False
    for line in f.readlines():
        if(line[:3]=='```'):
            iscode= not iscode
            
        # fix code indent bug and fix other indentation bug. 2020/7/3
        if not iscode:
            temp_line=line.strip(' ')
        try: # 起始是代码
            ls=temp_line.split(' ')
        except: 
            return '去draft看'

        if len(ls)>1 and ls[0] in headline_dic.keys() and not iscode:
            headline_counter+=1
            c_status=headline_dic[ls[0]]
            # find first rank headline
            if last_status==-1 or c_status==0 or suojin[c_status]==0:
                # init suojin
                for key in suojin.keys():
                    suojin[key]=-1
                suojin[c_status]=0
            elif c_status>last_status:
                suojin[c_status]=suojin[last_status]+1
            
            # update headline text
            headtext=' '.join(ls[1:-1])
            if ls[-1][-1]=='\n':
                headtext+=(' '+ls[-1][:-1])
            else:
                headtext+=(' '+ls[-1])
            headid = '{}{}'.format('head',headline_counter)
            headline=ls[0]+' <span id=\"{}\"'.format(headid)+'>'+ headtext+'</span>'+'\n'
            org_str+=headline

            jump_str='- [{}](#{}{})'.format(headtext,'head',headline_counter)
            insert_str+=('\t'*suojin[c_status]+jump_str+'\n')
                    
            last_status=c_status
        else:
            org_str+=line
            
            
    return insert_str+org_str
 
    
if __name__=='__main__':

    dirname = sys.argv[1]
    if not os.path.exists(os.path.join('./blog', dirname)):
        os.makedirs(os.path.join('./blog', dirname))
        
    filename = sys.argv[2]

    #print(filename)
    d_file = os.path.join('./draft/'+dirname, filename)
    with open(d_file,'r',encoding='utf-8') as f:
        insert_str=detectHeadLines(f)
    b_file = './blog/{}.md'.format(os.path.join(dirname, filename[:filename.find('.')]))
    with open(b_file,'w',encoding='utf-8') as f:
        f.write(insert_str)
    os.system('git status')
    os.system('git add {}'.format(d_file))
    os.system('git add {}'.format(b_file))
    
    comment = sys.argv[3]
    if comment == 0:
        os.system('git commit -m %date:~0,8%_%time:~0,8%__{}'.format(comment))
    else:
        os.system('git commit -m %date:~0,8%_%time:~0,8%__modify__{}'.format(filename))
    os.system('git push')
    os.system('*'*20)
    os.system('git status')

    # # 所有文章生成TOC
    # f_list = []
    # for root, subdir, files in os.walk('../draft'):
    #     for f_name in files:
    #         f_list.append(os.path.join(root, f_name))

    # f_list.pop(0) # 弹出toc.py

    # for file in f_list:
    #     dirname, filename = os.path.split(file)
    
    #     #print(filename)
    #     with open(os.path.join(dirname, filename),'r',encoding='utf-8') as f:
    #         insert_str=detectHeadLines(f)
        
    #     dirname = dirname.replace('draft', 'blog')
    #     if not os.path.exists(dirname):
    #         os.makedirs(dirname)
    #     with open(r'{}.md'.format(os.path.join(dirname, filename[:filename.find('.')])),'w',encoding='utf-8') as f:
    #         f.write(insert_str)
