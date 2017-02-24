var restManager = angular.module('restManager', []).config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] =  'application/x-www-form-urlencoded';
})



restManager.controller('restManagerController', [ '$scope', 'konfService', 'dringService', 'restaurantInfoService', 'foodService', function($scope, konfService, dringService, restaurantInfoService, foodService ){
	
	$scope.profilShow = true;
	
	$scope.getRestaurant = function() {
		restaurantInfoService.getRestaurant().then(
				function(response){
					$scope.restaurant = response.data;
					alert($scope.restaurant);
					console.log($scope.restaurant);
				}
		
		); 
	}
	
	$scope.saveRestaurantInfo = function() {
		
	}
		
	$scope.getFoods = function(){
		
	}
	
	$scope.saveFoodChanges = function(){
		
	}
	
	$scope.addFood = function(){
		
	}
	
	$scope.getDrinks = function(){
		
	}
	
	$scope.saveDrinkChanges = function(){
		
	}
	
	$scope.addDrink = function(){
		
	}
	
	
	$scope.getKonf = function(){
		
	}
	
	$scope.saveKonfChanges = function(){
		
	}
	
	$scope.addKonf = function(){
		
	}
	
	
}]);








restManager.service('restaurantInfoService',['$window', '$http', function($window, $http){
	this.getRestaurant = function(){
		return $http.get("../restManager/myRestaurant/1");
	}
	
	
	this.saveChanges;
	
}]);

restManager.service('foodService',['$window', '$http', function($window, $http){
	
	this.getFoods;
	this.saveChanges;
	this.addFood;
	
}]);

restManager.service('dringService', ['$window', '$http', function($window, $http){
	this.getDrinks;
	this.saveChanges;
	this.addDring;
	
}]);

restManager.service('konfService', ['$window', '$http', function($window, $http){
	this.getKonf;
	this.saveChanges;
	this.addKonf;
	
}]);





