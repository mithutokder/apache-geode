var app = angular.module('app', ['ngMessages']);

app.controller('UserCtrl', ['$scope','UserService', function ($scope,UserService) {
	
	$scope.submitted = false;
	
	$scope.getUsers = function() {
		   UserService.getUsers().then( function(data){
		       $scope.users = data;
	       });
	   }
   $scope.getUsers();
   
   $scope.resetForm = function () {
	   $scope.userForm.$setPristine();
	   $scope.user=null;
	   $scope.message='';
	   $scope.errorMessage='';
	   $scope.submitted = false;
   }
    
}]);

app.service('UserService',['$http', function ($http) {
	
    this.getUsers = function getUsers(){
        return $http({
          method: 'GET',
          url: 'users',
          headers:'Accept:application/json'
        }).then( function(response){
        	return response.data;
        } );
    }

}]);