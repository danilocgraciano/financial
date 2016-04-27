var app = angular.module('financial',['ngRoute','ngResource','login','user','type']);

app.run(function($http,$window){
	$http.defaults.headers.common['Auth-Token'] = $window.sessionStorage.getItem('financialToken');
});