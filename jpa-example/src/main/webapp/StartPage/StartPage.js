angular.element(document).ready(function () {
    console.log('page loading completed');
});

var app = angular.module('app', []).run(['$rootScope', '$http', '$window', function ($rootScope, $http, $window) {
	
	if (typeof $.cookie('user') !== 'undefined') {
		user = JSON.parse($.cookie('user'));
		
		if(user.userType == "GUEST") {
			$window.location.href = "/GuestPage/GuestPage.html";
		}else if(user.userType == "WAITER"){
			$window.location.href = "/WorkerPage/WaiterPage.html";
		}else if(user.userType == "BARTENDER"){
			$window.location.href = "/WorkerPage/BartenderPage.html";
		}else if(user.userType == "COOK"){
			$window.location.href = "/WorkerPage/CookPage.html";
		}else if(user.userType == "OFFERER"){
			$window.location.href = "/Offerer/Offerer.html";
		}else if(user.userType == "RESTAURANTMANAGER"){
			$window.location.href = "/RestaurantManager/RestaurantManager.html";
		}
	}
	
	
}]);
				
						 
app.controller('startPageController', [ '$scope', 'loginService', 'registrationService',  function($scope, loginService, registrationService){
	
	$scope.errorMessage = false;
	
	$scope.user = {
			"email" : "",
			"password" : ""
	}

	$scope.login = function(){
		
		var retVal = loginService.validateLoginInput($scope.user);
		
		if(retVal) {
			$scope.errorMessage = retVal;
		}
		else {
			loginService.login($scope.user).then(function(data){
				if(data == "")
					$scope.errorMessage = "Korisnik sa unetim podacima ne postoji.";
		    });
		}
	}
	
	$scope.register = function(){
		registrationService.register();
	}
		 
}]);

app.service('loginService', ['$http', '$window', function($http, $window){
	
	this.validateLoginInput = function(user){
		if(user.email == "" || user.password == "")
			return "Potrebno je uneti e-mail i lozinku."
		
		return false;
	}
	
	 this.login = function(user) {
		 return $http({
				  method: 'POST',
				  data : user,
			      url : "../user/login"
			}).then(function success(response) {
				if(response.data.email == null || response.data.email == "") {
					return "";
				}
				else {
					$.cookie.json = true;
					$.cookie("user", response.data, {path    : '/', domain  : ''});
					
					if(response.data.userType == "GUEST"){
						$window.location.href = '/GuestPage/GuestPage.html';
					}else if(response.data.userType == "WAITER"){
						$window.location.href = "/WorkerPage/WaiterPage.html";
					}else if(response.data.userType == "BARTENDER"){
						$window.location.href = "/WorkerPage/BartenderPage.html";
					}else if(response.data.userType == "COOK"){
						$window.location.href = "/WorkerPage/CookPage.html";
					}else if(response.data.userType == "OFFERER"){
						$window.location.href = "/Offerer/Offerer.html";
					}else if(response.data.userType == "RESTAURANTMANAGER"){
						$window.location.href = "/RestaurantManager/RestaurantManager.html";
					}
				}
				
			  }, function error(response) {
				  alert(response)
			  });
	 }
			 
	 
}]);

app.service('registrationService', ['$window', function($window){
	
	this.register = function(){
		$window.location.href = '/StartPage/RegistrationPage.html'
	}
	 
}]);
