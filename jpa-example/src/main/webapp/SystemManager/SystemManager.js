angular.element(document).ready(function () {
    console.log('page loading completed');
});

var sysManager = angular.module('sysManager', []).config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] =  'application/x-www-form-urlencoded';
})

sysManager.controller('sysManagerContoller', [ '$scope', 'regRestManagerService', 'regRestaurantService', function($scope, regRestManagerService, regRestaurantService){
	
	$scope.viewRestaurants = false;
	$scope.viewManagers = false;
	
	$scope.getRestaurants = function() {
		$scope.viewRestaurants = true;
		$scope.viewManagers = false;
		regRestaurantService.getRests().then(
				function(response){
					$scope.restaurants = response.data;
					console.log($scope.restaurants);
				}
		)
	}
	
	$scope.getRestManagers = function() {
		$scope.viewRestaurants = false;
		$scope.viewManagers = true;
		regRestManagerService.getRestManagers().then(
				function(response){
					$scope.managers = response.data;
					console.log($scope.managers);
				}
		)
	}
		
	$scope.registerRestManager = function(){
		regRestManagerService.registerRestManager();
	}
	
	$scope.registerRestaurant = function(){
		regRestaurantService.registerRestaurant();
	}
}]);

sysManager.service('regRestManagerService', ['$window', '$http', function($window, $http){
	
	this.registerRestManager = function(){
		$window.location.href = '/SystemManager/RegistrationRestManager.html'
	}
	
	this.getRestManagers = function(){
		return $http.get("../sysManager/restManagers")
	}
}]);

sysManager.service('regRestaurantService',['$window', '$http', function($window, $http){
	
	this.registerRestaurant = function(){
		$window.location.href = '/SystemManager/RegistrationRestaurant.html'
	}
	
	this.getRests = function(){
		return $http.get("../sysManager/restaurants")
	}
	
}]);