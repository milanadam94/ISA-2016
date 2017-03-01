var waiter = angular.module('waiter', []).run(['$rootScope', '$http', '$window', function ($rootScope, $http, $window) {
    
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

waiter.controller('waiterController', [ '$scope', 'waiterService', function($scope, waiterService){
	
	$scope.viewProfile = true;
	$scope.makeOrder = false;
	$scope.viewOrders = false;
	$scope.firstLogin = false;
	$scope.total = "";
	/*$scope.orderFoods = [];
	$scope.orderDrinks = [];
	$scope.order = {
			"foods" : $scope.orderFoods,
			"drinks" : $scope.orderDrinks
	};*/
	
	waiterService.getWaiter().then(
			function(response){
				$scope.waiter = response.data;
				$scope.editWaiter = angular.copy($scope.waiter);
				console.log($scope.waiter);
			}
		
	)
	$scope.getProfile = function(){
		
		$scope.viewProfile = true;
		$scope.makeOrder = false;
		$scope.viewOrders = false;
		waiterService.getWaiter().then(
				function(response){
					$scope.waiter = response.data;
					$scope.editWaiter = angular.copy($scope.waiter);
					console.log($scope.waiter);
				}
			
		)
	}
	waiterService.getFirstLogin().then(
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
		var returnValue = waiterService.validatePasswords($scope.waiter, $scope.oldFirstPassword,$scope.newFirstPassword,$scope.confirmFirstPassword);
		
		if(returnValue){
			$scope.greska = returnValue;
		}else{
			$scope.greska = "";
			$scope.firstLogin = false;
			$scope.viewProfile = true;
			$scope.waiter.user.password = $scope.newFirstPassword;
			waiterService.saveFirstLogin($scope.waiter).then(function(data){
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
	
	$scope.gotovaPorudzbina = function() {
		$scope.viewProfile = false;
		$scope.makeOrder = true;
		$scope.viewOrders = false;
		waiterService.saveGuestOrder();
		
	}
	$scope.porudzbine = function() {
		$scope.viewProfile = false;
		$scope.makeOrder = false;
		$scope.viewOrders = true;
		waiterService.getGuestOrders().then(
				function(response){
					$scope.guestOrders = response.data;
				}
		)
	}
	$scope.novaPorudzbina = function() {
		$scope.viewProfile = false;
		$scope.makeOrder = true;
		$scope.viewOrders = false;
	}
	$scope.getOrders = function() {
		$scope.viewProfile = false;
		$scope.makeOrder = true;
		$scope.viewOrders = false;
	}
	$scope.getDrinks = function() {
		waiterService.getDrinks().then(
				function(response){
					$scope.drinks = response.data;
				}
		)
	}
	$scope.getFoods = function() {
		waiterService.getFoods().then(
				function(response){
					$scope.foods = response.data;
				}
		)
	}
	$scope.addDrink = function(drink) {
		waiterService.addOrderDrink(drink);
	}
	$scope.addFood = function(food) {
		waiterService.addOrderFood(food);
	}
	$scope.deleteOrder = function(id) {
		waiterService.deleteOrder(id);
		//$scope.guestOrders.splice(id, 1);
		/*for(i=0; i<$scope.guestOrders.length; i++){
		    if($scope.guestOrders[i].id == id){
		      $scope.guestOrders.shift();
		    }
		}*/
	}
	$scope.makeBill = function(orderId){
		waiterService.getTotal(orderId).then(
				function(response){
					$scope.total = response.data;
					console.log($scope.total);
				}
		)
		$scope.viewOrders = false;
		$scope.bill = true;
	}
	$scope.racun = function(){
		$scope.viewOrders = true;
		$scope.bill = false;
	}
}]);

waiter.controller('profileController', [ '$scope', 'waiterService', function($scope, waiterService){
	
	$scope.errorMessage = false;
	
	$scope.oldPassword = "";
	$scope.newPassword = "";
	$scope.confirmPassword = "";
	
	$scope.resetNewWaiter = function() {
		$scope.$parent.editWaiter.user.email = $scope.$parent.waiter.user.email;
		$scope.$parent.editWaiter.user.name = $scope.$parent.waiter.user.name;
		$scope.$parent.editWaiter.user.lastName = $scope.$parent.waiter.user.lastName;
		$scope.$parent.editWaiter.suitSize = $scope.$parent.waiter.suitSize;
		$scope.$parent.editWaiter.shoeSize = $scope.$parent.waiter.shoeSize;
		$scope.$parent.editWaiter.birthday = $scope.$parent.waiter.birthday;
		$scope.oldPassword = "";
		$scope.newPassword = "";
		$scope.confirmPassword = "";
		$scope.errorMessage = false;
	}
	
	$scope.editProfile = function() {
		var retVal = waiterService.validatePasswords($scope.$parent.editWaiter, $scope.oldPassword, $scope.newPassword, $scope.confirmPassword);
		if(retVal){
			$scope.errorMessage = retVal;
		}else{
			$scope.errorMessage = "";
			$scope.$parent.editWaiter.user.password = $scope.newPassword;
			waiterService.editProfile($scope.$parent.editWaiter).then(function(data){
				if(data != "") {
					$scope.errorMessage = data;
				}
				else
				{
					$scope.$parent.waiter = $scope.$parent.editWaiter;
					//$.cookie.json = true;
					//$.cookie("user", $scope.$parent.newGuest.user, {path    : '/', domain  : ''});
					angular.element('#profileModal').modal('hide');
				}
		    });
			
		}
	}
	
}]);

waiter.service('waiterService', ['$window', '$http', function($window, $http){
	
	this.getWaiter = function() {
		return $http.get("../worker/waiter/3")
	}

	this.editProfile = function(waiter) {
		 
		 return $http({
				  method: 'POST',
				  data : waiter,
			      url : "../worker/waiter/saveChanges",
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
	
	this.validatePasswords = function(waiter, password, newPassword, newPasswordConfirm){
			
			if(password != waiter.user.password) {
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
	
	this.getFoods = function() {
		return $http.get("../worker/waiter/getFoods/3")
	}
	this.getDrinks = function() {
		return $http.get("../worker/waiter/getDrinks/3")
	}
	this.addOrderDrink = function(drink) {
		return $http({
			  method: 'POST',
			  data : drink,
		      url : "../worker/waiter/addOrderDrink",
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
	this.addOrderFood = function(food) {
		return $http({
			  method: 'POST',
			  data : food,
		      url : "../worker/waiter/addOrderFood",
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
	
	this.saveGuestOrder = function() {
		return $http.post("/worker/waiter/saveGuestOrder/3");
		/*return $http({
			  method: 'POST',
			  data : order,
		      url : "../worker/waiter/saveGuestOrder/3",
		}).then(function success(response) {
			if(response.data == "Error free") {
				return "";
			}
			else {
				return response.data;
			}
			
		  }, function error(response) {
			  alert(response)
		  });*/
	}
	this.getGuestOrders = function() {
		return $http.get("../worker/waiter/getGuestOrders/3");
	}
	this.deleteOrder = function(id){
		return $http.post("../worker/waiter/deleteGuestOrder/"+id);
		/*return $http({
			  method: 'POST',
			  data : order,
		      url : "../worker/waiter/deleteGuestOrder",
		}).then(function success(response) {
			if(response.data == "Error free") {
				return "";
			}
			else {
				return response.data;
			}
			
		  }, function error(response) {
			  alert(response)
		  });*/
	}
	this.getTotal = function(orderId){
		return $http.get("../worker/waiter/getTotal/"+orderId);
	}
	
	this.saveFirstLogin = function(waiter) {
		 
		 return $http({
				  method: 'POST',
				  data : waiter,
			      url : "../worker/waiter/firstLogin",
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
		return $http.get("../worker/firstLogin/3")
	}
}]);
	
