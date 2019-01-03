angular.module('contacts.contact')
    .controller('EventListCtrl', ['$scope', 'EventResource', 'dialogs', 'Flash', '$location',
        function ($scope, EventResource, dialogs, Flash, $location) {
            //start of controller initialization
            $scope.data = {};
            $scope.actions = {};
            $scope.filter = {};
            $scope.parameters = {};
            $scope.controls = {};

            $scope.controls.datePickerOptions = {
                calType: 'jalali',
                format: 'YYYY-MM-DD hh:mm',
                zIndex: 10000,
                autoClose: true,
                multiple: false
            };
            // end of controller initialization

            var initiatePageFromURL = function () {
                var searchCriteriaFromURL = $location.search();
                $scope.filter = {};
                $scope.filter.title = searchCriteriaFromURL.title;

                $scope.filter.eventStartFrom = searchCriteriaFromURL.eventStartFrom == null ? null : convertFromUTCz2JalaliInTehranTimeZone(searchCriteriaFromURL.eventStartFrom);
                $scope.filter.eventStartTo = searchCriteriaFromURL.eventStartTo == null ? null : convertFromUTCz2JalaliInTehranTimeZone(searchCriteriaFromURL.eventStartTo);
                $scope.filter.eventEndFrom = searchCriteriaFromURL.eventEndFrom == null ? null : convertFromUTCz2JalaliInTehranTimeZone(searchCriteriaFromURL.eventEndFrom);
                $scope.filter.eventEndTo = searchCriteriaFromURL.eventEndTo == null ? null : convertFromUTCz2JalaliInTehranTimeZone(searchCriteriaFromURL.eventEndTo);
            };

            var findAndSearchCallBack = function (events) {
                for (var i = 0; i < events.length; i++) {
                    events[i].startDateTime = convertFromUTCz2JalaliInTehranTimeZone(events[i].startDateTime);
                    events[i].endDateTime = convertFromUTCz2JalaliInTehranTimeZone(events[i].endDateTime);
                }
                $scope.data.events = events;
            };

            $scope.actions.list = function () {
                EventResource.list().$promise.then(findAndSearchCallBack);
                $location.search({});
                initiatePageFromURL();
            };

            $scope.actions.showSaveOrUpdateDialog = function (id) {
                var openDialog = function (newContact) {
                    var url = 'event/event.edit.html';
                    var ctrlr = 'EventSaveOrUpdateCtrl';
                    var data = newContact;
                    var opts = {};
                    dialogs.create(url, ctrlr, data, opts)
                        .result
                        .then(function (result) {
                            // alert('edit dialog is closed');
                            $scope.actions.search();
                        });
                };

                var newContact;
                if (id == 0) {
                    newContact = {id: null};
                    openDialog(newContact)
                } else {
                    EventResource.find({id:id}).$promise.then(function (eventToEdit) {
                        eventToEdit.startDateTime = convertFromUTCz2JalaliInTehranTimeZone(eventToEdit.startDateTime);
                        eventToEdit.endDateTime = convertFromUTCz2JalaliInTehranTimeZone(eventToEdit.endDateTime);
                        openDialog(eventToEdit);
                    });
                }
                ;
            };

            $scope.actions.search = function () {
                var titleFieldIsSet = $scope.filter.title != null && $scope.filter.title != "";
                var eventStartFromFieldIsSet = $scope.filter.eventStartFrom != null && $scope.filter.eventStartFrom != "";
                var eventEndFromFieldIsSet = $scope.filter.eventEndFrom != null && $scope.filter.eventEndFrom != "";
                var eventStartToFieldIsSet = $scope.filter.eventStartTo != null && $scope.filter.eventStartTo != "";
                var eventEndToFieldIsSet = $scope.filter.eventEndTo != null && $scope.filter.eventEndTo != "";

                var searchCriteria = {};
                if (titleFieldIsSet) searchCriteria.title = $scope.filter.title;
                if (eventStartFromFieldIsSet) {
                    searchCriteria.eventStartFrom = covertFromJalaliInTehranTimeZone2UTCz($scope.filter.eventStartFrom);
                }
                if (eventEndFromFieldIsSet) {
                    searchCriteria.eventEndFrom = covertFromJalaliInTehranTimeZone2UTCz($scope.filter.eventEndFrom);
                }
                if (eventStartToFieldIsSet) {
                    searchCriteria.eventStartTo = covertFromJalaliInTehranTimeZone2UTCz($scope.filter.eventStartTo);
                }
                if (eventEndToFieldIsSet) {
                    searchCriteria.eventEndTo = covertFromJalaliInTehranTimeZone2UTCz($scope.filter.eventEndTo);
                }

                $location.search(searchCriteria);

                EventResource.search(searchCriteria).$promise.then(findAndSearchCallBack);

            };

            $scope.actions.delete = function (id) {
                EventResource.delete({id: id}).$promise.then(function (actionResult) {
                    initiatePageFromURL();
                    $scope.actions.search();
                    Flash.create('success', actionResult.message, 5000, {container: 'flash-in-index'});
                });
            };
            initiatePageFromURL();
            $scope.actions.search();
            // last line of EventListCtrl
        }])
    .controller('EventSaveOrUpdateCtrl',
        ['$scope', '$routeParams', '$uibModalInstance', 'EventResource', 'data', 'Flash',
            function ($scope, $routeParams, $uibModalInstance, EventResource, data, Flash) {
                //initialize controller
                $scope.data = {};
                $scope.actions = {};
                $scope.filter = {};
                $scope.parameters = {};
                $scope.controls = {};

                $.extend($scope.data, data);

                $scope.controls.datePickerOptions = {
                    calType: 'jalali',
                    format: 'YYYY-MM-DD hh:mm',
                    default: 'today',
                    zIndex: 10000,
                    autoClose: true,
                    multiple: false
                }
                //end of controller initialization

                $scope.actions.saveOrUpdate = function () {
                    console.log(!$scope.data.startDateTime);
                    var editedEvent = {};

                    editedEvent.id = $scope.data.id;
                    editedEvent.title = $scope.data.title;
                    editedEvent.description = $scope.data.description;
                    editedEvent.startDateTime = covertFromJalaliInTehranTimeZone2UTCz($scope.data.startDateTime);
                    editedEvent.endDateTime = covertFromJalaliInTehranTimeZone2UTCz($scope.data.endDateTime);


                    EventResource.saveOrUpdate(editedEvent).$promise.then(
                        function (actionResult) {
                            if (actionResult.success == true) {
                                Flash.create('success', actionResult.message, 5000, {container: 'flash-in-index'});
                                $uibModalInstance.close(actionResult);
                            } else {
                                Flash.create('danger', actionResult.message, 5000, {container: 'flash-in-modal'});
                            }
                        });
                };


                $scope.actions.dismiss = function () {
                    $uibModalInstance.dismiss('');
                };
            }
        ])
    .controller("EventDisplayCtrl",
        ['$scope', '$routeParams', 'EventResource', 'dialogs', '$window',
            function ($scope, $routeParams, EventResource, dialogs, $window) {
                $scope.data = {};
                $scope.actions = {};
                $scope.filter = {};
                $scope.parameters = {};

                var id = $routeParams.id;

                var initializePageWithEventHavingId = function(id){
                    EventResource.find({id: id}).$promise.then(function (serverEvent) {
                        $scope.data.event = {};
                        $scope.data.event.id = serverEvent.id;
                        $scope.data.event.title = serverEvent.title;
                        $scope.data.event.description = serverEvent.description;
                        $scope.data.event.startDateTime = convertFromUTCz2JalaliInTehranTimeZone(serverEvent.startDateTime);
                        $scope.data.event.endDateTime = convertFromUTCz2JalaliInTehranTimeZone(serverEvent.endDateTime);

                    });
                };

                initializePageWithEventHavingId(id);



                $scope.actions.showSaveOrUpdateDialog = function (id) {
                    var openDialog = function (newContact) {
                        var url = 'event/event.edit.html';
                        var ctrlr = 'EventSaveOrUpdateCtrl';
                        var data = newContact;
                        var opts = {};
                        dialogs.create(url, ctrlr, data, opts)
                            .result
                            .then(function (result) {
                                initializePageWithEventHavingId(id);
                            });
                    };

                    var newEvent;
                    if (id == 0) {
                        newEvent = {id: null};
                        openDialog(newEvent)
                    } else {
                        EventResource.find({id: id}).$promise.then(function(newEvent){
                            openDialog(newEvent);
                        });
                    }
                };

                $scope.actions.back = function () {
                    $window.history.back();
                };
            }]
    );
;



