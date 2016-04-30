var app = angular.module('invoice',[], ['$routeProvider', function($routeProvider){

	$routeProvider.when('/invoices', {
		templateUrl:'static/js/app/components/invoice/invoiceList.html',
		controller:'InvoiceListController'
	}).when('/invoices/:invoiceId', {
        templateUrl: 'static/js/app/components/invoice/invoiceForm.html',
        controller: 'InvoiceFormController'
    }).otherwise({
        redirectTo: '/invoices'
    });
}]);

app.factory('InvoiceFactory', function($resource) {

	return $resource('/financial/api/invoices/:invoiceId',{},{
		update : {
			method : 'PUT'
		}
	});
});

app.controller('InvoiceListController', function($scope,InvoiceFactory){
	
	load();
	
	function load(){
		InvoiceFactory.query().$promise.then(function(data) {
			$scope.invoices = data;
		},function(errorMsg){
			alert(errorMsg.data.reason);
		});
	};
	
	
	$scope.remove = function(data){
		
		var resp = confirm('Confirm delete? ['+data.partner + ' - ' + data.value+']');
		if (resp == true) {
			InvoiceFactory.remove({ invoiceId: data.id }).$promise.then(function(data) {
				load();
			});
			
		} 
	};
	
	
});

app.controller('InvoiceFormController', function($scope, $routeParams, $location, $filter, InvoiceFactory, TypeFactory, PartnerFactory) {
	
	init();
	
	function init(){
		
		InvoiceFactory.get({ invoiceId: $routeParams.invoiceId }).$promise.then(function(data) {
			$scope.invoice = data;
		},function(errorMsg){
			alert(errorMsg.data.reason);
		});
		
		TypeFactory.query().$promise.then(function(data) {
			$scope.types = data;
		},function(errorMsg){
			alert(errorMsg.data.reason);
		});
		
		PartnerFactory.query().$promise.then(function(data) {
			$scope.partners = data;
		},function(errorMsg){
			alert(errorMsg.data.reason);
		});
		
		
	}
	
	
	
	$scope.submit = function(){
		if ($scope.invoice.id === undefined){
			InvoiceFactory.save($scope.invoice).$promise.then(function(data) {
				$location.path('/invoices');
			},function(errorMsg){
				alert(errorMsg.data.reason);
			});
		}else{
			InvoiceFactory.update($scope.invoice).$promise.then(function(data) {
				$location.path('/invoices');
			},function(errorMsg){
				alert(errorMsg.data.reason);
			});
		}
		
		
	};
});