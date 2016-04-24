var app = angular.module('login',[], ['$routeProvider', function($routeProvider){

	$routeProvider.when('/', {
		templateUrl:'static/js/app/components/login/login.html',
		controller:'LoginController'
	});
}]);

app.service('LoginService', function($http,$window){
	
	this.login = function(data){
		return $http.post('/financial/api/login',data).then(function(response){
			$window.sessionStorage.setItem('financialToken', response.data.token);
			return response;
		});
	};
	
});

app.controller('LoginController', function($scope, $location, LoginService){
	
	$scope.submit = function(){
		LoginService.login($scope.user).then(function(response){
			$location.path('/usuarios');
		},function(errorMsg){
			alert(errorMsg.data.reason);
		});
	};
	
});