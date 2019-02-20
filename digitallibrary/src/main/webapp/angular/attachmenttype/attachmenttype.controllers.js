angular.module('dl.attachmenttype')
    .controller('AttachmenttypeEditCtrl', ['$scope', 'AttachmenttypeResource', 'dlControllerService', function ($scope, AttachmenttypeResource, dlControllerService) {
      var loading = $.fn.showLoading();
    	dlControllerService.init($scope);
     	dlControllerService.applyEditCtrl($scope,'attachmentType', AttachmenttypeResource, function (data) {
        $.fn.hideLoading(loading);
      });
    }])

    .controller('AttachmenttypeListCtrl', ['$scope', 'AttachmenttypeResource', 'dlControllerService', function ($scope, AttachmenttypeResource, dlControllerService) {
      var loading = $.fn.showLoading();
    	dlControllerService.init($scope);
     	dlControllerService.applyListCtrl($scope,'attachmentType', AttachmenttypeResource, function (data) {
        $.fn.hideLoading(loading);
      });
    }])
    
    	

    .controller('AttachmenttypeDisplayCtrl', ['$scope', '$location', '$routeParams', 'AttachmenttypeResource',  'AttachmentResource', 'dlControllerService', function ($scope, $location, $routeParams, AttachmenttypeResource,  AttachmentResource, dlControllerService) {
      var loading = $.fn.showLoading();
    	dlControllerService.init($scope);
      dlControllerService.applyDisplayCtrl($scope,'attachmentType', AttachmenttypeResource, function (data) {
          $.fn.hideLoading(loading);
      });
      
      
      
    }]);
