# 下载Node.js并安装

 首先上Node.js官网找到了本机操作系统对应的Node.js版本进行了安装包的下载。
官网：https://nodejs.org/en/download/

镜像：[Index of /nodejs/v11.10.0/ (nju.edu.cn)](http://mirrors.nju.edu.cn/nodejs/v11.10.0/)

验证是否安装成功:

```
npm -v
node -v
```

配置环境变量

```
在nodejs安装路径下，新建node_global和node_cache两个文件夹

#命令行设置缓存文件夹路径
npm config set cache “D:\nodeJs\node_cache”
#设置全局模块存放路径
npm config set cache “D:\nodeJs\node_global”

创建系统环境变量
NODE_PATH：D:\nodejs
编辑系统环境变量Path，加入路径D:\nodejs
将用户环境变量的Path加入
%NODE_PATH%\node_global
%NODE_PATH%\node_modules
%NODE_PATH%

```

安装淘宝镜像（类似于阿里云的maven中央仓库镜像）

```
首先切换淘宝镜像：
npm config set registry https://registry.npm.taobao.org
验证是否成功：
npm config get registry
安装命令：npm install -g cnpm --registry=https://registry.npm.taobao.org

验证命令：cnpm -v
```

安装webpack

```
cnpm install webpack -g
```

安装Vue

```
cnpm install vue -g
```



安装vue命令行工具，即vue-cli 脚手架

```
cnpm install vue-cli -g
```





```css
<!-- 开发环境版本，包含了有帮助的命令行警告 -->
  <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
  <script>
    var app = new Vue({
      el:"#app",
      data:{
        message:" 你好 小黑! "
      }
    })
  </script>
```

# el:挂载点

vue**实例的作用范围是什么呢**
vue会管理el选项命中的元素及其内部的后代元素
**是否可以使用其他的选择器**？
可以使用其他的选择器但是建议使用ID选择
**是否可以设置其他的**dom元素呢？
可以使用其他的双标签不能使用HTML和BODY

# data:数据对象

Vue中用到的数据定义在data中
data中可以写复杂类型的数据
渲染复杂类型数据时遵守is的语法即可

# v-text:设置标签的文本值

```css
<div id="app">
	<h2 v-text="message+'!'">深圳</h2>
    <h2>{{ message +'!'}}深圳</h2>   //差值表达式
</div>
var app = new Vue({
	el:"#app",
	data:{
		message:"黑马程序员"
	}
})

```

v-text指令的作用是设置标签的内容( textcontent)
默认写法会替换全部内容使用差值表达式{{}}可以替换指定内容
内部支持写表达式

# v-html:设置标签的innerHTML

v-html指令的作用是:设置元素的innerHTML

内容中有html结构会被解析为标签

v-text指令无论内容是什么,只会解析为文本

解析文本使用v-text,需要解析html结构使用v-html

```css
   <div id="app">
          <p v-html="content"></p>
    </div>
    <!-- 开发环境版本，包含了有帮助的命令行警告 -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script>
        var app = new Vue({
            el:"#app",
            data:{
                content:"<a href='#'>黑马程序员</a>"
            }
        })
    </script>
```

# v-on:为元素绑定事件

- 指令可以简写为@

- 绑定的方法定义在methods属性中，方法中通过this,关键字获取data中的数据

- 调用的方法有参数<button @click="btn1Click()" 要加括号
  没有参数，绑定时可加可不加（）

-   v-on省略了小括号, 但是方法本身是需要一个参数的, 这个时候, Vue会默认将浏览器生产的event事件对象作为参数传入到方法

  方法定义时, 我们需要event对象, 同时又需要其他参数。在调用方式, 如何手动的获取到浏览器参数的event对象: $event

- 事件的后面跟上 .修饰符 可以对事件进行限制，.enter 可以限制触发的按键为回车

  ![quicker_56c08a88-83d8-4ddf-99b8-29fafd123554.png](https://i.loli.net/2021/07/22/Ig86VC2Qxi1SHn5.png)

### [事件修饰符](https://cn.vuejs.org/v2/api/#v-on)

.stop - 调用 event.stopPropagation()。

.prevent - 调用 event.preventDefault()。
.{keyCode | keyAlias} - 只当事件是从特定键触发时才触发回调。
.native - 监听组件根元素的原生事件。
.once - 只触发一次回调。

```css
  <!-- html结构 -->
  <div id="app">
    <!-- 计数器功能区域 -->
    <div class="input-num">
      <button @click="sub">-</button>
      <span>{{ num }}</span>
      <button @click="add">+</button>
    </div>
  </div>
  <!-- 开发环境版本，包含了有帮助的命令行警告 -->
  <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
  <!-- 编码 -->
  <script>
  // 创建Vue实例
  var app = new Vue({
    el:"#app",
    data:{
      num:1
    },
    methods: {
      add:function(){
        // console.log('add');
        if(this.num<10){
        this.num++;
        }else{
          alert('别点啦,最大啦!');
        }
      },
      sub:function(){
        // console.log('sub');
        if(this.num>0){
          this.num--;
        }else{
          alert("别点啦,最小啦!")
        }
      }
    },
  })
  </script>
```





# v-show:根据表达值的真假,切换元素的显示和隐藏

原理是修改元素的display,实现显示隐藏

指令后面的内容,最终都会解析为布尔值

值为true元素显示,值为false元素隐藏

数据改变之后,对应元素的显示状态会同步更新

```css
	<div id="app">
      <input type="button" value="切换显示状态" @click="changeIsShow">
      <input type="button" value="累加年龄" @click="addAge">
      <img v-show="isShow" src="./img/monkey.gif" alt="">
      <img v-show="age>=18" src="./img/monkey.gif" alt="">
    </div>
    <!-- 1.开发环境版本，包含了有帮助的命令行警告 -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script>
      var app = new Vue({
        el:"#app",
        data:{
          isShow:false,
          age:1
        },
        methods: {
          changeIsShow:function(){
            this.isShow = !this.isShow;
          },
          addAge:function(){
            this.age++;
          }
        },
      })
    </script>
```

# v-if:根据表达值的真假,切换元素的显示和隐藏(操纵dom元素)

本质是通过操纵dom元素来切换显示状态

**频繁的切换v-show,反之使用v-if,前者的切换消耗小**

表达式的值为true,元素存在于dom树中,为false,从dom树中移除

# v-bind设置元素的属性(如:src,title,class)

完整写法是 v-bind:属性名, 简写的话可以直接省略v-bind，**只保留 :属性名**

 需要动态的增删class建议使用对象的方式

```css
   	  <!-- 图片 -->
      <img :src="imgArr[index]" alt="" />
      <!-- 左箭头 -->
      <a href="javascript:void(0)" v-if="index!=0" @click="prev" class="left">
          <img src="./images/prev.png" alt="" />
        </a>
      <!-- 右箭头 -->
      <a href="javascript:void(0)" v-show="index<imgArr.length-1" @click="next" class="right">
        <img src="./images/next.png" alt="" />  
        <!-- alt= 如果无法显示图像，浏览器将显示替代文本，就像这样： -->
        <!-- "javascript:void(0)"让超链接去执行一个js函数，而不是去跳转到一个地址，让超链接去执行一个js函数，而不是去跳转到一个地址， -->
      </a>

  <script> 
    var app = new Vue({
      el: "#mask",
      data: {
        imgArr: [
          "./images/00.jpg",
          "./images/01.jpg",
        ],
        index: 0
      },
      methods: {
        prev:function(){
          this.index--;
        },
        next:function(){
          this.index++;
```





# v-for:根据数据生成列表结构



数组经常和v-for结合使用

语法是( item,index ) in 数据

item 和 index 可以结合其他指令一起使用

数组长度的更新会同步到页面上,是响应式的

```css
	<div id="app">
        <input type="button" value="添加数据" @click="add">
        <input type="button" value="移除数据" @click="remove">
        <ul>
            <li v-for="(it,index) in arr">
                {{ index+1 }}黑马程序员校区:{{ it }}
            </li>
        </ul>
        <h2 v-for="item in vegetables" :title="item.name">  
    <!-- title: 鼠标放上去显示的内容-->

            {{ item.name }}
        </h2>
    </div>
    <!-- 1.开发环境版本，包含了有帮助的命令行警告 -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script>
        var app = new Vue({
            el:"#app",
            data:{
                arr:["北京","上海","广州","深圳"],
                vegetables:[
                    {name:"西兰花炒蛋"},
                    {name:"蛋炒西蓝花"}
                ]
            },
            methods: {
                add:function(){
                    this.vegetables.push({ name:"花菜炒蛋" });
                },
                remove:function(){
                    this.vegetables.shift();
                }
            },
        })
    </script>
```



# v-model:获取和设置表单元素的值(双向数据绑定)

绑定的数据会和表单元素值相关联

绑定的数据←→表单元素的值

```css
	<div id="app">
        <input type="button" value="修改message" @click="setM">
        <input type="text" v-model="message" @keyup.enter="getM">
        <h2>{{ message }}</h2>
    </div>
    <!-- 开发环境版本，包含了有帮助的命令行警告 -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script>
        var app = new Vue({
            el:"#app",
            data:{
                message:"黑马程序员"
            },
            methods: {
                getM:function(){
                    alert(this.message);
                },
                setM:function(){
                    this.message ="酷丁鱼";
                }
```

# v-once:元素和组件(组件后面才会学习)只渲染一次

该指令后面不需要跟任何表达式(比如之前的v-for后面是由跟表达式的)

该指令表示元素和组件(组件后面才会学习)只渲染一次，不会随着数据的改变而改变。

# 笔记本应用

```css
<html>

<head>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <title>小黑记事本</title>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
  <meta name="robots" content="noindex, nofollow" />
  <meta name="googlebot" content="noindex, nofollow" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <!-- <link rel="stylesheet" type="text/css" href="./css/index.css" /> -->
</head>

<body>
                    <!-- 新增\删除\统计\清空\隐藏 -->
  <!-- 主体区域 -->
  <section id="todoapp">
    <!-- 输入框 -->
    <header class="header">
      <h1>小黑记事本</h1>
      <input v-model="inputValue" @keyup.enter="add" autofocus="autofocus" autocomplete="off" placeholder="请输入任务"
        class="new-todo" />
    </header>
    <!-- 列表区域 -->
    <section class="main">
      <ul class="todo-list">
        <li class="todo" v-for="(item,index) in list">
          <div class="view">
            <span class="index">{{ index+1 }}.</span>
            <label>{{ item }}</label>
            <button class="destroy" @click="remove(index)"></button>
          </div>
        </li>
      </ul>
    </section>
    <!-- 统计和清空 -->
    <footer class="footer" v-show="list.length!=0">
      <span class="todo-count" v-if="list.length!=0">
        <strong>{{ list.length }}</strong> items left
      </span>
      <button v-show="list.length!=0" class="clear-completed" @click="clear">
        Clear
      </button>
    </footer>
  </section>
  <!-- 底部 -->
  <footer class="info">
    <p>
      <a href="http://www.itheima.com/"><img src="./img/black.png" alt="" /></a>
    </p>
  </footer>
  <!-- 开发环境版本，包含了有帮助的命令行警告 -->
  <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
  <script>
    var app = new Vue({
      el: "#todoapp",
      data: {
        list: ["写代码", "吃饭饭", "睡觉觉"],
        inputValue: "好好学习,天天向上"
      },
      methods: {
        add: function () {
          this.list.push(this.inputValue);
        },
        remove: function (index) {
          console.log("删除");
          console.log(index);
          this.list.splice(index, 1);
        },
        clear: function () {
          this.list = [];
        }
      },
    })
  </script>
</body>

</html>
```



# 计算属性

我们知道，在模板中可以直接通过插值语法显示一些data中的数据。但是在某些情况，我们可能需要对数据进行一些转化后再显示，或者需要将多个数据结合起来进行显示
比如我们有firstName和lastName两个变量，我们需要显示完整的名称。但是如果多个地方都需要显示完整的名称，我们就需要写多个{{firstName}} {{lastName}}

methods和computed看起来都可以实现我们的功能，
**计算属性会进行缓存，如果多次使用时，计算属性只会调用一次。**

每个计算属性都包含一个getter和一个setter
在上面的例子中，我们只是使用getter来读取。
在某些情况下，你也可以提供一个setter方法（不常用）。

![quicker_b90c2d58-414d-47ef-88ae-eb0d1237ed23.png](https://i.loli.net/2021/07/24/NwTOBekKl1xhARC.png)

# axios

axios必须先导入才可以使用

使用get或post方法即可发送对应的请求

then方法中的回调函数会在请求成功或失败时触发

通过回调函数的形参可以获取响应内容,或错误信息**文档传送门**https://github.com/axios/axios

axios回调函数中的this已经改变,无法访问到data中数据，把this保存起来,回调函数中直接使用保存的this即可

axios回调函数中this指向改变了，需要额外的保存一份



```css
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    axios.get("地址?key=value&key2=values").then(function(response){},function(err){})
    axios.post("地址",{key:value,key2:value2}).then(function(response){},function(err){})

```

## axios使用

```css
<body>
    <input type="button" value="get请求" class="get">
    <input type="button" value="post请求" class="post">
    <!-- 官网提供的 axios 在线地址 -->
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <script>
        /*
            接口1:随机笑话
            请求地址:https://autumnfish.cn/api/joke/list
            请求方法:get
            请求参数:num(笑话条数,数字)
            响应内容:随机笑话
        */
        document.querySelector(".get").onclick = function () {
            axios.get("https://autumnfish.cn/api/joke/list?num=6")
            .then(function (response) {
                console.log(response);
              },function(err){
                  console.log(err);
              })
        }
        /* 
             接口2:用户注册
             请求地址:https://autumnfish.cn/api/user/reg
             请求方法:post
             请求参数:username(用户名,字符串)
             响应内容:注册成功或失败
         */
        document.querySelector(".post").onclick = function () {
            axios.post("https://autumnfish.cn/api/user/reg",{username:"盐焗西兰花"})
            .then(function(response){
                console.log(response);
                console.log(this.skill);
            },function (err) {
                console.log(err);
              })
          }

    </script>
</body>

```

## axios+vue

```css
<body>
    <div id="app">
        <input type="button" value="获取笑话" @click="getJoke">
        <p> {{ joke }}</p>
    </div>
    <!-- 官网提供的 axios 在线地址 -->
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
    <!-- 开发环境版本，包含了有帮助的命令行警告 -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
    <script>
        /*
            接口:随机获取一条笑话
            请求地址:https://autumnfish.cn/api/joke
            请求方法:get
            请求参数:无
            响应内容:随机笑话
        */
        var app = new Vue({
            el:"#app",
            data:{
                joke:"很好笑的笑话"
            },
            methods: {
                getJoke:function(){
                    // console.log(this.joke);
                    var that = this;
                    axios.get("https://autumnfish.cn/api/joke").then(function(response){
                        // console.log(response)
                        console.log(response.data);
                        // console.log(this.joke);
                        that.joke = response.data;
                    },function (err) {  
                        console.log(err)
                    })
                }
            },
        })

    </script>
</body>
```

