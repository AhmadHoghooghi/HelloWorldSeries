angular.module('contacts.event', ['ngRoute', 'ui.bootstrap', 'dialogs.main','ngResource'])//,'ngFlash'
    .config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider
                .when("/event/list", {
                    templateUrl: "event/event.list.html",
                    controller: "EventListCtrl",
                    reloadOnSearch: false
                })
                .when("/event/save-or-update", {
                    templateUrl: "event/event.edit.html",
                    controller: "EventSaveOrUpdateCtrl"
                })
                .when("/event/display/:id", {
                    templateUrl: "event/event.display.html",
                    controller: "EventDisplayCtrl"
                });
        }
    ])
;
