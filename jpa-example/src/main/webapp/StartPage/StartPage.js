angular.element(document).ready(function () {
    console.log('page loading completed');
});

var app = angular.module('app', []).config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] =  'application/x-www-form-urlencoded';
})
						 
app.controller('startPageController', [ '$scope', 'loginService', 'registrationService',  function($scope, loginService, registrationService){
	
	$scope.user = {
			email : "",
			password : ""
	}

	$scope.login = function(){
		loginService.login($scope.user).then(function(data){
			alert("vraceno")
	    });
	}
	
	$scope.register = function(){
		registrationService.register();
	}
		 
}]);

app.service('loginService', ['$http', '$window', function($http, $window){
	
	 this.login = function(user) {
		 var promise = $http({
				  method: 'POST',
				  data : $.param(user),
			      url : "../user/login"
			}).then(function success(response) {
				alert(response.data)
				if(response.data == "Error free")
					//$window.location.href = '/GuestPage/GuestPage.html';
					alert("Uspesno logovanje")
				else
					return response.data;
				
			  }, function error(response) {
				  alert(response)
			  });
		 return promise;
	 }
			 
	 
}]);

app.service('registrationService', ['$window', function($window){
	
	this.register = function(){
		$window.location.href = '/StartPage/RegistrationPage.html'
	}
	 
}]);
