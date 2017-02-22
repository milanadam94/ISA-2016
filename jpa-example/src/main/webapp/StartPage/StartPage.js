angular.element(document).ready(function () {
    console.log('page loading completed');
});

var app = angular.module('app', []).config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] =  'application/x-www-form-urlencoded';
}).run(['$rootScope', '$http', '$window', function ($rootScope, $http, $window) {
	
	if (typeof $.cookie('user') !== "undefined") {
		//$window.location.href = "/StartPage/StartPage.html"
		
		// SALJEM TE NA TVOJ PAGE AKO SI VEC LOGOVAN
	}
		 
	
	
}]);
				
						 
app.controller('startPageController', [ '$scope', 'loginService', 'registrationService',  function($scope, loginService, registrationService){
	
	$scope.errorMessage = false;
	
	$scope.user = {
			email : "",
			password : ""
	}

	$scope.login = function(){
		
		var retVal = loginService.validateLoginInput($scope.user);
		
		if(retVal != "") {
			$scope.errorMessage = retVal;
			return;
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
		
		return "";
	}
	
	 this.login = function(user) {
		 var promise = $http({
				  method: 'POST',
				  data : $.param(user),
			      url : "../user/login"
			}).then(function success(response) {
				if(response.data.email == "") {
					return "";
				}
				else {
					//$window.location.href = '/GuestPage/GuestPage.html';
					$.cookie('user', user)
					alert("Uspesno logovanje")
				}
				
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
