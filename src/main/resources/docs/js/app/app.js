'use strict';

var theModule = angular.module('SinglePageMath', [
      'ngResource'
    ]);

function MathController($scope, $resource, $location){
    console.log("MathController() created.")
    $scope.number1
    $scope.number2
    $scope.operation
    $scope.answer
    $scope.answers =[]

   $scope.operations = [
        {symbol:"+", name:"add"},
        {symbol:"-", name:"subtract"},
        {symbol:"*", name:"multiply"},
        {symbol:"/", name:"divide"},
        {symbol:"%", name:"modulo"},
        {symbol:"^", name:"exponent"}
    ]

    console.log("MathController() creating REST resource.");
    $scope.restSvc = $resource('/:operation/:number1/:number2', {});
    console.log("MathController() REST resource created!");
    //$scope.restSvc =  $resource($location.protocol()+"://"+$location.host()+":"+$location.port() )
    $scope.doCompute = function( ){
        console.log("MathController.doCompute() called.")
        if(!$scope.number1 || !$scope.number2 || !$scope.operation){
            return false
        }
        var response =$scope.restSvc.get({operation:$scope.operation.name, number1:$scope.number1, number2:$scope.number2},function(){
                $scope.answers.push(response)
        })
        return false
    }

    console.log("MathController() DONE!")
    return this;
  };
MathController.$inject = ["$scope", "$resource", "$location"]

theModule.config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/index.html',
        controller: "MathController"
      })
      .otherwise({
        redirectTo: '/'
      });
  });
