var app = angular
		.module('app', [])
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

app.controller('restaurantsController', [ '$scope', 'restaurantsService',  function($scope, restaurantsService) {

	$scope.sortType = 'name';
	$scope.sortReverse = false;

} ]);

angular.module('app').filter('isFriend', function() {
	  return function (potFriend, guest) {
		  
		  var check = false;
		  guest.friends.forEach(function(friend) {
			  if(friend.id == potFriend.id) {
				  check = true;
				  return;
			  }
		  });
		  return check;
	  };
	});

app.controller('friendsController', [ '$scope', 'guestService', function($scope, guestService) {

	$scope.sortType = 'name';
	$scope.sortReverse = false;

	$scope.searchInput = "";

	$scope.addFriend = function(friend) {
		guestService.addFriend($scope.$parent.guest, friend).then(function(data) {
			
		})
	}
	
	$scope.searchGuests = function() {
		guestService.searchGuests($scope.$parent.guest, $scope.searchInput).then(function(data) {
			$scope.guests = data;
		})
	}

} ]);

app.controller('profileController', [ '$scope', 'guestService', function($scope, guestService) {
	
	$scope.errorMessage = false;
	$scope.userInfoErrorMessage = false;
	
	$scope.searchInput = "";
	
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
	
	$scope.editGuestInfo = function() {
		
		var retVal = guestService.validateEditUserInfoInput($scope.$parent.newGuest, $scope.password, $scope.newPassword, $scope.newPasswordConfirm);
		if(retVal) {
			$scope.userInfoErrorMessage = retVal;
		}
		else {
			$scope.userInfoErrorMessage = "";
			$scope.$parent.newGuest.user.password = $scope.newPassword;
			guestService.editProfile($scope.$parent.newGuest).then(function(data){
				if(data != "") {
					$scope.userInfoErrorMessage = data;
					$scope.$parent.newGuest.user.password = $scope.password;
				}
				else
				{
					$scope.$parent.guest = $scope.$parent.newGuest;
					$.cookie.json = true;
					$.cookie("user", $scope.$parent.newGuest.user, {path    : '/', domain  : ''});
					angular.element('#editGuestInfoModal').modal('hide');
				}
		    });
		}
	}
	
	$scope.editProfile = function() {
		var retVal = guestService.validateEditProfileInput($scope.$parent.newGuest);
		
		if(retVal) {
			$scope.errorMessage = retVal;
		}
		else {
			$scope.errorMessage = "";
			guestService.editProfile($scope.$parent.newGuest).then(function(data){
				if(data != "") {
					$scope.errorMessage = data;
				}
				else
				{
					$scope.$parent.guest = $scope.$parent.newGuest;
					$.cookie.json = true;
					$.cookie("user", $scope.$parent.newGuest.user, {path    : '/', domain  : ''});
					angular.element('#editProfileModal').modal('hide');
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
				guestService.logout();
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
	
	this.addFriend = function(guest, friend) {
		return $http({
			method : 'POST',
			url : "../guest/addFriend/" + guest.id,
			data: friend
		}).then(function success(response) {
			return response.data;
		}, function error(response) {
			alert(response)
		});
	}
	
	this.searchGuests = function(guest, searchInput) {
		return $http({
			method : 'POST',
			url : "../guest/searchGuests/" + guest.id,
			data: searchInput
		}).then(function success(response) {
			return response.data;
		}, function error(response) {
			alert(response)
		});
	}
	
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
	
	this.validateEditProfileInput = function(guest){
		
		if(guest.user.name == "" || guest.user.lastName == "")
			return "Ime i prezime je obavezno.";
		
		return false;
	}
	
	this.validateEditUserInfoInput = function(guest, password, newPassword, newPasswordConfirm){
		
		if(password == "" 
			|| newPassword == "" || newPasswordConfirm == "")
			return "Potrebno je uneti sve podatke.";
		
		console.log(password)
		console.log(guest.user.password)
		if(password != guest.user.password) {
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
	
	 this.editProfile = function(guest) {
		 
		 return $http({
				  method: 'POST',
				  data : guest,
			      url : "../guest/editProfile",
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
