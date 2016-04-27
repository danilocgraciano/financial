var app = angular.module('type',[], ['$routeProvider', function($routeProvider){

	$routeProvider.when('/types', {
		templateUrl:'static/js/app/components/type/typeList.html',
		controller:'TypeListController'
	}).when('/types/:typeId', {
        templateUrl: 'static/js/app/components/type/typeForm.html',
        controller: 'TypeFormController'
    }).otherwise({
        redirectTo: '/types'
    });
}]);

app.factory('TypeFactory', function($resource) {

	return $resource('/financial/api/types/:typeId',{},{
		update : {
			method : 'PUT'
		}
	});
});

app.controller('TypeListController', function($scope,TypeFactory){
	
	load();
	
	function load(){
		TypeFactory.query().$promise.then(function(data) {
			$scope.types = data;
		},function(errorMsg){
			alert(errorMsg.data.reason);
		});
	};
	
	
	$scope.remove = function(data){
		
		var resp = confirm('Confirm delete? ['+data.description+']');
		if (resp == true) {
			TypeFactory.remove({ typeId: data.id }).$promise.then(function(data) {
				load();
			});
			
		} 
	};
	
	
});

app.controller('TypeFormController', function($scope, $routeParams, $location, TypeFactory) {
	
	$scope.type = TypeFactory.get({ typeId: $routeParams.typeId });
	
	$scope.submit = function(){
		if ($scope.type.id === undefined){
			TypeFactory.save($scope.type).$promise.then(function(data) {
				$location.path('/types');
			},function(errorMsg){
				alert(errorMsg.data.reason);
			});
		}else{
			TypeFactory.update($scope.type).$promise.then(function(data) {
				$location.path('/types');
			},function(errorMsg){
				alert(errorMsg.data.reason);
			});
		}
		
		
	};
});