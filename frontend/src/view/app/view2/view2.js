'use strict';

angular.module('myApp.view2', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/view2', {
    templateUrl: 'view2/view2.html',
    controller: 'View2Ctrl'
  });
}])

.controller('View2Ctrl', [function() {

}]);

angular.module('myApp.view2', [])
    .controller('ScrollController', ['$scope', '$location', '$anchorScroll',
      function($scope, $location, $anchorScroll) {
        $scope.gotoBottom = function() {
          // set the location.hash to the id of
          // the element you wish to scroll to.
          $location.hash('bottom');

          // call $anchorScroll()
          $anchorScroll();
        };
      }]);

angular.module("myApp.view2", ["chart.js"]).controller("BarController", function ($scope) {
        $scope.labels = ['2006', '2007', '2008', '2009', '2010', '2011', '2012'];
        $scope.series = ['Series A', 'Series B'];
        $scope.data = [ [65, 59, 80, 81, 56, 55, 40], [28, 48, 40, 19, 86, 27, 90] ];
});
