	
	/* Inicializa API da ESRI */
    require(
		[
			"esri/map",
			"dojo/_base/connect", 				//Eventos
			"esri/dijit/BasemapToggle", 		//Alterar Mapa/Satélite
			"esri/dijit/LocateButton", 			//Geolocalização
			"esri/dijit/Geocoder", 				//Barra de Buscas da Argis
			"esri/geometry/Point",				//Para Criar Pontos
			"esri/geometry/webMercatorUtils",
			"esri/symbols/PictureMarkerSymbol",	//Cria Pin
			"esri/graphic",						//Cria Grafico
		], function(Map, connect, BasemapToggle, LocateButton, Geocoder, Point, webMercatorUtils, PictureMarkerSymbol, Graphic)
    {
			
			/*  Inicializa mapa */
            var mapInstance = new Map("map",
			{
				center: ["-46.633927", "-23.550393"],
				zoom: 15,				
				basemap: "streets"
			});
			
			connect.connect(mapInstance, "onLoad", function(){
				
				// Cria botão para alternar mapa/satélite
                var toggle = new BasemapToggle({
                    map: mapInstance,
                    basemap: "satellite"
                }, "BasemapToggle");
                toggle.startup();
				
				// Cria botão para detectar local atual
                var geoLocate = new LocateButton({ map: mapInstance }, "LocateButton");
                geoLocate.startup();
				
				//Cria barra de busca de endereços
				var geocoder = new esri.dijit.Geocoder({
				  autoComplete: true,
				  maxLocations: 20,
				  zoom:16,
				  outFields:"*",
				  map: mapInstance 
				}, "search");
                
                geocoder.startup();
				
				var postos = Car.getPostos();
				plotaPostos(postos);
				
				$("#dummy").change(function(){
					var val = $(this).val();
					val = val.split("|");
					var posto = {
								codigo: val[0],
								descricao: val[1],
								latitude : val[2],
								longitude : val[3],
								telefone: val[4],
								endereco:  val[5]
							}
					showPosto(posto);
					
				});
				
				function showPosto(posto){
					var point = new Point(posto.longitude, posto.latitude);
						point = webMercatorUtils.geographicToWebMercator(point);
					
					mapInstance.centerAt(point);
					
					var content = "<p><b>Endere&ccedil;o: </b>"+ posto.endereco+ "</p><p><b>Telefone: </b>"+ posto.telefone + "</p>";
					
					// mapInstance.infoWindow.resize(200,50);
					// mapInstance.infoWindow.setTitle(posto.descricao);
					// mapInstance.infoWindow.setContent(content);
					// mapInstance.infoWindow.show(point,mapInstance.getInfoWindowAnchor(point));
					// mapInstance.infoWindow.show(point);
				}
				
				function plotaPostos(postos){
					
					for(var x = 0; x < postos.length; x++){
						var posto = postos[x];
						
						var point = new Point(posto.longitude, posto.latitude);
						point = webMercatorUtils.geographicToWebMercator(point);
						
						var marker = new PictureMarkerSymbol('img/oficina.png', 34, 44).setOffset(4, 22);
						
						var arcgisGraphic = new Graphic(point, marker);
						
						var arcgisLayer = mapInstance.graphics;
						
						arcgisLayer.add(arcgisGraphic);
						
						if (!arcgisGraphic.info) {
							arcgisGraphic.info = {};
						}
										
					}
				}
			});
	});
	
	function selecionaPosto(cod, desc, lat, lon, tel, end){
		$("#dummy").val(cod +"|"+ desc +"|"+lat+"|"+lon+"|"+tel+"|"+end);
		$("#dummy").change();
	};