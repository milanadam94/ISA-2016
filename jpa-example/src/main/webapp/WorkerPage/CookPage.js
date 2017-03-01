var cook = angular.module('cook', []).run(['$rootScope', '$http', '$window', function ($rootScope, $http, $window) {
    
	if (typeof $.cookie('user') === 'undefined') {
		
		$window.location.href = "/StartPage/StartPage.html";
	}
	else
	{
		user = JSON.parse($.cookie('user'));
	
	}
	
}]);

cook.controller('cookController', [ '$scope', 'cookService', function($scope, cookService){
	 
	$scope.viewProfile = true;
	$scope.firstLogin = false;
	$scope.viewOrders = false;
	$scope.calendar = false;
	
	cookService.getCook().then(
			function(response){
				$scope.cook = response.data;
				$scope.editCook = angular.copy($scope.cook);
				console.log($scope.cook);
			}
		
	)
	
	$scope.getProfile = function(){
		$scope.viewProfile = true;
		$scope.firstLogin = false; 
		$scope.viewOrders = false;
		$scope.calendar = false;
		cookService.getCook().then(
				function(response){
					$scope.cook = response.data;
					$scope.editCook = angular.copy($scope.cook);
					console.log($scope.cook);
				}
			
		)
	}
	
	cookService.getFirstLogin().then(
		function (response){
			$scope.firstLogin = response.data;
			console.log($scope.firstLogin);
			if($scope.firstLogin){
				$scope.firstLogin = false;
				$scope.viewProfile = true;
				$scope.viewOrders = false;
				$scope.calendar = false;
			}else{
				$scope.firstLogin = true;
				$scope.viewProfile = false;
				$scope.viewOrders = false;
				$scope.calendar = false;
			}
	
		}
	)
	
	$scope.novaLozinka = function(){
		var returnValue = cookService.validatePasswords($scope.cook, $scope.oldFirstPassword,$scope.newFirstPassword,$scope.confirmFirstPassword);
		
		if(returnValue){
			$scope.greska = returnValue;
		}else{
			$scope.greska = "";
			$scope.firstLogin = false;
			$scope.viewProfile = true;
			$scope.viewOrders = false;
			$scope.calendar = false;
			$scope.cook.user.password = $scope.newFirstPassword;
			cookService.saveFirstLogin($scope.cook).then(function(data){
				if(data != "") {
					$scope.errorMessage = data;
				}else{
					//$scope.$parent.cook = $scope.$parent.editCook;
					//$.cookie.json = true;
					//$.cookie("user", $scope.$parent.newGuest.user, {path    : '/', domain  : ''});
					//angular.element('#profileModal').modal('hide');
				}
		    });
		}
	}
	
	$scope.getOrders = function() {
		$scope.viewProfile = false;
		$scope.firstLogin = false;
		$scope.viewOrders = true;
		$scope.calendar = false;
		
		cookService.getFoodOrders().then(
				function(response){
					$scope.foodOrders = response.data;
					console.log($scope.foodOrders);
				}
		)
	}
	
	$scope.startPreparing = function(foodOrder){
		cookService.startPrepareFood(foodOrder);
	}
	$scope.preparingDone = function(foodOrder){
		cookService.prepareDone(foodOrder);
	}
	$scope.logout = function(){
		cookService.logout();
	}
	
	$scope.konacnaLista = [];
	
	$scope.getCalendar = function(){
		$scope.viewProfile = false;
		//$scope.makeOrder = false;
		$scope.viewOrders = false;
		$scope.firstLogin = false;
		$scope.calendar = true;
		
		
		
		cookService.getCooks().then(
				function(response){
					$scope.konacnaLista = [];
					$scope.cooks = response.data;
					
					$scope.cooks.forEach(function(cook){
						
						cookService.loadSegments(cook.id).then(
								function(response){
									$scope.segments = response.data;
									
									//alert(response.data.startDate)
									response.data.startDate = new Date(response.data.startDate);
									response.data.endDate = new Date(response.data.endDate);
									//alert(response.data.startDate)
									pom = {
										cook: cook,
										schedule: response.data
									}
									
									$scope.konacnaLista.push(pom);
								}
								
						);
						
					});
					console.log($scope.konacnaLista);
				}
		);	
	}
}]);

cook.controller('profileController', [ '$scope', 'cookService', function($scope, cookService){
	$scope.errorMessage = false;
	
	$scope.oldPassword = "";
	$scope.newPassword = "";
	$scope.confirmPassword = "";
	
	$scope.resetNewWaiter = function() {
		$scope.$parent.editCook.user.email = $scope.$parent.cook.user.email;
		$scope.$parent.editCook.user.name = $scope.$parent.cook.user.name;
		$scope.$parent.editCook.user.lastName = $scope.$parent.cook.user.lastName;
		$scope.$parent.editCook.suitSize = $scope.$parent.cook.suitSize;
		$scope.$parent.editCook.shoeSize = $scope.$parent.cook.shoeSize;
		$scope.$parent.editCook.birthday = $scope.$parent.cook.birthday;
		$scope.oldPassword = "";
		$scope.newPassword = "";
		$scope.confirmPassword = "";
		$scope.errorMessage = false;
	}
	
	$scope.editProfile = function() {
		var retVal = cookService.validatePasswords($scope.$parent.editCook, $scope.oldPassword, $scope.newPassword, $scope.confirmPassword);
		if(retVal){
			$scope.errorMessage = retVal;
		}else{
			$scope.errorMessage = "";
			$scope.$parent.editCook.user.password = $scope.newPassword;
			cookService.editProfile($scope.$parent.editCook).then(function(data){
				if(data != "") {
					$scope.errorMessage = data;
				}
				else
				{
					$scope.$parent.cook = $scope.$parent.editCook;
					//$.cookie.json = true;
					//$.cookie("user", $scope.$parent.newGuest.user, {path    : '/', domain  : ''});
					angular.element('#profileModal').modal('hide');
				}
		    });
			
		}
	}
	
}]);

cook.service('cookService', ['$window', '$http', function($window, $http){
	
	this.getCook = function() {
		return $http.get("../worker/cook/"+user.id)
	}
	
	this.validatePasswords = function(cook, password, newPassword, newPasswordConfirm){
		
		//if(password == ""){
		//	return "Morate uneti vasu lozinku.";
		//}
		if(password != cook.user.password) {
			return "Lozinka nije ispravna.";
		}
		
		if(newPassword != newPasswordConfirm) {
			return "Unete lozinke se ne podudaraju.";
		}
		
		if(password == newPassword) {
			return "Uneli ste lozinku koju vec posedujete.";
		}
		
		return false;		
	}	
	
	this.editProfile = function(cook) {
		 
		 return $http({
				  method: 'POST',
				  data : cook,
			      url : "../worker/cook/saveChanges",
			}).then(function success(response) {
				if(response.data == "Error free") {
					return "";
				}
				else {
					return response.data;
				}
				
			  }, function error(response) {
				  alert(response)
			  });
	 }
	this.saveFirstLogin = function(cook) {
		 
		 return $http({
				  method: 'POST',
				  data : cook,
			      url : "../worker/cook/firstLogin",
			}).then(function success(response) {
				if(response.data == "Error free") {
					return "";
				}
				else {
					return response.data;
				}
				
			  }, function error(response) {
				  alert(response)
			  });
	 }
	this.getFirstLogin = function() {
		return $http.get("../worker/firstLogin/"+user.id)
	}
	
	this.getFoodOrders = function() {
		return $http.get("../worker/cook/getFoodOrders/"+user.id)
	}
	
	this.startPrepareFood = function(foodOrder) {
		return $http({
			  method: 'POST',
			  data : foodOrder,
		      url : "../worker/cook/startPrepareFood",
		}).then(function success(response) {
			if(response.data == "Error free") {
				return "";
			}
			else {
				return response.data;
			}
			
		  }, function error(response) {
			  alert(response)
		  });
	}
	this.prepareDone = function(foodOrder) {
		return $http({
			  method: 'POST',
			  data : foodOrder,
		      url : "../worker/cook/prepareFoodDone",
		}).then(function success(response) {
			if(response.data == "Error free") {
				return "";
			}
			else {
				return response.data;
			}
			
		  }, function error(response) {
			  alert(response)
		  });
	}
	
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
	
	this.loadSegments = function(id){
		return $http({
			  method: 'GET',
		      url : "../restManager/loadAllMySegments/"+id 
		});
		
	}
	
	this.getCooks = function() {
		return $http.get("../worker/cooks/"+user.id)
	}
}]);
