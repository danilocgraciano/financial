var app = angular.module('financial',['ngRoute','ngResource','login','user','type','partner','invoice','ufCombo','ui.bootstrap']);

app.run(function($http,$window){
	$http.defaults.headers.common['Auth-Token'] = $window.sessionStorage.getItem('financialToken');
});

app.controller('DatepickerPopupDemoCtrl', function ($scope) {
	  
	  $scope.format = 'dd/MM/yyyy';
	  
	  $scope.open1 = function() {
	    $scope.popup1.opened = true;
	  };

	  $scope.popup1 = {
	    opened: false
	  };

});

