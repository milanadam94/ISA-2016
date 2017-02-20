var app = angular.module('app', []).config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] =  'application/x-www-form-urlencoded';
})

app.controller('registrationPageController', [ '$scope', 'registrationService',  function($scope, registrationService){
	
	$scope.errorMessage = false;
	
	$scope.user = {
			email : "",
			password : "",
			name : "",
			lastName : ""
	}
	$scope.passwordConfirm = "";
	
	$scope.register = function(){
		
		retVal = registrationService.validateRegistration($scope.user, $scope.passwordConfirm)
		
		if(retVal != "") {
			$scope.errorMessage = retVal;
			return;
		}
		else {
			registrationService.register($scope.user).then(function(data){
				$scope.errorMessage = "User with that e-mail is already registered"
				});
		}
	}
		 
}]);

app.service('registrationService', ['$http', '$window', function($http, $window){
	
	this.validateRegistration = function(user, passwordConfirm){
		if(user.email == "" || user.password == "" || user.name == "" || user.lastName == "")
			return "All fields are required."
		if(user.password != passwordConfirm)
			return "Password mismatch.";
		
		return "";
	}
	
	this.register = function(user){
			
		 var promise = $http({
			  method: 'POST',
			  data : $.param(user),
		      url : "../user/register"
		}).then(function success(response) {
				if(response.data == "Error free")
					$window.location.href = '/StartPage/StartPage.html'
				else
					return response.data;
			
		  }, function error(response) {
			  alert(response)
		  });
		 
		 return promise;
	}
}]);