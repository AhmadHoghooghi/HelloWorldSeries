angular.module('dl.library')
    .controller('LibraryEditCtrl', ['$scope', 'LibraryResource', 'dlControllerService', function ($scope, LibraryResource, dlControllerService) {
      var loading = $.fn.showLoading();
    	dlControllerService.init($scope);
     	dlControllerService.applyEditCtrl($scope,'library', LibraryResource, function (data) {
        $.fn.hideLoading(loading);
      });
    }])

    .controller('LibraryListCtrl', ['$scope', 'LibraryResource', 'dlControllerService', function ($scope, LibraryResource, dlControllerService) {
      var loading = $.fn.showLoading();
    	dlControllerService.init($scope);
     	dlControllerService.applyListCtrl($scope,'library', LibraryResource, function (data) {
        $.fn.hideLoading(loading);
      });
    }])
    
    	

    .controller('LibraryDisplayCtrl', [
            '$scope', '$location', '$routeParams', 'LibraryResource', 'dlControllerService', function (
            $scope, $location, $routeParams, LibraryResource, dlControllerService) {
      var loading = $.fn.showLoading();
    	dlControllerService.init($scope);
      dlControllerService.applyDisplayCtrl($scope,'library', LibraryResource, function (data) {
          $.fn.hideLoading(loading);
      });
      
      
      
    }]);
