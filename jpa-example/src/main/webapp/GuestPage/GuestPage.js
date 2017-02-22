angular.element(document).ready(function () {
    console.log('page loading completed');
});

var app = angular.module('app', []).config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] =  'application/x-www-form-urlencoded';
}).run(['$rootScope', '$http', '$window', function ($rootScope, $http, $window) {
	
	if(typeof $.cookie('user') === 'undefined') {
			$window.location.href = "/StartPage/StartPage.html";
	}
	else
	{
		user = JSON.parse($.cookie('user'));
		if(user.userType == "SYSTEMMANAGER")
			alert("dalje")
	}
		 
	
	
}]);
				
						 
app.controller('guestPageController', [ '$scope', 'logoutService', 'restaurantService',  function($scope, logoutService, restaurantService){
	
	$scope.logout = function() {
		logoutService.logout();
	}

	$scope.restaurants = function(){
		
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

app.service('restaurantService', ['$http', '$window', function($http, $window){
	
	this.validateLoginInput = function(user){
		if(user.email == "" || user.password == "")
			return "Potrebno je uneti e-mail i lozinku."
		
		return "";
	}
	
	 this.login = function(user) {
		 return $http({
				  method: 'POST',
				  data : $.param(user),
			      url : "../user/login"
			}).then(function success(response) {
				if(response.data.email == null || response.data.email == "") {
					return "";
				}
				else {
					$.cookie('user', user);
					console.log(response.data)
					
					if(response.data.userType == "GUEST")
						$window.location.href = '/GuestPage/GuestPage.html';
				}
				
			  }, function error(response) {
				  alert(response)
			  });
	 }
			 
	 
}]);

app.service('logoutService', ['$window', '$http', function($window, $http){
	
	this.logout = function(){
		user = JSON.parse($.cookie("user"));
		$.cookie("user", undefined, { path: '/', domain: '' });
		$http({
			  method: 'PUT',
			  data : $.param(user),
		      url : "../user/logout"
		}).then(function success(response) {
			
		  }, function error(response) {
			  alert(response)
		  });
		
		$window.location.href = '/StartPage/StartPage.html';
	}
	 
}]);
