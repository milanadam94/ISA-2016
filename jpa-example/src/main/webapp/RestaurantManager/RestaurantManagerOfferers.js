angular.element(document).ready(function () {
    console.log('page loading completed');
});
var restManager = angular.module('restManager', []).config(['$qProvider', '$httpProvider', function ($qProvider, $httpProvider)  {
    $qProvider.errorOnUnhandledRejections(false);
    
}]).run(['$rootScope', '$http', '$window', function ($rootScope, $http, $window) {
	
	//namestiti dole u funckcijama gde treba user.email kada se ovo namesti!
	if (typeof $.cookie('user') !== 'undefined') {
		user = JSON.parse($.cookie('user'));
		
		if(user.userType == "GUEST") {
			$window.location.href = "/GuestPage/GuestPage.html";
		}else if(user.userType == "WAITER"){
			$window.location.href = "/WorkerPage/WaiterPage.html";
		}else if(user.userType == "BARTENDER"){
			$window.location.href = "/WorkerPage/BartenderPage.html";
		}else if(user.userType == "COOK"){
			$window.location.href = "/WorkerPage/CookPage.html";
		}else if(user.userType == "OFFERER"){
			$window.location.href = "/Offerer/Offerer.html";
		}
	}
		 
}]);




restManager.controller('restManagerOfferersController', [ '$scope', 'registarService','tenderService', function($scope, registarService, tenderService){

	setShows = function(registrovanjeShow, objavljivanjeShow, pregledShow){
		$scope.registrovanjeShow = registrovanjeShow;
		$scope.objavljivanjeShow = objavljivanjeShow;
		$scope.pregledShow = pregledShow;
		
		if(registrovanjeShow){
			$scope.registracijaActive = "active";
		}else{
			$scope.registracijaActive = "";
		}
		
		if(objavljivanjeShow){
			$scope.objavljivanjeActive = "active";
		}else{
			$scope.objavljivanjeActive = "";
		}
		
		if(pregledShow){
			$scope.pregledActive = "active";
		}else{
			$scope.pregledActive = "";
		}
		
		$scope.tenderiPregled = false;
		$scope.offeringsPregled = false;
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
		$scope.tenderiPregled = true;
		$scope.offeringsPregled = false;
		tenderService.getAllMyTenders().then(
		
				function(response){
					$scope.myAllActiveTenders = response.data;
				}
				
		);
		
		
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
			toastr.info("Ne sme biti prazno!");
			return;
		}
		
		if($scope.newTender.startDate > $scope.newTender.endDate){
			toastr.info("Datum pocetka mora biti pre datuma zavrsetka.");
			return;
		}
		
		tenderService.createTender($scope.newTender);
		
	}
	
	
	// PREGLED
	
	$scope.showOfferings = function(tenderID){
		
		$scope.tenderiPregled = false;
		$scope.offeringsPregled = true;
		
		tenderService.getOfferingsForTender(tenderID).then(
				function(response){
					$scope.selectedOfferings = response.data;
				}
		);
	}
	
	
	$scope.chooseOffering = function(offeringsID){
		tenderService.chooseOffering(offeringsID).then(
				function(response){
					if(resposne.data == "Error free"){
						toastr.success("Uspesno!");
					}else{
						toastr.info(response.data);
					}
					
					location.reload();
				}
		);
	}
	
	
	$scope.logout = function(){
		registarService.logout();
	}
	
}]);



restManager.service('registarService',['$window', '$http', function($window, $http){

	this.logout = function() {
		user = $.cookie("user");
		$http({
			method : 'PUT',
			data : user,
			url : "../user/logout"
		}).then(function success(response) {

		}, function error(response) {
			
		});
		$.removeCookie('user', {
			path : '/',
			domain : ''
		});
		$window.location.href = '/StartPage/StartPage.html';
	}
	
	
	this.registarOfferer = function(newOfferer){
		
		$http({
			  method: 'POST',
			  data : newOfferer,
		      url : "../restManager/registarOfferer"
		}).then(function success(response){	
				if(response.data == "Error free"){
					toastr.success("Uspesno registrovan ponudjac");	
					location.reload();
				}else{
					toastr.info(response.data);
				}
				
			},
				function error(response) {
					toastr.error("Error!");
			  }
		);
		
	}
	

}]);


restManager.service('tenderService',['$window','$http', function($window, $http){
	

	this.createTender = function(newTender){
		user = JSON.parse($.cookie('user'));
		$http({
			  method: 'POST',
			  data : newTender,
		      url : "../restManager/createTender/"+user.email
		}).then(function success(response){	
				if(response.data == "Error free"){
					toastr.success("Uspesno kreiran tender");	
					location.reload();
				}else{
					toastr.info(response.data);
				}
				
			},
				function error(response) {
					toastr.error("Error!");
			  }
		);		
		
	}
	
	
	this.getAllMyTenders = function(){
		user = JSON.parse($.cookie('user'));
		
		return $http({
			  method: 'GET',
		      url : "../restManager/getAllMyTenders/"+user.email
		});
	}
	
	
	this.getOfferingsForTender = function (tenderID){
		
		return $http({
			  method: 'GET',
		      url : "../restManager/getOfferingsForTender/" + tenderID
		});
	}
	
	this.chooseOffering = function (offeringID){
		return $http({
			  method: 'POST',
		      url : "../restManager/chooseOffering/" + offeringID
		});
		
	}
	
}]);







