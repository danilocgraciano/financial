var app = angular.module('user',[], ['$routeProvider', function($routeProvider){

	$routeProvider.when('/users', {
		templateUrl:'static/js/app/components/user/userList.html',
		controller:'UserListController'
	}).when('/users/:userId', {
        templateUrl: 'static/js/app/components/user/userForm.html',
        controller: 'UserFormController'
    }).otherwise({
        redirectTo: '/users'
    });
}]);

app.factory('UserFactory', function($resource) {

	return $resource('/financial/api/users/:userId',{},{
		update : {
			method : 'PUT'
		}
	});
});

app.controller('UserListController', function($scope,UserFactory){
	
	load();
	
	function load(){
		UserFactory.query().$promise.then(function(data) {
			$scope.users = data;
		},function(errorMsg){
			alert(errorMsg.data.reason);
		});
	};
	
	
	$scope.remove = function(data){
		
		var resp = confirm('Confirm delete? ['+data.nome+']');
		if (resp == true) {
			UserFactory.remove({ userId: data.id }).$promise.then(function(data) {
				load();
			});
			
		} 
	};
	
	
});

app.controller('UserFormController', function($scope, $routeParams, $location, UserFactory) {
	
	$scope.user = UserFactory.get({ userId: $routeParams.userId });
	
	$scope.submit = function(){
		if ($scope.user.id === undefined){
			UserFactory.save($scope.user).$promise.then(function(data) {
				$location.path('/users');
			},function(errorMsg){
				alert(errorMsg.data.reason);
			});
		}else{
			UserFactory.update($scope.user).$promise.then(function(data) {
				$location.path('/users');
			},function(errorMsg){
				alert(errorMsg.data.reason);
			});
		}
		
		
	};
});