var app = angular.module("com.its.hack", [ 'ui-leaflet' ]);
app
	.controller(
		"MainHeatmapController",
		[
			"$scope",
			"$http",
			"$interval",
			function($scope, $http, $interval) {
			     var mapEvents = ['click', 'contextmenu', 'popupopen', 'popupclose'];
	             var eventName = 'leafletDirectiveMap.' + 'popupopen';
	             $scope.$on(eventName, function(event){
	              // TODO write here fetching the destination data and drawing it
	                  $scope.eventDetected = event.name;
	             });
	            
			    
			    $interval(function() {
				updateData();
				markerData = $scope.sensorData.map(function(dat) {
						return {
							lat : dat.latitude,
							lng : dat.longitude,
							message : 'Name: ' + dat.merchantName + ' Industry: ' + dat.industry
						}
				  });
				$scope.markers = markerData; 
				
			    }, 10000);

			    updateData = function() {
				$http.get("../rest/merchants/-37.8140000/144.9633200").success(
					function(data) {
					    // Store the data
					    $scope.sensorData = data;
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
							ocm : {
							    name : 'OpenCycleMap',
							    url : 'http://{s}.tile.opencyclemap.org/cycle/{z}/{x}/{y}.png',
							    type : 'xyz'
							}
						    }
						}
					    });
			} ]);

