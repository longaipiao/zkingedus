var time=60;
var code = "come";
$(function(){
    $('#getCode').click(function(){
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
                "mobile":$('#phone').val(),
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
        });
        timeStart();
    });
});
function timeStart(){
    if(time>1){
        $('#getCode').css("pointer-events","none");
        time=time-1;
        $('#getCode').text("重新获取("+time+")");
        setTimeout(timeStart,1000);
    }else{
        time=60;
        $('#getCode').css("pointer-events","auto");
        $('#getCode').text("获取验证码");
    }
}

function phoneCheck() {
    if(!(/^1[3456789]\d{9}$/.test($('#phone').val()))){
        if($('#phone').val().length==0){
            alert("请输入手机号");
            return false;
        }
        alert("手机号码有误，请重填");
        $('#phone').val('');
        return false;
    }
    return true;
}

function checkPhoneu() {
    var f = true;
    $.ajax({
        url:'user/checkPhone',
        type:'post',
        data:{
            userPhone:$("#phone").val()
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
 * 提交修改申请
 */
function subUpPhone() {
    if(phoneCheck()==false){
        return false;
    }else if(code!=$('#code').val()){
        if($('#code').val().length==0){
            alert("请输入验证码");
            return false;
        }
        alert("验证码有误");
        $('#code').val("")
        return false;
    }else if(checkPhoneu()==false){
        return false;
    }
    $.ajax({
        url:'/user/updatePhone',
        type:'post',
        data:{
            phone:$('#phone').val(),
            userId:$('#userId').val()
        },
        success:function (data) {
            if(data==1){
                location.href="/user/userinfo";
            }
        },
        error:function () {
            console.log("修改手机号码错误");
        }

    });
}