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




restManager.controller('restManagerOfferersController', [ '$scope', 'registarService','tenderService', function($scope, registarService, tenderService){

	setShows = function(registrovanjeShow, objavljivanjeShow, pregledShow){
		$scope.registrovanjeShow = registrovanjeShow;
		$scope.objavljivanjeShow = objavljivanjeShow;
		$scope.pregledShow = pregledShow;
	}
	setShows(false,false,false);
	
	$scope.error = false;
	$scope.errorMessage = "";
	
	
	$scope.registracija = function(){
		setShows(true,false,false);
	}
	
	$scope.objavljivanje = function(){
		setShows(false,true,false);
	}
	
	$scope.pregled = function(){
		setShows(false,false,true);
	}
	
	
	$scope.newOfferer= {
			email : "",
			password : "",
			name : "",
			lastName : "",
			userType : "OFFERER"
	}
	$scope.passwordConfirm = "";
	
	$scope.newTender={
			description : "",
			startDate : "",
			endDate : ""
	}
	
	
	$scope.registerOfferer = function(){
		
		if($scope.newOfferer.email == "" || 
				$scope.newOfferer.pasword == "" || 
				$scope.newOfferer.name == "" || 
				$scope.newOfferer.lastName == "" || 
				$scope.newOfferer.userType != "OFFERER" ||
				$scope.newOfferer.password != $scope.passwordConfirm){
			
			$scope.error = true;
			$scope.errorMessage = "Neispravan unos!";			
			return;
		}
		
		registarService.registarOfferer($scope.newOfferer);
			
	}
	
	$scope.ObjaviTender = function(){
		
		if($scope.newTender.description == "" || $scope.newTender.startDate == "" || $scope.newTender.endDate == ""){
			alert("Ne sme biti prazno!");
			return;
		}
		
		if($scope.newTender.startDate > $scope.newTender.endDate){
			alert("Datum pocetka mora biti pre datuma zavrsetka.");
			return;
		}
		
		tenderService.createTender($scope.newTender);
		
	}
	
	
}]);



restManager.service('registarService',['$window', '$http', function($window, $http){

	this.registarOfferer = function(newOfferer){
		
		$http({
			  method: 'POST',
			  data : newOfferer,
		      url : "../restManager/registarOfferer"
		}).then(function success(response){	
				if(response.data == "Error free"){
					alert("Uspesno registrovan ponudjac");	
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


restManager.service('tenderService',['$window','$http', function($window, $http){
	

	this.createTender = function(newTender){
		
		$http({
			  method: 'POST',
			  data : newTender,
		      url : "../restManager/createTender/1" //========================================== staviti user email
		}).then(function success(response){	
				if(response.data == "Error free"){
					alert("Uspesno kreiran tender");	
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







