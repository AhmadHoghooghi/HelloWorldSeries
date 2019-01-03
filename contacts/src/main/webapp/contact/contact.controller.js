angular.module('contacts.contact')
    .controller('ContactListCtrl', ['$scope', 'ContactResource', 'dialogs', 'Flash', '$location',
        function ($scope, ContactResource, dialogs, Flash, $location) {

            var initiatePageFromURL = function () {
                var searchCriteriaFromURL = $location.search();

                $.extend($scope, searchCriteriaFromURL);

                $scope.search();
            };


            var callback = function (value) {
                $scope.contacts = value.data;
            };

            function resetSearchForm() {
                $scope.name = "";
                $scope.homePhone = "";
                $scope.mobile = "";
                $scope.email = "";
            }

            $scope.findAll = function () {
                resetSearchForm();
                $location.search({});
                ContactResource.findAll(callback);
            };


            $scope.search = function () {
                var nameFieldIsSet = $scope.name != null && $scope.name != "";
                var homePhoneFieldIsSet = $scope.homePhone != null && $scope.homePhone != "";
                var mobileFieldIsSet = $scope.mobile != null && $scope.mobile != "";
                var emailFieldIsSet = $scope.email != null && $scope.email != "";

                var searchCriteria = {};
                if (nameFieldIsSet) searchCriteria.name = $scope.name;
                if (homePhoneFieldIsSet) searchCriteria.homePhone = $scope.homePhone;
                if (mobileFieldIsSet) searchCriteria.mobile = $scope.mobile;
                if (emailFieldIsSet) searchCriteria.email = $scope.email;

                $location.search(searchCriteria);

                ContactResource.search(searchCriteria, function (searchResultContacts) {
                    $scope.contacts = searchResultContacts;
                });
            };


            $scope.remove = function (id) {
                var removeCallback = function (result) {
                    var actionResult = result.data;
                    if (actionResult.success == true) {
                        Flash.create('success', actionResult.message, 5000, {container: 'flash-in-index'});
                    } else {
                        Flash.create('danger', actionResult.message, 5000, {container: 'flash-in-index'});
                    }
                    refresh();
                };

                ContactResource.remove(id, removeCallback);
            }

            var refresh = function () {
                initiatePageFromURL();
            }

            $scope.showSaveOrUpdateDialog = function (id) {
                var openDialog = function (newContact) {
                    var url = 'contact/contact.edit.html';
                    var ctrlr = 'ContactSaveOrUpdateCtrl';
                    var data = newContact;
                    var opts = {};
                    dialogs.create(url, ctrlr, data, opts)
                        .result
                        .then(function (result) {
                            refresh();
                        });
                };


                var newContact;
                if (id == null || id <= 0) {
                    newContact = {id: null};
                    openDialog(newContact)
                } else {
                    ContactResource.find(id, function (result) {
                        newContact = result.data;
                        openDialog(newContact);
                    });
                }
                ;
            };

            $scope.keyPressEventHandler = function (keyEvent) {
                if (keyEvent.which === 13) {
                    $scope.search();
                }
            }
            initiatePageFromURL();
        }])
    .controller('ContactSaveOrUpdateCtrl',
        ['$scope', '$routeParams', '$uibModalInstance', 'ContactResource', 'data', 'Flash',
            function ($scope, $routeParams, $uibModalInstance, ContactResource, data, Flash) {
                $scope.id = data.id;
                $scope.name = data.name;
                $scope.homePhone = data.homePhone;
                $scope.mobile = data.mobile;
                $scope.address = data.address;
                $scope.email = data.email;

                var editedContact = {};
                editedContact.id = $scope.id;
                editedContact.name = $scope.name;
                editedContact.homePhone = $scope.homePhone;
                editedContact.mobile = $scope.mobile;
                editedContact.address = $scope.address;
                editedContact.email = $scope.email;

                var actionResult;

                var updateCallback = function (result) {
                    actionResult = result.data;
                    if (actionResult.success == true) {
                        Flash.create('success', actionResult.message, 5000, {container: 'flash-in-index'});
                        $uibModalInstance.close(actionResult);
                    } else {
                        Flash.create('danger', actionResult.message, 5000, {container: 'flash-in-modal'});
                    }
                };
                ContactResource.saveOrUpdate(editedContact, updateCallback);




                $scope.dismiss = function () {
                    $uibModalInstance.dismiss('');
                };
            }
        ])
    .controller("ContactDisplayCtrl",
        ['$scope', '$routeParams', 'ContactResource', 'dialogs', '$window', function ($scope, $routeParams, ContactResource, dialogs, $window) {

            var id = $routeParams.id;

            var populatePageWithContactHavingId = function (id) {
                var findCallback = function (result) {
                    var contactToDisplay = result.data;
                    $scope.name = contactToDisplay.name;
                    $scope.homePhone = contactToDisplay.homePhone;
                    $scope.mobile = contactToDisplay.mobile;
                    $scope.address = contactToDisplay.address;
                    $scope.email = contactToDisplay.email;
                };
                ContactResource.find(id, findCallback);
            };
            populatePageWithContactHavingId(id);


            $scope.edit = function () {
                ContactResource.find(id, function (result) {
                    var contactToEdit = result.data;

                    var url = 'contact/contact.edit.html';
                    var ctrlr = 'ContactSaveOrUpdateCtrl';
                    var data = contactToEdit;
                    var opts = {};
                    dialogs.create(url, ctrlr, data, opts)
                        .result
                        .then(function () {
                            populatePageWithContactHavingId(id);
                        });
                });
            }

            $scope.back = function () {
                $window.history.back();
            };
        }]
    );
;



