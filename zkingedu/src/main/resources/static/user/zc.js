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
                    "showapi_appid": '99583', //这里需要改成自己的appid
                    "showapi_sign": '0bff2581f0a548a9a65c4f06163f3044',  //这里需要改成自己的应用的密钥secret
                    "mobile":$('#phonez').val(),
                    "content":"{\"name\":\"\",\"code\":\""+code+"\"}",
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
    layui.use('layer',function () {
        var layer = layui.layer;
        if(checkPhone()==false){//查询重复用户
            return false;
        }else if(!(/^1[3456789]\d{9}$/.test($('#phonez').val()))){
            layer.msg("手机号码有误，请重填",{time:1200});
            $('#phonez').val('');
            return false;
        }else if(!(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,16}$/.test($('#upwd').val()))){
            layer.msg("密码至少8-16个字符，至少1个大写字母，1个小写字母和1个数字，其他可以是任意字符",{time:1200});
            $('#upwd').val('');
            return false;
        }else if(code!=$('#code').val()){
            layer.msg("验证码错误",{time:1200});
            $('#code').val('');
            return false;
        }
        $.ajax({
            url:'/user/zc',
            type:'post',
            async:false,
            data:{
                userPhone:$("#phonez").val(),
                userPassword:$("#upwd").val()
            },
            success:function (data) {
                if(data=='注册成功'){
                    layer.msg("注册完成",{time:1600});
                    location.href='/'
                }else{
                    layer.msg("注册失败",{time:1600});
                }
            },
            error:function () {
                console.log("注册错误")
            }
        });
    });
}
/*查询重复用户*/
function checkPhone() {
    var f = true;
    $.ajax({
        url:'/user/checkPhone',
        type:'post',
        data:{
            userPhone:$("#phonez").val()
        },
        success:function (data) {
            if(data=='1'){
                layer.msg("账号已存在",{time:1200});
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

