var waiter = angular.module('waiter', []).run(['$rootScope', '$http', '$window', function ($rootScope, $http, $window) {
    
}]);

waiter.controller('waiterController', [ '$scope', 'waiterService', function($scope, waiterService){
	
	$scope.viewProfile = false;

	$scope.getProfile = function(){
		$scope.viewProfile = true;
		waiterService.getWaiter().then(
				function(response){
					$scope.waiter = response.data;
					console.log($scope.waiter);
				}
		)
		
		$scope.updateProfile = function() {
			waiterService.saveChanges($scope.waiter);
		}
	}
	
		
}]);

waiter.controller('profileController', [ '$scope', 'waiterService', '$timeout', function($scope, waiterService, $timeout){
	
	$scope.oldPassword = "";
	$scope.newPassword = "";
	$scope.confirmPassword = "";
	
	$scope.updateProfile = function() {
		$scope.$parent.editWaiter.user.name = $scope.$parent.waiter.user.name;
		$scope.$parent.editWaiter.user.lastname = $scope.$parent.waiter.user.lastname;
		$scope.$parent.editWaiter.user.email = $scope.$parent.waiter.user.email;	
		$scope.$parent.editWaiter.user.name = $scope.$parent.waiter.user.name;
		$scope.$parent.editWaiter.birthday = $scope.$parent.waiter.birthday;
		$scope.$parent.editWaiter.suitSize = $scope.$parent.waiter.suitSize;
		$scope.$parent.editWaiter.shoeSize = $scope.$parent.waiter.shoeSize;
		$scope.oldPassword = "";
		$scope.newPassword = "";
		$scope.confirmPassword = "";
	}
	
	$scope.editProfile = function() {
		var retVal = guestService.validatePasswords($scope.$parent.editWaiter, $scope.oldPassword, $scope.newPassword, $scope.confirmPassword);
		if(retVal){
			toastr.info('Ponovo unesite lozinke!')
			$timeout(function() {
				//$window.location.href = '/StartPage/StartPage.html'
				}, 2000);
		}else{
			$scope.$parent.newGuest.user.password = $scope.newPassword;
			guestService.editProfile($scope.$parent.newGuest)
			
			toastr.success('We do have the Kapua suite available.', 'Turtle Bay Resort', {timeOut: 1000})
		}
	}
	
}]);

waiter.service('waiterService', ['$window', '$http', '$timeout', function($window, $http, $timeout){
	
	this.getWaiter = function() {
		return $http.get("../worker/waiter/3")
	}

	this.saveChanges = function(waiter) {
		$http({
			  method: 'POST',
			  data : $.param(waiter),
		      url : "../worker/waiter/saveChanges/3"
		}).then(function success(response) {
					alert(response.data);
			  }, function error(response) {
				  	alert("Error!");
			  }
		);
	}
	
	this.validatePasswords = function(waiter, oldPassword, newPassword, newPasswordConfirm){
		if(oldPassword != waiter.user.password) {
			toastr.error('Niste uneli vasu lozinku!','Greska!')
			return true;
			//toastr.error('I do not think that word means what you think it means.', 'Inconceivable!')
		}
		if(newPassword != newPasswordConfirm) {
			//return "Unete lozinke se ne podudaraju.";
			toastr.error('Unete lozinke se ne podudaraju.','Greska!')
			return true;
		}
	
		return false;
	}
	
}]);
	
