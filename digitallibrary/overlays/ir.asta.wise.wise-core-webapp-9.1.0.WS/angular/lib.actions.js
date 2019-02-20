'use strict';
(function () {
  'use strict';
  var appName = '${appNameToLower}';
  angular.module(appName + '.lib.actions', [])
      .factory(appName + 'Actions', function () {
        //this service should not depend on any other service, all required services have to be passed to action methods
        var service = {};
        var isLogin = false;//used in login method
        service.cleanse = $.fn.cleanse;
        //Actions that may be overridden by developer defined here
        service.populateScope = function ($scope) {
          $scope.actions = {
            common: {},
            edit: {},
            display: {},
            list: {}
          };
          $scope.widget = {};
          //merge url parameters into scope parameters 
          //scope parameters are preferent
          $scope.actions.common.mergeParameters = function (parameters, $location) {
            if (parameters.ignoreQueryString)
              return;
            var urlParams = $location.search();
            for (var prop in urlParams) {
              if (angular.isUndefined(parameters[prop]))
                parameters[prop] = urlParams[prop];
            }
          };
          $scope.actions.common.makeEditDialog = function (entityName, id, remoteResource, dialogs, updateCallback, master_id) {
            dialogs.create('/' + appName + '/angular/' + entityName + '/' + entityName + '.edit.html', appName + 'CrudWindowCtrl', {
              params: {
                pk: id,
                masterPK: master_id,
                ignoreQueryString: true
              },
              updateCallback: updateCallback,
              entityName: entityName,
              resource: remoteResource
            });
          };
          //for edit forms, if its returns null, the form considered to be create
          $scope.actions.common.resolveId = function ($routeParams, paramUtil) {
            if (paramUtil.getParam('ignoreQueryString') || !$routeParams.id)
              return paramUtil.getUserParam('pk');
            return $routeParams.id;
          };
          //returns formTitle
          $scope.actions.common.resolveFormTitle = function (formType, entityName, paramUtil) {
            if (paramUtil.getUserParam('formTitle')) {
              return paramUtil.getUserParam('formTitle');
            }
            switch (formType) {
              case 'create':
                return $scope.message('common.edit.create_title') + ' ' + $scope.message(entityName + '_singular');
              case 'edit':
                return $scope.message('common.edit.update_title') + ' ' + $scope.message(entityName + '_singular');
              case 'display':
                return $scope.message('common.display.title.prefix') + ' ' + $scope.message(entityName + '_singular');
              case 'list':
                return $scope.message('common.list.title.prefix') + ' ' + $scope.message(entityName + '_plural');
              default :
                return 'no title';
            }
          };
          $scope.actions.common.cleanse = service.cleanse;
          $scope.actions.common.showImageFullScreen = $.fn.showImageFullScreen;
          $scope.actions.common.showLoading = $.fn.showLoading;
          $scope.actions.common.hideLoading = $.fn.hideLoading;
          $scope.actions.common.hideAllLoadings = $.fn.hideAllLoadings;
          $scope.actions.common.login = function (restPrefix) {
            if (!isLogin && (!$scope.parameters || !$scope.parameters.ignoreLogin)) {
              //currently login is called on page refresh, if we wanna avoid that we should persist isLogin in client-side
              var request = new XMLHttpRequest();
              request.open('GET', restPrefix + 'dummyLogin', false);//'false' makes the request synchronous
              request.send();
              if (request.status === 200)
                isLogin = true;
              else
                console.error('login error');
            }
          };
          $scope.actions.common.validateForm = function (flash, element, errorHandler, successHandler) {
            return $(element).validate({errorHandler:errorHandler,successHandler:successHandler}).form();
          };
          $scope.actions.display.loadEntity = function (remoteResource, loadCallback) {
            remoteResource.show({id: $scope.id, options:false}).$promise.then(function (data) {
              if (angular.isFunction(loadCallback)) {
                loadCallback(data);
              }
              $.extend(true, $scope, {data: service.cleanse(data)});
            });
          };
          $scope.actions.display.back = function ($location, entityName, parentEntityName, masterPK) {
            if (parentEntityName && masterPK)
              $location.path('/' + appName + '/' + parentEntityName + '/display/' + masterPK);
            else
              $location.path('/' + appName + '/' + entityName + '/list');
          };
          $scope.actions.display.backBrowser = function($window){
        	  $window.history.back();
          }
          $scope.actions.display.edit = function (itemPK, remoteResource, entityName, dialogs, master_pk, loadCallback) {
            $scope.actions.common.makeEditDialog(entityName, itemPK, remoteResource, dialogs, function () {
              remoteResource.show({id: itemPK}).$promise.then(function (data) {
                if (angular.isFunction(loadCallback)) {
                  loadCallback(data);
                }
                $scope.data = data;
              });
            }, master_pk);
          };
          $scope.actions.edit.loadEntity = function (remoteResource, loadCallback) {
            var resource = $scope.id ? remoteResource.show({id: $scope.id}) : remoteResource.load();
            resource.$promise.then(function (data) {
              if (angular.isFunction(loadCallback)) {
                loadCallback(data);
              }
              $.extend(true, $scope, {data: service.cleanse(data)});
            });
          };
          $scope.actions.edit.back = function ($location, entityName) {
            $location.path('/' + appName + '/' + entityName + '/list');
          };
          $scope.actions.edit.save = function (remoteResource, entityName, flash, updateCallback, masterFieldName) {
            var loading = $.fn.showLoading();
            if (masterFieldName)
              $scope.data[masterFieldName] = {pk: $scope.actions.common.params.getParam('masterPK')};
            if ($scope.actions.common.validateForm(flash, "#" + entityName + "EditForm")) {
              remoteResource.update($scope.data).$promise.then(function (data) {
                $.fn.hideLoading(loading);
                if (data.success) {
                  flash.success = data.message;
                  if (angular.isFunction(updateCallback))
                    updateCallback(data);
                } else {
                  flash.error = data.message;
                }
              }, function (error) {
                $.fn.hideLoading(loading);
                flash.error = error.data.message;
              });
              return true;
            } else {
              $.fn.hideLoading(loading);
            }
            return false;
          };
          $scope.actions.list.loadTable = function (remoteResource, flash, $filter, loadCallback, NgTableParams, $location, navigationService) {
            remoteResource.searchDefault().$promise.then(function (data) {
              $.extend(true, $scope, {data: {permissions: data.permissions}});
              $.extend(true, $scope, {data: {enums: data.enums}});
              $.extend(true, $scope, {data: {options: data.options}});
              data.permissions = null;
              data.enums = null;
              data.options = null;
              var defaultFilter=$scope.actions.list.defaultSearchParams(navigationService,$scope.parameters);
              $.extend(true, $scope, {filter: $scope.actions.common.cleanse(data)},{filter: $scope.actions.common.cleanse(defaultFilter)});
              $scope.widget.tableParams = new NgTableParams(
                  $scope.actions.list.getTableParameters(
                		  $scope.parameters['searchparam.pageSize'],
                		  $scope.parameters['searchparam.pageNumber'],
                		  $scope.parameters['searchparam.sortColumn'],
                		  $scope.parameters['searchparam.sortAscending']),
                  $scope.actions.list.getSettings(remoteResource, flash, $filter, loadCallback,$location, navigationService, 'tableParams')
              );
            });
          };
          $scope.actions.list.defaultSearchParams = function(navigationService,params){
        	  var res={adaptiveDataModel:{}};
        	  $.extend(true, res, navigationService.loadAllUrlParametersWithPrefix(params,'filter'));//for BC
        	  $.extend(true, res, navigationService.loadAllUrlParametersWithPrefix(params,'searchparam.filter'));
        	  return res;
          };
          $scope.actions.list.getTableWidgetName = function(tableWidgetName) {
          	if (!tableWidgetName) {
          		return 'tableParams';
          	}
          	if (typeof tableWidgetName !== 'string' && !(tableWidgetName instanceof String)) {
          		return 'tableParams';
          	}
          	if (tableWidgetName.length === 0 || tableWidgetName.trim().length === 0) {
          		return 'tableParams';
          	}
          	return tableWidgetName;
          };
          $scope.actions.list.reloadTable = function (tableWidgetName) {
          	tableWidgetName = $scope.actions.list.getTableWidgetName(tableWidgetName);
            $scope.widget[tableWidgetName].reload();
            $scope.widget[tableWidgetName].settings({
              hasSelected: false,
              hasSelectedAll: false
            });
          };
          $scope.actions.list.edit = function (itemPK, remoteResource, entityName, dialogs, master_pk) {
            $scope.actions.common.makeEditDialog(entityName, itemPK, remoteResource, dialogs, $scope.actions.list.reloadTable, master_pk);
          };
          $scope.actions.list.display = function (itemPK, entityName, $location, master_pk) {
            if (master_pk) {
              $location.path('/' + appName + '/' + entityName + '/display/' + itemPK);
              $location.search('masterPK', master_pk);
            } else
              $location.path('/' + appName + '/' + entityName + '/display/' + itemPK);
          };
          $scope.actions.list.createEntity = function (entityName, remoteResource, dialogs, master_pk) {
            $scope.actions.common.makeEditDialog(entityName, null, remoteResource, dialogs, $scope.actions.list.reloadTable, master_pk);
          };
          $scope.actions.list.deleteEntities = function (remoteResource, flash, tableWidgetName) {
          	tableWidgetName = $scope.actions.list.getTableWidgetName(tableWidgetName);
          	var loading = $.fn.showLoading();
            var resource = remoteResource.deleteEntities({}, $scope.widget[tableWidgetName].settings().getSelectedItems());
            resource.$promise.then(function (data) {
              if (data.success) {
                flash.success = data.message;
                $scope.actions.list.reloadTable(tableWidgetName);
              } else {
                flash.error = data.message;
              }
            }, function (error) {
              flash.error = error.data.message;
            });
            $.fn.hideLoading(loading);
          };
          $scope.actions.list.purgeEntities = function (remoteResource, flash, tableWidgetName) {
          	tableWidgetName = $scope.actions.list.getTableWidgetName(tableWidgetName);
	        	var loading = $.fn.showLoading();
	        	var resource = remoteResource.purgeEntities({}, $scope.widget[tableWidgetName].settings().getSelectedItems());
            resource.$promise.then(function (data) {
              if (data.success) {
                flash.success = data.message;
                $scope.actions.list.reloadTable(tableWidgetName);
              } else {
                flash.error = data.message;
              }
            }, function (error) {
              flash.error = error.data.message;
            });
            $.fn.hideLoading(loading);
          };
          $scope.actions.list.restoreEntities = function (remoteResource, flash, tableWidgetName) {
          	tableWidgetName = $scope.actions.list.getTableWidgetName(tableWidgetName);
            var resource = remoteResource.restoreEntities({}, $scope.widget[tableWidgetName].settings().getSelectedItems());
            resource.$promise.then(function (data) {
              if (data.success) {
                flash.success = data.message;
                $scope.actions.list.reloadTable(tableWidgetName);
              } else {
                flash.error = data.message;
              }
            }, function (error) {
              flash.error = error.data.message;
            });
          };
          $scope.actions.list.resetPage = function (tableWidgetName) {
          	tableWidgetName = $scope.actions.list.getTableWidgetName(tableWidgetName);
            $scope.widget[tableWidgetName].settings().setPageNumber(1);
          };
          $scope.actions.list.search = function (tableWidgetName) {
          	tableWidgetName = $scope.actions.list.getTableWidgetName(tableWidgetName);
            $scope.actions.list.resetPage(tableWidgetName);
            $scope.actions.list.reloadTable(tableWidgetName);
          };
          $scope.actions.list.clearSearch = function ($location, tableWidgetName) {
          	tableWidgetName = $scope.actions.list.getTableWidgetName(tableWidgetName);
            $scope.actions.list.resetPage(tableWidgetName);
            $scope.filter = {};
            $scope.actions.list.resetUrlFilter($location);
            $scope.actions.list.reloadTable(tableWidgetName);
          };
          $scope.actions.list.getTableParameters = function (defaultPageSize,defaultPageNumber,defaultSortColumn,defaultSortAscending) {
        	  var sorting = {};
        	  if(defaultSortColumn)
        		  sorting[defaultSortColumn]=defaultSortAscending?'asc':'desc';
        	  return {
        		  count: defaultPageSize || 10,
        		  page: defaultPageNumber || 1,
        		  sorting: sorting
        	  }
          };
          $scope.actions.list.getSettings = function (remoteResource, flash, $filter, loadCallback, $location, navigationService, tableWidgetName) {
          	tableWidgetName = $scope.actions.list.getTableWidgetName(tableWidgetName);
            return {
              requestNumber : 0,
              getData: function ($defer, params) {
            	if(this.requestNumber++===0 && $scope.actions.common.params.getParam('ignoreFirstSearch'))
            		return $defer.resolve([]);
                $scope.actions.list.fetchPage($defer, params, remoteResource, flash, loadCallback, $location, navigationService)
              },
              hasSelected: false,
              hasSelectedAll: false,
              selectedItemsChanged: function (index, $event) {
                var oldValue = $scope.data.items[index].$selected;
                $scope.data.items[index].$selected = !$scope.data.items[index].$selected;
                var selectedItems = $filter('filter')($scope.data.items, {'$selected': true});
                $scope.widget[tableWidgetName].settings({
                  hasSelected: (selectedItems != null && selectedItems.length > 0),
                  hasSelectedAll: (selectedItems != null && selectedItems.length == $scope.data.items.length)
                });
              },
              toggleSelectAll: function ($event) {
                var checked = $event.target.checked;
                if (checked) {
                  for (var i = 0; i < $scope.data.items.length; i++) {
                    $scope.data.items[i].$selected = true;
                  }
                } else {
                  for (var i = 0; i < $scope.data.items.length; i++) {
                    $scope.data.items[i].$selected = false;
                  }
                }
                var selectedItems = $filter('filter')($scope.data.items, {'$selected': true});
                $scope.widget[tableWidgetName].settings({
                  hasSelected: (selectedItems != null && selectedItems.length > 0),
                  hasSelectedAll: (selectedItems != null && selectedItems.length == $scope.data.items.length)
                });
              },
              clearSelection: function () {
                for (var i = 0; i < $scope.data.items.length; i++) {
                  $scope.data.items[i].$selected = false;
                }
                $scope.widget[tableWidgetName].settings({
                  hasSelected: false,
                  hasSelectedAll: false
                });
              },
              getSelectedItems: function () {
                return $filter('filter')($scope.data.items, {'$selected': true});
              },
              hasDeletedInSelectedItems: function () {
                var selectedItems = $filter('filter')($scope.data.items, {'$selected': true});
                for (var idx in selectedItems) {
                  var item = selectedItems[idx];
                  if (item.deleted) return true;
                }
                return false;
              },
              hasNonDeletedInSelectedItems: function () {
                var selectedItems = $filter('filter')($scope.data.items, {'$selected': true});
                for (var idx in selectedItems) {
                  var item = selectedItems[idx];
                  if (!item.deleted) return true;
                }
                return false;
              },
              getFilteredCounts: function () {
                var ret = [];
                if ($scope.data && $scope.data.items && $scope.data.items.length>0) {
                  var counts = $scope.widget[tableWidgetName].settings().counts;
                  for(var idx=0; idx<counts.length; idx++){
                	  ret.push(counts[idx]);
                	  if(counts[idx]>=$scope.widget[tableWidgetName].total() && ret.length>1)//at least show 2 pagesize(because of styling issues)
                		  break;
                  }
                }
                return ret;
              },
              setPageSize : function (pageSize){
            	  $scope.widget[tableWidgetName].count(pageSize);
              },
              setPageNumber : function(pageNumber){
            	  $scope.widget[tableWidgetName].page(pageNumber);
              }          
            }
          };
          $scope.actions.list.getSearchParams = function (filter) {
            if (filter == null)
              return {};
            var searchParams = {};
            for(var k in filter) {
              if(!filter.hasOwnProperty(k) || angular.isFunction(filter[k]) || k.charAt(0) == '$' || !filter[k])
            	  continue;
              if(k === 'adaptiveDataModel'){
                for (var adaptiveKey in filter.adaptiveDataModel){
                  if (adaptiveKey.charAt(0) !== '$')
                    searchParams[adaptiveKey] = filter.adaptiveDataModel[adaptiveKey];
                }
              }else if(angular.isObject(filter[k]) && filter[k].pk != null){//for suggests
                  searchParams[$scope.actions.list.getSuggestFilterKey(k)] = filter[k].pk;
              }else if(angular.isArray(filter[k])){//for multiple suggests
            	  if(filter[k].length==0);
            	  else if(filter[k][0].pk){
            		  var pkArray=[];
            		  angular.forEach(filter[k],function(item){
            			  pkArray.push(item.pk);
            		  })
            		  searchParams[$scope.actions.list.getSuggestFilterKey(k)] = pkArray.join(',');
            	  }else
            		  console.warn('unknown search filter :'+k);
              }else if(angular.isString(filter[k]) || angular.isDate(filter[k]) || angular.isNumber(filter[k]) || typeof filter[k] === 'boolean'){//strings, dates,combos, enums, checkboxes is handled here
                  searchParams[k] = filter[k];
              }else{
            	  console.warn('unknown search filter :'+k);
              }
            }
            return searchParams;
          };
          $scope.actions.list.getSuggestFilterKey = function(key){
        	  var suffixes=['.pk','.id','.treeId'];
        	  var hasSuffix = false;
        	  angular.forEach(suffixes,function(suffix){
        		  if(key.indexOf(suffix, key.length-suffix.length)!==-1)//endsWidth() for god's sake!
        			  hasSuffix=true;
        	  }) 
        	  return hasSuffix ? key : key + ".pk";  
          }
          $scope.actions.list.resetUrlFilter = function($location){
        	  var urlParams = $location.search();
        	  for(var urlKey in urlParams)
        		  if(urlKey.lastIndexOf('searchparam.filter.', 0) == 0)
        			  $location.search(urlKey,null);
          }
          $scope.actions.list.saveSearchParams = function(filter, params, $location, navigationService){
        	  //first, remove all searchParams
        	  $scope.actions.list.resetUrlFilter($location);
        	  //then add them again
        	  for(var key in filter){
        		  if(!filter.hasOwnProperty(key) || angular.isFunction(filter[key]) || key.charAt(0) == '$' || !filter[key])
        			  continue;
        		  navigationService.saveUrlParameter('searchparam.filter',key, filter[key]);
        	  }
    		  navigationService.saveUrlParameter('searchparam','pageSize',$scope.actions.list.params.getCount(params));
    		  navigationService.saveUrlParameter('searchparam','pageNumber',$scope.actions.list.params.getPage(params));
              var sortField = $scope.actions.list.params.getFirstSortField(params);
              if(sortField){
            	  navigationService.saveUrlParameter('searchparam','sortColumn',sortField);
            	  var isAscending = !!$scope.actions.list.params.isSortingAsc(params,sortField);
            	  navigationService.saveUrlParameter('searchparam','sortAscending',isAscending);
              }
    		  $location.replace();
          }
          $scope.actions.list.params = {};
          $scope.actions.list.params.getCount = function (params) {
            return params.count();
          };
          $scope.actions.list.params.getPage = function (params) {
            return params.page();
          };
          $scope.actions.list.params.getTotal = function (params) {
            return null;
          };
          $scope.actions.list.params.getSorting = function (params) {
            return params.sorting();
          };
          $scope.actions.list.params.getFirstSortField = function (params) {
            return Object.keys(params.sorting())[0];
          };
          $scope.actions.list.params.isSortingAsc = function (params, field) {
            var sorting_object = params.sorting();
            if (sorting_object[field] === 'asc') {
            	return true;
            } else if (sorting_object[field] === 'desc') {
            	return false;
            }
          };
          $scope.actions.list.params.isFirstSortingAsc = function (params) {
            return $scope.actions.list.params.isSortingAsc(params, $scope.actions.list.params.getFirstSortField(params));
          };
          $scope.actions.list.fetchPage = function ($defer, params, remoteResource, flash, loadCallback, $location, navigationService) {
            var searchParams = $scope.actions.list.getSearchParams($scope.filter);
            if($location && navigationService){//for backward compatibility            	
            	$scope.actions.list.saveSearchParams($scope.filter, params, $location, navigationService);
            }
            searchParams['pageSize'] = $scope.actions.list.params.getCount(params);
            searchParams['pageNumber'] = $scope.actions.list.params.getPage(params);
            searchParams['totalSize'] = $scope.actions.list.params.getTotal(params);
            searchParams['sortColumn'] = $scope.actions.list.params.getFirstSortField(params);
            searchParams['sortAscending'] = $scope.actions.list.params.isFirstSortingAsc(params);
            if ($scope.actions.common.params.getParam('enableFetchPageLoading')) {
          		var fetchPageLoading = $scope.actions.common.showLoading();
          	}
            remoteResource.search(searchParams).$promise.then(function (data) {
          		$scope.actions.common.hideLoading(fetchPageLoading);
            	$scope.actions.list.onFetchPageSuccess(data, $defer, params, flash, loadCallback);
            }, function(data) {
          		$scope.actions.common.hideLoading(fetchPageLoading);
            	$scope.actions.list.onFetchPageFailure(data, fetchPageLoading, $defer, params, flash, loadCallback);
            });
          };
          $scope.actions.list.onFetchPageSuccess = function (data, $defer, params, flash, loadCallback) {
          	if (!data.items) {
          		flash.error = data.message;
          		return;
          	}
          	if (angular.isFunction(loadCallback)) {
          		loadCallback(data.items);
          	}
          	$scope.data.items = data.items;
          	$defer.resolve($scope.data.items);
          	params.total(data.totalSize);
          	params.page(data.pageNumber);
          	params.settings({
          		hasSelected: false,
          		hasSelectedAll: false
          	});
          };
          $scope.actions.list.onFetchPageFailure = function (data, $defer, params, flash, loadCallback) {
          };
        };
        //Actions which will be used in template file directly are defined here
        //we separate them from init() actions because these methods could
        //have been replaced by developer in template file and don't need to be overriden
        service.addTemplateActions = function ($scope, entityName, remoteResource, uuid4, Upload, flash, $http, PersianDateService, configs) {
          $scope.message = function (key) {
            return $scope.$root.message(key, appName);
          };
          $scope.actions.common.getSuggestPageSize = function () {
        	  return configs.defaultSuggestPageSize;
          };
          $scope.actions.common.refreshSuggest = function (relatedEntityName, field, query, limit) {
            if (!$scope.widget[field]) $scope.widget[field] = [];
            remoteResource.searchOptions({
            		entity: relatedEntityName,
            		'query': query,
            		'pageSize': limit || $scope.actions.common.getSuggestPageSize()
            	}).$promise.then(function (data){
            		if(data && data.items && angular.isArray(data.items))
            			$scope.widget[field] = data;
            	});
          };
          $scope.actions.common.refreshSuggestPath = function (out, field, url, params) {
            if(!out) 
            	throw 'output must be defined and nut null';
            var suggestParams =  params || {};
            suggestParams.pageSize = suggestParams.pageSize || $scope.actions.common.getSuggestPageSize();
            $http.get(url, {params:suggestParams})
                .success(function (data, status, headers, config) {
                  out[field] = data;
                });
          };
          $scope.actions.common.refreshSuggestWithPagination = function (relatedEntityName, field, query, pageNumber, pageSize) {
        	if (!$scope.widget[field]) 
        		$scope.widget[field] = [];
            remoteResource
                .searchOptions({
                  'entity': relatedEntityName,
                  'pageNumber': pageNumber || 1,
                  'pageSize': pageSize || $scope.actions.common.getSuggestPageSize(),
                  'query': query
                })
                .$promise.then(function (data) {
                  if (data && data.items && angular.isArray(data.items)) {
                    if (data.totalSize > data.pageSize) {
                      var obj = {
                        pageNumber: data.pageNumber,
                        pageSize: data.pageSize,
                        totalSize: data.totalSize
                      };
                      obj['is:pagination'] = true;
                      data.items.push(obj);
                    }
                    $scope.widget[field] = data;
                  }
                });
          };
          $scope.actions.common.refreshSuggestPathWithPagination = function (out, field, url, params) {
            if(!out) 
            	throw 'output must be defined and nut null';
            var suggestParams =  params || {};
            suggestParams.pageSize = suggestParams.pageSize || $scope.actions.common.getSuggestPageSize();
            suggestParams.pageNumber = suggestParams.pageNumber || 1;
            $http.get(url, {params:suggestParams})
                .success(function (data, status, headers, config) {
                  if (data && data.items && angular.isArray(data.items)) {
                    if (data.totalSize > data.pageSize) {
                      var obj = {
                        pageNumber: data.pageNumber,
                        pageSize: data.pageSize,
                        totalSize: data.totalSize
                      };
                      obj['is:pagination'] = true;
                      data.items.push(obj);
                    }
                    out[field] = data;
                  }
                });
          };
          $scope.actions.common.fillDate = function (field) {
            $scope.widget[field] = {};
            $scope.widget[field].isOpen = false;
            $scope.widget[field].openDate = function ($event) {
              if ($event) {
                $event.preventDefault();
                $event.stopPropagation();
              }
              $scope.widget[field].isOpen = true;
            };
            $scope.widget[field].options = {
              startingDay: 6
            };
          };
          $scope.actions.common.getDownloadLink = function (field, itemPK) {
            return '/' + appName + '/rest/' + entityName + '/download/' + itemPK + '/' + field;
          };
          $scope.actions.common.onFileChange = function ($files, field, uploadUrl) {
            if (!$files || !$files.length)
              return;
            $scope.widget[field] = {message: ''};
            if (!uploadUrl)
              uploadUrl = '/' + appName + '/rest/' + entityName + '/upload/' + field;
            for (var i = 0; i < $files.length; i++) {
              Upload.upload({
                url: uploadUrl,
                method: 'POST',
                file: $files[i],
                fileFormDataName: uuid4.generate()
              }).progress(function (evt) {
                //TODO progress bar
              }).success(function (data, status, headers, config) {
                if (data.success)
                  $scope.data[field] = data.data;
                $scope.widget[field] = {message: data.message};
              });
            }
          }
          function getWizardCtrl() {
            var wizardElem = $('div[hide-indicators]');
            if (wizardElem.length !== 0) {
              return wizardElem.controller('wizard');
            }
          }
          $scope.actions.common.goNextInWizard = function () {
            if (!$scope.actions.common.wizardHasNext())
              return false;
            var wizardCtrl = getWizardCtrl();
            wizardCtrl.next();
            return true;
          }
          $scope.actions.common.goPreviousInWizard = function () {
            if (!$scope.actions.common.wizardHasPrevious())
              return false;
            getWizardCtrl().previous();
            return true;
          }
          $scope.actions.common.goToWizardLastStep = function () {
            var wizardCtrl = getWizardCtrl();
            if (!wizardCtrl)
              return false;
            wizardCtrl.goTo(wizardCtrl.getNumberOfSteps() - 1);
            return true;
          }
          $scope.actions.common.wizardHasNext = function () {
            var wizardCtrl = getWizardCtrl();
            return wizardCtrl && wizardCtrl.currentStepNumber() < wizardCtrl.getNumberOfSteps();
          }
          $scope.actions.common.wizardHasPrevious = function () {
            var wizardCtrl = getWizardCtrl();
            return wizardCtrl && wizardCtrl.currentStepNumber() !== 1;
          }
        };
        return service;
      }
  );
})();