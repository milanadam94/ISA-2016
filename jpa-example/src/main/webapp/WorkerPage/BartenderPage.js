var waiter = angular.module('bartender', []).config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] =  'application/x-www-form-urlencoded';
})

waiter.controller('bartenderController', [ '$scope', 'bartenderService', function($scope, cookService){
	
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

waiter.service('bartenderService', ['$window', '$http', function($window, $http){
	
}]);