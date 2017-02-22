angular.element(document).ready(function () {
    console.log('page loading completed');
});

var restaurant = angular.module('restaurant', []).config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] =  'application/x-www-form-urlencoded';
})

restaurant.controller('restContoller', [ '$scope', 'regService', function($scope, regService){
	
	$scope.errorMessage = false;
	
	$scope.rest = {
		name : "",	
		description : "",
		category : ""
	};
	
	$scope.registerRestaurant = function(){
		
		retVal = regService.validateRegistration($scope.rest)
		
		if(retVal != "") {
			$scope.errorMessage = retVal;
			return;
		}
		else {
			regService.registerRestaurant($scope.rest).then(function(data){
				$scope.errorMessage = "Restoran sa tim nazivom vec postoji."
				});
		}
	}
}]);

restaurant.service('regService', ['$http', '$window', function($http, $window){
	
	this.validateRegistration = function(rest){
		if(rest.name == "" || rest.description == "")
			return "Sva polja moraju biti popunjena.";
		return "";
	}
	
	this.registerRestaurant = function(rest){
		
		var promise = $http({
			  method: 'POST',
			  data : $.param(rest),
		      url : "../sysManager/registerRestaurant"
		}).then(function success(response) {
				if(response.data == "Error free")
					$window.location.href = '/SystemManager/SystemManager.html'
				else
					return response.data;
			
		  }, function error(response) {
			  alert(response)
		  });
		 
		 return promise;
	}
}]);