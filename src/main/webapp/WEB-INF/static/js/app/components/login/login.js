var app = angular.module('login',[], ['$routeProvider', function($routeProvider){

	$routeProvider.when('/', {
		templateUrl:'static/js/app/components/login/login.html',
		controller:'LoginController'
	}).when('/account', {
		templateUrl:'static/js/app/components/login/newUserForm.html',
		controller:'LoginController'
	});
}]);

app.service('LoginService', function($http,$window){
	
	this.login = function(data){
		return $http.post('/financial/api/login',data).then(function(response){
			$window.sessionStorage.setItem('financialToken', response.data.token);
			$http.defaults.headers.common['Auth-Token'] = $window.sessionStorage.getItem('financialToken');
			return response;
		});
	};
	
	this.newUser = function(data){
		return $http.post('/financial/api/account',data).then(function(response){
			return response;
		});
	};
	
});

app.controller('LoginController', function($scope, $location, LoginService){
	
	$scope.submit = function(){
		LoginService.login($scope.user).then(function(response){
			$location.path('/users');
		},function(errorMsg){
			alert(errorMsg.data.reason);
		});
	};
	
	$scope.newUser = function(){
		LoginService.newUser($scope.user).then(function(response){
			$location.path('/');
		},function(errorMsg){
			alert(errorMsg.data.reason);
		});
	};
	
});