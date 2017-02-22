angular.element(document).ready(function () {
    console.log('page loading completed');
});

var app = angular.module('restManager', []).config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] =  'application/x-www-form-urlencoded';
})

app.controller('restManagerContoller', [ '$scope', 'registrationService',  function($scope, registrationService){
	
	$scope.errorMessage = false;
	
	$scope.restManager = {
			email : "",
			password : "",
			name : "",
			lastName : ""
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
				$scope.errorMessage = "Menadzer restorana sa tim email-om vec postoji."
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
				if(response.data == "Error free")
					$window.location.href = '/SystemManager/SystemManager.html'
				else
					return response.data;
			
		  }, function error(response) {
			  alert(response)
		  });
		 
		 return promise;
	}
}]);