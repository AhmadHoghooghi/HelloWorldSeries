angular.module('dl.contenttype')
    .factory('ContenttypeResource', ['$resource', 'dlResourceService', function ($resource, dlResourceService) {
      return $resource('', {}, dlResourceService.create('contentType'));
    }]);
