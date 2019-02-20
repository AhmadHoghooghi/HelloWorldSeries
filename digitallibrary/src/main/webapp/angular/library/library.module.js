angular.module('dl.library',['ngRoute',
   ['/dl/angular/lib.js'
    ,'/dl/angular/library/library.controllers.js'
    ,'/dl/angular/library/library.services.js']])
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/dl/library/list', {templateUrl: '/dl/angular/library/library.list.html', controller: 'LibraryListCtrl'})
      .when('/dl/library/edit', {templateUrl: '/dl/angular/library/library.edit.html', controller: 'LibraryEditCtrl'})
      .when('/dl/library/edit/:id', {templateUrl: '/dl/angular/library/library.edit.html', controller: 'LibraryEditCtrl'})
      .when('/dl/library/display/:id', {templateUrl: '/dl/angular/library/library.display.html', controller: 'LibraryDisplayCtrl'})
      	
  }]);
