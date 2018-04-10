<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>文章列表</title>
    <link rel="stylesheet" href="${request.contextPath}/plugins/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="${request.contextPath}/plugins/build/css/app.css" media="all">
    <link rel="stylesheet" href="${request.contextPath}/plugins/build/css/themes/red.css" media="all">
	<script src="${request.contextPath}/plugins/layui/layui.js"></script>
</head>

<body>
    <div class="kit-table">
    <form class="layui-form" lay-filter="kit-search-form">
        <div class="kit-table-header">
            <div class="kit-search-btns">
                <a href="javascript:;" class="addUser layui-btn layui-btn-sm"><i class="layui-icon">&#xe608;</i> 新增</a>
                <a href="javascript:;" data-action="del-bulk" class="layui-btn layui-btn-sm layui-btn-danger"><i class="layui-icon">&#xe640;</i> 批量删除</a>
                <button type="button" class="layui-btn" id="article_upload_btn">
				  <i class="layui-icon">&#xe67c;</i>上传文件
				</button>
            </div>
            <div class="kit-search-inputs">
                <div class="kit-search-keyword">
                    <input type="text" class="layui-input" name="keyword" placeholder="搜索关键字.." />
                    <button lay-submit lay-filter="search"><i class="layui-icon">&#xe615;</i></button>
                </div>
                
            </div>
        </div>
        
    </form>
    <div class="kit-table-body">
        <table id="demo" lay-filter="demo"></table>
        <script type="text/html" id="barDemo">
            <a class="layui-btn layui-btn-xs" lay-event="detail">查看</a>
            <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
        </script>
    </div>
</div>

<!-- d就代表这一行的数据，layui2.2.0就是这样定义的 -->
<script type="text/html" id="statusTpl">

{{#  if(d.status == 0){ }}
    	不展示
  {{#  } else if(d.status == 1){ }}
    	展示
  
  {{#  } }}
  
</script>

<script>
    layui.use(['table'], function() {
        var table = layui.table,
            $ = layui.jquery,
            layer = layui.layer,
            form = layui.form,
            laytpl = layui.laytpl;
        var tableIns = table.render({
            elem: '#demo',
            height: 'full-135', //容器高度
            url: '/article/list',
            page: true,
            id: 'demo',
            cols: [
                [{
                    checkbox: true,
                    fixed: true
                }, {
                    field: 'id',
                    title: 'ID',
                    width: 80
                }, {
                    field: 'title',
                    title: '标题',
                    width: 280
                },{
                    field: 'publishingLocation',
                    title: '发布地区',
                    width: 120
                }, {
                    field: 'publisher',
                    title: '发布人',
                    width: 140
                }, {
                    field: 'releaseDate',
                    title: '发布时间',
                    width: 160
                }, {
                    field: 'pageView',
                    title: '热度',
                    width: 80
                }, {
                    field: 'status',
                    title: '状态',
                    width: 80,
                    templet: '#statusTpl'
                },  {
                    fixed: 'right',
                    title: '操作',
                    width: 150,
                    align: 'center',
                    toolbar: '#barDemo'
                }]
            ],
            done: function(res, curr, count) {
                //如果是异步请求数据方式，res即为你接口返回的信息。
                //如果是直接赋值的方式，res即为：{data: [], count: 99} data为当前页数据、count为数据总长度
                // console.log(res);
                // //得到当前页码
                // console.log(curr);
                // //得到数据总量
                // console.log(count);
            },
            loading: true,
            //method: 'post'
        });
        
        //监听搜索表单提交
        form.on('submit(search)', function(data) {
            console.log(data.field);
            layer.msg(JSON.stringify(data.field));
            //带条件查询
            tableIns.reload({
                where: data.field
            });
            return false;
        });
        //监听工具条
        table.on('tool(demo)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
            var data = obj.data; //获得当前行数据
            var layEvent = obj.event; //获得 lay-event 对应的值
            var tr = obj.tr; //获得当前行 tr 的DOM对象

            if (layEvent === 'detail') { //查看
                console.log(table.checkStatus('demo'));
                //do somehing
            } else if (layEvent === 'del') { //删除
                layer.confirm('真的删除行么', function(index) {
                    obj.del(); //删除对应行（tr）的DOM结构
                    layer.close(index);
                    //向服务端发送删除指令
                });
            } else if (layEvent === 'edit') { //编辑
            	$(window).one("resize",function(){
            			var index = layui.layer.open({
            				title : "编辑文章",
            				type : 2,
            				content : "/article/editArticle?id="+data.id,
            				success : function(layero, index){
            					setTimeout(function(){
            						layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
            							tips: 3
            						});
            					},500)
            				}
            			})			
            			layui.layer.full(index);
            	}).resize();
              
            }
        });
        form.render(null, 'kit-search-form');
        $('#kit-search-more').on('click', function() {
            $('.kit-search-mored').toggle();
        });
        var editIndex;
        form.on('submit(formEdit)', function(data) {
            layer.msg('formEdit');
            editIndex && layer.close(editIndex); //关闭弹出层
            return false;
        });
        $('.kit-search-btns').children('a').off('click').on('click', function() {
            var $that = $(this),
                action = $that.data('action');
            switch (action) {
                case 'add':
                    var d = {
                        user: {
                            sign: '',
                            city: '',
                            classify: '',
                            experience: '',
                            id: 0,
                            logins: '',
                            score: '',
                            sex: 1,
                            sign: '',
                            username: '',
                            wealth: ''
                        },
                        citys: staticData.citys,
                        classifies: staticData.classifies
                    };
                    //渲染
                    laytpl($('#edit-tpl').html()).render(d,
                        function(html) {
                            layer.open({
                                type: 1,
                                title: '表单',
                                content: html,
                                area: ['800px', '600px'],
                                btn: ['提交', '重置', '取消'],
                                yes: function(index, layero) {
                                    editIndex = index;
                                    $('form[lay-filter="form-edit"]').find('button[lay-submit]').click();
                                },
                                btn2: function(index, layero) {
                                    $('form[lay-filter="form-edit"]').find('button[type="reset"]').click();
                                    return false;
                                },
                                success: function() {
                                    form.render(null, 'form-edit');
                                }
                            });
                        });
                    break;
                case 'del-bulk':
                    var d = table.checkStatus('demo');
                    if (d.data.length === 0) {
                        layer.msg('请选择要删除的数据');
                        return;
                    }
                    var data = d.data,
                        names = [],
                        ids = [];
                    layui.each(data, function(index, item) {
                        console.log(item);
                        names.push(item.username);
                        ids.push(item.id);
                    });
                    layer.msg(names.join(','));
                    console.log(ids.join(','));
                    break;
            }
        });
        //添加
	//改变窗口大小时，重置弹窗的高度，防止超出可视区域（如F12调出debug的操作）
	$(window).one("resize",function(){
		$(".addUser").click(function(){
			var index = layui.layer.open({
				title : "添加文章",
				type : 2,
				content : "/admin/addUser",
				success : function(layero, index){
					setTimeout(function(){
						layui.layer.tips('点击此处返回文章列表', '.layui-layer-setwin .layui-layer-close', {
							tips: 3
						});
					},500)
				}
			})			
			layui.layer.full(index);
		})
	}).resize();
        
});
    
</script>

<script>
layui.use('upload', function(){
  var upload = layui.upload;
   
  //执行实例
  var uploadInst = upload.render({
    elem: '#article_upload_btn' //绑定元素
    ,url: '/article/upload' //上传接口
    ,method: 'POST'
    ,accept: 'file'
    ,size: 102400
   	,before: function(obj){
       layer.load();
     }
    ,done: function(res){
    	layer.closeAll('loading');
    	layer.alert(res.data);
    }
    ,error: function(){
    	layer.closeAll('loading');
    }
  });
});
</script>
</body>

</html>