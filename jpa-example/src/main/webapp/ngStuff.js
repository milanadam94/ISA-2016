
angular.element(document).ready(function () {
    console.log('page loading completed');
});

var app = angular.module('app', [])
						 
app.controller('startPageController', function($scope, validateLoginService){
		
	$scope.guest = {
			email:"",
			password:""
	}	 
	
	$scope.login = function(guest){
		alert("aa");
		validateLoginService.validateLogin(guest);
	}
		 
});

app.service('validateLoginService', function($http){
	
	 this.validateLogin = function(guest) {
		 $.ajax({
				type : "GET",
				url : "../guest/a",
				success : function(data) {
					console.log("SUCCESS: ", data);
				},
				error : function(e) {
					console.log("ERROR: ", e);
				}
			});
		 
	   }
	 
});
