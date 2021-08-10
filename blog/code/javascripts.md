- [ 语法](#head1)
- [ let/var](#head2)
- [ const](#head3)
- [ 对象增强写法](#head4)


# <span id="head1"> 语法</span>

**这是一个输入框**
var age = prompt('请输入您的年龄');
**alert 弹出警示框 输出的 展示给用户的**
alert('计算的结果是');
**console 控制台输出 给程序员测试用的**  
console.log('我是程序员能看到的');

**交换变量需要**temp

js 的变量数据类型是只有程序在运行过程中，根据等号右边的值来确定的
 js是动态语言 变量的数据类型是可以变化的

```
// 3. 数字型的最大值
console.log(Number.MAX_VALUE);
// 4. 数字型的最小值
console.log(Number.MIN_VALUE);
// 5. 无穷大
console.log(Number.MAX_VALUE * 2); // Infinity 无穷大  
// 6. 无穷小
console.log(-Number.MAX_VALUE * 2); // -Infinity 无穷大
// 7. 非数字
console.log('pink老师' - 100); // NaN

// isNaN() 这个方法用来判断非数字 


 // 字符串的拼接 +  只要有字符串和其他类型相拼接 最终的结果是字符串类型
        console.log('沙漠' + '骆驼'); // 字符串的 沙漠骆驼
        console.log('pink老师' + 18); // 'pink老师18'
        console.log('pink' + true); // pinktrue
        console.log(12 + 12); // 24
        console.log('12' + 12); // '1212'
        
//获取变量类型
        var vari = undefined;
        console.log(typeof vari); // undefined
        var timer = null;
        console.log(typeof timer); // object
        
//数据类型转换
// 1. 把数字型转换为字符串型 变量.toString()
        var num = 10;
        var str = num.toString();
        // 2. 我们利用 String(变量)   
        console.log(String(num));
        // 3. 利用 + 拼接字符串的方法实现转换效果 隐式转换
        console.log(num + '');
// 1. parseInt(变量)  可以把 字符型的转换为数字型 得到是整数
        console.log(parseInt('3.14')); // 3 取整
        console.log(parseInt('3.94')); // 3 取整
        console.log(parseInt('120px')); // 120 会去到这个px单位
        console.log(parseInt('rem120px')); // NaN
// 2. parseFloat(变量) 可以把 字符型的转换为数字型 得到是小数 浮点数
        console.log(parseFloat('3.14')); // 3.14
        console.log(parseFloat('120px')); // 120 会去掉这个px单位
        console.log(parseFloat('rem120px')); // NaN
// 3. 利用 Number(变量) 
        var str = '123';
        console.log(Number(str));
        console.log(Number('12'));
// 4. 利用了算数运算 -  *  /  隐式转换
        console.log('12' - 0); // 12
        console.log('123' - '120');
        console.log('123' * 1);
//布尔值转换
        console.log(Boolean('')); // false
        console.log(Boolean(0)); // false
        console.log(Boolean(NaN)); // false
        console.log(Boolean(null)); // false
        console.log(Boolean(undefined)); // false
        
// 2. 浮点数 算数运算里面会有问题
        console.log(0.1 + 0.2); // 0.30000000000000004
        console.log(0.07 * 100); // 7.000000000000001
        // 3. 我们不能直接拿着浮点数来进行相比较 是否相等
        var num = 0.1 + 0.2;
        console.log(num == 0.3); // false
        

// 2. 前置递增运算符  ++ 写在变量的前面
        var age = 10;
        ++age; // 类似于 age = age + 1
        console.log(age);            //11
        // 3. 先加1  后返回值
        var p = 10;
        console.log(++p + 10);      //21
        // 1. 前置自增和后置自增如果单独使用 效果是一样的
        // 2. 后置自增 口诀：先返回原值 后自加1 
        var age = 10;
        console.log(age++ + 10);  //20
        console.log(age);      //11        
        
        
        
        
/1. 我们程序里面的等于符号 是 ==  默认转换数据类型 会把字符串型的数据转换为数字型 只要求值相等就可以
        console.log(18 == '18'); // true
        // 2. 我们程序里面有全等 一模一样  要求 两侧的值 还有 数据类型完全一致才可以 true
        console.log(18 === 18);
        console.log(18 === '18'); // false 
        
        
// 1. 有三元运算符组成的式子我们称为三元表达式
        // 条件表达式 ？ 表达式1 ： 表达式2
        // 如果条件表达式结果为真 则 返回 表达式1 的值 如果条件表达式结果为假 则返回 表达式2 的值
        var num = 10;
        var result = num > 5 ? '是的' : '不是的'; // 我们知道表达式是有返回值的        
```





// 2. js的作用域（es6）之前 ： 全局作用域   局部作用域 
        // 3. 全局作用域： 整个script标签 或者是一个单独的js文件
        // 4. 局部作用域（函数作用域） 在函数内部就是局部作用域 这个代码的名字只在函数内部起效果和作用
        // 作用域链  ： 内部函数访问外部函数的变量，采取的是链式查找的方式来决定取那个值   就近原则

        // var obj = {};  // 创建了一个空的对象 
        var obj = {
                uname: '张三疯',
                age: 18,
                sex: '男',
                sayHi: function() {
                    console.log('hi~');
    
                }
            }
            // (1). 调用对象的属性 我们采取 对象名.属性名 . 我们理解为 的
        // (2). 调用属性还有一种方法 对象名['属性名']
        // (3) 调用对象的方法   对象名.方法名() 千万别忘记添加小括号
        // 利用 new Object 创建对象
        var obj = new Object(); // 创建了一个空的对象
        obj.uname = '张三疯';
        obj.age = 18;
        obj.sex = '男';
        obj.sayHi = function() {
                console.log('hi~');
            }
        // new 构造函数名();
        function Star(uname, age, sex) {
            this.name = uname;
            this.age = age;
            this.sex = sex;
            this.sing = function(sang) {
                console.log(sang);
    
            }
        }
        var ldh = new Star('刘德华', 18, '男'); // 调用函数返回的是一个对象
         // new关键字执行过程
        // 1. new 构造函数可以在内存中创建了一个空的对象 
        // 2. this 就会指向刚才创建的空对象
        // 3. 执行构造函数里面的代码 给这个空对象添加属性和方法
        // 4. 返回这个对象
        
        // Math数学对象 不是一个构造函数 ，所以我们不需要new 来调用 而是直接使用里面的属性和方法即可
        console.log(Math.PI); // 一个属性 圆周率
        console.log(Math.max(1, 99, 3)); // 99


	// 1. 使用Date  如果没有参数 返回当前系统的当前时间
	    var date = new Date();
	    // 2. 参数常用的写法  数字型  2019, 10, 01  或者是 字符串型 '2019-10-1 8:8:8'
	    var date1 = new Date(2019, 10, 1);
	    console.log(date1); // 返回的是 11月 不是 10月 
	    var date2 = new Date('2019-10-1 8:8:8');
	    console.log(date2);    // 返回的是  10月
	            // 格式化日期 年月日 
	    var date = new Date();
	    console.log(date.getFullYear()); // 返回当前日期的年  2019
	    console.log(date.getMonth() + 1); // 月份 返回的月份小1个月   记得月份+1 呦
	    console.log(date.getDate()); // 返回的是 几号
	    console.log(date.getDay()); // 3  周一返回的是 1 周六返回的是 6 但是 周日返回的是 0			
	            console.log(date.getHours()); // 时
	    console.log(date.getMinutes()); // 分
	    console.log(date.getSeconds()); // 秒
	      // 获得Date总的毫秒数(时间戳)  不是当前时间的毫秒数 而是距离1970年1月1号过了多少毫秒数
	    // 1. 通过 valueOf()  getTime()
	    var date = new Date();
	    console.log(date.valueOf()); // 就是 我们现在时间 距离1970.1.1 总的毫秒数
	    console.log(date.getTime());
	    // 2. 简单的写法 (最常用的写法)
	    var date1 = +new Date(); // +new Date()  返回的就是总的毫秒数
	    console.log(date1);
	    // 3. H5 新增的 获得总的毫秒数
	    console.log(Date.now());
	    
	// 创建数组的两种方式
	    var arr = [1, 2, 3];
	    var arr1 = new Array(2, 3); // 等价于 [2,3]  这样写表示 里面有2个数组元素 是 2和3
	     // 检测是否为数组
	    // (1) instanceof  运算符 它可以用来检测是否为数组
	    var arr = [];
	    console.log(arr instanceof Array);
	    // (2) Array.isArray(参数);  H5新增的方法  ie9以上版本支持
	    console.log(Array.isArray(arr));
	// 添加删除数组元素方法
	    // 1. push() 在我们数组的末尾 添加一个或者多个数组元素   push  推
	    console.log(arr.push(4, 'pink'));
	    // (1) push 是可以给数组追加新的元素
	    // (3) push完毕之后，返回的结果是 新数组的长度 
	// 2. unshift 在我们数组的开头 添加一个或者多个数组元素
	    console.log(arr.unshift('red', 'purple'));
	    // (1) unshift是可以给数组前面追加新的元素
	    // (3) unshift完毕之后，返回的结果是 新数组的长度 
	
	// plice() 方法向/从数组中添加/删除项目，然后返回被删除的项目。
	arrayObject.splice(index,howmany,item1,.....,itemX)
	
	// 3. pop() 它可以删除数组的最后一个元素  
	    console.log(arr.pop());
	    console.log(arr);
	    // (1) pop是可以删除数组的最后一个元素 记住一次只能删除一个元素
	    // (3) pop完毕之后，返回的结果是 删除的那个元素 
	// 4. shift() 它可以删除数组的第一个元素  
	    console.log(arr.shift());
	    console.log(arr);
	    // (1) shift是可以删除数组的第一个元素 记住一次只能删除一个元素
	    // (3) shift完毕之后，返回的结果是 删除的那个元素 
	    // 返回数组元素索引号方法  indexOf(数组元素)  作用就是返回该数组元素的索引号 从前面开始查找
	    // 它如果在该数组里面找不到元素，则返回的是 -1  
	    var arr = ['red', 'green', 'pink'];
	    console.log(arr.indexOf('blue'));
	    // 返回数组元素索引号方法  lastIndexOf(数组元素)  作用就是返回该数组元素的索引号 从后面开始查找
	    var arr = ['red', 'green', 'blue', 'pink', 'blue'];
	    console.log(arr.lastIndexOf('blue')); // 4
	    
	    // 数组转换为字符串 
	    // 1. toString() 将我们的数组转换为字符串
	    var arr = [1, 2, 3];
	    console.log(arr.toString()); // 1,2,3
	    // 2. join(分隔符) 
	    var arr1 = ['green', 'blue', 'pink'];
	    console.log(arr1.join('-')); // green-blue-pink
	    
	    // 字符串对象  根据字符返回位置  str.indexOf('要查找的字符', [起始的位置])
	            // 1. charAt(index) 根据位置返回字符
		        // 字符串操作方法
	    // 1. concat('字符串1','字符串2'....)
	    var str = 'andy';
	    console.log(str.concat('red'));
	    // 2. substr('截取的起始位置', '截取几个字符');
	    var str1 = '改革春风吹满地';
	    console.log(str1.substr(2, 2)); // 第一个2 是索引号的2 从第几个开始  第二个2 是取几个字符
	    // 1. 替换字符 replace('被替换的字符', '替换为的字符')  它只会替换第一个字符
	    var str = 'andyandy';
	    console.log(str.replace('a', 'b'));
	    // 2. 字符转换为数组 split('分隔符')    前面我们学过 join 把数组转换为字符串
	    var str2 = 'red, pink, blue';
	    console.log(str2.split(','));





```
(param1, param2, …, paramN) => { statements }
(param1, param2, …, paramN) => expression
//相当于：(param1, param2, …, paramN) =>{ return expression; }

// 当只有一个参数时，圆括号是可选的：
(singleParam) => { statements }
singleParam => { statements }

// 没有参数的函数应该写成一对圆括号。
() => { statements }
```

# <span id="head2"> let/var</span>

我们可以将let看成更完美的var。有if/for的块级作用域。

# <span id="head3"> const</span>

将某个变量修饰为常量, 不可以再次赋值。来保证数据的安全性.

**建议: 在ES6开发中,优先使用const, 只有需要改变某一个标识符的时候才使用let.** 

创建时就要赋值，常量（变量保存的是内存地址，地址不能改内容可以）的含义是指向的对象不能修改，但是可以修改对象内部属性

# <span id="head4"> 对象增强写法</span>

```
let a=1
let obj = {
	a
}
console.log(obj)


let obj1 = {
	test(){
		console.log('hi')
	}
}
obj1.teset()
```

```css
for (let i in this.books){

​	const book= this.books[i]

}

for (let item of this.book){
    //item即是book
}
```

高阶函数

```css
// 1.filter函数的使用
filter中的回调函数有一个要求: 必须返回一个boolean值
当返回true时, 函数内部会自动将这次回调的n加入到新的数组中。当返回false时, 函数内部会过滤掉这次的n
// // 2.map函数的使用
映射
// // 3.reduce函数的使用
对数组中所有的内容进行汇总


let total = nums.filter(function (n) {
  return n < 100
}).map(function (n) {
  return n * 2
}).reduce(function (prevValue, n) {
  return prevValue + n
}, 0)
console.log(total);

return this.books.reduce(function (preValue, book) {
        return preValue + book.price * book.count
```

