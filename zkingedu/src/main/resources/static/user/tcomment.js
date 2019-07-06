/**
 * 回复评论
 */
var quname;
var toid;
function tcomment(tcoid,uname){
    quname = uname;
    toid = tcoid;
    $('#form1').hide();
    $('#form2').show();
    $('#userTconmment').html('<button type="button" lay-submit lay-filter="come2" class="layui-btn">回复@'+uname+'</button>')
}
function f1orf2() {
    $('#form2').hide();
    $('#form1').show();
}

/**
 * 是否显示评论
 */

function showH(aid,bid){
    $('#'+aid).show();
    $('#'+bid).html('<A href="javascript:void(0)" th:id="${tcos.post_id}" onclick="hideH('+aid+','+bid+');" style="color: #0ace9d">收起回复(<span>20</span>)</A>');
}
function hideH(aid,bid){
    $('#'+aid).hide();
    $('#'+bid).html('<A href="javascript:void(0)" th:id="${tcos.post_id}" onclick="showH('+aid+','+bid+');" style="color: #0ace9d">查看回复(<span>20</span>)</A>');
}



$(function () {
    layui.use(['form','layer'],function () {
        var form = layui.form;
        form.on('submit(come)',function (data) {
            var formData = data.field;
            $.ajax({
                url:'/addTcomment',
                type:'post',
                data:{
                    tcommentCid:formData.postID,
                    tcommentContent:formData.content
                },
                success:function (result) {
                    if(result=='1'){
                        layer.msg('评论成功',{time:2000});
                        location.reload();
                    }else{
                        layer.msg('评论失败',{time:2000});
                    }
                },
                error:function () {
                    console.log('评论Error');
                }
            });
            return false;
        });


        form.on('submit(come2)',function (data) {
            var formData = data.field;
            $.ajax({
                url:'/addTcommentUser',
                type:'post',
                data:{
                    tcommentCid:$('#postID').val(),
                    tcommentContent:formData.content2,
                    tcommentFid:toid
                },
                success:function (result) {
                    if(result=='1'){
                        layer.msg('回复评论成功',{time:2000});
                        location.reload();
                    }else{
                        layer.msg('回复评论失败',{time:2000});
                    }
                },
                error:function () {
                    console.log('回复评论Error');
                }
            });
            return false;
        });
    });

    /**
     * 查询该用户是否收藏了该帖子
     */


});