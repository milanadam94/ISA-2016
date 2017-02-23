angular.element(document).ready(function() {
	console.log('page loading completed');
});

var app = angular
		.module('app', [])
		.config(function($httpProvider) {
					$httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
					$httpProvider.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
				}


app.controller('guestPageController', [ '$scope', 'userService',
		'restaurantService', function($scope, userService, restaurantService) {

			$scope.addFriend = function() {
				
			}

			$scope.loadRestaurants = function() {
				restaurantService.loadRestaurants().then(function(data) {
					$scope.restaurants = data;
				});
				$scope.showRestaurants = true;
				$scope.showFriends = false;
				$scope.showProfile = false;
			}

			$scope.loadUserFriends = function() {
				$scope.showRestaurants = false;
				$scope.showFriends = true;
				$scope.showProfile = false;

			}

			$scope.loadUserProfile = function() {
				$scope.showRestaurants = false;
				$scope.showFriends = false;
				$scope.showProfile = true;
			}
		} ]);

app.service('restaurantService', [ '$http', '$window',
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

app.service('userService', [ '$window', '$http', function($window, $http) {

	this.logout = function() {
		user = JSON.parse($.cookie("user"));
		$.cookie("user", undefined, {
			path : '/',
			domain : ''
		});
		$http({
			method : 'PUT',
			data : $.param(user),
			url : "../user/logout"
		}).then(function success(response) {

		}, function error(response) {
			alert(response)
		});

		$window.location.href = '/StartPage/StartPage.html';
	}

} ]);
