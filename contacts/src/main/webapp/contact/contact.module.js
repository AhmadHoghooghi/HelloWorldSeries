angular.module('contacts.contact', ['ngRoute', 'ui.bootstrap', 'dialogs.main','ngResource'])//,'ngFlash'
    .config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider
                .when("/contact/list", {
                    templateUrl: "contact/contact.list.html",
                    controller: "ContactListCtrl",
                    reloadOnSearch: false
                })
                .when("/contact/save-or-update", {
                    templateUrl: "contact/contact.edit.html",
                    controller: "ContactSaveOrUpdateCtrl"
                })
                .when("/contact/display/:id", {
                    templateUrl: "contact/contact.display.html",
                    controller: "ContactDisplayCtrl"
                });
        }
    ])
;
