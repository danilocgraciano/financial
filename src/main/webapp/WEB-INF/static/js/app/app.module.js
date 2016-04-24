var app = angular.module('financial',['ngRoute','ngResource','login','usuario']);

app.run(function($http,$window){
	$http.defaults.headers.common['Auth-Token'] = $window.sessionStorage.getItem('financialToken');
});