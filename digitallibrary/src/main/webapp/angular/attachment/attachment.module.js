angular.module('dl.attachment',['ngRoute',
   ['/dl/angular/lib.js'
    ,'/dl/angular/attachment/attachment.controllers.js'
    ,'/dl/angular/attachment/attachment.services.js']])
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/dl/attachment/list', {templateUrl: '/dl/angular/attachment/attachment.list.html', controller: 'AttachmentListCtrl'})
      .when('/dl/attachment/edit', {templateUrl: '/dl/angular/attachment/attachment.edit.html', controller: 'AttachmentEditCtrl'})
      .when('/dl/attachment/edit/:id', {templateUrl: '/dl/angular/attachment/attachment.edit.html', controller: 'AttachmentEditCtrl'})
      .when('/dl/attachment/display/:id', {templateUrl: '/dl/angular/attachment/attachment.display.html', controller: 'AttachmentDisplayCtrl'})
      	
  }]);
