$(function(){
	//获取地址
	var address = $("#ip").html().split("\'");
	var city = null;
	if(address.length > 3){
		address = address[3];
		address = address.split(" ")[0];
		address = address.split("省");
		var province = address[0];
		
		address = address[1].split("市");
		city = address[0]+"市";
		var area = address[1];
	}else{
		city = "广州市";
	}
	var map = new AMap.Map('container', {
        resizeEnable: true
    });
	AMap.service(["AMap.PlaceSearch"], function() {
        var placeSearch = new AMap.PlaceSearch({ //构造地点查询类
            pageSize: 5,
            pageIndex: 1,
            city: city, //城市
            map: map,
            panel: "panel"
        });
        //关键字查询
        placeSearch.search('献血');
    });
});
