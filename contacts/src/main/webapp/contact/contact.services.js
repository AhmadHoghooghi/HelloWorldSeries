angular.module("contacts.contact")
    .factory('ContactResource', ['$http', '$resource',
            function ($http, $resource) {
                var applicationContext = "";
                var contactResource = {};
                contactResource.findAll = function (callback) {
                    var url = applicationContext.concat('rest/contact/all');
                    $http.get(url).then(callback);
                };

                contactResource.search = function (searchCriteria, callback) {
                    var url = applicationContext.concat("rest/contact/search");

                    var contactResource = $resource(url, searchCriteria);
                    contactResource.query().$promise.then(callback);
                };

                contactResource.saveOrUpdate = function (newContact, saveOrUpdateCallback) {
                    var url = applicationContext.concat("rest/contact/save-or-update");
                    $http.post(url, newContact, []).then(saveOrUpdateCallback);
                };

                contactResource.remove = function (id, removeCallback) {
                    var url = applicationContext.concat("rest/contact/delete/").concat(id);
                    $http.post(url).then(removeCallback);
                };

                contactResource.find = function (id, findCallback) {
                    var url = applicationContext.concat("rest/contact/find/").concat(id);
                    $http.get(url).then(findCallback);
                };


                return contactResource;
            }
        ]
    )

;