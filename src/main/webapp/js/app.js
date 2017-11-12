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
    .controller('EmployeeDetailsController', function ($scope, $http, SpringDataRestAdapter,$filter) {
    		
    	
    	   $scope.invalidManagerId = false;
    	   $scope.isManager = false;
    	   $scope.managerAlreadyExist = false;
    	   var employeeDetails ={};
    	   $scope.employeeDetails = employeeDetails;
    	   
    	   var employeeIdList =[];
    	   $scope.employeeIdList = employeeIdList;

    	   var employeeDetailsList = [];
    	   $scope.employeeDetailsList = employeeDetailsList;
    	   
    	   $scope.loadEmployeeHierarchy = function()  {
    		   var request   = {
          			    "viewEmployeeHierarchyRequest": {
          			        "limitHeirarchy": "10"
          			    }
          	   };
          	   
          	        	   
          	  $http.post('/api/v0/employeeDetails/fetchEmpHierarchy',request, {
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
    	   
    	   $scope.clearForm = function()  {
    		   $scope.employeeDetails ={};
    		   $scope.invalidManagerId = false;
    	   };
    	   
    	   $scope.initialize = function()  {
    		   
    		console.log("initialize");
    	    var httpPromise = $http.get('/api/v0/employeeDetails/initialize')
	       		.success(function (response) {
	       			console.log("initialize");
	       	  	   $scope.fetchAll();
	       		});
    	        
    	   };
    	   $scope.fetchAll = function()  {
    		   
       		$scope.managerAlreadyExist = false;
       	    var httpPromise = $http.get('/api/v0/employeeDetails/fetchAll')
   	       		.success(function (response) {
   	       			$scope.employeeDetailsList = response;
   	       			$scope.employeeDetailsList.forEach(function (item) {
   	       				$scope.employeeIdList.push(item.employeeId);
   	       				if(item.managerId === null){
   	       					$scope.managerAlreadyExist = true;
   	       				}
   	       			});
   	       			$scope.employeeIdList.push('');
   	       		});
       	        
       	   };
       	   
       	 $scope.clearData = function()  {
  		   
        	    var httpPromise = $http.get('/api/v0/employeeDetails/clearDb')
    	       		.success(function (response) {
    	       			$scope.fetchAll();
    	       		});
        	        
        	   };
     	   
    	   
    	   $scope.saveEmpDetails = function(isValid) {
    		   if($scope.isManager || $scope.employeeIdList.indexOf($scope.employeeDetails.managerId) !== -1){
    			   
    			   $http.post('/api/v0/employeeDetails/create',$scope.employeeDetails, {
                       headers : {'API-KEY': 'xJ9a34fo' }
                 }).then(onSuccess, onError);
             	   
             	   function onSuccess(data) {
    	         		$scope.employeeDetails = {};
    	         		$scope.invalidManagerId = false;
    	         		$scope.isManager = false;
    	         		$scope.fetchAll();
                 }
                    
             	   function onError(data){
             	   } 
    		   }else{
    			   $scope.invalidManagerId = true;
    		   }
    	   }
       	   
       

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
                        '<tr  style="width:100%; height:30%;">' +
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
