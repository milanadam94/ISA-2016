var waiter = angular.module('waiter', []).config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] =  'application/x-www-form-urlencoded';
})

waiter.controller('waiterContoller', [ '$scope', 'waiterService', function($scope, waiterService){
	
	$scope.viewProfile = false;
	
	$scope.getProfile = function(){
		$scope.viewProfile = true;
		waiterService.getWaiter().then(
				function(response){
					$scope.waiter = response.data;
					console.log($scope.waiter.birthday);
				}
		)
	}
		
}]);

waiter.service('waiterService', ['$window', '$http', function($window, $http){
	
	this.getWaiter = function() {
		return $http.get("../worker/waiter/adam94@gmail.com")
	}
//	user = JSON.parse($.cookie("user"));
//	$.cookie("user", undefined, { path: '/', domain: '' });
//	$http({
//		  method: 'PUT',
//		  data : $.param(user),
//	      url : "../user/logout"
//	}).then(function success(response) {
//		
//	  }, function error(response) {
//		  alert(response)
//	  });
	
}]);
	
