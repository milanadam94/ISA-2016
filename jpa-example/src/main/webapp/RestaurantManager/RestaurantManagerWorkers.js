angular.element(document).ready(function () {
    console.log('page loading completed');
});
var restManager = angular.module('restManager', []).config(['$qProvider', '$httpProvider', function ($qProvider, $httpProvider)  {
    $qProvider.errorOnUnhandledRejections(false);
    
}]).run(['$rootScope', '$http', '$window', function ($rootScope, $http, $window) {
	
	//namestiti dole u funckcijama gde treba user.email kada se ovo namesti!
	if (typeof $.cookie('user') !== 'undefined') {
		$scope.user = JSON.parse($.cookie('user'));
		
		if(user.userType == "GUEST") {
			$window.location.href = "/GuestPage/GuestPage.html";
		}
		// DALJE
	}
		 
}]);




restManager.controller('restManagerWorkersController', [ '$scope', 'registarService', 'workerService', function($scope, registarService, workerService){

	setShows = function(registrovanjeShow, periodicniShow, reonUSmeniShow, spisakRadnikaShow){
		$scope.registrovanjeShow = registrovanjeShow;
		$scope.periodicniShow = periodicniShow;
		$scope.reonUSmeniShow = reonUSmeniShow;
		$scope.spisakRadnikaShow = spisakRadnikaShow;
	}
	setShows(false,false,false,false);
	
	$scope.error = false;
	$scope.errorMessage = "";
	
	
	$scope.registracija = function(){
		setShows(true,false,false,false);
	}
	
	$scope.periodicniNivo = function(){
		setShows(false,true,false,false);
	}
	
	$scope.reonUSmeni = function(){
		setShows(false,false,true,false);
	}
	
	$scope.slisakRadnika = function(){
		setShows(false,false,false,true);
		
		workerService.getCooks().then(
				function(response){
					$scope.cooks = response.data;
				}
		);
		
		workerService.getBartenders().then(
				function(response){
					$scope.bartenders = response.data;
				}
		);
		
		workerService.getWaiters().then(
				function(response){
					$scope.waiters = response.data;
				}
		);
		
	}
	
	$scope.newWorker = {
			email : "",
			password : "",
			name : "",
			lastName : "",
			userType : ""			
	}
	
	
	
	$scope.registerWorker = function(){
		if($scope.newWorker.email == "" || 
				$scope.newWorker.pasword == "" || 
				$scope.newWorker.name == "" || 
				$scope.newWorker.lastName == "" || 
				$scope.newWorker.userType == "" ||
				$scope.newWorker.password != $scope.passwordConfirm){
			
			$scope.error = true;
			$scope.errorMessage = "Neispravan unos!";			
			return;
		}
		
		registarService.registarWorker($scope.newWorker);
	}
	
	
	
	
	
	

	
}]);





restManager.service('registarService',['$window', '$http', function($window, $http){

	this.registarWorker = function(newWorker){
		
		$http({
			  method: 'POST',
			  data : newWorker,
		      url : "../restManager/registarWorker/1" //========================================== dodati user email
		}).then(function success(response){	
				if(response.data == "Error free"){
					alert("Uspesno registrovan radnik");	
					location.reload();
				}else{
					alert(response.data);
				}
				
			},
				function error(response) {
				  	alert("Error!");
			  }
		);
		
	}
	

}]);

restManager.service('workerService',['$window', '$http', function($window, $http){

	this.getCooks = function (){
		
		return $http({
			  method: 'GET',
		      url : "../restManager/getCooks/1" //========================================== dodati user email
		})
		
	}
	
	this.getBartenders = function(){
		return $http({
			  method: 'GET',
		      url : "../restManager/getBartenders/1" //========================================== dodati user email
		})
		
	}
	
	this.getWaiters = function(){
		return $http({
			  method: 'GET',
		      url : "../restManager/getWaiters/1" //========================================== dodati user email
		})
		
	}
	
	

}]);








