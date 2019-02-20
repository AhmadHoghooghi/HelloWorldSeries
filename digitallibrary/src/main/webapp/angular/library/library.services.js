angular.module('dl.library')
    .factory('LibraryResource', ['$resource', 'dlResourceService', function ($resource, dlResourceService) {
      return $resource('', {}, dlResourceService.create('library'));
    }]);
