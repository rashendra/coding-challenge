'use strict';

var app = angular.module('coding-challenge-app', ['ui.bootstrap', 'ngResource', 'ngRoute', 'hljs', 'spring-data-rest'])
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider.
            when('/', {
                templateUrl: 'partials/main.html'
            }).otherwise({
                redirectTo: '/'
            });
    }])
    .controller('NavigationController', function ($scope, $location) {
        $scope.navigateTo = function (path) {
            $location.path(path);
        };
        $scope.isCodeCollapsed = true;
        $scope.isResponseCollapsed = true;
        $scope.isProcessedResponseCollapsed = true;
    })
    .controller('EmployeeDetailsController', function ($scope, $http, SpringDataRestAdapter) {
    		
    	      
    	   $scope.loadEmployeeHierarchy = function()  {
    		   var request   = {
          			    "viewEmployeeHierarchyRequest": {
          			        "limitHeirarchy": "10"
          			    }
          	   };
          	   
          	        	   
          	   $http.post('/api/v0/employeeHierarchy/fetch',request, {
                    headers : {'API-KEY': 'xJ9a34fo' }
              }).then(onSuccess, onError);
          	   
          	   function onSuccess(data) {
          		   $scope.employeeRepresentation = data.data.viewEmployeeHierarchyResponse.employeeRepresentationDTO;
              }
                 
          	   function onError(data){
          	   	   console.log(data);
          	   }
    	   };
    	   
    	   $scope.clear = function()  {
    		   $scope.employeeRepresentation = '';
    	   };
    	   
    	   $scope.initialize = function()  {
    		   console.log("initialize");
    	        var httpPromise = $http.get('/api/v0/employeeHierarchy/initialize')
       		.success(function (response) {
       			console.log("initialize");
       		});
    	   };
       	   
       

        console.log("line 45");
    })
    .directive('tree', function ($compile) {
        return {
            restrict: 'E',
            link: function (scope, element, attrs) {
                var parent = attrs.parent;
                var searchQry = attrs.search;
                console.log(parent);
                var template =
                    ' <table class="table table-striped table-bordered" id="nested-table">' + 
                        '<tr  style="width:100%">' +
                          '<td style="width:50%">{{ '+parent+'.employeeName  }}</td>' +
                        '</tr>' +
                        '<tr style="width:100%" ng-repeat="sub in '+parent+'.subs">' +
                           '<td style="width:50%"></td><td><tree parent="sub"></tree></td>' +  
                        '</tr>' +
                      '</table>';
                
                var noRecordsTemplate ='<h2>No records found - Please initialize employees </h2>';
                
                element.html('').append($compile(template)(scope));
            }
        };
    });
