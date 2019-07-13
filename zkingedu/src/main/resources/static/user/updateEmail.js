var time=60;
var code = "come";
$(function(){
    $('#getEmailCode').click(function(){
        code = "";
        for(var i=0;i<6;i++){
            code+=Math.floor(Math.random()*10);
        }
        alert(code);
        $.ajax({
                type: 'post',
                url: '/user/sendMail',
                dataType: 'json',
                data: {
                    Email:$('#email').val(),
                    code:code
                },
                error: function() {
                    console.log('邮件验证码发送失败');
                },
                success: function(result) {
                    if(result==1){
                        console.log('邮件发送成功');
                    }
                }
        });

        timeStart1();
    });

});
function timeStart1(){
    if(time>1){
        $('#getEmailCode').css("pointer-events","none");
        time=time-1;
        $('#getEmailCode').text("重新获取("+time+")");
        setTimeout(timeStart1,1000);
    }else{
        time=60;
        $('#getEmailCode').css("pointer-events","auto");
        $('#getEmailCode').text("获取验证码");
    }
}

function emailCheck() {
    if($('#email').val().length==0){
        alert("请输入邮箱地址");
        return false;
    }
    return true;
}

/**
 * 提交修改申请
 */
function subUpEmail() {
    if(emailCheck()==false){
        return false;
    }else if(code!=$('#emailCode').val()){
        if($('#emailCode').val().length==0){
            alert("请输入验证码");
            return false;
        }
        alert("验证码有误");
        $('#emailCode').val("")
        return false;
    }

    $.ajax({
        url:'/user/updateEmail',
        type:'post',
        async:false,
        data:{
            email:$('#email').val(),
            userId:$('#userId').val()
        },
        success:function (data) {
            if(data==1){
                location.href="/user/userinfo";
            }
        },
        error:function () {
            console.log("修改邮箱地址错误");
        }

    });
}

function updateName11() {
    if(!$('#inputName').val().length>2&&$('#inputName').val().length<24){
        $('#inputName').val('');
        return false;
    }
    $.ajax({
        url:'/user/updateName',
        type:'post',
        data:{
            inputName:$('#inputName').val(),
            userId:$('#userId').val()
        },
        success:function (data) {
            if(data==1){
                location.href="/user/userinfo";
            }
        },
        error:function () {
            console.log("修改名字错误");
        }

    });
}