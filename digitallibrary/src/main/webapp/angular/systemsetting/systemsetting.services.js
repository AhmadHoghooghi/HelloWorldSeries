angular.module('dl.systemsetting')
    .factory('SystemsettingResource', ['$resource', 'dlResourceService', function ($resource, dlResourceService) {
        var systemSetting = dlResourceService.create('systemSetting');
        // load-single-instance
        systemSetting.loadSingleInstance = {method: 'GET', url: '/dl/rest/systemSetting/load-single-instance'};
        return $resource('', {}, systemSetting);
    }]);
