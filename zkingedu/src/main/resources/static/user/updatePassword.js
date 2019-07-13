
function newPwd() {
 if(!(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[^]{8,16}$/.test($('#inputPassword2').val()))){
    alert('密码至少8-16个字符，至少1个大写字母，1个小写字母和1个数字，其他可以是任意字符');
     $('#inputPassword2').val('');
     return false;
 }else if($('#inputPassword2').val().length==0){
     alert('请输入新密码');
     return false;
 }
    return true;
}

function newPwd2() {
    if($('#inputPassword2').val()!=$('#inputPassword3').val()){
        alert('两次输入密码不匹配，请重新输入');
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
                    alert('密码修改成功，登录失效')
                    location.href='/';
                }
            },
            error:function () {
                console.log("修改密码错误")
            }

        });

    }

}