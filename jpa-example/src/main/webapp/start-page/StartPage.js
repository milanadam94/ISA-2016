angular.element(document).ready(function () {
    console.log('page loading completed');
});

var app = angular.module('app', []);
						 
app.controller('startPageController', function($scope, loginService){
	
	$scope.user = {
			email : "",
			password : ""
	}

	$scope.login = function(){
		loginService.login($scope.user);
	}
		 
});

app.service('loginService', function(){
	
	 this.login = function(user) {
		 $.ajax({
				type : "POST",
				data : user,
				url : "../user/login",
				success : function(data) {
					alert(data);
				},
				error : function(data) {
					alert(data);
				}
			});
	   }
	 
});
