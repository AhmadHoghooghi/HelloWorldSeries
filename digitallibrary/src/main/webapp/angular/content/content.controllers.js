angular.module('dl.content')
    .controller('ContentEditCtrl', ['$scope', 'ContentResource', 'dlControllerService', 'Upload',
        function ($scope, ContentResource, dlControllerService, Upload) {
            var loading = $.fn.showLoading();
            dlControllerService.init($scope);
            dlControllerService.applyEditCtrl($scope, 'content', ContentResource, function (data) {
                $.fn.hideLoading(loading);
                var validFileTypes = data.options.systemSetting.config.validFileTypes;
                var array = [];
                if (validFileTypes) {
                    array = validFileTypes.split(",");
                }

                var dotPrefixedArray = [];
                for (var i = 0; i < array.length; i++) {
                    dotPrefixedArray[i] = ".".concat(array[i].trim());
                }
                var dotPrefixed = dotPrefixedArray.join();
                data.options.systemSetting.config.validFileTypes = dotPrefixed;

            });

            $scope.ahmad = function (relatedEntityName, field, query, limit) {
                if (!$scope.widget[field]) $scope.widget[field] = [];
                librarySearchParameterObject = {
                    entity: relatedEntityName,
                    'query': query,
                    'pageSize': limit || $scope.actions.common.getSuggestPageSize()
                };

                if ($scope.data && $scope.data.contentType && $scope.data.contentType.pk) {
                    librarySearchParameterObject["eq:contentType.pk"] = $scope.data.contentType.pk;
                }

                ContentResource.searchOptions(librarySearchParameterObject).$promise.then(function (data) {
                    if (data && data.items && angular.isArray(data.items))
                        $scope.widget[field] = data;
                });
            };

            $scope.$watch("data.contentType.pk", function () {
                $scope.ahmad("library", "library", "", 10);
                if ($scope.data) {
                    $scope.data.library = {};
                }
            });
        }])

    .controller('ContentListCtrl', ['$scope', 'ContentResource', 'dlControllerService', 'dialogs', '$filter',
        function ($scope, ContentResource, dlControllerService, dialogs, $filter) {
            var loading = $.fn.showLoading();
            dlControllerService.init($scope);
            dlControllerService.applyListCtrl($scope, 'content', ContentResource, function (data) {
                $.fn.hideLoading(loading);
            });
            $scope.changeStatusOfThisContent = function (pk) {
                var pkArray = [];
                pkArray.push(pk);
                console.log(pkArray);
                $scope.changeStatus(pkArray);
            };
            $scope.changeStatus = function (pkArray) {
                var url = '/dl/angular/content/content.change-status.html';
                var ctrlr = 'ContentChangeStatusCtrl';
                var data = {
                    params: {
                        pkArray: pkArray,
                        ignoreQueryString: true
                    },
                    entityName: 'content',
                    resource: ContentResource
                };
                var opts = {};
                // var ctrlAs = null;
                dialogs.create(url, ctrlr, data, opts).result.then(function () {
                    $scope.actions.list.search();
                    // console.log("dialog is closed");
                });
            };
            $scope.changeStatusOfEntities = function () {
                var tableWidgetName = $scope.actions.list.getTableWidgetName('');
                var selectedItems = $scope.widget[tableWidgetName].settings().getSelectedItems();
                var selectedItemIds = [];
                for (var i = 0; i < selectedItems.length; i++) {
                    selectedItemIds.push(selectedItems[i].id);
                }
                $scope.changeStatus(selectedItemIds)
            }
            $scope.allSelectedItemsAreIN_PROCESS = function () {
                var selectedItems = $filter('filter')($scope.data.items, {'$selected': true});
                for (var idx in selectedItems) {
                    var item = selectedItems[idx];
                    if (item.status != "IN_PROCESS") return false;
                }
                return true;
            };
            $scope.showReportDialog = function () {
                // console.log("showReportDialog in contentListCtrl");
                var url = '/dl/angular/content/content.report.html';
                var ctrlr = 'ContentReportCtrl';
                var data = $scope.filter;
                var opts = {size:'md'};
                // var ctrlAs = null;
                dialogs.create(url, ctrlr, data, opts)//.result.then(function () {console.log("dialog is closed");});
            };

        }])

    .controller('ContentHistoryCtrl', ['$scope', '$window', '$routeParams', 'ContentResource', '$filter', '$location', function ($scope, $window, $routeParams, ContentResource, $filter, $location) {
        var searchVersion = {id: $routeParams.id};
        ContentResource.searchVersions(searchVersion).$promise.then(function (data) {
            for (k in data.items) {
                data.items[k].revInfo.revisionDateStr = $filter('persianDate')(data.items[k].revInfo.revisionDate, 'fullDate') + ' - ' + $filter('date')(data.items[k].revInfo.revisionDate, 'H:mm');
            }
            $scope.items = data.items;
        });

        $scope.back = function () {
            $window.history.back();
        };

        $scope.showVersion = function (pk, revisionNumber) {
            $location.path('/dl/content/version/' + pk + '/' + revisionNumber);
        };
    }])

    .controller('ContentVersionCtrl', ['$scope', '$window', '$routeParams', 'ContentResource', 'dlControllerService', function ($scope, $window, $routeParams, ContentResource, dlControllerService) {
        dlControllerService.init($scope);
        dlControllerService.applyDisplayCtrl($scope, 'content', ContentResource, function (data) {
            $.fn.hideLoading(loading);
        });
        var pk = $routeParams.id;
        var revisionNumber = $routeParams.revisionNumber;
        ContentResource.loadVersion({id: pk, revision: revisionNumber}).$promise.then(function (data) {
            $scope.data = data.entity;
        });

        $scope.back = function () {
            $window.history.back();
        };

    }])

    .controller('ContentDisplayCtrl', ['$scope', '$location', '$routeParams', 'ContentResource', 'AttachmentResource', 'dlControllerService',
        function ($scope, $location, $routeParams, ContentResource, AttachmentResource, dlControllerService) {
            var loading = $.fn.showLoading();
            dlControllerService.init($scope);
            dlControllerService.applyDisplayCtrl($scope, 'content', ContentResource, function (data) {
                $.fn.hideLoading(loading);
            });

            $scope.ShowHistory = function () {
                $location.path('/dl/content/history/' + $routeParams.id);
            };


            $scope.attachmentParams = {
                hideSearchPanel: true,
                masterPK: $scope.id,
                'filter.eq:content.pk': $scope.id
            };
        }])

    .controller('ContentChangeStatusCtrl', ['$scope', 'ContentResource', 'data', '$modalInstance', 'flash',
        function ($scope, ContentResource, data, $modalInstance, flash) {
            //start of controller initialization
            $scope.data = {};
            $scope.actions = {};
            $scope.filter = {};
            $scope.parameters = {};
            $scope.controls = {};

            // console.log("hi");
            var pkArray = data.params.pkArray;

            ContentResource.enums().$promise.then(function (enums) {
                // console.log(enums.status);
                $scope.data.enums = enums;
            });

            $scope.actions.dismiss = function () {
                $modalInstance.dismiss('');
            };

            $scope.actions.save = function () {
                var contentStatusChangeDTO = {
                    pkArray: pkArray,
                    newStatus: $scope.parameters.newStatus
                };
                ContentResource.changeStatus(contentStatusChangeDTO).$promise.then(
                    function (actionResult) {
                        if (actionResult.success == true) {
                            // console.log("success");
                            flash.success = actionResult.message;
                            $modalInstance.close(actionResult);
                        }
                    },
                    function (actionResult) {
                        // console.log("failure");
                        flash.error = actionResult.message;
                    }
                );
            }
        }])

    .controller('ContentReportCtrl', ['$scope', 'ContentResource', 'data', '$modalInstance', 'dlControllerService',
        function ($scope, ContentResource, data, $modalInstance, dlControllerService) {
            //start of controller initialization
            dlControllerService.init($scope);
            dlControllerService.applyMinimalCtrl($scope, 'content', ContentResource);
            $scope.data = {};
            $scope.actions.dismiss = function () {
                $modalInstance.dismiss('');
            };
            // console.log(data);
            var searchParamsFiltered = $scope.actions.list.getSearchParams(data);
            console.log(searchParamsFiltered);
            ContentResource.report(searchParamsFiltered).$promise.then(function (reportResult) {
                // console.log(reportResult);
                $scope.data.reportItems = reportResult;
            });
        }]);
;

