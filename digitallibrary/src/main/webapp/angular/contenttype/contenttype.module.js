angular.module('dl.contenttype',['ngRoute',
   ['/dl/angular/lib.js'
    ,'/dl/angular/contenttype/contenttype.controllers.js'
    ,'/dl/angular/contenttype/contenttype.services.js']])
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/dl/contenttype/list', {templateUrl: '/dl/angular/contenttype/contenttype.list.html', controller: 'ContenttypeListCtrl'})
      .when('/dl/contenttype/edit', {templateUrl: '/dl/angular/contenttype/contenttype.edit.html', controller: 'ContenttypeEditCtrl'})
      .when('/dl/contenttype/edit/:id', {templateUrl: '/dl/angular/contenttype/contenttype.edit.html', controller: 'ContenttypeEditCtrl'})
      .when('/dl/contenttype/display/:id', {templateUrl: '/dl/angular/contenttype/contenttype.display.html', controller: 'ContenttypeDisplayCtrl'})
      	
  }]);
