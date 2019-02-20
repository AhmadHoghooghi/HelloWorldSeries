angular.module('dl.attachment')
    .factory('AttachmentResource', ['$resource', 'dlResourceService', function ($resource, dlResourceService) {
        var objectToCreateServiceFrom = dlResourceService.create('attachment');
        objectToCreateServiceFrom.validAttachmenttypeOptionsForContentId = {method: 'GET', url: "/dl/rest/attachmentType/validAttachmentTypeOptionsForContent/:id"};
        return $resource('', {}, objectToCreateServiceFrom);
    }]);
