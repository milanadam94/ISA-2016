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



restManager.controller('restManagerController', [ '$scope', 'konfService', 'drinkService', 'restaurantInfoService', 'foodService','canvasService', function($scope, konfService, drinkService, restaurantInfoService, foodService, canvasService ){

	$scope.newFood = {
			name: "",
			description: "",
			price: 0
	}
	
	$scope.newDrink = {
			name: "",
			description: "",
			price: 0
	}
	
	
	findUser = function(){
		restaurantInfoService.findRestaurantManager().then(
				function(response){
					$scope.user = response.data;
					
				}
				
		);
	}
	
	findUser();
	
	setShows = function(profilShow, picaShow, jelovnikShow, konfiguracijaShow){
		$scope.profilShow = profilShow;
		$scope.picaShow = picaShow;
		$scope.jelovnikShow = jelovnikShow;
		$scope.konfiguracijaShow = konfiguracijaShow;
	}
	setShows(false,false,false,false);
	
	$scope.error = false;
	$scope.errorMessage = "";
	
	restaurantInfoService.getRestaurant($scope.user).then(
			function(response){
				$scope.restaurant = response.data;
				
				if(response.data.name == null){
					alert("Restoran nije pronadjen!");
				}
			}
	);
	
	
	$scope.getRestaurant = function() {		
			if($scope.restaurant.name != null){
				setShows(true, false, false, false);
			}else{
				alert("Restoran nije pronadjen!");
			}
			
	}
	

	$scope.getFoods = function(){

		restaurantInfoService.getMenu($scope.user, $scope.restaurant).then(
				function(response){

					if(response.data != null){
						setShows(false, false, true, false);
						$scope.menu = response.data;
					}else{
						alert("Meni nije pronadjen!");
					}	
					
				}
		);	
		
	}
	
	$scope.changeFood = function(foodID){
		selectedFood = "";
		
		angular.forEach($scope.menu.foods, function(value){
			if(value.id == foodID){
				selectedFood = value;
			}
			
		})
		
		if(selectedFood.name == "" || selectedFood.price < 0 || $scope.restaurant == null){
			alert("Ime ne sme biti prazno. Cena ne sme biti manja od 0");
			return;
		}
		
		foodService.saveChanges(selectedFood, $scope.menu);
		
	}
	
	
	$scope.addFood = function(){
		
		if($scope.newFood.name == "" || $scope.newFood.price < 0 || $scope.restaurant == null){
			alert("Ime ne sme biti prazno i cena ne sme biti manja od 0!");
			return;
		}
		
		foodService.addFood($scope.newFood, $scope.menu);
		restaurantInfoService.getMenu($scope.user, $scope.restaurant).then(
				function(response){
								
					if(response.data != null){
						$scope.menu = response.data;
						location.reload();
					}else{
						alert("Meni nije pronadjen!");
					}	
								
				}
			);	
		
		
	}
	
	
	$scope.deleteFood = function(foodID){
		
		foodService.deleteFood(foodID, $scope.menu);
		restaurantInfoService.getMenu($scope.user, $scope.restaurant).then(
			function(response){
							
				if(response.data != null){
					$scope.menu = response.data;
					location.reload();
				}else{
					alert("Meni nije pronadjen!");
				}	
							
			}
		);	
			
	
	}
	
	
	
	$scope.getDrinks = function(){
		restaurantInfoService.getMenu($scope.user, $scope.restaurant).then(
				function(response){
					
					if(response.data != null){
						setShows(false, true, false, false);
						$scope.menu = response.data;
					}else{
						alert("Meni nije pronadjen!");
					}	
					
				}
		);	
		
	}
	
	
	$scope.addDrink = function(){
		
		if($scope.newDrink.name == "" || $scope.newDrink.price < 0 || $scope.restaurant == null){
			alert("Ime ne sme biti prazno i cena ne sme biti manja od 0!");
			return;
		}
		
		drinkService.addDrink($scope.newDrink, $scope.menu);
		restaurantInfoService.getMenu($scope.user, $scope.restaurant).then(
				function(response){
								
					if(response.data != null){
						$scope.menu = response.data;
						location.reload();
					}else{
						alert("Meni nije pronadjen!");
					}	
								
				}
			);	
	}
	
	$scope.changeDrink = function(drinkID){
		selectedDrink = "";
		
		angular.forEach($scope.menu.drinks, function(value){
			if(value.id == drinkID){
				selectedDrink = value;
			}
			
		})
		
		if(selectedDrink.name == "" || selectedDrink.price < 0 || $scope.restaurant == null){
			alert("Ime ne sme biti prazno. Cena ne sme biti manja od 0");
			return;
		}
		
		drinkService.saveChanges(selectedDrink, $scope.menu);
		
	}
	
	$scope.deleteDrink = function(drinkID){
		drinkService.deleteDrink(drinkID, $scope.menu);
		restaurantInfoService.getMenu($scope.user, $scope.restaurant).then(
			function(response){
							
				if(response.data != null){
					$scope.menu = response.data;
					location.reload();
				}else{
					alert("Meni nije pronadjen!");
				}	
							
			}
		);		
	}
	
	
	// ===============================================================
	
	$scope.newSegment = {
			name: "",
			smoking: false
	}
	
	$scope.newTable = {
			seatCount: 0,
			tag: "",
			description: "",
			xCoord: 0,
			yCoord: 0
	}
	
	
	$scope.getKonf = function(){
		
		
	}
	
	
	$scope.addKonfig = function(){
		
		if($scope.newSegment.name == "" || $scope.restaurant == null){
			alert("Neispravan unos ili restoran nije pronadjen")
			return;
		}
		
		konfService.addSegment($scope.newSegment, $scope.restaurant.id).then(
				function(response){
					alert(response.data);
					
					location.reload();
				}
		);
		
		
	}
	
	restaurantInfoService.loadAllSegments().then(
			function(response){
				$scope.segments = response.data;
			}
			
	);
	
	
	$scope.konfig = function(){
		setShows(false,false,false,true);
		
		$scope.loadTables();
		
	}
	

	$scope.saveRestaurantInfo = function() {
		
		if($scope.profilShow){
			if($scope.restaurant.name == "" || $scope.restaurant.description == ""){
				$scope.error = true;
				$scope.errorMessage = "Ne moze biti prazno!";
				return;
			}else{
				$scope.error = false;
				$scope.errorMessage = "";
			}			
			restaurantInfoService.saveChanges($scope.restaurant, $scope.user);
			
			
		}else if ($scope.jelovnikShow) {
			
			
		}else if ($scope.piceShow){
			
		}else if ($scope.konfiguracijaShow){
			
		}else {
			alert("Error! Nothing selected.");
		}
		
	}
	
	// KANVAS ====================================================
	$scope.myNewSegment = "";
	
	
	$scope.draw = function(event) {
    	var canvas = document.getElementById('canvas');
    	var context = canvas.getContext('2d');
    	
    	var rect = canvas.getBoundingClientRect();
        var x = event.clientX - rect.left;
        var y = event.clientY - rect.top; var rect = canvas.getBoundingClientRect(), 
        scaleX = canvas.width / rect.width,    // relationship bitmap vs. element for X
        scaleY = canvas.height / rect.height;  // relationship bitmap vs. element for Y

	    x = (event.clientX - rect.left) * scaleX,   // scale mouse coordinates after they have
	    y = (event.clientY - rect.top) * scaleY     // been adjusted to be relative to element
	    //  context.clearRect(0, 0, canvas.width, canvas.height);
	    
	    xIndex = x/30.0 + 1;
	    yIndex = y/30.0 + 1;
	    xIndex = Math.floor(xIndex);
	    yIndex = Math.floor(yIndex);
	    
		startX = x/30.0 + 1;
	    startY = y/30.0 + 1;
	    startX = Math.floor(startX)*30 -30;
	    startY = Math.floor(startY)*30 -30;
		
	    $scope.newTable.xCoord = xIndex;
	    $scope.newTable.yCoord = yIndex;
	    
	    
	    $scope.newTableShow = true;
	    $scope.deleteTableButtonShow = false;
	    
	    $scope.restaurantTables.forEach(function(table) {
			if(table.xCoord == xIndex && table.yCoord == yIndex){
				$scope.newTableShow = false;
				$scope.deleteTableButtonShow = true;
			}
		});
	    
	    
  }
	
	$scope.restaurantTables = [];

	$scope.loadTables = function() {
		
		$scope.newTableShow = false;
		var canvas = document.getElementById('canvas');
    	var context = canvas.getContext('2d');
    	context.clearRect(0, 0, canvas.width, canvas.height);
    	
    	for(var j = 0; j < 5; j++)
    	{
	    	for(var i=0; i < 10; i++)
	    	{
	    		context.rect(i*30 ,j*30 ,30,30);
	            context.stroke();
	    	}
    	}
    	
    	
    	canvasService.getAllTables().then(
    			function(response){
    				$scope.restaurantTables = response.data;
    				$scope.restaurantTables.forEach(function(table) {
    					x = parseFloat(table.xCoord)*30 - 30;
    					y = parseFloat(table.yCoord)*30 - 30;
    					context.globalAlpha = 1.0;
    					context.fillStyle="#000000";
    					context.font="20px Arial";
    					context.fillText(table.tag, x + 8, y + 22);
    				});
    			}
    	);
    	
    	
    	
    	
	}
	
	
	
	$scope.addTable = function(){
		
		if($scope.newTable.seatCount < 0 || $scope.newTable.seatCount > 20 ||
				$scope.newTable.tag == "" || $scope.myNewSegment == ""){
			alert("Los unos!");
			return;			
		}
		
		if($scope.newTable.tag.length > 2){
			alert("Oznaka moze imati maksimalno 2 karaktera!");
			return;
		}
		
		
		canvasService.addTable($scope.newTable, $scope.myNewSegment).then(
				function(response){
					alert(response.data);
					location.reload();
				}
		);
		
	}
	
	
	$scope.deleteTable = function(){
		flag = false;
		
		$scope.restaurantTables.forEach(function(table) {
			if(table.xCoord == $scope.newTable.xCoord && table.yCoord == $scope.newTable.yCoord){
				flag = true;
			}
		});
		
		if(!flag){
			alert("Niste selektovali sto");
			return;
		}
		
		canvasService.deleteTable($scope.newTable);
		
		
	}
	
	
	
}]);








restManager.service('restaurantInfoService',['$window', '$http', function($window, $http){
	
	this.loadAllSegments = function(){
		return $http({
			  method: 'GET',
		      url : "../restManager/loadAllSegments/1" //========================================== dodati user Email
		});
		
	}
	
	
	this.getRestaurant = function(user){
		return $http.get("../restManager/myRestaurant/1"); // ================================= OVDE NAMESTITI EMAIL USERA
	}
	
	this.findRestaurantManager = function(){
		return $http({
			  method: 'GET',
		      url : "../restManager/getUser/1" //========================================== dodati user Email
		});
	}
	
	this.saveChanges = function(restaurant, user){
		
		$http({
			  method: 'POST',
			  data : $.param(restaurant),
		      url : "../restManager/saveRestaurantInfo/1" // =================================== OVDE NAMESTITI EMAIL USERA
		}).then(function success(response) {
					alert("Success!");
			  }, function error(response) {
				  	alert("Error!");
			  }
			);
	};
	
	this.getMenu = function (user, restaurant){
		return $http({
			method: 'POST',
			data : restaurant,
			url: "../restManager/getMenu/1"  // ==================== user.mail
		});
	}
	
	
}]);

restManager.service('foodService',['$window', '$http', function($window, $http){
	
	this.saveChanges = function (food, menu){
		$http({
			method: 'POST',
			data: food,
			url: "../restManager/changeFood/" + menu.id
		}).then(function success() {
			alert("Uspesno promenjeno!");
		  }, function error() {
			  	alert("Error!");
		  }
		  
		);	
	}
	
	this.addFood = function (newFood, menu){
		$http({
			method: 'POST',
			data: newFood,
			url: "../restManager/addFood/"+menu.id
		});
		
	}
	
	this.deleteFood = function (foodID, menu){
		$http({
			method: 'DELETE',
			url: "../restManager/deleteFood/"+foodID+"/"+menu.id
		});
	}
	
}]);

restManager.service('drinkService', ['$window', '$http', function($window, $http){

	this.saveChanges = function (drink, menu){
		$http({
			method: 'POST',
			data: drink,
			url: "../restManager/changeDrink/" + menu.id
		}).then(function success() {
			alert("Uspesno promenjeno!");
		  }, function error() {
			  	alert("Error!");
		  }
		  
		);		
	}
	
	this.deleteDrink = function(drinkID, menu){
		$http({
			method: 'DELETE',
			url: "../restManager/deleteDrink/"+drinkID+"/"+menu.id
		}); 
	}
	
	this.addDrink = function (newDrink, menu){
		$http({
			method: 'POST',
			data: newDrink,
			url: "../restManager/addDrink/"+menu.id
		});
	}
	
}]);

restManager.service('konfService', ['$window', '$http', function($window, $http){
	
	this.addSegment = function(newSegment, restoranID){
		return $http({
			method: 'POST',
			data: newSegment,
			url: "../restManager/addSegment/"+restoranID
		});
		
	}
	
	this.getKonf;
	this.saveChanges;
	this.addKonf;
	
}]);


restManager.service('canvasService', ['$window', '$http', function($window, $http){
	
	this.addTable = function(newTable, segmentID){
		return $http({
			method: 'POST',
			data: newTable,
			url: "../restManager/addTable/"+segmentID+"/1" //==================================== staviti email restoran menagera
		});
	}
	
	this.getAllTables = function(){
		return $http({
			method: 'GET',
			url: "../restManager/getAllTables/1" //==================================== staviti email restoran menagera
		});
	}
	
	
	this.deleteTable = function (newTable){
		$http({
			method: 'POST',
			data: newTable,
			url: "../restManager/deleteTable"
		}).then(
				function success(){
					location.reload();
				},
				function error(){
					alert("Error!");
				}
		);
	}
	
	
}]);




