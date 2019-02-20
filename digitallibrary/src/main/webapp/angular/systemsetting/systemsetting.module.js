angular.module('dl.systemsetting',['ngRoute',
   ['/dl/angular/lib.js'
    ,'/dl/angular/systemsetting/systemsetting.controllers.js'
    ,'/dl/angular/systemsetting/systemsetting.services.js']])
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/dl/systemsetting/edit', {templateUrl: '/dl/angular/systemsetting/systemsetting.edit.html', controller: 'SystemsettingEditCtrl'})
  }]);
