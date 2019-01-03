angular.module('contacts', ['contacts.contact','contacts.event','ngFlash','ADM-dateTimePicker'])
    .config(['$routeProvider', 'FlashProvider',
         function ($routeProvider, FlashProvider ) {//$resourceProvider
            FlashProvider.setTimeout(5000);
            FlashProvider.setShowClose(true);
        }
    ])
;
