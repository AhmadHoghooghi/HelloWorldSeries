angular.module('dl.attachmenttype')
    .factory('AttachmenttypeResource', ['$resource', 'dlResourceService', function ($resource, dlResourceService) {
        var objectToCreateServicesFrom = dlResourceService.create('attachmentType');
        return $resource('', {}, objectToCreateServicesFrom);
    }]);
