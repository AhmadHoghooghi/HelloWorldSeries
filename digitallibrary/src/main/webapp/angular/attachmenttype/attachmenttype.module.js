angular.module('dl.attachmenttype',['ngRoute',
   ['/dl/angular/lib.js'
    ,'/dl/angular/attachmenttype/attachmenttype.controllers.js'
    ,'/dl/angular/attachmenttype/attachmenttype.services.js']])
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/dl/attachmenttype/list', {templateUrl: '/dl/angular/attachmenttype/attachmenttype.list.html', controller: 'AttachmenttypeListCtrl'})
      .when('/dl/attachmenttype/edit', {templateUrl: '/dl/angular/attachmenttype/attachmenttype.edit.html', controller: 'AttachmenttypeEditCtrl'})
      .when('/dl/attachmenttype/edit/:id', {templateUrl: '/dl/angular/attachmenttype/attachmenttype.edit.html', controller: 'AttachmenttypeEditCtrl'})
      .when('/dl/attachmenttype/display/:id', {templateUrl: '/dl/angular/attachmenttype/attachmenttype.display.html', controller: 'AttachmenttypeDisplayCtrl'})
      	
  }]);
