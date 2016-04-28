var app = angular.module('partner',[], ['$routeProvider', function($routeProvider){

	$routeProvider.when('/partners', {
		templateUrl:'static/js/app/components/partner/partnerList.html',
		controller:'PartnerListController'
	}).when('/partners/:partnerId', {
        templateUrl: 'static/js/app/components/partner/partnerForm.html',
        controller: 'PartnerFormController'
    }).otherwise({
        redirectTo: '/partners'
    });
}]);

app.factory('PartnerFactory', function($resource) {

	return $resource('/financial/api/partners/:partnerId',{},{
		update : {
			method : 'PUT'
		}
	});
});

app.controller('PartnerListController', function($scope,PartnerFactory){
	
	load();
	
	function load(){
		PartnerFactory.query().$promise.then(function(data) {
			$scope.partners = data;
		},function(errorMsg){
			alert(errorMsg.data.reason);
		});
	};
	
	
	$scope.remove = function(data){
		
		var resp = confirm('Confirm delete? ['+data.name+']');
		if (resp == true) {
			PartnerFactory.remove({ partnerId: data.id }).$promise.then(function(data) {
				load();
			});
			
		} 
	};
	
	
});

app.controller('PartnerFormController', function($scope, $routeParams, $location, PartnerFactory) {
	
	$scope.partner = PartnerFactory.get({ partnerId: $routeParams.partnerId });
	
	$scope.submit = function(){
		if ($scope.partner.id === undefined){
			PartnerFactory.save($scope.partner).$promise.then(function(data) {
				$location.path('/partners');
			},function(errorMsg){
				alert(errorMsg.data.reason);
			});
		}else{
			PartnerFactory.update($scope.partner).$promise.then(function(data) {
				$location.path('/partners');
			},function(errorMsg){
				alert(errorMsg.data.reason);
			});
		}
		
		
	};
});