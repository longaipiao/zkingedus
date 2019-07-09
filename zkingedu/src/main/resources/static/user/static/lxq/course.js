layui.use(['layer', 'table', 'element','form','upload'], function () {
    var $= layui.jquery
        , layer = layui.layer //弹层
        , table = layui.table //表格
        , element = layui.element //元素操作
        , form = layui.form //表单
        , upload= layui.upload //文件上传

    //搜索参数
    var nametype,name;

    //自定义方法
    var active = {
        //重载（刷新数据表格）
        reload:function(nametype,name,curr){
            setTimeout(function () {
                table.reload("couList", {//执行传值并重载
                    page: {
                        curr: curr //页码
                    },
                    where: {
                        nametype: nametype, //搜索名字类别
                        name: name //搜索名字
                    }
                });
            }, 800);
        },
        //获取父体系及子体系
        sysList:function () {
            var con='';
            $.ajax({
                url: '/admin/allsys'
                ,async: false
                ,data: {
                    systemFid: 0
                }
                ,success: function(res){
                    //拼接课程体系下拉框
                    $(res).each(function(i,s){
                        con+='<optgroup label="'+s.system_name+'">';
                        $(s.sysson).each(function(j,z){
                            con+='<option value="'+z.systemID+'">'+z.systemName+'</option>';
                        });
                        con+='</optgroup>';
                    });
                }
                ,error: function () {
                    alert("数据回调异常");
                }
            });
            //返回拼接字符串
            return con;
        },
        //根据体系Id获取课程类别
        ctypeList:function(systemID){
            var con='';
            $.ajax({
                url: '/admin/cTypes',
                async: false,
                data: {
                    systemID: systemID
                },
                success: function(res){
                    //拼接类别下拉框
                    con+='<option value="">请选择一个类别</option>';
                    $(res).each(function(i,ct){
                        con+='<option class="'+ct.tsid+'" value="'+ct.tid+'">'+ct.tname+'</option>';
                    });
                    /*$('#adcourseType').html(con);
                    //重新渲染下拉框
                    form.render('select');*/
                },
                error: function(){
                    alert("课程类别接口回调失败");
                }
            });
            return con;
        }
    };

    //监听头工具栏
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            //添加课程
            case 'add':
                //下拉框赋值
                $('#adcourseSid').append(active.sysList());
                //积分不显示
                $('#tigger').hide();

                //打开弹出层
                layer.open({
                    type: 1
                    ,title: "添加课程" //弹出层标题
                    ,area: ['80%', '90%'] //弹出层大小
                    ,content: $("#courseAdd") //弹出层内容
                    ,shade: 0 //不显示遮罩
                    ,yes: function(){
                        layer.closeAll();
                    }
                });

                //重新渲染下拉框
                form.render('select');
                break;
        };
    });

    //监听增加课程表单单选框
    form.on("radio(courseFree)", function (data) {
        var courseFree = data.value;
        if (courseFree!=0) {
            //收费让积分文本框显示
            $('#tigger').show();
        } else{
            //免费则不显示
            $('#tigger').hide();
        }
    });
    //监听修改课程表单单选框
    form.on("radio(upcourseFree)", function (data) {
        var courseFree = data.value;
        if (courseFree!=0) {
            //收费让积分文本框显示
            $('#uptigger').show();
        } else{
            //免费则不显示
            $('#uptigger').hide();
        }
    });

    //监听增加表单下拉框点击（二级联动）
    form.on('select(courseSid)',function (data) {
        //获得选中的体系Id
        var systemID=data.value;
        var ctypeList = active.ctypeList(systemID);
        $('#adcourseType').html(ctypeList);
        //重新渲染下拉框
        form.render('select');
    });
    //监听修改表单下拉框点击（二级联动）
    form.on('select(upcourseSid)',function (data) {
        //获得选中的体系Id
        var systemID=data.value;
        var ctypeList = active.ctypeList(systemID);
        $('#upcourseType').html(ctypeList);
        //重新渲染下拉框
        form.render('select');
    });

    //监听搜索按钮
    $("#searchBtn").on("click",function(){
        nametype=$('#nametype').val();
        name=$('#name').val();
        //调用重载
        active.reload(nametype,name,1);
    });

    //渲染数据表格（获取所有体系）
    table.render({
        id: 'couList',
        elem: '#couList', //数据表格
        url: '/admin/couList',  //后台传来的数据
        page: true, //开启分页
        toolbar: '#toolbarDemo', //开启工具栏
        limit: 6,
        limits: [6,12,18,24,30],  //每页条数下拉框选项
        cols: [[
            {field: 'course_id', align: 'left', title: '课程ID', sort: true}
            ,{field: 'course_name', align: 'left', title: '课程名称'}
            ,{field: 'system_fname', align: 'left', title: '所属父体系'}
            ,{field: 'system_name', align: 'left', title: '所属子体系'}
            ,{field: 't_name', align: 'left', title: '课程类别'}
            ,{field: 'course_free', align: 'left', title: '免费章节数', sort: true}
            ,{field: 'course_time', align: 'left', title: '更新时间', sort: true}
            ,{field: 'emp_name', align: 'left', title: '上传教师'}
            ,{field: 'course_inte', align: 'left', title: '课程积分', sort: true}
            ,{field: 'course_desc', align: 'left', title: '课程简介'}
            ,{field: 'course_img', hide:true, align: 'left', title: '课程图片'}
            ,{field: 'system_id', hide:true, align: 'left', title: '课程子体系Id'}
            ,{field: 't_id', hide:true, align: 'left', title: '课程类别Id'}
            ,{field: 'course_num', hide:true, align: 'left', title: '学习人数'}
            ,{field: 'course_state', hide:true, align: 'left', title: '课程状态'}
            ,{title: '操作', toolbar: '#barDemo'}
        ]]
    });

    //监听增加课程提交
    form.on('submit(formDemo)', function(data){
        // layer.msg(JSON.stringify(data.field));
        $.ajax({
            url:'/admin/courseAdd',
            data:data.field,
            success:function(res){
                alert(res);
                if(res!=0){
                    //获取当前页码数
                    var curr=$(".layui-laypage-em").next().html();
                    //重载刷新数据
                    active.reload(nametype,name,curr);
                    alert("添加成功");
                    //关闭弹出层
                    layer.closeAll();
                    //清空添加form表单的数据
                    document.getElementById("couAdd").reset();
                }
            },
            error:function (data) {
                layui.alert("接口回调失败");
            }
        }) ;
        return false;
    });
    //监听修改课程提交
    form.on('submit(couUpd)', function(data){
        alert(JSON.stringify(data.field));
        $.ajax({
            url:'/admin/courseUpd',
            data:data.field,
            success:function(res){
                alert(res);
                if(res!=0){
                    //获取当前页码数
                    var curr=$(".layui-laypage-em").next().html();
                    //重载刷新数据
                    active.reload(nametype,name,curr);
                    alert("修改成功");
                    //关闭弹出层
                    layer.closeAll();
                    //清空添加form表单的数据
                    document.getElementById("couUpd").reset();
                }else{
                    layer.msg("修改失败");
                }
            },
            error:function (data) {
                layui.alert("接口回调失败");
            }
        }) ;
        return false;
    });

    //添加课程图片上传
    var uploadInst = upload.render({
        elem: '#test' //图片上传点击事件
        ,url: '/admin/adcourseImg'
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                //图片预览（base64）
                $('#adcourseImg').attr('src', result);
            });
        }
        //文件上传成功返回的参数
        ,done: function(res){
            alert(res.code);
            //上传失败
            if(res.code != 0){
                return layer.msg('上传失败');
            }else{//上传成功
                //将返回的图片路径放进隐藏域
                $('#simg').val(res.data.src);
            }
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });
    //修改课程图片上传
    var uploadInst1 = upload.render({
        elem: '#upcourseImg' //图片上传点击事件
        ,url: '/admin/adcourseImg'
        ,before: function(obj){
            //预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
                //图片预览（base64）
                $('#upcourseImg').attr('src', result);
            });
        }
        //文件上传成功返回的参数
        ,done: function(res){
            alert(res.code);
            //上传失败
            if(res.code != 0){
                return layer.msg('上传失败');
            }else{//上传成功
                //将返回的图片路径放进隐藏域
                $('#simg1').val(res.data.src);
            }
        }
        ,error: function(){
            //演示失败状态，并实现重传
            var demoText = $('#demoText1');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst1.upload();
            });
        }
    });

    //监听行工具栏(查看课程、修改课程)
    table.on('tool(test)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data //获得点击行数据
            ,layEvent = obj.event; //获得 lay-event 对应的值，判断进行的操作
        if(layEvent=='detail'){//查看操作

        }else if(layEvent=='edit'){ //修改操作
            //给弹出层赋值
            $("#upcourseID").val(data.course_id);
            $("#upcourseName").val(data.course_name);
            $("#upcourseInte").val(data.course_inte);
            $("#upcourseDesc").val(data.course_desc);
            //课程图片赋值
            $("#upcourseImg").attr("src",data.course_img);
            //图片隐藏域赋值
            $('#simg1').val(data.course_img);
            //课程体系下拉框
            $('#upcourseSid').append(active.sysList());
            //体系下拉框选中
            $("#upcourseSid").find("option[value='"+data.system_id+"']").prop("selected",true);
            //课程分类下拉框
            var ctypeList = active.ctypeList(data.system_id);
            $('#upcourseType').html(ctypeList);
            //课程分类下拉框选中
            $("#upcourseType").find("option[class='"+data.system_id+"']").prop("selected",true);
            //课程收费单选框选中
            $("input[lay-filter=upcourseFree][value="+data.course_free+"]").prop("checked",true);

            alert(data.course_free);

            form.render("radio");

            //判断课程是否收费，如不收费则不显示积分
            if (data.course_free!=0) {
                //收费让积分文本框显示
                $('#uptigger').show();
            } else{
                //免费则不显示
                $('#uptigger').hide();
            }

            //打开弹出层
            var a=layer.open({
                type: 1
                ,title: "课程修改" //弹出层标题
                ,area: ['80%', '90%'] //弹出层大小
                ,content: $("#courseUpd") //弹出层内容
                ,shade: 0 //不显示遮罩
                ,yes: function(){
                    layer.closeAll();
                }
            });

            //渲染form表单
            form.render();
        }
    });
});