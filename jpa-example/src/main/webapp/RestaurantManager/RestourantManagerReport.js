angular.element(document).ready(function () {
    console.log('page loading completed');
});
var restManager = angular.module('restManager', []).config(['$qProvider', '$httpProvider', function ($qProvider, $httpProvider)  {
    $qProvider.errorOnUnhandledRejections(false);
    
}]).run(['$rootScope', '$http', '$window', function ($rootScope, $http, $window) {
	
	//namestiti dole u funckcijama gde treba user.email kada se ovo namesti!
	if (typeof $.cookie('user') !== 'undefined') {
		user = JSON.parse($.cookie('user'));
		
		if(user.userType == "GUEST") {
			$window.location.href = "/GuestPage/GuestPage.html";
		}
		// DALJE
	}
		 
	
	
}]);



restManager.controller('reportControler', [ '$scope', 'reportService', function($scope, reportService){
	
	$scope.choosenOption = "";
	$scope.foodName = "Unesite naziv jela";
	$scope.ocenaRestorana = 0;
	$scope.waiterName = "Unesite ime konobara";
	$scope.waiterPeriodList = [];
	$scope.restoranPeriodList = [];
	
	$scope.period = {
			startDate: "",
			endDate: ""
	}
	
	setShows = function(ocenaRestoranaShow, jeluShow, konobaruShow, posecenostShow, prihodiShow, prihodiPoKonobaruShow) {
		$scope.ocenaRestoranaShow = ocenaRestoranaShow;
		$scope.jeluShow = jeluShow;
		$scope.konobaruShow = konobaruShow;
		$scope.posecenostShow = posecenostShow;
		$scope.prihodiShow = prihodiShow;
		$scope.prihodiPoKonobaruShow = prihodiPoKonobaruShow;
		
	}
	
	setShows(false,false,false,false,false,false);
	
	
	
	$scope.reloadPage = function(){
		if($scope.choosenOption == "Ocena Restorana"){
			setShows(true,false,false,false,false,false);
			
			reportService.getRestaurantRecension().then(
					function(response){
	
						$scope.restaurantRecensions = response.data;
						$scope.ocenaRestorana = 0;
						
						$scope.restaurantRecensions.forEach(function(recension){
							$scope.ocenaRestorana += recension.grade;
						});
						
						if($scope.restaurantRecensions.length != 0){
							$scope.ocenaRestorana /= $scope.restaurantRecensions.length;
						}
											
					}
			);
			
			
			
		}else if($scope.choosenOption == "Jelu"){
			setShows(false,true,false,false,false,false);
			
			$scope.findFood();

			
		}else if($scope.choosenOption == "Konobaru"){
			setShows(false,false,true,false,false,false);
			
			$scope.findWaiter();
			
		}else if($scope.choosenOption == "Posecenosti"){
			setShows(false,false,false,true,false,false);
			
		}else if($scope.choosenOption == "Prihodu"){
			setShows(false,false,false,false,true,false);
			
		}else if($scope.choosenOption == "Prihodi Po Konobaru"){
			setShows(false,false,false,false,false,true);

		}else{
			setShows(false,false,false,false,false,false);
		}
		
	}
	
	
	$scope.findFood = function(){
		
		if($scope.foodName == ""){
			$scope.foodName = "Unesite naziv jela"
		}
		
		
		reportService.getFoodRecension($scope.foodName).then(
				function(response){

					$scope.foodRecensions = response.data;
				
					if($scope.foodRecensions.length == 0){
						toastr.info("Jelo nije ocenjivano.");
					}
										
				}
		);
	}
	
	$scope.findWaiter = function(){
		
		if($scope.waiterName == ""){
			$scope.waiterName = "Unesite ime konobara"
		}
		
		reportService.getWaiterRecension($scope.waiterName).then(
				function(response){

					$scope.waiterRecensions = response.data;
				
					if($scope.waiterRecensions.length == 0){
						toastr.info("Konobar nije ocenjivan.");
					}
										
				}
		);
		
	}
	
	
	$scope.findByWaiterPeriod = function(){
		if($scope.period.startDate == "" || $scope.period.endDate == ""){
			toastr.info("Morate odabrati datum!");
			return;
		}
		$scope.waiterPeriodList = [];
		
		reportService.getWaiters().then(
				function(waiters){
					
					if(waiters.data == null){
						toastr.info("Nisu pronadjeni konobari");
						return;
					}
					
					
					reportService.getAllOrders().then(
							function(orders){
								
								if(orders.data == null){
									toastr.info("Nisu pronadjene porudzbine!");
									return;
								}
								
								waiters.data.forEach(function(waiter){
									var mojPrihod = 0;
									
									orders.data.forEach(function(order){
										
										if(order.waiter.id == waiter.id && $scope.period.startDate < order.orderDate && $scope.period.endDate > order.orderDate){
											
											order.foodOrders.forEach(function(food){
												mojPrihod += (food.quantity * food.food.price);
											});
											
											order.drinkOrders.forEach(function(drink){
												mojPrihod += (drink.quantity * drink.drink.price);
											});
										}
										
										
									});
									
									pom = {
										waiter: waiter,
										prihod: mojPrihod
									}
									
									$scope.waiterPeriodList.push(pom);
									
								});
								
							}							
								
					);	
				}	
		);	
	}
	
	
	
	$scope.findInPeriod = function(){
		
		if($scope.period.startDate == "" || $scope.period.endDate == ""){
			toastr.info("Morate odabrati datum!");
			return;
		}
		$scope.restoranPeriodList = [];
		
		
		reportService.getAllOrders().then(
				function(orders){
					
					if(orders.data == null){
						toastr.info("Nisu pronadjene porudzbine!");
						return;
					}
					
					
					pomocnaListOrdera = [];
			
						orders.data.forEach(function(order){
							var mojPrihod = 0;
							if($scope.period.startDate < order.orderDate && $scope.period.endDate > order.orderDate){
								
								order.foodOrders.forEach(function(food){
									mojPrihod += (food.quantity * food.food.price);
								});
								
								order.drinkOrders.forEach(function(drink){
									mojPrihod += (drink.quantity * drink.drink.price);
								});
								
								pom = {
										order: order,
										prihod: mojPrihod
								}
								
								pomocnaListOrdera.push(pom);
								
							}
							
							
						});
						
					
						
						pomocnaListOrdera.forEach(function(ord1){
							
							var flag = true;
							$scope.restoranPeriodList.forEach(function(ord2){
								
								if(ord1.order.orderDate == ord2.order.orderDate){
									ord2.prihod += ord1.prihod;
									flag = false;
								}
								
								
							});
							
							if(flag){
								$scope.restoranPeriodList.push(ord1);
							}
							
						});
						
					
				}							
					
		);	
		
		
		
	}
	
	
	
	
}]);




restManager.service('reportService', ['$window', '$http', function($window, $http){
	
	this.getRestaurantRecension = function(){
		return $http({
			method: 'GET',
			url: "../restManager/getRestaurantRecension/1" //  //==================================== staviti email restoran menagera
		});
	}
	
	this.getFoodRecension = function(foodName){
		return $http({
			method: 'GET',
			url: "../restManager/getFoodRecension/"+foodName+"/1" //  //==================================== staviti email restoran menagera
		});
	}
	
	this.getWaiterRecension = function(waiterName){
		return $http({
			method: 'GET',
			url: "../restManager/getWaiterRecension/"+waiterName+"/1" //  //==================================== staviti email restoran menagera
		});
	}
	
	
	this.getWaiters = function(){
		return $http({
			  method: 'GET',
		      url : "../restManager/getWaiters/1" //========================================== dodati user email
		});
		
	}
	
	this.getAllOrders = function(){
		return $http({
			  method: 'GET',
		      url : "../restManager/getAllOrders/1" //========================================== dodati user email
		});
		
	}
	
}]);




















