angular.module('dl.content', ['ngRoute','textAngular',
        [
            '/dl/angular/lib.js'
            , '/dl/angular/attachment/attachment.module.js'
            , '/dl/angular/content/content.controllers.js'
            , '/dl/angular/content/content.services.js'
            , '/dl/angular/content/textangular/textAngular.min.js'
        ]
    ]
)
    .config(['$routeProvider', function ($routeProvider) {
        $routeProvider
            .when('/dl/content/list', {
                templateUrl: '/dl/angular/content/content.list.html',
                controller: 'ContentListCtrl'
            })
            .when('/dl/content/edit', {
                templateUrl: '/dl/angular/content/content.edit.html',
                controller: 'ContentEditCtrl'
            })
            .when('/dl/content/edit/:id', {
                templateUrl: '/dl/angular/content/content.edit.html',
                controller: 'ContentEditCtrl'
            })
            .when('/dl/content/display/:id', {
                templateUrl: '/dl/angular/content/content.display.html',
                controller: 'ContentDisplayCtrl'
            })
            .when('/dl/content/history/:id', {
                templateUrl: '/dl/angular/content/content.history.html',
                controller: 'ContentHistoryCtrl'
            })
            .when('/dl/content/version/:id/:revisionNumber', {
                templateUrl: '/dl/angular/content/content.version.html',
                controller: 'ContentVersionCtrl'
            })
    }]);
