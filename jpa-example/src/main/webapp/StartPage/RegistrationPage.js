var app = angular.module('app', []).run(['$rootScope', '$http', '$window', function ($rootScope, $http, $window) {
	if (typeof $.cookie('user') !== 'undefined') {
		user = JSON.parse($.cookie('user'));
		
		if(user.userType == "GUEST") {
			$window.location.href = "/GuestPage/GuestPage.html";
		}
		// DALJE
	}
}]);

app.controller('registrationPageController', [ '$scope', 'registrationService',  function($scope, registrationService){
	
	$scope.errorMessage = false;
	
	$scope.user = {
			"email" : "",
			"password" : "",
			"name" : "",
			"lastName" : "",
			"userType" : "GUEST"
	}
	$scope.passwordConfirm = "";
	
	$scope.register = function(){
		
		retVal = registrationService.validateRegistrationInput($scope.user, $scope.passwordConfirm)
		
		if(retVal) {
			$scope.errorMessage = retVal;
		}
		else {
			registrationService.register($scope.user).then(function(data){
				if(data != "Error free")
					$scope.errorMessage = data;
				else
					$scope.errorMessage = "";
			});
		}
	}
		 
}]);

app.service('registrationService', ['$http', '$window', '$timeout', function($http, $window, $timeout){
	
	this.validateRegistrationInput = function(user, passwordConfirm){
		if(user.email == "" || user.password == "" || user.name == "" || user.lastName == "" || passwordConfirm == "")
			return "Sva polja se moraju uneti."
		if(user.password != passwordConfirm)
			return "Lozinke se ne podudaraju.";
		
		return false;
	}
	
	this.register = function(user){
			
		 return $http({
			  method: 'POST',
			  data : user,
		      url : "../user/register"
		}).then(function success(response) {
				if(response.data == "Error free") {
					toastr.info('Aktivacioni kod je poslat na vas e-mail.')
					$timeout(function() {
						$window.location.href = '/StartPage/StartPage.html'
						}, 2000);
				}
				else
					return response.data;
			
		  }, function error(response) {
			  alert(response)
		  });
	}
}]);