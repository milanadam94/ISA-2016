angular.element(document).ready(function () {
    console.log('page loading completed');
});

var app = angular.module('app', []);
						 
app.controller('startPageController', [ '$scope', 'loginService', 'registrationService',  function($scope, loginService, registrationService){
	
	$scope.user = {
			email : "",
			password : ""
	}

	$scope.login = function(){
		loginService.login($scope.user);
	}
	
	$scope.register = function(){
		registrationService.register();
	}
		 
}]);

app.service('loginService', [function(){
	
	 this.login = function(user) {
		 $.ajax({
				type : "POST",
				data : user,
				url : "../user/login",
				success : function(data) {
					alert(data);
				},
				error : function(data) {
					alert(data);
				}
			});
	   }
	 
}]);

app.service('registrationService', ['$window', function($window){
	
	this.register = function(){
		$window.location.href = '/StartPage/RegistrationPage.html'
	}
	 
}]);
