var cook = angular.module('cook', []).run(['$rootScope', '$http', '$window', function ($rootScope, $http, $window) {
    
	/*if (typeof $.cookie('user') === 'undefined') {
		
		$window.location.href = "/StartPage/StartPage.html";
	}
	else
	{
		user = JSON.parse($.cookie('user'));
		
		if(user.userType != "GUEST") {
			alert("DALJE")
		}
	}*/
	
}]);

cook.controller('cookController', [ '$scope', 'cookService', function($scope, cookService){
	 
	$scope.viewProfile = true;
	$scope.firstLogin = false;
	
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
			}else{
				$scope.firstLogin = true;
				$scope.viewProfile = false;
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
		return $http.get("../worker/cook/5")
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
		return $http.get("../worker/firstLogin/5")
	}
}]);
