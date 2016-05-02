var app = angular.module('financial',['ngRoute','ngResource','ui.bootstrap','login','user','type','partner','invoice','inputDate','ufCombo']);

app.run(function($http,$window){
	$http.defaults.headers.common['Auth-Token'] = $window.sessionStorage.getItem('financialToken');
});

