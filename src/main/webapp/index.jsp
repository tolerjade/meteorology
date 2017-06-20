<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html>
<html lang='en'>
<head>
    <meta charset='utf-8'/>
    <!--<link href="<%=basePath %>ol3/ol.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=basePath %>ol3/ol.js" charset="utf-8"></script>-->
    <title>wfs-demo</title>
    <script type='text/javascript' src='http://www.openlayers.cn/olapi/OpenLayers.js'></script>
    <script type='text/javascript'>
        var map;
        function init() {
            //定义地图边界
            var bounds = new OpenLayers.Bounds(73.44696, 6.3186412, 135.08583, 53.557926);
            var options = {
                numZoomLevels: 10,
                projection: "EPSG:4326",
            };
            map = new OpenLayers.Map('map', options);
            var wms_layer_map = new OpenLayers.Layer.WMS(
                'BASE Layer',
                'http://localhost:8080/geoserver/wms',
                {
                    layers: "LanMeiArea:Countries"
                },
                {isBaseLayer: true}
            );
            var wms_layer_province = new OpenLayers.Layer.WMS(
                'Province Layer',
                'http://localhost:8080/geoserver/wms',
                {
                    layers: "LanMeiArea:Provinces", transparent: true
                },
                {opacity: .8}
            );
            var wms_layer_rails = new OpenLayers.Layer.WMS(
                'Rails Layer',
                'http://localhost:8080/geoserver/wms',
                {
                    layers: "LanMeiArea:Rails", transparent: true
                },
                {transitionEffect: "resize"}
            );
            var wms_layer_roads = new OpenLayers.Layer.WMS(
                'Roads Layer',
                'http://localhost:8080/geoserver/wms',
                {
                    layers: "LanMeiArea:Roads", transparent: true
                },
                {transitionEffect: "resize"}
            );

            map.addLayers([wms_layer_map, wms_layer_province, wms_layer_rails, wms_layer_roads]);
            map.addControl(new OpenLayers.Control.LayerSwitcher());
            map.addControl(new OpenLayers.Control.MousePosition());
            map.addControl(new OpenLayers.Control.ScaleLine());
            map.addControl(new OpenLayers.Control.Scale);
            map.zoomToExtent(bounds);
        }

    </script>
</head>
<body onload="init();">
<div id="map" style='width:1800px; height: 1000px;'></div>
<!--
<script>
    var vector = new ol.layer.Vector({
        source : new ol.source.Vector({
            format : new ol.format.GeoJSON(),
            //url : 'http://localhost:8080/geoserver/nyc_roads/ows?service=WFS&version=1.0.0&request=GetFeature&typeName=nyc_roads:nyc_roads&maxFeatures=50&outputFormat=application%2Fjson'
            //url : 'http://192.168.31.134:8080/geoserver/wfs?service=wfs&version=1.1.0&request=GetFeature&typeNames=NaturalEarth:ne_10m_admin_0_countries&outputFormat=application/json&srsname=EPSG:4326'
            url : 'http://192.168.31.134:8080/geoserver/wfs?service=wfs&version=1.1.0&request=GetFeature&LanMeiArea:LanMeiArea&outputFormat=application/json&srsname=EPSG:4326'
        }),
        style : function(feature, resolution){
            return new ol.style.Style({
                stroke : new ol.style.Stroke({
                    color : 'blue',
                    width : 1
                })
            });
        }
    });

    var map = new ol.Map({
        // 设置地图图层
        layers: [
            // 创建一个使用Open Street Map地图源的瓦片图层
            new ol.layer.Tile({source: new ol.source.OSM()}),
            vector
        ],
        // 设置显示地图的视图
        view: new ol.View({
            //extent: [110, 39, 1116, 42],
            center :  [-73.99710639567148, 40.742270050255556],    // 定义地图显示中心于经度0度，纬度0度处
            zoom : 14,
            maxZoom : 19,
            projection : "EPSG:4326"// 并且定义地图显示层级为2
        }),
        // 让id为map的div作为地图的容器
        target: 'map'
    });
</script>
-->
</body>

</body>
</html>