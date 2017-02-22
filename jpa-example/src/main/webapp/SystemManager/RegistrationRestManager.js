angular.element(document).ready(function () {
    console.log('page loading completed');
});

var app = angular.module('restManager', []).config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] =  'application/x-www-form-urlencoded';
}).run(['$rootScope', '$http', '$window', function ($rootScope, $http, $window) {
	if (typeof $.cookie('user') !== "undefined") {
		//$window.location.href = "/StartPage/StartPage.html"
		
		// AKO SI ULOGOVAN NE MOZES VRSITI REGISTRACIJU, REDIREKCIJA NA TVOJ PAGE
	}
}]);

app.controller('restManagerContoller', [ '$scope', 'registrationService',  function($scope, registrationService){
	
	$scope.errorMessage = false;
	
	$scope.restManager = {
			email : "",
			password : "",
			name : "",
			lastName : "",
			userType : "RESTAURANTMANAGER"
	}
	$scope.passwordConfirm = "";
	
	$scope.registerManager = function(){
		
		retVal = registrationService.validateRegistration($scope.restManager, $scope.passwordConfirm)
		
		if(retVal != "") {
			$scope.errorMessage = retVal;
			return;
		}
		else {
			registrationService.registerManager($scope.restManager).then(function(data){
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

app.service('registrationService', ['$http', '$window', function($http, $window){
	
	this.validateRegistration = function(restManager, passwordConfirm){
		if(restManager.email == "" || restManager.password == "" || restManager.name == "" || restManager.lastName == "")
			return "Sva polja moraju biti popunjena."
		if(restManager.password != passwordConfirm)
			return "Lozinke se ne poklapaju.";
		
		return "";
	}
	
	this.registerManager = function(restManager){
			
		 var promise = $http({
			  method: 'POST',
			  data : $.param(restManager),
		      url : "../sysManager/registerRestManager"
		}).then(function success(response) {
				if(response.data == "Error free"){
					toastr.info('Aktivacioni kod je poslat na vas e-mail.')
					$timeout(function() {
						$window.location.href = '/SystemManager/SystemManager.html'
						}, 2000);
				}else
					return response.data;
			
		  }, function error(response) {
			  alert(response)
		  });
		 
		 return promise;
	}
}]);