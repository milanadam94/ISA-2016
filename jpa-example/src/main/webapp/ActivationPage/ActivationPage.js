var app = angular.module('app', []).config(function ($httpProvider) {
    $httpProvider.defaults.headers.put['Content-Type'] = 'application/x-www-form-urlencoded';
    $httpProvider.defaults.headers.post['Content-Type'] =  'application/x-www-form-urlencoded';
}).run(['$rootScope', '$http', '$window', function ($rootScope, $http, $window) {
	if (typeof $.cookie('user') !== "undefined") {
		//$window.location.href = "/StartPage/StartPage.html"
		
		// AKO SI ULOGOVAN NE MOZES VRSITI REGISTRACIJU, REDIREKCIJA NA TVOJ PAGE
	}
}]);

app.controller('activationPageControler', [ '$scope', '$window', function($scope, $window){
	
	$scope.toHomePage = function() {
		$window.location.href = "/StartPage/StartPage.html"
	}
}]);