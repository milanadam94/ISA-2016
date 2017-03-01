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

	setShows = function(registrovanjeShow, periodicniShow, spisakRadnikaShow, pregledRaspored){
		$scope.registrovanjeShow = registrovanjeShow;
		$scope.periodicniShow = periodicniShow;
		$scope.spisakRadnikaShow = spisakRadnikaShow;
		$scope.pregledRaspored = pregledRaspored;
		
		if(registrovanjeShow){
			$scope.registracijaActive = "active";
		}else{
			$scope.registracijaActive = "";
		}
		
		if(spisakRadnikaShow){
			$scope.spisakActive = "active";
		}else{
			$scope.spisakActive = "";
		}
		
	}
	setShows(false,false,false,false);
	
	$scope.error = false;
	$scope.errorMessage = "";
	
	$scope.cookType = "";
	$scope.today = new Date();
	
	
	$scope.registracija = function(){
		setShows(true,false,false,false);
	}
	
	$scope.periodicniNivo = function(){
		setShows(false,true,false,false);
	}

	
	$scope.slisakRadnika = function(){
		setShows(false,false,true,false);
		
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
				$scope.newWorker.password != $scope.passwordConfirm ||
				($scope.newWorker.userType == "COOK" && $scope.cookType == "")){
			
			$scope.error = true;
			$scope.errorMessage = "Neispravan unos!";			
			return;
		}
		$scope.error = false;
		registarService.registarWorker($scope.newWorker,$scope.cookType);
	}
	
	
	// PREGLED RADA
	$scope.checkSchedule = function(workerID){
		$scope.pregledRaspored = true;
		$scope.spisakRadnikaShow = false;
		$scope.workerID = workerID;
			
		workerService.getSchedules(workerID).then(
				
			function(response){
				$scope.myWorkerSchedule = response.data;
			}
		
		);
		
		workerService.loadAllSegments().then(
				function(response){
					$scope.segments = response.data;
				}
				
		);
		
		registarService.getUser(workerID).then(
				
				function(response){
					$scope.fullWorker = response.data;
					
					if($scope.fullWorker.id == ""){
						return;
					}
					
					workerService.loadAllMySegments($scope.fullWorker.id).then(
							function(response){
								
								$scope.allMySegments = response.data;
								
							}
							
					);
					
				}
				
		);
		
	
		
	}
	
	$scope.newSchedule = {
			startDate: "",
			endDate: ""
	}
	
	$scope.shiftType = ""
	$scope.newSegment = "";
	
	$scope.addSchedule = function(){
		
		if($scope.newSchedule.startDate < new Date() || $scope.newSchedule.startDate == "" ||
				$scope.newSchedule.endDate < new Date() || $scope.newSchedule.endDate == "" ||
				$scope.newSchedule.startDate > $scope.newSchedule.endDate ||
				$scope.shiftType == "" || $scope.workerID == ""){
			toastr.info("Los unos!");
			return;
		}
		
		if($scope.newSegment == ""){
			$scope.newSegment = 0;
		}
		
		$scope.newSchedule.startDate = new Date($scope.newSchedule.startDate);
		$scope.newSchedule.endDate = new Date($scope.newSchedule.endDate);
		
		
		
		workerService.addSchedule($scope.newSchedule, $scope.newSegment,$scope.workerID, $scope.shiftType).then(
				
				function(response){
					if(response.data == "Error free"){
						toastr.success("Uspesno!");
						$scope.newSchedule.startDate = "";
						$scope.newSchedule.endDate = "";
						$scope.newSegment = "";
						$scope.shiftType = "";
						
						location.reload();
					}else{
						toastr.info(response.data);
					}
					
					
					
				}
				
		);
		
	}
	
	

	
}]);





restManager.service('registarService',['$window', '$http', function($window, $http){

	this.registarWorker = function(newWorker, cookType){
		
		$http({
			  method: 'POST',
			  data : newWorker,
		      url : "../restManager/registarWorker/1/" + cookType //========================================== dodati user email
		}).then(function success(response){	
				if(response.data == "Error free"){
					toastr.success("Uspesno registrovan radnik");	
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
	
	this.getUser = function (userID){
		return $http({
			  method: 'GET',
		      url : "../restManager/getUser/" + userID
		})
		
	}
	

}]);

restManager.service('workerService',['$window', '$http', function($window, $http){

	this.getCooks = function (){
		
		return $http({
			  method: 'GET',
		      url : "../restManager/getCooks/1" //========================================== dodati user email
		});
		
	}
	
	this.getBartenders = function(){
		return $http({
			  method: 'GET',
		      url : "../restManager/getBartenders/1" //========================================== dodati user email
		});
		
	}
	
	this.getWaiters = function(){
		return $http({
			  method: 'GET',
		      url : "../restManager/getWaiters/1" //========================================== dodati user email
		});
		
	}
	
	
	this.getSchedules = function(workerID){
		
		return $http({
			  method: 'GET',
		      url : "../restManager/getSchedules/" + workerID
		});
		
	}
	
	
	this.loadAllSegments = function(){
		return $http({
			  method: 'GET',
		      url : "../restManager/loadAllSegments/1" //========================================== dodati user Email
		});
		
	}

	
	this.addSchedule = function(newSchedule, scheduleSegment, workerID, shiftType){
		console.log(newSchedule)
		return $http({
			  method: 'POST',
			  data: newSchedule,
		      url : "../restManager/addSchedule/"+shiftType+"/"+ workerID + "/" + scheduleSegment
		});
	}
	
	
	this.loadAllMySegments = function(workerID){
		return $http({
			  method: 'GET',
		      url : "../restManager/loadAllMySegments/" + workerID
		});		
	}
	
}]);








