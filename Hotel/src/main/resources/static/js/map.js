//初始化地图插件
window.onload = function(){
	var map = new AMap.Map('wrapper',{
		 center: [104.06658,30.659391],
		 zoom:20
	});

	var marker = new AMap.Marker({
	    position: new AMap.LngLat(104.06658,30.659391),
	    offset: new AMap.Pixel(-10, -10),
	    icon: "../img/hotel.png", // 添加 Icon 实例
	});
	 marker.setLabel({
	        offset: new AMap.Pixel(10, -20),
	        content: "罗马假日大酒店"
	    });
	//实例化信息窗体
	//标题头
	var title = '罗马假日酒店<span style="font-size:11px;color:#F00;">价格:99999</span>',
	    content = [];   //内容
	content.push("<img src='http://tpc.googlesyndication.com/simgad/5843493769827749134'>地址：天府大道8888号");
	content.push("电话：010-64733333");
	content.push("<a href='http://ditu.amap.com/detail/B000A8URXB?citycode=110105'>详细信息</a>");

	//创建信息窗体
	var infoWindow = new AMap.InfoWindow({
		isCustom:true, //是否自定义信息窗体
		content: createInfoWindow(title, content.join("<br/>")), //调用创建信息窗体的方法--信息窗体的内容
		offset:new AMap.Pixel(16, -45) //偏移量
	});

	//如果希望的是点击标记才 出现这个信息窗口，那把 下面的注释去掉即可
	AMap.event.addListener(marker,'click',function(){ //监听点标记的点击事件
		infoWindow.open(map,marker.getPosition()); //信息窗体打开
	});

	//构建自定义信息窗体
	function createInfoWindow(title, content) {
		//info 为 信息窗体
	    var info = document.createElement("div");
	    info.className = "info";

	    //可以通过下面的方式修改自定义窗体的宽高
	    //info.style.width = "400px";
	    // 定义顶部标题
	    var top = document.createElement("div");
	    var titleD = document.createElement("div");
	    var closeX = document.createElement("img");
	    top.className = "info-top";
	    titleD.innerHTML = title;
	    closeX.src = "http://webapi.amap.com/images/close2.gif";
	    closeX.onclick = closeInfoWindow; //点击右上角的x可以关闭该信息窗体

	    top.appendChild(titleD);
	    top.appendChild(closeX);
	    info.appendChild(top);   //信息窗体增加顶部的div

	    // 定义中部内容
	    var middle = document.createElement("div");
	    middle.className = "info-middle";
	    middle.style.backgroundColor = 'white';
	    middle.innerHTML = content;
	    info.appendChild(middle);  //信息窗体增加中部的div

	    // 定义底部内容
	    var bottom = document.createElement("div");
	    bottom.className = "info-bottom";
	    bottom.style.position = 'relative';
	    bottom.style.top = '0px';
	    bottom.style.margin = '0 auto';
	    var sharp = document.createElement("img");
	    sharp.src = "http://webapi.amap.com/images/sharp.png";
	    bottom.appendChild(sharp);
	    info.appendChild(bottom);  //信息窗体增加底部的div
	    return info;
	}

	//关闭信息窗体
	function closeInfoWindow() {
	    map.clearInfoWindow();
	} 
	map.add(marker);
	
}



