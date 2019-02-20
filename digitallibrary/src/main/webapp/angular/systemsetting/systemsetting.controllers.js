angular.module('dl.systemsetting')
    .controller('SystemsettingEditCtrl', ['$scope', 'SystemsettingResource', 'dlControllerService','flash'
        , function ($scope, SystemsettingResource, dlControllerService,flash) {
        var loading = $.fn.showLoading();
        dlControllerService.init($scope);
        dlControllerService.applyMinimalCtrl($scope, 'systemSetting', SystemsettingResource);
        SystemsettingResource.loadSingleInstance().$promise.then(function (systemSettingEntity) {
            console.log(systemSettingEntity);
            $scope.data = systemSettingEntity;
            $.fn.hideLoading(loading);
        });
        //-------------------------------------------------
        $scope.save = function (masterFieldName) {
            // $scope.actions.edit.save(remoteResource, entityName.toLowerCase(), flash, function () {
            //     $location.path('/' + appName + '/' + entityName.toLowerCase() + '/list');
            // });
            SystemsettingResource.update($scope.data).$promise.then(function (data) {
                if (data.success) {
                    flash.success = data.message;
                } else {
                    flash.error = data.message;
                }
            }, function (error) {
                flash.error = error.data.message;
            });

        };

        // $scope.actions.edit.save = function (remoteResource, entityName, flash, updateCallback, masterFieldName) {
        //         var loading = $.fn.showLoading();
        //         if (masterFieldName)
        //             $scope.data[masterFieldName] = {pk: $scope.actions.common.params.getParam('masterPK')};
        //         if ($scope.actions.common.validateForm(flash, "#" + entityName + "EditForm")) {
        //             remoteResource.update($scope.data).$promise.then(function (data) {
        //                 $.fn.hideLoading(loading);
        //                 if (data.success) {
        //                     flash.success = data.message;
        //                     if (angular.isFunction(updateCallback))
        //                         updateCallback(data);
        //                 } else {
        //                     flash.error = data.message;
        //                 }
        //             }, function (error) {
        //                 $.fn.hideLoading(loading);
        //                 flash.error = error.data.message;
        //             });
        //             return true;
        //         } else {
        //             $.fn.hideLoading(loading);
        //         }
        //         return false;
        //     };
        //--------------------------------------------------
    }])
;
