angular.module('dl.contenttype')
    .controller('ContenttypeEditCtrl', ['$scope', 'ContenttypeResource', 'dlControllerService', function ($scope, ContenttypeResource, dlControllerService) {
      var loading = $.fn.showLoading();
    	dlControllerService.init($scope);
     	dlControllerService.applyEditCtrl($scope,'contentType', ContenttypeResource, function (data) {
        $.fn.hideLoading(loading);
      });
    }])

    .controller('ContenttypeListCtrl', ['$scope', 'ContenttypeResource', 'dlControllerService', function ($scope, ContenttypeResource, dlControllerService) {
      var loading = $.fn.showLoading();
    	dlControllerService.init($scope);
     	dlControllerService.applyListCtrl($scope,'contentType', ContenttypeResource, function (data) {
        $.fn.hideLoading(loading);
      });
    }])
    
    	

    .controller('ContenttypeDisplayCtrl', [
                '$scope', '$location', '$routeParams', 'ContenttypeResource' , 'dlControllerService',
        function ($scope, $location, $routeParams, ContenttypeResource, dlControllerService) {
      var loading = $.fn.showLoading();
    	dlControllerService.init($scope);
      dlControllerService.applyDisplayCtrl($scope,'contentType', ContenttypeResource, function (data) {
          $.fn.hideLoading(loading);
      });
      
      
      
    }]);
