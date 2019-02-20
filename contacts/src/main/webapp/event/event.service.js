angular.module("contacts.contact")
    .factory('EventResource', ['$http', '$resource',
        function ($http, $resource) {
            var baseURL = "rest/event";

            var listURL = baseURL.concat("/list");
            var saveORUpdateURL = baseURL.concat("/save-or-update");
            var searchURL = baseURL.concat("/search");
            var findURL = baseURL.concat("/find/:id");
            var deleteURL = baseURL.concat("/delete/:id");

            var url = "";
            var paramDefaults = {};

            var actions = {
                'list': {
                    method: 'GET',
                    url: listURL,
                    isArray: true
                },
                'saveOrUpdate': {
                    method: 'POST',
                    url: saveORUpdateURL,
                    params: '@saveOrUpdateParams'
                },
                'search': {
                    method: 'GET',
                    url: searchURL,
                    isArray: true,
                    params:'@searchParams'
                },
                'find': {
                    method: 'GET',
                    url: findURL,
                    params:{id:'@id'}
                },
                'delete': {
                    method: 'POST',
                    url: deleteURL,
                    params:{id:'@id'}
                }
            };

            return $resource(url, paramDefaults, actions);
            // EventResource.query().$promise.then(callback);

        }]);