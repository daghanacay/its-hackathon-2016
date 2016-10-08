var app = angular.module("com.its.hack", [ 'ui-leaflet' ]);
app
	.controller(
		"MainHeatmapController",
		[
			"$scope",
			"$http",
			"$interval",
			function($scope, $http, $interval) {
			    
			    
			    $scope.$on('$routeChangeStart', function(event,
				    next, current) {
				$scope.mapHeight = "500px";
			    });

			    $interval(function() {
				updateData();
				$scope.layers.overlays.heat.doRefresh = true;
			    }, 10000);

			    updateData = function() {
				$http.get("../rest/merchants").success(
					function(data) {
					    // Store the data
					    $scope.sensorData = data;
					    // Convert sensor data to be used by
					    // the heat map
					    mapData = data.map(function(dat) {
						return [ dat.latitude,
							dat.longitude,
							dat.heat]
					    });
					    $scope.layers.overlays = {
						heat : {
						    name : 'Heat Map',
						    type : 'heat',
						    data : mapData,
						    layerOptions : {
							radius : 20,
							blur : 10
						    },
						    visible : true,
						    doRefresh : true
						}
					    };
					});
			    }

			    angular
				    .extend(
					    $scope,
					    {
						center : {
						    lat : -37.8140000,
						    lng : 144.9633200,
						    zoom : 16
						},
						markers : {},
						layers : {
						    baselayers : {
							osm : {
							    name : 'OpenStreetMap',
							    url : 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
							    type : 'xyz'
							}
						    }
						}
					    });
			} ]);

