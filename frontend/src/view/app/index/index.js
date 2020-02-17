'use strict';

// Declare app level module which depends on views, and core components
angular.module('index', [
  'ngRoute',
  'main',
  'index.version',
  'chart.js'
]).
config(['$locationProvider', '$routeProvider', function($locationProvider, $routeProvider) {
  $locationProvider.hashPrefix('!');

  $routeProvider.otherwise({redirectTo: '/mian'});
}]);
