var waiter = angular.module('waiter', []).config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] =  'application/x-www-form-urlencoded';
})

waiter.controller('waiterContoller', [ '$scope', 'waiterService', function($scope, waiterService){
	
	$scope.viewProfile = false;
	
	$scope.getProfile = function(){
		$scope.viewProfile = true;
	}
}]);

waiter.service('waiterService', ['$window', '$http', function($window, $http){
	
}]);
	
