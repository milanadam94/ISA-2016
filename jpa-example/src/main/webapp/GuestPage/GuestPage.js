var app = angular
		.module('app', [])
		.config(
				function($httpProvider) {
					$httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
					$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
				})
		.run(['$rootScope', '$http', '$window', function ($rootScope, $http, $window) {
	
			if (typeof $.cookie('user') === 'undefined') {
				
				$window.location.href = "/StartPage/StartPage.html";
			}
			else
			{
				user = JSON.parse($.cookie('user'));
				
				if(user.userType != "GUEST") {
					alert("DALJE")
				}
			}
}]);

app.controller('restaurantsController', [ '$scope', 'restaurantsService', function($scope, restaurantsService) {

	$scope.sortType = 'name';
	$scope.sortReverse = false;

} ]);

app.controller('friendsController', [ '$scope', function($scope) {

	$scope.sortType = 'name';
	$scope.sortReverse = false;

	$scope.friendsSearch = "";

	$scope.addFriend = function() {

	}
	
	$scope.searchFriends = function() {
		
	}

} ]);

app.controller('profileController', [ '$scope', 'guestService', function($scope, guestService) {
	
	$scope.errorMessage = false;
	
	$scope.password = "";
	$scope.newPassword = "";
	$scope.newPasswordConfirm = "";
	
	$scope.resetNewGuest = function() {
		$scope.$parent.newGuest.user.email = $scope.$parent.guest.user.email;
		$scope.$parent.newGuest.user.name = $scope.$parent.guest.user.name;
		$scope.$parent.newGuest.user.lastName = $scope.$parent.guest.user.lastName;
		$scope.$parent.newGuest.city = $scope.$parent.guest.city;
		$scope.$parent.newGuest.address = $scope.$parent.guest.address;
		$scope.password = "";
		$scope.newPassword = "";
		$scope.newPasswordConfirm = "";
		$scope.errorMessage = false;
	}
	
	$scope.editProfile = function() {
		var retVal = guestService.validateEditInput($scope.$parent.newGuest, $scope.password, $scope.newPassword, $scope.newPasswordConfirm);
		
		if(retVal != "") {
			$scope.errorMessage = retVal;
			return;
		}
		else {
			$scope.errorMessage = "";
			guestService.editProfile($scope.$parent.newGuest).then(function(data){
				if(data != "") {
					$scope.errorMessage = "Neuspesna izmena.";
				}
				else
				{
					$scope.$parent.guest = $scope.$parent.newGuest;
					$.cookie.json = true;
					$.cookie("user", $scope.$parent.newGuest.user, {path    : '/', domain  : ''});
				}
		    });
		}
	}

} ]);

app.controller('guestPageController', [ '$scope', 'restaurantsService', 'guestService', 
		function($scope, restaurantsService, guestService) {
			
			$scope.showRestaurants = false;
			$scope.showFriends = false;
			$scope.showProfile = false;
			
			$scope.loadRestaurants = function() {
				restaurantsService.loadRestaurants().then(function(data) {
					$scope.restaurants = data;
				});
				$scope.showRestaurants = true;
				$scope.showFriends = false;
				$scope.showProfile = false;
			}

			$scope.loadUserFriends = function() {
				guestService.loadGuest().then(function(data) {
					$scope.guest = data;
				});
				$scope.showRestaurants = false;
				$scope.showFriends = true;
				$scope.showProfile = false;

			}
			
			$scope.userLogout = function() {
				guestService.logout($scope.user);
			}

			$scope.loadUserProfile = function() {
				guestService.loadGuest().then(function(data) {
					$scope.guest = data;
					$scope.newGuest = angular.copy($scope.guest);
				});
				$scope.showRestaurants = false;
				$scope.showFriends = false;
				$scope.showProfile = true;
			}

		} ]);

app.service('restaurantsService', [ '$http', '$window',
		function($http, $window) {

			this.loadRestaurants = function() {
				return $http({
					method : 'GET',
					url : "../restaurant/loadRestaurants"
				}).then(function success(response) {
					return response.data;
				}, function error(response) {
					alert(response)
				});
			}

} ]);

app.service('guestService', [ '$http', '$window', function($http, $window) {
	
	this.loadGuest = function() {
		user = JSON.parse($.cookie("user"));
		return $http({
			method : 'POST',
			url : "../guest/loadGuest/" + user.id
		}).then(function success(response) {
			return response.data;
		}, function error(response) {
			alert(response)
		});
	}
	
	this.logout = function(user) {
		$.removeCookie('user', {
			path : '/',
			domain : ''
		});
		$http({
			method : 'PUT',
			data : $.param(user),
			url : "../user/logout"
		}).then(function success(response) {

		}, function error(response) {
			
		});

		$window.location.href = '/StartPage/StartPage.html';
	}
	
	this.validateEditInput = function(guest, password, newPassword, newPasswordConfirm){
		
		if(guest.user.email == "" || password == "" || newPassword == "" 
			|| newPasswordConfirm == "" || guest.user.name == "" || guest.user.lastName == "" 
			|| guest.address == "" || guest.city == "")
			return "Potrebno je uneti sve podatke.";
			
		if(password != guest.user.password) {
			return "Lozinka nije ispravna.";
		}
		
		if(newPassword != newPasswordConfirm) {
			return "Unete lozinke se ne podudaraju.";
		}
	
		return "";
	}
	
	 this.editProfile = function(guest) {
		 return $http({
				  method: 'POST',
				  data : $.param(guest),
			      url : "../guest/editProfile"
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
	
} ]);
