angular.element(document).ready(function () {
    console.log('page loading completed');
});
var offererApp = angular.module('offererApp', []).config(['$qProvider', '$httpProvider', function ($qProvider, $httpProvider)  {
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
		}else if(user.userType == "RESTAURANTMANAGER"){
			$window.location.href = "/RestaurantManager/RestaurantManager.html";
		}
	}
		 
}]);


offererApp.controller('offererControler', [ '$scope', 'tenderService', 'acceptedService', 'accountService', function($scope, tenderService, acceptedService, accountService){

	$scope.today = new Date();
	
	setShows = function(tendersShow, acceptedShow, accountShow, sendedShow, showProfile){
		$scope.tendersShow = tendersShow;
		$scope.acceptedShow = acceptedShow;
		$scope.accountShow = accountShow;
		$scope.sendedShow = sendedShow;
		
		$scope.showProfile = showProfile;
		
		if(tendersShow){
			$scope.tenderActive = "active";
		}else{
			$scope.tenderActive = "";
		}
		
		if(sendedShow){
			$scope.poslaneActive = "active";
		}else{
			$scope.poslaneActive = "";
		}
		
		if(acceptedShow){
			$scope.prihvaceneActive = "active";
		}else{
			$scope.prihvaceneActive = "";
		}
		
	}
	setShows(false,false,false,false, false);
	
	$scope.newPassword = "";
	
	

	$scope.account = function(){
		setShows(false,false,false,false,true);
		
		
		tenderService.getOfferer().then(
				function(response){
					
					if(response.data == null){
						toastr.info("Nije pronadjen!");
						return;
					}
					
					$scope.offerer = response.data;
					$scope.newPassword = response.data.user.password;
				}
		);
		
		
		
	}
	
	
	$scope.promeniAccount = function(){
		
		if($scope.offerer.user.name == "" || $scope.offerer.user.lastName == "" || 
				$scope.offerer.user.password == "" || ($scope.offerer.user.password != $scope.newPassword)){
			
			toastr.info("Neispravan unos!");
			return;
		}
		
		console.log($scope.offerer.user);
		tenderService.saveOfferer($scope.offerer.user);
		
	}
	
	

	
	
	
	// TENDER METODE
	
	$scope.newOfferings={
			description : "",
			price : 0,
			deliveryDate: ""
	}
	
	
	
	
	$scope.tender = function(){
		setShows(true,false,false,false, false);
		
		tenderService.getActiveTenders().then(
				function(response){
					$scope.activeTenders = response.data;
				}
		);
		
	}
	
	$scope.createNewOfferings = function(tenderID){
		if($scope.newOfferings.price < 0 || $scope.newOfferings.deliveryDate == "" || $scope.newOfferings.deliveryDate < new Date()){
			toastr.info("Cena ne sme biti manja od nule i morate predefinisati datum dostavljanja u buducnosti")
			return;
		}
		
		tenderService.createNewOfferings($scope.newOfferings, tenderID);
		
	}
	
	//SENDED
	$scope.sended = function(){
		setShows(false,false,false,true,false);
		
		tenderService.getMyAllOfferings().then(
				function(response){
					$scope.myAllOfferings = response.data;
					
					angular.forEach($scope.myAllOfferings, function(value,key){
						value.deliveryDate = new Date(value.deliveryDate);
					});
					
				}
		);
		
	}
	
	
	$scope.deleteOfferings = function (offeringID){
		tenderService.deleteOfferings(offeringID);
		location.reload();
	}
	
	$scope.changeOfferings = function (offeringID){
		
		
		angular.forEach($scope.myAllOfferings, function(values, key){
			
			if(values.id == offeringID){
				
				if(values.price < 0 || values.deliveryDate == "" || values.deliveryDate < new Date()){
					toastr.info("Cena ne sme biti manja od nule i morate predefinisati datum dostavljanja u buducnosti")
					return;
				}
				
				tenderService.changeOfferings(values).then(
						function(response){
							if(response.data == "Error free"){
								toastr.success("Uspesno!");
							}else{
								toastr.info(response.data);
							}
							location.reload();
							
						}
					);
				
			}
			
		});
		
		
		
		
	}
	
	
	// ACCEPTED METODE
	$scope.accepted = function(){
		setShows(false,true,false,false,false);
	}
	
	
	$scope.logout = function(){
		accountService.logout();
	}
	
	
}]);



offererApp.service('tenderService',['$window', '$http', function($window, $http){
	
	this.saveOfferer = function(newOfferer){
		return $http({
			  method: 'POST',
			  data: newOfferer,
			  url : "../offerer/saveOfferer" 
		}).then(function success(response){
		
			toastr.success("Uspesno!");
		}, function error(response){
			
			toastr.error("Error!");
		});
	}
	
	this.getOfferer = function(){
		user = JSON.parse($.cookie('user'));
		return $http({
			  method: 'GET',
		      url : "../offerer/getOfferer/"+user.email
		});
	}
	
	
	this.getActiveTenders = function(){
		return $http({
			  method: 'GET',
		      url : "../offerer/getActiveTenders"
		});
	}
	
	this.getMyAllOfferings = function(){
		user = JSON.parse($.cookie('user'));
		return $http({
			method: 'GET',
			url: "../offerer/getMyAllOfferings/" + user.email
		});
	}
	
	this.createNewOfferings = function(newOfferings, tenderID){
		user = JSON.parse($.cookie('user'));
		$http({
			  method: 'POST',
			  data: newOfferings,
		      url : "../offerer/createNewOfferings/" + tenderID + "/"+user.email 
		}).then(function success(response) {
				if(response.data == "Error free"){
					toastr.success("Uspesno!");
				}else{
					toastr.info(response.data);
				}
				location.reload();
		  }, function error(response) {
			  	toastr.error("Error!");
		  }
		);
	}
	
	
	this.deleteOfferings = function(offeringID){
		
		$http({
			  method: 'DELETE',
		      url : "../offerer/deleteOfferings/" + offeringID
		});
	}
	
	this.changeOfferings = function(offering){
		return $http({
			  method: 'PUT',
			  data: offering,
		      url : "../offerer/changeOfferings"
		});	
		
	}

}]);

offererApp.service('acceptedService',['$window', '$http', function($window, $http){

	

}]);


offererApp.service('accountService',['$window', '$http', function($window, $http){
	
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
	

}]);












