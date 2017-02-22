var app = angular.module('app', []).config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] =  'application/x-www-form-urlencoded';
}).run(['$rootScope', '$http', '$window', function ($rootScope, $http, $window) {
	if (typeof $.cookie('user') !== "undefined") {
		//$window.location.href = "/StartPage/StartPage.html"
		
		// AKO SI ULOGOVAN NE MOZES VRSITI REGISTRACIJU, REDIREKCIJA NA TVOJ PAGE
	}
}]);

app.controller('registrationPageController', [ '$scope', 'registrationService',  function($scope, registrationService){
	
	$scope.errorMessage = false;
	
	$scope.user = {
			email : "",
			password : "",
			name : "",
			lastName : "",
			userType : "GUEST"
	}
	$scope.passwordConfirm = "";
	
	$scope.register = function(){
		
		retVal = registrationService.validateRegistrationInput($scope.user, $scope.passwordConfirm)
		
		if(retVal != "") {
			$scope.errorMessage = retVal;
			return;
		}
		else {
			registrationService.register($scope.user).then(function(data){
				if(data == "Email error") {
					$scope.errorMessage = "E-mail nije u validnom formatu."
				}
				else if(data == "Password error") {
					$scope.errorMessage = "Lozinka nije u validnom formatu ili nije dovoljno dugacka (minimum 7 karaktera). Dozvoljeni su samo slova i brojevi."
				}
				else if(data == "Name error") {
					$scope.errorMessage = "Ime nije u validnom formatu. Mora poceti velikim slovom i sadrzati iskljucivo slova."
				}
				else if(data == "LastName error") {
					$scope.errorMessage = "Prezime nije u validnom formatu. Mora poceti velikim slovom i sadrzati iskljucivo slova."
				}
				else if(data == "Registration error"){
					
					$scope.errorMessage = "Korisnik sa navedenim e-mail-om vec postoji."
				}
					
				
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
		
		return "";
	}
	
	this.register = function(user){
			
		 var promise = $http({
			  method: 'POST',
			  data : $.param(user),
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
		 
		 return promise;
	}
}]);