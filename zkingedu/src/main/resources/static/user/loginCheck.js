function loginChek() {
    if(!(/^1[3456789]\d{9}$/.test($('#loginPhone').val()))){
        alert("手机号码有误，请重填");
        $('#phone').val('');
        return false;
    }else if ($('#loginUpwd').val().length==0){
        alert("请输入密码");
        return false;
    }
    $.ajax({
        url:'user/loginCheck',
        type:'post',
        async:false,
        data:{
            userPhone:$("#loginPhone").val(),
            userPassword:$("#loginUpwd").val()
        },
        success:function (data) {
            if(data==1){
                alert('登陆成功');
                location.href='/';
            }else{
                alert('账户名或密码错误');
            }
        },
        error:function () {
            console.log("登陆错误")
        }
    });
}