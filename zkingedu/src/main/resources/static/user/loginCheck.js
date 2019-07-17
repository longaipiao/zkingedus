function loginChek() {
    layui.use('layer',function () {
       var layer = layui.layer;

        if(!(/^1[3456789]\d{9}$/.test($('#loginPhone').val()))){
            layer.msg('手机号码有误,请重填',{time:1200});
            $('#phone').val('');
            return false;
        }else if ($('#loginUpwd').val().length==0){
            layer.msg('请输入密码',{time:1200});
            return false;
        }
        $.ajax({
            url:'/user/loginCheck',
            type:'post',
            async:false,
            data:{
                userPhone:$("#loginPhone").val(),
                userPassword:$("#loginUpwd").val()
            },
            success:function (data) {
                if(data=='1'){
                    location.href='/';
                }else if(data=='3'){
                    layer.msg('该账户已被封禁，请联系管理人员',{time:1200});
                }else{
                    layer.msg('账户名或密码错误',{time:1200});
                }
            },
            error:function () {
                console.log("登陆错误")
            }
        });
    });
}