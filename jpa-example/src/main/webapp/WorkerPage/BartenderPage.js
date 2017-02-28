var bartender = angular.module('bartender', []).run(['$rootScope', '$http', '$window', function ($rootScope, $http, $window) {
    
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

bartender.controller('bartenderController', [ '$scope', 'bartenderService', function($scope, bartenderService){
	
	$scope.viewProfile = true;
	$scope.firstLogin = false;
	
	bartenderService.getBartender().then(
			function(response){
				$scope.bartender = response.data;
				$scope.editBartender = angular.copy($scope.bartender);
				console.log($scope.bartender);
			}
		
	)
	
	$scope.getProfile = function(){
		$scope.viewProfile = true;
		
		bartenderService.getBartender().then(
				function(response){
					$scope.bartender = response.data;
					$scope.editBartender = angular.copy($scope.bartender);
					console.log($scope.bartender);
				}
			
		)
	}
	bartenderService.getFirstLogin().then(
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
		var returnValue = bartenderService.validatePasswords($scope.bartender, $scope.oldFirstPassword,$scope.newFirstPassword,$scope.confirmFirstPassword);
		
		if(returnValue){
			$scope.greska = returnValue;
		}else{
			$scope.greska = "";
			$scope.firstLogin = false;
			$scope.viewProfile = true;
			$scope.bartender.user.password = $scope.newFirstPassword;
			bartenderService.saveFirstLogin($scope.bartender).then(function(data){
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

bartender.controller('profileController', [ '$scope', 'bartenderService', function($scope, bartenderService){
	$scope.errorMessage = false;
	
	$scope.oldPassword = "";
	$scope.newPassword = "";
	$scope.confirmPassword = "";
	
	$scope.resetNewWaiter = function() {
		$scope.$parent.editBartender.user.email = $scope.$parent.bartender.user.email;
		$scope.$parent.editBartender.user.name = $scope.$parent.bartender.user.name;
		$scope.$parent.editBartender.user.lastName = $scope.$parent.bartender.user.lastName;
		$scope.$parent.editBartender.suitSize = $scope.$parent.bartender.suitSize;
		$scope.$parent.editBartender.shoeSize = $scope.$parent.bartender.shoeSize;
		$scope.$parent.editBartender.birthday = $scope.$parent.bartender.birthday;
		$scope.oldPassword = "";
		$scope.newPassword = "";
		$scope.confirmPassword = "";
		$scope.errorMessage = false;
	}
	
	$scope.editProfile = function() {
		var retVal = bartenderService.validatePasswords($scope.$parent.editBartender, $scope.oldPassword, $scope.newPassword, $scope.confirmPassword);
		if(retVal){
			$scope.errorMessage = retVal;
		}else{
			$scope.errorMessage = "";
			$scope.$parent.editBartender.user.password = $scope.newPassword;
			bartenderService.editProfile($scope.$parent.editBartender).then(function(data){
				if(data != "") {
					$scope.errorMessage = data;
				}
				else
				{
					$scope.$parent.bartender = $scope.$parent.editBartender;
					//$.cookie.json = true;
					//$.cookie("user", $scope.$parent.newGuest.user, {path    : '/', domain  : ''});
					angular.element('#profileModal').modal('hide');
				}
		    });
			
		}
	}
}]);

bartender.service('bartenderService', ['$window', '$http', function($window, $http){
	
	this.getBartender = function() {
		return $http.get("../worker/bartender/4")
	}
	
	this.validatePasswords = function(bartender, password, newPassword, newPasswordConfirm){
		
		if(password != bartender.user.password) {
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
	
	this.editProfile = function(bartender) {
		 
		 return $http({
				  method: 'POST',
				  data : bartender,
			      url : "../worker/bartender/saveChanges",
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
	this.saveFirstLogin = function(bartender) {
		 
		 return $http({
				  method: 'POST',
				  data : bartender,
			      url : "../worker/bartender/firstLogin",
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
		return $http.get("../worker/firstLogin/4")
	}
}]);