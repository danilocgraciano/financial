var app = angular.module('financial',['ngRoute','ngResource','login','user','type','partner','invoice','ufCombo','ui.bootstrap']);

app.run(function($http,$window){
	$http.defaults.headers.common['Auth-Token'] = $window.sessionStorage.getItem('financialToken');
});

