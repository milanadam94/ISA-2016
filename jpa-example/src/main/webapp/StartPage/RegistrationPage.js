var app = angular.module('app', []);

app.controller('registrationPageController', [ '$scope', 'registrationService',  function($scope, registrationService){
	
	$scope.user = {
			email : "",
			password : "",
			name : "",
			lastName : ""
	}

	$scope.register = function(){
		registrationService.register($scope.user);
	}
		 
}]);

app.service('registrationService', ['$window', function($window){
	
	this.register = function(user){
			 $.ajax({
					type : "POST",
					data : user,
					url : "../user/register",
					success : function(data) {
						//$window.location.href = '/StartPage/RegistrationPage.html'
						alert(data)
					},
					error : function(data) {
						alert(data);
					}
				});
		   }
		
}]);