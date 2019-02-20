angular.module('dl.content')
    .factory('ContentResource', ['$resource', 'dlResourceService', function ($resource, dlResourceService) {
        var objectToCreateServices = dlResourceService.create('content');
        objectToCreateServices.changeStatus = {method: 'POST', url: '/dl/rest/content/changeStatus'};
        objectToCreateServices.enums = {method: 'GET', url: '/dl/rest/content/enums'};
        objectToCreateServices.report = {method: 'GET', url: '/dl/rest/contentReport/distReport', isArray: true};
        return $resource('', {}, objectToCreateServices);
    }]);
