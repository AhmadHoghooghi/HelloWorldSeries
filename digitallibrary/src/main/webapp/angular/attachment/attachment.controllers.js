angular.module('dl.attachment')
    .controller('AttachmentEditCtrl', ['$scope', 'AttachmentResource', 'dlControllerService',
        function ($scope, AttachmentResource, dlControllerService) {
            var loading = $.fn.showLoading();
            dlControllerService.init($scope);
            dlControllerService.applyEditCtrl($scope, 'attachment', AttachmentResource, function (data) {
                $.fn.hideLoading(loading);
            });
            //actions.common.refreshSuggest('attachmentType','attachmentType', $select.search, 10)
            $scope.actions.common.refreshSuggestForAttachmentType = function (relatedEntityName, field, query, limit) {
                if (!$scope.widget[field]) $scope.widget[field] = [];
                var searchOptionsObject = {
                    id: $scope.parameters.masterPK
                };
                AttachmentResource.validAttachmenttypeOptionsForContentId(searchOptionsObject).$promise.then(function (data) {
                    if (data && data.items && angular.isArray(data.items))
                        $scope.widget[field] = data;
                });
            };
        }])

    .controller('AttachmentListCtrl', ['$scope', 'AttachmentResource', 'dlControllerService', function ($scope, AttachmentResource, dlControllerService) {
        var loading = $.fn.showLoading();
        dlControllerService.init($scope);
        dlControllerService.applyListCtrl($scope, 'attachment', AttachmentResource, function (data) {
            $.fn.hideLoading(loading);
        });
    }])
    .controller('AttachmentDisplayCtrl',
        ['$scope', '$location', '$routeParams', 'AttachmentResource', 'dlControllerService',
            function ($scope, $location, $routeParams, AttachmentResource, dlControllerService) {
                var loading = $.fn.showLoading();
                dlControllerService.init($scope);
                dlControllerService.applyDisplayCtrl($scope, 'attachment', AttachmentResource, function (data) {
                    $.fn.hideLoading(loading);
                });
            }]);
