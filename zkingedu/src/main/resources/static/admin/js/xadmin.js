$(function () {
    //加载弹出层
    layui.use(['form','element'],
    function() {
        layer = layui.layer;
        element = layui.element;
    });

    //触发事件
  var tab = {
        tabAdd: function(title,url,id){
          //新增一个Tab项
          element.tabAdd('xbs_tab', {
            title: title 
            ,content: '<iframe tab-id="'+id+'" frameborder="0" src="'+url+'" scrolling="yes" class="x-iframe"></iframe>'
            ,id: id
          })
        }
        ,tabDelete: function(othis){
          //删除指定Tab项
          element.tabDelete('xbs_tab', '44'); //删除：“商品管理”
          othis.addClass('layui-btn-disabled');
        }
        ,tabChange: function(id){
          //切换到指定Tab项
          element.tabChange('xbs_tab', id); //切换到：用户管理
        }
      };


    tableCheck = {
        init:function  () {
            $(".layui-form-checkbox").click(function(event) {
                if($(this).hasClass('layui-form-checked')){
                    $(this).removeClass('layui-form-checked');
                    if($(this).hasClass('header')){
                        $(".layui-form-checkbox").removeClass('layui-form-checked');
                    }
                }else{
                    $(this).addClass('layui-form-checked');
                    if($(this).hasClass('header')){
                        $(".layui-form-checkbox").addClass('layui-form-checked');
                    }
                }
                
            });
        },
        getData:function  () {
            var obj = $(".layui-form-checked").not('.header');
            var arr=[];
            obj.each(function(index, el) {
                arr.push(obj.eq(index).attr('data-id'));
            });
            return arr;
        }
    }

    //开启表格多选
    tableCheck.init();
      

    $('.container .left_open i').click(function(event) {
        if($('.left-nav').css('left')=='0px'){
            $('.left-nav').animate({left: '-221px'}, 100);
            $('.page-content').animate({left: '0px'}, 100);
            $('.page-content-bg').hide();
        }else{
            $('.left-nav').animate({left: '0px'}, 100);
            $('.page-content').animate({left: '221px'}, 100);
            if($(window).width()<768){
                $('.page-content-bg').show();
            }
        }

    });

    $('.page-content-bg').click(function(event) {
        $('.left-nav').animate({left: '-221px'}, 100);
        $('.page-content').animate({left: '0px'}, 100);
        $(this).hide();
    });

    $('.layui-tab-close').click(function(event) {
        $('.layui-tab-title li').eq(0).find('i').remove();
    });

   $("tbody.x-cate tr[fid!='0']").hide();
    // 栏目多级显示效果
    $('.x-show').click(function () {
        if($(this).attr('status')=='true'){
            $(this).html('&#xe625;'); 
            $(this).attr('status','false');
            cateId = $(this).parents('tr').attr('cate-id');
            $("tbody tr[fid="+cateId+"]").show();
       }else{
            cateIds = [];
            $(this).html('&#xe623;');
            $(this).attr('status','true');
            cateId = $(this).parents('tr').attr('cate-id');
            getCateId(cateId);
            for (var i in cateIds) {
                $("tbody tr[cate-id="+cateIds[i]+"]").hide().find('.x-show').html('&#xe623;').attr('status','true');
            }
       }
    })

    //左侧菜单效果
    // $('#content').bind("click",function(event){
	//点击菜单显示效果	
	$(document).ready(function() {
       $('.left-nav #nav li .sub-menu li ').click(function(){
		    $(this).addClass('menu-current').siblings().removeClass('menu-current');
		   })
    });

    // $('body .left-nav #side-nav #nav .show-id').click(function (event) {
    $('.left-nav #nav li').click(function (event) {

        if($(this).children('.sub-menu').length){
            if($(this).hasClass('open')){
                $(this).removeClass('open');
                $(this).find('.nav_right').html('&#xe6a7;');
                $(this).children('.sub-menu').stop().slideUp();
                $(this).siblings().children('.sub-menu').slideUp();
				
            }else{
                $(this).addClass('open');
                $(this).children('a').find('.nav_right').html('&#xe6a6;');
                $(this).children('.sub-menu').stop().slideDown();
                $(this).siblings().children('.sub-menu').stop().slideUp();
                $(this).siblings().find('.nav_right').html('&#xe6a7;');
                $(this).siblings().removeClass('open');
            }
        }else{
            var url = $(this).children('a').attr('_href');
            var title = $(this).find('cite').html();
            var index  = $('.left-nav #nav li').index($(this));

            for (var i = 0; i <$('.x-iframe').length; i++) {
                if($('.x-iframe').eq(i).attr('tab-id')==index+1){
                    tab.tabChange(index+1);
                    event.stopPropagation();
                    return;
                }
            };
            
            tab.tabAdd(title,url,index+1);
            tab.tabChange(index+1);
        }

        event.stopPropagation();

    })
    
})
var cateIds = [];
function getCateId(cateId) {
    
    $("tbody tr[fid="+cateId+"]").each(function(index, el) {
        id = $(el).attr('cate-id');
        cateIds.push(id);
        getCateId(id);
    });
}

/*弹出层*/
/*
    参数解释：
    title   标题
    url     请求的url
    id      需要操作的数据id
    w       弹出层宽度（缺省调默认值）
    h       弹出层高度（缺省调默认值）
*/
function x_admin_show(title,url,w,h){
    if (title == null || title == '') {
        title=false;
    };
    if (url == null || url == '') {
        url="404.html";
    };
    if (w == null || w == '') {
        w=($(window).width()*0.9);
    };
    if (h == null || h == '') {
        h=($(window).height() - 50);
    };
    layer.open({
        type: 2,
        area: [w+'px', h +'px'],
        fix: false, //不固定
        maxmin: true,
        shadeClose: true,
        shade:0.4,
        title: title,
        content: url
    });
}
function updatepwd(){
    layer.open({
        id:1,
        type: 1,
        title:'修改密码',
        skin:'layui-layer-rim',
        area: ['400px','260px'],
        content: '<div class="layui-form-item">'
            +'<label class="layui-form-label"> 旧密码</label>'
            +' <div class="layui-input-block">'
            +'<input type="password" name="password" placeholder="请输入密码" autocomplete="off" class="layui-input" style="width: 200px" id="pwd0">'
            +'</div></div>'
            +'<div class="layui-form-item">'
            +'<label class="layui-form-label"> 新 密 码</label>'
            +' <div class="layui-input-block">'
            +'<input type="password" name="password" placeholder="请输入密码" autocomplete="off" class="layui-input" style="width: 200px" id="pwd1">'
            +'</div></div>'
            +'<div class="layui-form-item">'
            +'<label class="layui-form-label">确认密码</label>'
            +' <div class="layui-input-block">'
            +'<input type="password" name="password" placeholder="请再次输入密码" autocomplete="off" class="layui-input" style="width: 200px" id="pwd2">'
            +'</div></div>'
        ,
        btn:['保存','取消'],
        btn1: function (index,layero) {
            if($("#pwd0").val()==""){
                layer.msg("旧密码不能为空",{icon: 2,time: 700,shade : [0.5 , '#000' , true]});
                $("#pwd0").select();
            }
            else if($("#pwd").val()!=$("#pwd0").val()){
                layer.msg("旧密码输入有误",{icon: 2,time: 700,shade : [0.5 , '#000' , true]});
                $("#pwd0").select();
            }
            else if($("#pwd1").val()==""){
                layer.msg("新密码不能为空",{icon: 2,time: 700,shade : [0.5 , '#000' , true]});
                $("#pwd1").select();
            }
            else if($("#pwd2").val()==""){
                layer.msg("再次输入不能为空",{icon: 2,time: 700,shade : [0.5 , '#000' , true]});
                $("#pwd2").select();
            }
            else if($("#pwd1").val()!=$("#pwd2").val()){
                layer.msg("两次密码输入不一致",{icon: 2,time: 700,shade : [0.5 , '#000' , true]});
                $("#pwd2").select();
            }
            else{
                $.ajax({
                    url:'/admin/updateemp',
                    type:'post',
                    data:{
                        empID:$("#empid").val(),
                        empPassword:$("#pwd2").val()
                    },
                    success:function (result) {
                        if(result=="ok"){
                            layer.confirm('修改成功,跳转至登录页面,请重新登录', {
                                btn : [ '确定' ]//按钮
                            }, function(index) {
                                window.location.href='/url/loginpage';
                            });
                        }else{
                            layer.msg("出现bug了，请联系9527")
                        }
                    },
                    error:function () {
                        layer.msg("出现bug了，请联系921607915")
                    }
                });
            }
        },
        btn2:function (index,layero) {
            layer.close(index);
        }
    });
}



/*关闭弹出框口*/
function x_admin_close(){
    var index = parent.layer.getFrameIndex(window.name);
    parent.layer.close(index);
}

//动态加载 js /css
function loadjscssfile(filename, filetype) {
    if (filetype == "js") { //判定文件类型
        var fileref = document.createElement('script')//创建标签
        fileref.setAttribute("type", "text/javascript")//定义属性type的值为text/javascript
        fileref.setAttribute("src", filename)//文件的地址
    }
    else if (filetype == "css") { //判定文件类型
        var fileref = document.createElement("link")
        fileref.setAttribute("rel", "stylesheet")
        fileref.setAttribute("type", "text/css")
        fileref.setAttribute("href", filename)
    }
    if (typeof fileref != "undefined")
        document.getElementsByTagName("head")[0].appendChild(fileref)
}

