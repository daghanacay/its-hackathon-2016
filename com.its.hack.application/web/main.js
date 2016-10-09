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
	             
	             var local_icons = {
	            	        bike_icon: {
	            	            iconUrl: '../resources/bike.png',
	            	            iconSize:     [45, 45],
	            	            // iconAnchor:   [0, 0],
	            	        },
     	        			merchant_icon: {
     	        				iconUrl: '../resources/mastercard.png',
     	        				iconSize:     [45, 45],
     	        				// iconAnchor:   [0, 0],
     	        			}
	            	    };

	             
	             $scope.$on(eventName, function(event, args){
	              // TODO write here fetching the destination data and drawing it
	                  $scope.eventDetected = args.leafletEvent.popup._latlng.lat;
	                  getMerchants();
	                  console.log(args.leafletEvent.latlng);
	                  $scope.eventData = args.leafletEvent.popup;
	             });
	            
			    
			    $interval(function() {
				updateData();
				}, 10000);

				getMerchants = function(){
				$http.get("../rest/merchants/-37.8140000/144.9633200").success(
					function(data) {
					    // Store the data
					    $scope.marchantData = data;
					    $scope.merchantMarkerData = $scope.marchantData.map(function(dat) {
						return {
							lat : dat.latitude,
							lng : dat.longitude,
							message : 'Name: ' + dat.name + ' Bikes Available: ' + dat.freeBikes + ', Empty Slots: ' + dat.emptySlots,
							icon: local_icons.merchant_icon
						}
				  });
				$scope.markers = $scope.merchantMarkerData.concat($scope.parkMarkerData); 
					});
}


			    updateData = function() {
				$http.get("../rest/bikestations").success(
					function(data) {
					    // Store the data
					    $scope.sensorData = data;
					    $scope.parkMarkerData = $scope.sensorData.map(function(dat) {
						return {
							lat : dat.latitude,
							lng : dat.longitude,
							message : 'Name: ' + dat.name + ' Bikes Available: ' + dat.freeBikes + ', Empty Slots: ' + dat.emptySlots,
							icon: local_icons.bike_icon
						}
				  });
				$scope.markers = $scope.parkMarkerData.concat($scope.merchantMarkerData); 
					});
			    }
			    
			    angular.extend($scope, {
			        icons: local_icons
			    });

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

