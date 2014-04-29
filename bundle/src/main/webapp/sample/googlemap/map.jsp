<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page contentType="text/html; charset=utf-8" %>
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:v="urn:schemas-microsoft-com:vml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <title>地图效果</title>
    <script src="http://maps.google.com/maps?file=api&amp;v=2&amp;sensor=true&amp;key=ABQIAAAAR3MKn7u7mNCdazg9tGUdFhRwBbGnZgpAwn52unSmXYqKdxdDuxRUrSvstEg8cmZG7wphOrDDNB7bMQ" type="text/javascript"></script>

   <script type="text/javascript">

/*地图*/
    function initialize() {
      if (GBrowserIsCompatible()) {      
        
        //加载地图区域
        var map = new GMap2(document.getElementById("map_canvas"));
        //设置中心坐标,缩放等级
        map.setCenter(new GLatLng(39.917, 116.397), 13);
        
        //控制器类型,（控件位置,控件大小）-可选参数
        map.addControl(new GLargeMapControl(),new GControlPosition(G_ANCHOR_TOP_LEFT , new GSize(10,10)));
        //加入卫星和混合地图标签
   map.addControl(new GHierarchicalMapTypeControl());
        //开启缩略图
        map.addControl(new GOverviewMapControl());
        
        //开启鼠标滑轮
        map.enableScrollWheelZoom();
        //开启地图搜索控件
        map.enableGoogleBar();
        
        // 添加底面叠加层,定坐标
        var boundaries = new GLatLngBounds(new GLatLng(39.917, 116.397), new GLatLng(39.935, 11***2));
        // 添加底面叠加层,添加图片,可作广告之类
        var oldmap = new GGroundOverlay("/image/ceng.jpg", boundaries);

        map.addOverlay(oldmap);
        
        //在地图的特定坐标位置 创建HTML文本
        map.openInfoWindowHtml(new GLatLng(39.922, 11***14),"<font color='blue' size='2'>大家好,我是 slzs……－ －|||<br>这个添加层可以在很多地方利用</font>");
        /*map.getCenter();可以得到地图中心位置坐标*/
        
        //在特定位置加标记
        map.addOverlay(new GMarker(new GLatLng(39.922,116.377)));
        //延时3.5秒显示
        window.setTimeout(function(){
        map.openInfoWindowHtml(new GLatLng(39.922,116.377),"<font color='red' size='2'>这是一个固定位置的标记</font>");
        },3500);
        
        //添加一个可拖动的标记
        var marker = new GMarker(new GLatLng(39.905,116.377), {draggable: true});
        
        //监听这个标记的提起动作
        GEvent.addListener(marker, "dragstart", function() {
          map.closeInfoWindow();
        });
   //监听这个标记的放下动作
        GEvent.addListener(marker, "dragend", function() {
          marker.openInfoWindowHtml("<font color='red' size='2'>已经放下，标记反弹</font>");
       //延时1秒关闭提示信息
       window.setTimeout(function(){marker.closeInfoWindowHtml();},1000);
        });
   //添加这个标记
        map.addOverlay(marker);
        //延时6秒显示提示信息
        window.setTimeout(function(){
        marker.openInfoWindowHtml("<font color='red' size='2'>这是一个可拖动位置的标记,按住鼠标左键拖动</font>");
        },6000);
      }
    }

    </script>
</head>
<body onload="initialize()" onunload="GUnload()">
<center>

    <div style="position: absolute;z-index: 10;margin-top: 472px;margin-left:5px;background-color: #fff;width: 55px;height: 24px"/><img src="/image/search.jpg" onerror="this.style.display='none'" alt="搜 索1"/></div>
    <!-- <div style="position: absolute;z-index: 10;margin-top: 583px;margin-left:500px;font-size: 12px;background-color: #fff"/> &nbsp;&nbsp;声明：部分图片，功能为本人制作&nbsp;---------&nbsp;&nbsp;0神龙之首0&nbsp;&nbsp; </div> -->
    <div id="map_canvas" style="width: 900px; height: 500px;background-image:url('/image/mapBg.jpg');z-index: 1"></div>
    </center>
</body>
</html>

