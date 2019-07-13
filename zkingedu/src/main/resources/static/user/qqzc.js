var time=60;
var code = "come";
$(function(){
    $('#getQQCode').click(function(){
        code = "";
        for(var i=0;i<6;i++){
            code+=Math.floor(Math.random()*10);
        }
        alert(code);
        $.ajax({
                 type: 'post',
                url: 'http://route.showapi.com/28-1',
                dataType: 'json',
                data: {
                    "showapi_appid": '99583', //这里需要改成自己的appid
                    "showapi_sign": '0bff2581f0a548a9a65c4f06163f3044',  //这里需要改成自己的应用的密钥secret
                    "mobile":$('#qqphone').val(),
                    "content":"{\"name\":\"\",\"code\":\""+code+"\",\"minute\":\"\"}",
                    "tNum":"T150606060602",
                    "big_msg":""

                },
                error: function(XmlHttpRequest, textStatus, errorThrown) {
                    alert("操作失败!");
                },
                success: function(result) {
                    console.log(result) //console变量在ie低版本下不能用
                    //alert(result.showapi_res_code)
                }
        });
        qqtimeStart();
    });
});
function qqtimeStart(){
    if(time>1){
        $('#getQQCode').css("pointer-events","none");
        time=time-1;
        $('#getQQCode').text("重新获取("+time+")");
        setTimeout(qqtimeStart,1000);
    }else{
        time=60;
        $('#getQQCode').css("pointer-events","auto");
        $('#getQQCode').text("获取验证码");
    }
}

/*注册*/
function qqzc() {
    if(qqcheckPhone()==false){//查询重复用户
        return false;
    }else if(!(/^1[3456789]\d{9}$/.test($('#qqphone').val()))){
        alert("手机号码有误，请重填");
        $('#qqphone').val('');
        return false;
    }else if(!(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,16}$/.test($('#qqpwd').val()))){
        alert("密码至少8-16个字符，至少1个大写字母，1个小写字母和1个数字，其他可以是任意字符");
        $('#qqpwd').val('');
        return false;
    }else if(code!=$('#qqcode').val()){
        alert("验证码错误");
        $('#qqcode').val('');
        return false;
    }
    $.ajax({
        url:'/addQQUser',
        type:'post',
        async:false,
        data:{
            userPhone:$("#qqphone").val(),
            userPassword:$("#qqpwd").val(),
            userOpenID:$('#userOpenID').val(),
            userImg:$('#userImg').val(),
            userName:$('#userName').val()
        },
        success:function (data) {
            if(data=='1'){
                alert("注册完成");
                location.href='/'
            }
        },
        error:function () {
            console.log("注册错误")
        }
    });
}
/*查询重复用户*/
function qqcheckPhone() {
    var f = true;
    $.ajax({
        url:'user/checkPhone',
        type:'post',
        data:{
            userPhone:$("#qqphone").val()
        },
        success:function (data) {
            if(data=='1'){
                alert("账号已存在");
                f=false;
            }else{

            }
        },
        error:function () {
            console.log("查询重复错误")
        }
    });
    return f;
}