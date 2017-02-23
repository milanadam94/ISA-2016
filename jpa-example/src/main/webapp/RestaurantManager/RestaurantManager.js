var restManager = angular.module('restManager', []).config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] =  'application/x-www-form-urlencoded';
})



restManager.controller('restManagerController', [ '$scope', 'konfService', 'dringService', 'restaurantInfoService', 'foodService', function($scope, konfService, dringService, restaurantInfoService, foodService ){
	
	setShows = function(profilShow, picaShow, jelovnikShow, konfiguracijaShow){
		$scope.profilShow = profilShow;
		$scope.picaShow = picaShow;
		$scope.jelovnikShow = jelovnikShow;
		$scope.konfiguracijaShow = konfiguracijaShow;
	}
	setShows(false,false,false,false);
	
	$scope.setButtonShow = function(){
		return ($scope.profilShow || $scope.picaShow || $scope.jelovnikShow || $scope.konfiguracijaShow);
	}
	$scope.error = false;
	$scope.errorMessage = "";
	
	$scope.getRestaurant = function() {		
		restaurantInfoService.getRestaurant().then(
				function(response){
					$scope.restaurant = response.data;
					if(response.data.name != null){
						setShows(true, false, false, false);
					}else{
						alert("Restoran nije pronadjen!");
					}
					
				}
		
		); 
	}
	
	$scope.saveRestaurantInfo = function() {
		
		if($scope.profilShow){
			if($scope.restaurant.name == "" || $scope.restaurant.description == ""){
				$scope.error = true;
				$scope.errorMessage = "Ne moze biti prazno!";
				return;
			}else{
				$scope.error = false;
				$scope.errorMessage = "";
			}			
			restaurantInfoService.saveChanges($scope.restaurant);
			
		}else if ($scope.jelovnikShow) {
			
		}else if ($scope.piceShow){
			
		}else if ($scope.konfiguracijaShow){
			
		}else {
			alert("Error! Nothing selected.");
		}
		
	}
		
	$scope.getFoods = function(){
		
	}
	
	
	$scope.addFood = function(){
		
	}
	
	$scope.getDrinks = function(){
		
	}
	
	
	$scope.addDrink = function(){
		
	}
	
	
	$scope.getKonf = function(){
		
	}
	
	
	$scope.addKonf = function(){
		
	}
	
	
}]);








restManager.service('restaurantInfoService',['$window', '$http', function($window, $http){
	this.getRestaurant = function(){
		return $http.get("../restManager/myRestaurant/1");
	}
	
	this.saveChanges = function(restaurant){
		$http({
			  method: 'PUT',
			  data : $.param(restaurant),
		      url : "../restManager/saveRestaurantInfo/1"
		}).then(function success(response) {
					alert(response.data);
			  }, function error(response) {
				  	alert(response.data);
			  }
			);
	};
	
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






