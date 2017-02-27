var waiter = angular.module('cook', []).config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] =  'application/x-www-form-urlencoded';
})

waiter.controller('cookController', [ '$scope', 'cookService', function($scope, cookService){
	
	$scope.viewProfile = false;
	
	$scope.getProfile = function(){
		$scope.viewProfile = true;
		/*waiterService.getWaiter().then(
				function(response){
					$scope.waiter = response.data;
					console.log($scope.waiter);
				}
		)
		
		$scope.updateProfile = function() {
			waiterService.saveChanges($scope.waiter);
		}*/
	}
	
		
}]);

waiter.service('cookService', ['$window', '$http', function($window, $http){
	
}]);
