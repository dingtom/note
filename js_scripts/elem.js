auto();  //确保打开无障碍
console.show();

setScreenMetrics(1080,2400);
console.log('打开饿了么');
launchApp("饿了么");
customSleep(getRandom(2,4));
console.log('点击我的');
id("home_bottom_tab_4_text").findOne().click();
customSleep(getRandom(2,4));
console.log("点击赚吃货豆")
click(300,1000)
//className('android.view.ViewGroup').depth(15).drawingOrder(1).findOne().click();
customSleep(getRandom(2,4));
//console.log(text("赚吃货豆").exists());
//click("跳过广告")
swipe(300,1000,800,1000,100)

var kw = ["去浏览","去变美","去搜索","去观看","去完成","去逛逛"];

while(true){
    var flag=false  //
    for(i=0;i<kw.length;i++){
        if(textContains(kw[i]).exists()){
            clickByTextName(kw[i]);
            customSleep(getRandom(2,4));
            flag=true //判断有没有执行，没有执行过说明任务完成
        }
    }
    if(!flag){
        log("任务完成返回桌面");
        customSleep(getRandom(2,4));
        home();
        break;
    }
}

function clickByTextName(textName){
    console.log("进入"+textName+"界面");
    textContains(textName).findOne().click();
    customSleep(getRandom(19,21));
    ifSign();
    backByFinish();
}

// function comment(){
// 	click(1023,1390);
//     while(j++<3){
//         swipe(500,1650,500,1000,500);
//     	customSleep(getRandom(3));
//     }
//     click(500,1860);
//     id("pt").findOne().setText("1111");
//     customSleep(1);
//     id("q4").findOne().click();
//     customSleep(3);
//     back();
//     customSleep(5);

// }

//    体眠函数
function customSleep(time){
	sleep(time*1000);
}
/// 产生[min， max）之间的值
function getRandom(min, max){
	 var value=(random()+1)*min;
	 return value>max?max:value;
}

// 判断是否需要返回
function backByFinish(){
    if(textContains("任务完成").exists() || textContains("全部完成啦").exists() ||
    descContains("任务完成").exists() || textContains("任务已完成").exists() ||
    descContains("继续退出").exists() || descContains("全部完成啦").exists() || 
    textContains("当面分享").exists() || textContains("当面扫码").exists() || 
    textContains("请返回重试").exists() || textContains("继续逛逛吧").exists() ||
    textContains("了解Ta").exists()){
        log("     出现任务结束符");
        customSleep(getRandom(1,2));
    }else{
        customSleep(getRandom(5,8));
        log("    时间到");
    }
    log("返回上层");
    back();
    customSleep(getRandom(2,4));
}

// 判断是否有签到
function ifSign(){
    var kw=['签到', '领取']
    var flag=false
    for(i=0;i<kw.length;i++){
        if(textContains(kw[i]).exists()){
            textContains(kw[i]).findOne().click();
            flag=true
        }
        if(descContains(kw[i]).exists()){
            descContains(kw[i]).findOne().click();
            flag=true
        }
    }
    if(flag){
        log("    去签到");
        customSleep(getRandom(2,4));
    }
}

