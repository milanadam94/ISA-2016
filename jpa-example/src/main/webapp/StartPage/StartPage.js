angular.element(document).ready(function () {
    console.log('page loading completed');
});

var app = angular.module('app', []).run(['$rootScope', '$http', '$window', function ($rootScope, $http, $window) {
	
	if (typeof $.cookie('user') !== 'undefined') {
		user = JSON.parse($.cookie('user'));
		
		if(user.userType == "GUEST") {
			$window.location.href = "/GuestPage/GuestPage.html";
		}
		// DALJE
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
					
					if(response.data.userType == "GUEST")
						$window.location.href = '/GuestPage/GuestPage.html';
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
