var app = angular.module('financial',['ngRoute','ngResource','login','user','type','partner']);

app.run(function($http,$window){
	$http.defaults.headers.common['Auth-Token'] = $window.sessionStorage.getItem('financialToken');
});
