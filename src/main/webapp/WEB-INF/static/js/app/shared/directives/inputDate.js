//http://stackoverflow.com/questions/35475552/angular-ui-datepicker-popup-open-without-ng-click
//http://plnkr.co/edit/N5jwYkfvVv4fkxWEhY2G?p=preview
var app = angular.module('inputDate',[]);
app.directive('inputDate', ['$compile',  function($compile) {
	  return {       
		    transclude: true,        
		    template: '<p class="input-group"><input type="text" class="form-control"/><span class="input-group-btn"><button type="button" class="btn btn-default"><i class="glyphicon glyphicon-calendar"></i></button></span></p>',
		    restrict: 'AE',                   
		    link: function ($scope, element, attrs) {
		      $scope.dateInfo = $scope.dateInfo || {};
		      var dateInfo = $scope.dateInfo,
		          input = element.find('input'),    
		          button = element.find('button'),    
		          name = input.name || 'date'+Object.keys($scope.dateInfo).length,
		          info = {
		            open: false,
		            click: function() {
		              this.open = true
		            }
		          }   

		      dateInfo[name] = info;
		      
		      if (!angular.isUndefined(attrs.name)){
		    	  input.attr('name', attrs.name);
		      }
		      
		      if (!angular.isUndefined(attrs.id)){
		    	  input.attr('id', attrs.id);
		      }
		      
		      if (!angular.isUndefined(attrs.required)){
		    	  input.attr('required','true');
		      }
		      
		      //datepicker configuration object
		      $scope.dateOptions = 
		      {
	    		  'startingDay': '1'
		      };
		      
		      
		      input.attr('ng-model', attrs.inputDate);
		      
		      input.attr('uib-datepicker-popup', 'dd/MM/yyyy');
		      
		      //This line is for configuring datepicker appearance 
		      input.attr('datepicker-options', 'dateOptions');
		      input.attr('is-open', 'dateInfo[\"'+name+'\"].open');
		      input.attr('current-text', 'Hoje');
		      input.attr('clear-text', 'Limpar');
		      input.attr('close-text', 'Fechar');
		      input.attr('is-open', 'open.start');
		      input.attr('placeholder', 'DD/MM/AAAA');
		      
		      button.attr('ng-click', 'open.start = true');
//		      button.attr('ng-click', 'dateInfo[\"'+name+'\"].click()');
		      
		      
		      $compile(element.contents())($scope);
		    }   
		  } 
		}]);