
function newPwd() {
 if(!(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,16}$/.test($('#inputPassword2').val()))){
     layer.msg('密码至少8-16个字符，至少1个大写字母，1个小写字母和1个数字，其他可以是任意字符',{time:1800});
     $('#inputPassword2').val('');
     return false;
 }else if($('#inputPassword2').val().length==0){
     layer.msg('请输入新密码',{time:1200});
     return false;
 }
    return true;
}

function newPwd2() {
    if($('#inputPassword2').val()!=$('#inputPassword3').val()){
        layer.msg('两次输入密码不匹配，请重新输入',{time:1400});
        $('#inputPassword3').val('');
        return false;
    }
    return true;
}

function subPwd(){
    if(newPwd2()==true&&newPwd()==true){
        $.ajax({
            url:'/user/updatePwd',
            type:'post',
            data:{
                pwd:$('#inputPassword2').val(),
                userId:$('#userId').val()
            },
            success:function (data) {
                if(data==1){
                    layer.msg('密码修改成功，登录失效',{time:1200});
                    location.href='/';
                }
            },
            error:function () {
                console.log("修改密码错误")
            }

        });

    }

}