var app = angular.module('app', []).run(['$rootScope', '$http', '$window', function ($rootScope, $http, $window) {
	
	if (typeof $.cookie('user') !== 'undefined') {
		user = JSON.parse($.cookie('user'));
		
		if(user.userType != "GUEST") {
			$window.location.href = "/StartPage/StartPage.html";
		}
		else
		{
			$rootScope.invites = [];
			
			$http({
				method : 'POST',
				url : "../guest/getFriendInvites/" + user.id,
			}).then(function success(response) {
					var invs = response.data;
					 invs.forEach(function(invite){
						$http({
							method : 'POST',
							url : "../restaurant/getRestaurantMenu",
							data : invite.reservation.restaurant
						}).then(function success(response) {
								var resMenuPair = {"invite" : invite, "menu" : response.data}
								$rootScope.invites.push(resMenuPair);
						}, function error(response) {
							alert(response)
						});
					})
			}, function error(response) {
				alert(response)
			});
			console.log($rootScope.invites)
		}
	}
	else {
		$window.location.href = "/StartPage/StartPage.html";
	}
		 
}]);
				
						 
app.controller('invitePageController', [ '$scope', '$rootScope', '$window', 'invitePageService',  function($scope, $rootScope, $window, invitePageService){
	
		 $scope.toGuestPage = function() {
			 $window.location.href = "/GuestPage/GuestPage.html";
		 }
		 
		 $scope.prepared = false;
		 
		 $scope.accept = function(i) {
			 $rootScope.invites.pop(i);
			 i.invite.accepted = true;
			 invitePageService.accept(i.invite);
			 var drinkOrders = [];
			 var foodOrders = [];
			 if(i.menu.foods != undefined && i.menu.drinks != undefined) {
				 i.menu.drinks.forEach(function(drink) {
					var val = angular.element('#drink' + drink.id).val();
					if(val > 0) {
						var drinkOrder = {"drink" : drink, "quantity" : val};
						drinkOrders.push(drinkOrder);
					}
				 });
				 i.menu.foods.forEach(function(food) {
						var val = angular.element('#food' + food.id).val();
						console.log(val)
						if(val > 0) {
							var foodOrder = {"food" : food, "quantity" : val};
							foodOrders.push(foodOrder);
						}
					});
					console.log(drinkOrders)
					console.log(foodOrders)
					if(drinkOrders.length != 0 || foodOrders.length != 0) {
						var guestOrder = {"prepared" : $scope.prepared, "foodOrders" : foodOrders, "drinkOrders" : drinkOrders, "restaurant" : i.invite.reservation.restaurant}
						invitePageService.order(guestOrder).then(function(data){
							console.log(data)
						})
					}
			 }
		 }
		 
		 $scope.decline = function(i) {
			 $rootScope.invites.pop(i);
			 invitePageService.decline(i.invite);
		 }
}]);

app.service('invitePageService', ['$http', '$window', function($http, $window){
	
	 	this.accept = function(invite) {
	 		return $http({
				method : 'POST',
				url : "../guest/acceptInvite/",
				data : invite
			}).then(function success(response) {
				return response.data;
			}, function error(response) {
				alert(response)
			});
	 	}
	 	
	 	this.decline = function(invite) {
	 		return $http({
				method : 'POST',
				url : "../guest/declineInvite/",
				data : invite
			}).then(function success(response) {
				return response.data;
			}, function error(response) {
				alert(response)
			});
	 	}
	 	
	 	this.order = function(guestOrder) {
	 		return $http({
				method : 'POST',
				url : "../restaurant/order/",
				data : guestOrder
			}).then(function success(response) {
				return response.data;
			}, function error(response) {
				alert(response)
			});
	 	}
}]);

