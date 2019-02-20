/**
 * dependencies of lib module have to reside in index.html of wise-core-webapp
 */
'use strict';
(function () {
  'use strict';
  var appName = '${appNameToLower}';
  var rest_servlet_path = '${rest.servlet.path}';

  function ParameterUtil(parameters) {
    var parameter_defaults = {
      formTitle: undefined,
      pk: undefined,
      hideSearchPanel: false,
      hideButtons: false,
      hideFormTitle: false,
      masterPK: undefined,
      ignoreQueryString: false,//used in detail forms & dynamic loading
      ignoreLogin: false,
      ignoreFirstSearch: false
    };
    //return $scope.parameters or default if it does not exists
    this.getParam = function (paramKey) {
      return parameters[paramKey] ? parameters[paramKey] : parameter_defaults[paramKey];
    };
    //return $scope.parameters
    this.getUserParam = function (paramKey) {
      return parameters[paramKey];
    }
  }

  angular.module(appName + '.lib', ['mainApp', ['/' + appName + '/angular/lib.actions.js', '/' + appName + '/angular/common.modules.js']])
      .constant(appName + 'RestPrefix', '/' + appName + '/' + rest_servlet_path + '/')
      .run([appName + 'RestPrefix', '$rootScope', function (restPrefix) {
    	  $.fn.loadMessageBundle(restPrefix,appName);
      }])
      .controller(appName + 'CrudWindowCtrl', function ($scope, $modalInstance, data, $controller, flash) {
        $scope.parameters = data.params;
        var entityPrefix = data.entityName.substr(0, 1).toUpperCase() + data.entityName.substr(1);
        $controller(entityPrefix + 'EditCtrl', {$scope: $scope});
        //Override Save/Back Buttons in window mode
        $scope.back = function () {
          $modalInstance.dismiss('window closed');
        };
        $scope.save = function (masterFieldName, callback) {
          $scope.actions.edit.save(data.resource, data.entityName, flash, function (result) {
            if(angular.isFunction(data.updateCallback)) {
              data.updateCallback(result);
            }
            if(angular.isFunction(callback)) {
              callback(result);
            }
            $modalInstance.close();
          }, masterFieldName);
        };
        $scope._getData = function () {
          return data;
        };
      })
      .factory(appName + 'ControllerService', [appName + 'Actions', '$routeParams', 'NgTableParams', '$timeout', '$filter', '$location', '$http', 'Upload', 'uuid4', 'dialogs', 'flash', '$controller', appName + 'RestPrefix', 'PersianDateService', '$window', 'navigationService', appName + '.configs', 
                                               function (actions, $routeParams, NgTableParams, $timeout, $filter, $location, $http, Upload, uuid4, dialogs, flash, $controller, restPrefix, PersianDateService, $window, navigationService, configs) {
        var service = {};
        service.init = function ($scope) {
          actions.populateScope($scope);
          $scope.actions.common.login(restPrefix);
        };
        service.applyMinimalCtrl = function ($scope, entityName, remoteResource) {
          actions.addTemplateActions($scope, entityName, remoteResource, uuid4, Upload, flash, $http, PersianDateService,configs);
        };
        service.applyEditCtrl = function ($scope, entityName, remoteResource, loadCallback) {
          actions.addTemplateActions($scope, entityName, remoteResource, uuid4, Upload, flash, $http, PersianDateService,configs);
          //Initialize Parameters
          if (!$scope.parameters)
            $scope.parameters = {};
          $scope.actions.common.mergeParameters($scope.parameters, $location);
          $scope.actions.common.params = new ParameterUtil($scope.parameters);
          //Decide create/edit
          $scope.id = $scope.actions.common.resolveId($routeParams, $scope.actions.common.params);
          var formType = ($scope.id) ? 'edit' : 'create';
          $scope.formTitle = $scope.actions.common.resolveFormTitle(formType, entityName, $scope.actions.common.params);
          //these are actually overridden in WindowCtrl
          $scope.save = function (masterFieldName) {
            $scope.actions.edit.save(remoteResource, entityName.toLowerCase(), flash, function () {
              $location.path('/' + appName + '/' + entityName.toLowerCase() + '/list');
            });
          };
          $scope.back = function () {
            $scope.actions.edit.back($location, entityName.toLowerCase());
          };
          $scope.actions.edit.loadEntity(remoteResource, loadCallback);
        };
        service.applyDisplayCtrl = function ($scope, entityName, remoteResource, loadCallback) {
          actions.addTemplateActions($scope, entityName, remoteResource, uuid4, Upload, flash, $http, PersianDateService, configs);
          //Initialize Parameters
          if (!$scope.parameters)
            $scope.parameters = {};
          $scope.actions.common.mergeParameters($scope.parameters, $location);
          $scope.actions.common.params = new ParameterUtil($scope.parameters);
          //Decide create/edit
          $scope.id = $scope.actions.common.resolveId($routeParams, $scope.actions.common.params);
          if (!$scope.id)
            throw new Error('display form is called without providing any id');
          var formType = 'display';
          $scope.formTitle = $scope.actions.common.resolveFormTitle(formType, entityName, $scope.actions.common.params);
          $scope.edit = function () {
            $scope.actions.display.edit($scope.id, remoteResource, entityName.toLowerCase(), dialogs, $scope.actions.common.params.getParam('masterPK'), loadCallback);
          };
          $scope.back = function (parentEntityName) {
        	  $scope.actions.display.backBrowser($window);  
          };
          $scope.actions.display.loadEntity(remoteResource, loadCallback);
        };
        service.applyListCtrl = function ($scope, entityName, remoteResource, loadCallback) {
          actions.addTemplateActions($scope, entityName, remoteResource, uuid4, Upload, flash, $http, PersianDateService, configs);
          //Initialize Parameters
          if (!$scope.parameters)
            $scope.parameters = {};
          $scope.actions.common.mergeParameters($scope.parameters, $location);
          $scope.actions.common.params = new ParameterUtil($scope.parameters);
          var formType = 'list';
          $scope.formTitle = $scope.actions.common.resolveFormTitle(formType, entityName, $scope.actions.common.params);
          $scope.actions.list.loadTable(remoteResource, flash, $filter, loadCallback, NgTableParams, $location, navigationService);
          $scope.createEntity = function () {
            $scope.actions.list.createEntity(entityName.toLowerCase(), remoteResource, dialogs, $scope.actions.common.params.getParam('masterPK'));
          };
          $scope.deleteEntities = function () {
            $scope.actions.list.deleteEntities(remoteResource, flash);
          };
          $scope.restoreEntities = function () {
            $scope.actions.list.restoreEntities(remoteResource, flash);
          };
          $scope.purgeEntities = function () {
            $scope.actions.list.purgeEntities(remoteResource, flash);
          };
          $scope.edit = function (pk) {
            $scope.actions.list.edit(pk, remoteResource, entityName.toLowerCase(), dialogs, $scope.actions.common.params.getParam('masterPK'));
          };
          $scope.display = function (pk) {
            $scope.actions.list.display(pk, entityName.toLowerCase(), $location, $scope.actions.common.params.getParam('masterPK'));
          };
          var oldClearSearch = $scope.actions.list.clearSearch;
          $scope.actions.list.clearSearch = function(){
        	  oldClearSearch($location);
          }
        };
        return service;
      }])
      .service(appName + 'ResourceService', [appName + 'RestPrefix', function (restPrefix) {
        return {
          create: function (entity) {
            return {
              show: {method: 'GET', url: restPrefix + entity + '/load/:id'},
              update: {method: 'POST', url: restPrefix + entity + '/saveOrUpdate'},
              load: {method: 'GET', url: restPrefix + entity + '/load'},
              search: {method: 'GET', url: restPrefix + entity + '/search'},
              deleteEntities: {method: 'POST', url: restPrefix + entity + '/removeEntities'},
              remove: {method: 'POST', url: restPrefix + entity + '/remove'},
              restoreEntities: {method: 'POST', url: restPrefix + entity + '/restoreEntities'},
              purgeEntities: {method: 'POST', url: restPrefix + entity + '/purgeEntities'},
              upload: {method: 'POST', url: restPrefix + entity + '/upload/:fieldname'},
              searchDefault: {method: 'GET', url: restPrefix + entity + '/searchDefault'},
              searchOptions: {method: 'GET', url: restPrefix + ':entity/searchOptions'},
              searchVersions: {method: 'GET', url: restPrefix + entity + '/searchVersions/:id'},
              loadVersion: {method: 'GET', url: restPrefix + entity + '/loadVersion/:id/:revision'},
              report: {method: 'POST', url: restPrefix + entity + '/report'},
              describe: {method: 'GET', url: restPrefix + entity + '/describe'},
              attributes: {method: 'GET', url: restPrefix + entity + '/attributes/:id', isArray: true}
            };
          }
        };
      }]);
})
();