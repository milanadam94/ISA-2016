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


app.filter('unique', function () {

	  return function (items, filterOn) {

	    if (filterOn === false) {
	      return items;
	    }

	    if ((filterOn || angular.isUndefined(filterOn)) && angular.isArray(items)) {
	      var hashCheck = {}, newItems = [];

	      var extractValueToCompare = function (item) {
	        if (angular.isObject(item) && angular.isString(filterOn)) {
	          return item[filterOn];
	        } else {
	          return item;
	        }
	      };

	      angular.forEach(items, function (item) {
	        var valueToCheck, isDuplicate = false;

	        for (var i = 0; i < newItems.length; i++) {
	          if (angular.equals(extractValueToCompare(newItems[i]), extractValueToCompare(item))) {
	            isDuplicate = true;
	            break;
	          }
	        }
	        if (!isDuplicate) {
	          newItems.push(item);
	        }

	      });
	      items = newItems;
	    }
	    return items;
	  };
	});

app.controller('restaurantsController', [ '$scope', 'restaurantsService',  function($scope, restaurantsService) {

	$scope.sortType = 'name';
	$scope.sortReverse = false;
	$scope.selectedCategory = "";
	$scope.idSelected = null;
	$scope.reservationDateTime = undefined;
	$scope.showCanvas = false;
	$scope.reservationDuration = undefined;
	$scope.tablesReservedThen = [];
	$scope.reserved = []
	$scope.menu = undefined;
	$scope.prepared = false;
	
	$scope.hideCanvas = function() {
		$scope.showCanvas = false;
	}
	
	$scope.reserveTables = function() {
		if($scope.reserved.length == 0) {
			toastr.options.timeOut = 1500;
			toastr.warning("Potrebno je izabrati barem jedan sto.");
			return;
		}
		console.log($scope.prepared)
		var drinkOrders = [];
		var foodOrders = [];
		$scope.menu.drinks.forEach(function(drink) {
			var val = angular.element('#drink' + drink.id).val();
			if(val > 0) {
				var drinkOrder = {"drink" : drink, "quantity" : val};
				drinkOrders.push(drinkOrder);
			}
		});
		$scope.menu.foods.forEach(function(food) {
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
			var guestOrder = {"prepared" : $scope.prepared, "foodOrders" : foodOrders, "drinkOrders" : drinkOrders, "restaurant" : $scope.$parent.selectedRestaurant}
			restaurantsService.order(guestOrder).then(function(data){
				console.log(data)
			})
		}
		d = new Date($scope.reservationDateTime);
		date = d.toISOString();
		restaurantsService.reserveTables($scope.$parent.selectedRestaurant, $scope.$parent.guest, date, $scope.reservationDuration, $scope.reserved).then(function(data){
			console.log(data)
		})
		toastr.options.timeOut = 2500;
		toastr.info("Vasa rezervacija se nalazi unutar taba 'Rezervacije'. Unutar taba mozete pozvati prijatelje da vas se pridruze.")
		$scope.$parent.showRestaurants = true;
		$scope.$parent.showReservations = false;
		$scope.showCanvas = false;
	}
	
	$scope.drawCanvas = function() {
		
		if($scope.reservationDateTime == undefined || $scope.reservationDateTime == null 
				|| $scope.reservationDuration == undefined || $scope.reservationDuration == null) {
			toastr.options.timeOut = 1500;
			toastr.warning("Izaberite datum, vreme i trajanje rezervacije.");
			return;
		}
		
		var selectedDate = new Date($scope.reservationDateTime);
		var now = new Date();
		
    	var hours = selectedDate.getHours();
    	if(hours == 0)
    		hours = 24;
    	var selectedMinutes = selectedDate.getMinutes() + hours * 60;
    	hours = now.getHours();
    	if(hours == 0)
    		hours = 24;
    	var nowMinutes = now.getMinutes() + hours * 60;
    	
    	if(selectedDate.getDate() == now.getDate() && selectedDate.getMonth() == now.getMonth() && selectedDate.getFullYear() == now.getFullYear()) {
    		if(selectedMinutes - nowMinutes < 30) {
    			toastr.options.timeOut = 1500;
    			toastr.error("Rezervacije se moze izvrsiti najkasnije 30 minuta ranije, a najvise mesec dana unapred.");
    			return;
    		}
    	}
    	//else if(selectedDate.getDate() > )
    	if(Math.round((selectedDate-now)/(1000*60*60*24)) > 30 || Math.round((selectedDate-now)/(1000*60*60*24)) < 0) {
    		toastr.options.timeOut = 1500;
			toastr.error("Rezervacije se moze izvrsiti najkasnije 30 minuta ranije, a najvise mesec dana unapred.");
			return;
    	}
    	
    	var duration = $scope.reservationDuration;
    	
    	
		
		$scope.reserved = [];
		$scope.tablesReservedThen = [];
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
    	$scope.restaurantTables.forEach(function(table) {
    			x = parseFloat(table.xCoord)*30 - 30;
 		    	y = parseFloat(table.yCoord)*30 - 30;
 		    	 context.globalAlpha = 1.0;
 		    	context.fillStyle="#000000";
 		    	context.font="20px Arial";
 			    context.fillText(table.tag, x + 8, y + 22);
		  });
    	
    	$scope.restaurantReservations.forEach(function(reservation) {
    			var resDate = new Date(reservation.reservationDateTime);
    			hours = resDate.getHours();
    			if(hours == 0)
    				hours = 24;
    			var resMinutes = resDate.getMinutes() + hours * 60;
    			var resDuration = reservation.duration;
    			var check = true;
    			if(resDate.getDate() == selectedDate.getDate() && resDate.getMonth() == selectedDate.getMonth() && resDate.getFullYear() == selectedDate.getFullYear()) {
    				for(var i = 0; i <= duration; i ++){
    					console.log(selectedMinutes + i*60)
    					console.log(resMinutes + resDuration * 60)
    					console.log(selectedMinutes + i*60)
    					console.log(resMinutes)
    					if((selectedMinutes + i*60) <= (resMinutes + resDuration * 60) && (selectedMinutes + i*60) >= resMinutes) {
    						check = false;
    						break;
    					}
    				}
    			}
    			if(!check) {
    				reservation.tables.forEach(function(table) {
    					$scope.tablesReservedThen.push(table);
    					x = parseFloat(table.xCoord)*30 - 30;
    	 		    	y = parseFloat(table.yCoord)*30 - 30;
    					context.globalAlpha = 0.2;
					    context.fillStyle="#FF0000";
					    context.fillRect(x,y,30,30);
					    context.globalAlpha = 1.0;
    				})
    			}
    	})
    	
    	$scope.showCanvas = true;
	}
	
	$scope.goToReservations = function() {
		toastr.options.timeOut = 1500;
		$scope.showCanvas = false;
		$scope.reservationDateTime = undefined;
		$scope.$parent.reservationDuration = null;
		if($scope.idSelected == null) {
			toastr.warning('Morate izabrati restoran.');
		}
		else {
			$scope.$parent.showRestaurants = false;
			$scope.$parent.showReservations = true;
			
			restaurantsService.getRestaurantMenu($scope.$parent.selectedRestaurant).then(function(data) {
				$scope.menu = data;
				console.log(data)
			});
			
			restaurantsService.loadRestaurantTables($scope.$parent.selectedRestaurant.id).then(function(data) {
				$scope.$parent.restaurantTables = data;
			});
			restaurantsService.loadRestaurantReservations($scope.$parent.selectedRestaurant.id).then(function(data) {
				$scope.$parent.restaurantReservations = data;
			});
		}
			
	}
	
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
		    
		    $scope.tablesReservedThen.forEach(function(table) {
				if(table.xCoord == xIndex && table.yCoord == yIndex) {
					return;
				}
			})
			
			startX = x/30.0 + 1;
		    startY = y/30.0 + 1;
		    startX = Math.floor(startX)*30 -30;
		    startY = Math.floor(startY)*30 -30;
			
			$scope.restaurantTables.forEach(function(table) {
				if(table.xCoord == xIndex && table.yCoord == yIndex && $scope.reserved.indexOf(table) == -1) {
					context.globalAlpha = 0.3;
					context.fillStyle="#7d9e7d";
					context.fillRect(startX, startY, 30, 30);
					$scope.reserved.push(table);
				}
			})
			
	     
	        console.log("x: " + x + " y: " + y);
	  }
	
	
	$scope.selectRestaurant = function (restaurant) {
		$scope.$parent.selectedRestaurant = restaurant;
		if($scope.idSelected == restaurant.id)
			$scope.idSelected = null;
		else
			$scope.idSelected = restaurant.id;
	};

} ]);



angular.module('app').filter('doesntContain', function() {
	  return function (guest, potFriend, what) {
		  check = 'add';
		  guest.friends.forEach(function(friend) {
			  if(friend.id == potFriend.id) {
				  check = 'remove';
			  }
		  });
		  guest.friendRequests.forEach(function(request) {
			  if(request.id == potFriend.id) {
				  check = 'requestRecieved';
			  }
		  });
		  potFriend.friendRequests.forEach(function(request) {
			  if(request.id == guest.id) {
				  check = 'requestSent';
			  }
		  });
		  if(what === check)
			  return true;
		  else
			  return false;
	  };
	});

app.controller('friendsController', [ '$scope', 'guestService', function($scope, guestService) {

	$scope.sortType = 'name';
	$scope.sortReverse = false;
	$scope.checkGuest = '';

	$scope.searchInput = "";

	$scope.addFriend = function(friend) {
		guestService.addFriend($scope.$parent.guest, friend.id);
		friend.friendRequests.push($scope.$parent.guest);
	}
	$scope.removeFriend = function(friend) {
		guestService.removeFriend($scope.$parent.guest, friend.id);
		$scope.$parent.guest.friends.forEach(function(f, index) { 
				if(f.id === friend.id) {
					$scope.$parent.guest.friends.splice(index,1);
				}
		});
	}
	
	$scope.acceptRequest = function(friendRequest) {
		guestService.acceptRequest($scope.$parent.guest, friendRequest.id);
		$scope.$parent.guest.friendRequests.forEach(function(f, index) { 
			if(f.id === friendRequest.id) {
				$scope.$parent.guest.friendRequests.splice(index,1);
			}
		});
		$scope.$parent.guest.friends.push(friendRequest);
	}
	
	$scope.declineRequest = function(friendRequest) {
		guestService.declineRequest($scope.$parent.guest, friendRequest.id);
		$scope.$parent.guest.friendRequests.pop(friendRequest);
		$scope.$parent.guest.friendRequests.forEach(function(f, index) { 
			if(f.id === friendRequest.id) {
				$scope.$parent.guest.friendRequest.splice(index,1);
			}
	});
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

angular.module('app').filter('invited', function() {
	  return function (friend, invites) {
		  check = true;
		  invites.forEach(function(invite) {
			  if(invite.friend.id == friend.id) {
				 check = false;
			  }
		  });
		  
		  return check;
		 
	  };
	});

app.controller('reservationsController', [ '$scope', 'guestService', function($scope, guestService) {
	
	$scope.reservationId = null;
	$scope.reservation = null;
	
	$scope.cancelReservation = function() {
		if($scope.reservationId == null) {
			toastr.options.timeOut = 1500;
			toastr.warning("Potrebno je izabrati rezervaciju.");
			return;
		}
		
		var resDate = new Date($scope.reservation.reservationDateTime);
		var now = new Date();
		if(resDate.getDate() == now.getDate() && resDate.getMonth() == now.getMonth() && resDate.getFullYear() == now.getFullYear()) {

			var hours = resDate.getHours();
			if(hours == 0)
				hours = 24;
			var resMinutes = resDate.getMinutes() + hours * 60;
			hours = now.getHours();
			if(hours == 0)
				hours = 24;
			var nowMinutes = now.getMinutes() + hours * 60;
			if(nowMinutes - resMinutes < 30) {
				toastr.options.timeOut = 1500;
				toastr.error("Ne mozete otkazati rezervaciju. Moze se otkazati najkasnije 30 minuta pre zakazanog termina");
				return;
			}
			
		}
		
		
		guestService.cancelReservation($scope.reservation);
		$scope.$parent.guestReservations.pop($scope.reservation);
	}
	
	$scope.selectReservation = function (reservation) {
		$scope.reservation = reservation;
		if($scope.reservationId == reservation.id)
			$scope.reservationId = null;
		else
			$scope.reservationId = reservation.id;
	};
	
	$scope.openFriends = function() {
		if($scope.reservationId == null) {
			toastr.options.timeOut = 1500;
			toastr.warning("Izaberite rezervaciju.");
		}
		else
		{
			
			$('#myModal').modal({ show: false})
			angular.element('#inviteFriendsModal').modal('show');
		}
	}
	
	$scope.inviteFriend = function(friend) {
		guestService.inviteFriend($scope.$parent.guest, friend, $scope.reservation).then(function(data) {
			console.log(data);
			guestService.loadGuestInvites().then(function(data) {
				$scope.$parent.invites = data;
			});
		});
	}

} ]);

app.controller('guestPageController', [ '$scope', 'restaurantsService', 'guestService', 
		function($scope, restaurantsService, guestService) {
			
			$scope.showRestaurants = false;
			$scope.showFriends = false;
			$scope.showProfile = false;
			$scope.showReservations = false;
			$scope.showGuestReservations = false;
			$scope.selectedRestaurant = null;
			$scope.invites = []
			
			$scope.loadRestaurants = function() {
				restaurantsService.loadRestaurants().then(function(data) {
					$scope.restaurants = data;
				});
				guestService.loadGuest().then(function(data) {
					$scope.guest = data;
				});
				$scope.showRestaurants = true;
				$scope.showFriends = false;
				$scope.showProfile = false;
				$scope.showReservations = false;
				$scope.showGuestReservations = false;
			}

			$scope.loadUserFriends = function() {
				guestService.loadGuest().then(function(data) {
					$scope.guest = data;
				});
				$scope.showRestaurants = false;
				$scope.showFriends = true;
				$scope.showProfile = false;
				$scope.showReservations = false;
				$scope.showGuestReservations = false;
			}
			
			$scope.userLogout = function() {
				guestService.logout();
			}

			$scope.loadGuestReservations = function() {
				guestService.loadGuestReservations().then(function(data) {
					$scope.guestReservations = data;
				});
				guestService.loadGuest().then(function(data) {
					$scope.guest = data;
				});
				guestService.loadGuestInvites().then(function(data) {
					$scope.invites = data;
				});
				$scope.showRestaurants = false;
				$scope.showFriends = false;
				$scope.showProfile = false;
				$scope.showReservations = false;
				$scope.showGuestReservations = true;
			}
			
			$scope.loadUserProfile = function() {
				guestService.loadGuest().then(function(data) {
					$scope.guest = data;
					$scope.newGuest = angular.copy($scope.guest);
				});
				$scope.showRestaurants = false;
				$scope.showFriends = false;
				$scope.showProfile = true;
				$scope.showReservations = false;
				$scope.showGuestReservations = false;
			}

		} ]);

app.service('guestService', [ '$http', '$window', function($http, $window) {
	
	this.inviteFriend = function(guest, friend, reservation) {
		var invite = {"guest" : guest, "friend" : friend, "reservation" : reservation, "accepted" : false}
		return $http({
			method : 'POST',
			url : "../guest/inviteFriend",
			data : invite
		}).then(function success(response) {
			return response.data;
		}, function error(response) {
			alert(response)
		});    
	}
	
	this.cancelReservation = function(reservation) {
		return $http({
			method : 'POST',
			url : "../guest/cancelReservation",
			data : reservation
		}).then(function success(response) {
			return response.data;
		}, function error(response) {
			alert(response)
		});            
	}
	
	this.loadGuestReservations = function() {
		user = JSON.parse($.cookie("user"));
		return $http({
			method : 'POST',
			url : "../guest/loadGuestReservations/" + user.id
		}).then(function success(response) {
			return response.data;
		}, function error(response) {
			alert(response)
		});                                                                                                                                   
	}
	
	this.loadGuestInvites = function() {
		user = JSON.parse($.cookie("user"));
		return $http({
			method : 'POST',
			url : "../guest/loadGuestInvites/" + user.id
		}).then(function success(response) {
			return response.data;
		}, function error(response) {
			alert(response)
		});                                                                                                                                   
	}
	
	this.addFriend = function(guest, friend) {
		$http({
			method : 'POST',
			url : "../guest/addFriend/" + guest.id,
			data: friend
		}).then(function success(response) {
			return response.data;
		}, function error(response) {
			alert(response)
		});
	}
	
	this.acceptRequest = function(guest, friend) {
		$http({
			method : 'POST',
			url : "../guest/acceptRequest/" + guest.id,
			data: friend
		}).then(function success(response) {
			return response.data;
		}, function error(response) {
			alert(response)
		});
	}
	
	this.declineRequest = function(guest, friend) {
		$http({
			method : 'POST',
			url : "../guest/declineRequest/" + guest.id,
			data: friend
		}).then(function success(response) {
			return response.data;
		}, function error(response) {
			alert(response)
		});
	}
	
	this.removeFriend = function(guest, friend) {
		$http({
			method : 'POST',
			url : "../guest/removeFriend/" + guest.id,
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

app.service('restaurantsService', [ '$http', '$window',
		function($http, $window) {
	
			this.getRestaurantMenu = function(restaurant) {
				return $http({
					method : 'POST',
					url : "../restaurant/getRestaurantMenu",
					data: restaurant
				}).then(function success(response) {
					return response.data;
				}, function error(response) {
					alert(response)
				});
			}
			
			this.order = function(order) {
				return $http({
					method : 'POST',
					url : "../restaurant/order",
					data: order
				}).then(function success(response) {
					return response.data;
				}, function error(response) {
					alert(response)
				});
			}

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
			
			this.loadRestaurantReservations = function(restaurantId) {
				return $http({
					method : 'POST',
					url : "../restaurant/loadRestaurantReservations/" + restaurantId
				}).then(function success(response) {
					return response.data;
				}, function error(response) {
					alert(response)
				});
			}
			
			this.loadRestaurantTables = function(restaurantId) {
				return $http({
					method : 'POST',
					url : "../restaurant/loadRestaurantTables/" + restaurantId
				}).then(function success(response) {
					return response.data;
				}, function error(response) {
					alert(response)
				});
			}
			
			this.reserveTables = function(selectedRestaurant, guest, reservationDateTime, reservationDuration, reserved) {
				var reservation = {
						"restaurant" : selectedRestaurant,
						"guest" : guest,
						"tables" : reserved,
						"reservationDateTime" : reservationDateTime,
						"duration" : reservationDuration
					}
				return $http({
					method : 'POST',
					url : "../restaurant/reserveTables",
				    data : reservation
				}).then(function success(response) {
					return response.data;
				}, function error(response) {
					alert(response)
				});
			}
} ]);


