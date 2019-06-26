var time=60;
var code = "come";
$(function(){
    $('#sendPhone').click(function(){
        code = "";
        for(var i=0;i<6;i++){
            code+=Math.floor(Math.random()*10);
        }
        alert(code);
        /*$.ajax({
                 type: 'post',
                url: 'http://route.showapi.com/28-1',
                dataType: 'json',
                data: {
                    "showapi_appid": '90784', //这里需要改成自己的appid
                    "showapi_sign": '36bb53e6712946d5b8bfd554e602cd17',  //这里需要改成自己的应用的密钥secret
                    "mobile":$('#phone').val(),
                    "content":"{\"name\":\"\",\"code\":\""+code+"\",\"minute\":\"\"}",
                    "tNum":"T170317004265",
                    "big_msg":""

                },
                error: function(XmlHttpRequest, textStatus, errorThrown) {
                    alert("操作失败!");
                },
                success: function(result) {
                    console.log(result) //console变量在ie低版本下不能用
                    //alert(result.showapi_res_code)
                }
        });*/
        timeStart();
    });
});
function timeStart(){
    if(time>1){
        $('#sendPhone').css("pointer-events","none");
        time=time-1;
        $('#sendPhone').text("重新获取("+time+")");
        setTimeout(timeStart,1000);
    }else{
        time=60;
        $('#sendPhone').css("pointer-events","auto");
        $('#sendPhone').text("获取验证码");
    }
}

/*注册*/
function zc() {
    if(checkPhone()==false){//查询重复用户
        return false;
    }else if(!(/^1[3456789]\d{9}$/.test($('#phone').val()))){
        alert("手机号码有误，请重填");
        $('#phone').val('');
        return false;
    }else if(!(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,16}$/.test($('#upwd').val()))){
        alert("密码至少8-16个字符，至少1个大写字母，1个小写字母和1个数字，其他可以是任意字符");
        $('#upwd').val('');
        return false;
    }else if(code!=$('#code').val()){
        alert("验证码错误");
        $('#code').val('');
        return false;
    }
    $.ajax({
        url:'user/zc',
        type:'post',
        async:false,
        data:{
            userPhone:$("#phone").val(),
            userPassword:$("#upwd").val()
        },
        success:function (data) {
            if(data=='注册成功'){
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
function checkPhone() {
    var f = true;
    $.ajax({
        url:'user/checkPhone',
        type:'post',
        data:{
            userPhone:$("#phone").val()
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

/**
 * 更换手机号码
 */

