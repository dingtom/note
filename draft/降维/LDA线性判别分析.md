

```
# 挖掘客户意见
import re
import collections
import pandas as pd
import jieba
from wordcloud import WordCloud
from gensim import corpora, models
import matplotlib.pyplot as plt

# 获得wordcloud 需要的 文本格式
with open("want_after.txt", "r", encoding='utf-8') as f:
     text = ' '.join(jieba.cut(f.read(),cut_all=False))
     # print(text)

backgroud_Image = plt.imread('豆瓣.jpg')  # 背景图

# 词云的一些参数设置
wc = WordCloud(
      background_color='white',
      mask=backgroud_Image,
      font_path='SourceHanSerifCN-Medium.otf',
      max_words=200,
      max_font_size=200,
      min_font_size=8,
      random_state=50,
      )

# 生成词云
word_cloud = wc.generate_from_text(text)

plt.imshow(word_cloud)
plt.axis('off')

wc.to_file('结果.jpg')

# 看看词频高的有哪些
process_word = WordCloud.process_text(wc, text)
sort = sorted(process_word.items(), key=lambda e: e[1], reverse=True)
sort_after = sort[:50]
print(sort_after)

# 把数据存成csv文件
df = pd.DataFrame(sort_after)
# 保证不乱码
df.to_csv('sort_after.csv', encoding='utf_8_sig')
# 1.文本预处理方法
# （1）文本去噪
# 包括标点符号、数字、英文字母、转换符等噪声，要删除。
inputfile = 'q_0.txt'  # 评论提取后保存路径
data = pd.read_csv(inputfile, encoding='utf-8', header=None)
datalist = list(data[0])
datalist_denoise = []
r0 = r'[\s+\.!\/_,$%^*(+\"\')]+|[:：+——()?【】“”！，。？、~@#￥%……&*（）]+'
r1 = '[^\u4e00-\u9fa5]'
for a_string in datalist:
    a_string = str(a_string)
    temp = re.sub(r0, '', a_string)   # 删除标点符号
    temp = re.sub(r1, '', temp)   # 删除英文和数字
    datalist_denoise.append(temp)
data_denoise = pd.DataFrame(datalist_denoise)
# filelist3.to_csv(save_path, index=False, header=False, encoding='utf-8')
# （2）文本去重
# 文本去重包括两个部分，即重复语句删除和重复词压缩。
l1 = len(data_denoise)
# 在重复文字中筛选保留其中1条
data_unique = pd.DataFrame(data[0].unique())
l2 = len(data_unique)
print(u'删除了%s条重复条目。' % (l1 - l2))
# 重复词压缩也称为机械压缩，比如：很好很好很好。
# data_compress = []
# for a_string in data_unique:
#     a_string = str(a_string)
#     temp1 = a_string.strip('\n')
#     temp2 = temp1.lstrip('\ufeff')
#     temp3 = temp2.strip('\r')
#     temp4 = wenben.find_chinese(temp3)
#     temp5 = ""
#     if temp4 != '':
#         temp5 = wenben.delete_copy(temp4)
#     data_compress.append(temp5)
# （3）短句删除
# 还存在一些短句字数过少，这也是没有意义的意见。比如：很不错
data_long = []
for a_string in data_unique:
    a_string = str(a_string)
    if a_string == '':
        continue
    else:
        if len(a_string) < 4:
            continue
        else:
            data_long.append(a_string)
# 2.文本分词和统计
# 将短句划分为词。接着，通过高频率词组进行统计，就可以掌握客户的主要意见。
# def stopwordslist(filepath):
#     stopwords = open(filepath, encoding='utf-8').read()
#     stopwords = jieba.cut(stopwords, cut_all=True)
#     stopwords2 = "\n ".join(stopwords)
#     return stopwords2
# def movestopwords(sentence):
#     stopwords = stopwordslist('stoplist.txt')  # 这里加载停用词的路径
#     outstr = ''
#     for word in sentence.split("\n"):
#         if word not in stopwords:
#             if word != '\t'and'\n':
#                 outstr += word
#     return outstr
#
# wordlist_text = jieba.cut(text, cut_all=True)
# wl_space_split = "\n ".join(wordlist_text)
# listcontent = movestopwords(wl_space_split)
# 统计词频
str1 = listcontent.split(" ")
q = collections.Counter(str1)
dataq = pd.DataFrame(q.items())
dataq.to_excel("q5.xlsx",  header=False, encoding='utf-8')
# 生成词云
outputfile = 'ciyun.jpg'
my_wordcloud = WordCloud(
            background_color='white',    # 设置背景颜
            # mask = abel_mask,        # 设置背景图片
            max_words=2000,            # 设置最大现实的字数
            collocations=False,
            font_path='simhei.ttf',  # 设置字体格式，如不设置显示不了中文
            scale=4).generate(listcontent)
plt.imshow(my_wordcloud)
plt.axis("off")
plt.show()
my_wordcloud.to_file(outputfile)


# 3.主题分析
# 主题分析是通过机器学习和自然语言处理等方法在大量的文本中自动抽象出主题的一种统计模型。这类模型要考虑到文本背后的语义关联的关系，发现文本词语的规律，实现在非结构化的文本中找到有效的信息。
inputfileq[1] = inputfileq[0].apply(lambda s: s.split(' ')) # 定义一个分割函数，然后用apply广播
# 主题分析
inputfileq_dict = corpora.Dictionary(inputfileq[1])  # 建立词典
inputfileq13_corpus = [inputfileq_dict.doc2bow(i) for i in inputfileq[1]]  # 建立语料库
inputfileq13_lda = models.LdaModel(
    inputfileq13_corpus,
    num_topics=3,
    id2word=inputfileq_dict)  # LDA模型训练
filelist = []
for i in range(3):
    filelist.append(inputfileq13_lda.print_topic(i))
    print(inputfileq13_lda.print_topic(i))  # 输出每个主题
filelist1 = pd.DataFrame(filelist)
filelist1.to_csv(outputfileq, index=False, header=False, encoding='utf-8')
```
