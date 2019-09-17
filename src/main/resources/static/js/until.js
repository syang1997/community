
function getDateDiff(dateTimeStamp) {
    var minute = 1000 * 60;
    var hour = minute * 60;
    var day = hour * 24;
    var halfamonth = day * 15;
    var month = day * 30;
    var now = new Date().getTime();
    var diffValue = now - dateTimeStamp;
    if (diffValue < 0) {
        //若日期不符则弹窗口告之,结束日期不能小于开始日期！
    }
    var monthC = diffValue / month;
    var weekC = diffValue / (7 * day);
    var dayC = diffValue / day;
    var hourC = diffValue / hour;
    var minC = diffValue / minute;
    if (monthC >= 1) {
        result = "发表于" + parseInt(monthC) + "个月前";
    }
    else if (weekC >= 1) {
        result = "发表于" + parseInt(weekC) + "周前";
    }
    else if (dayC >= 1) {
        result = "发表于" + parseInt(dayC) + "天前";
    }
    else if (hourC >= 1) {
        result = "发表于" + parseInt(hourC) + "个小时前";
    }
    else if (minC >= 1) {
        result = "发表于" + parseInt(minC) + "分钟前";
    } else
        result = "刚刚发表";
    return result;
}

//inputTime 参数是毫秒级时间戳
function formatDate(inputTime){
    var date = new Date(inputTime);
    var y = date.getFullYear();
    var m = date.getMonth() + 1;
    m = m < 10 ? ('0' + m) : m;
    var d = date.getDate();
    d = d < 10 ? ('0' + d) : d;
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;
    second = second < 10 ? ('0' + second) : second;
    var time=y + '-' + m + '-' + d+' '+h+':'+minute+':'+second
    return time;
}