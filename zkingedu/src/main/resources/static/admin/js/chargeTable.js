$(function () {

    /*
    *           我的充值记录    userinfo.html
    * */
    //方法二则直接使分用layui-v2.0.2里面的页功能，没有用到core.js里面的方法
    layui.use('laypage', function(){
        var laypage = layui.laypage;
        var config = {page:1,pageSize:3};
        var url = "/user/MyCharges";
        $.getJSON(url,config,function(res){
            laypage.render({
                elem: 'demo3',//必须放id
                count: 100,
                first: '首页',
                last: '尾页',
                count: res.count, //总条数
                limit:config.pageSize, //每页条数
                theme: '#FFB800', //自定义颜色
                jump: function(obj, first){
                    //alert(obj.curr)
                    if(!first){ //首次则不进入
                        config.page = obj.curr;
                        getMyChargesByPage(url,config)
                    }
                }
            });
            parseMyCharges(res,config.page);
        });
    });
    //点击页数从后台获取数据
    function getMyChargesByPage(url,config){
        $.getJSON(url,config,function(res){
            parseMyCharges(res,config.page);
        });
    }

    //解析数据，currPage参数为预留参数，当删除一行刷新列表时，可以记住当前页而不至于显示到首页去
    function parseMyCharges(res,currPage){
        var html = "";
        $.each(res.data,function (i,o) {
            html += "<tr>" +
                "<td>"+o.chargeMoney+"</td>" +
                "<td>"+o.chargeIntegral+"</td>" +
                "<td>"+o.chargeTime+"</td>" +
                "</tr>"
        });
        $("#chargeTbody").html(html);
    }
})