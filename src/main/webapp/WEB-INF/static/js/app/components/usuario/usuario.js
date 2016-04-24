var app = angular.module('usuario',[], ['$routeProvider', function($routeProvider){

	$routeProvider.when('/usuarios', {
		templateUrl:'static/js/app/components/usuario/usuarioList.html',
		controller:'UsuarioListController'
	}).when('/usuarios/:usuarioId', {
        templateUrl: 'static/js/app/components/usuario/usuarioForm.html',
        controller: 'UsuarioFormController'
    }).otherwise({
        redirectTo: '/usuarios'
    });
}]);

app.factory('UsuarioFactory', function($resource) {

	return $resource('/financial/api/usuarios/:usuarioId',{},{
		update : {
			method : 'PUT'
		}
	});
});

app.controller('UsuarioListController', function($scope,UsuarioFactory){
	
	$scope.usuarios = [];
	
	$scope.load = function(){
		$scope.usuarios = UsuarioFactory.query();
	};
	
	$scope.remove = function(data){
		
		var resp = confirm('Deseja realmente excluir ['+data.nome+']');
		if (resp == true) {
			UsuarioFactory.remove({ usuarioId: data.id }).$promise.then(function(data) {
				$scope.load();
			});
			
		} 
	};
	
	
});

app.controller('UsuarioFormController', function($scope, $routeParams, $location, UsuarioFactory) {
	
	$scope.user = UsuarioFactory.get({ usuarioId: $routeParams.usuarioId });
	
	$scope.submit = function(){
		if ($scope.user.id === undefined){
			UsuarioFactory.save($scope.user).$promise.then(function(data) {
				$location.path('/usuarios');
			},function(errorMsg){
				alert(errorMsg.data.reason);
			});
		}else{
			UsuarioFactory.update($scope.user).$promise.then(function(data) {
				$location.path('/usuarios');
			},function(errorMsg){
				alert(errorMsg.data.reason);
			});
		}
		
		
	};
});