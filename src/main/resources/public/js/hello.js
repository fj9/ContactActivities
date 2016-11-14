angular.module('hello', ['ngRoute', 'ngMaterial'])
    .config(function ($routeProvider, $httpProvider) {

        $routeProvider.when('/', {
            templateUrl: 'home.html',
            controller: 'home'
        }).when('/login', {
            templateUrl: 'login.html',
            controller: 'navigation'
        }).when('/contact-view', {
            templateUrl: 'contact-view.html',
            controller: 'contact-controller'
        }).otherwise('/');

        $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

    })
    .controller('home', function ($scope, $http) {
            $scope.greeting = 'WELCOME';
    })
    .controller('contact-controller', function ($scope, $http, $timeout, $mdSidenav, $log) {
        $scope.loadContact = function (contactId) {
            // if ($scope.contactId != null) {
                $http.get('contacts/' + contactId).success(function (data) {
                    $scope.contact = data;
                    $log.debug('contact ' + $scope.contact.firstName);
                });
            // }
        }
        // $scope.loadContact();
        $scope.toggleLeft = buildDelayedToggler('left');
        /**
         * Supplies a function that will continue to operate until the
         * time is up.
         */
        function debounce(func, wait, context) {
            var timer;

            return function debounced() {
                var context = $scope,
                    args = Array.prototype.slice.call(arguments);
                $timeout.cancel(timer);
                timer = $timeout(function () {
                    timer = undefined;
                    func.apply(context, args);
                }, wait || 10);
            };
        }

        /**
         * Build handler to open/close a SideNav; when animation finishes
         * report completion in console
         */
        function buildDelayedToggler(navID) {
            return debounce(function () {
                // Component lookup should always be available since we are not using `ng-if`
                $mdSidenav(navID)
                    .toggle()
                    .then(function () {
                        $log.debug("toggle " + navID + " is done");
                    });
            }, 200);
        }
    })
    .controller('LeftCtrl', function ($scope, $timeout, $mdSidenav, $log, $mdDialog, $http) {

        $scope.loadContacts = function () {
            $http.get('contacts').success(function (data) {
                $scope.contacts = data;
            });
        };
        $scope.loadContacts();
        $scope.close = function () {
            $scope.status = '';
            // Component lookup should always be available since we are not using `ng-if`
            $mdSidenav('left').close()
                .then(function () {
                    $log.debug("close LEFT is done");
                });

        };
        $scope.viewContact = function (contactId, event) {
            $scope.close();
            // $scope.contactId = contactId;
            $scope.loadContact(contactId);


            // $mdDialog.show(
            //     $mdDialog.alert()
            //         .title('Navigating')
            //         .textContent('Inspect ' + contactId)
            //         .ariaLabel('Person inspect demo')
            //         .ok('Neat!')
            //         .targetEvent(event)

        };
        $scope.addContactDialog = function (event, $http) {
            $mdDialog.show({
                controller: AddContactController,
                templateUrl: 'addcontact.html',
                parent: angular.element(document.body),
                targetEvent: event,
                clickOutsideToClose: true,
                fullscreen: $scope.customFullscreen // Only for -xs, -sm breakpoints.
            })
                .then(function (firstName, $http) {
                    $log.debug("then")
                    $scope.status = 'New contact ' + firstName + ' added.';
                    $scope.loadContacts();
                    // $http.get('contacts').success(function (data) {
                    //     $scope.contacts = data;
                    // });
                }, function () {
                    $log.debug("else")
                    $scope.status = 'You didn\'t add a contact.';
                });
        };
        function AddContactController($scope, $mdDialog, $http) {
            $scope.hide = function () {
                $mdDialog.hide();
            };

            $scope.cancel = function () {
                $log.debug("add contact cancel");
                $mdDialog.cancel();
            };
            $scope.addContact = function (firstName, lastName, emailAddress) {
                $log.debug("add contact " + firstName);
                var newContact = {
                    firstName: firstName,
                    lastName: lastName,
                    emailAddress: emailAddress
                };
                var res = $http.post('/contacts', newContact);
                res.success(function (data, status, headers, config) {
                    $log.debug("post for contact successful " + firstName)
                    $mdDialog.hide(firstName);
                });
                res.error(function (data, status, headers, config) {
                    $log.debug("post for contact error " + firstName)
                    alert("failure message: " + JSON.stringify({data: data}));
                    $mdDialog.cancel();
                });
            };
        }

    })
    .controller('navigation', function ($scope, $location, $log) {
        $scope.currentNavItem = 'home';

        $scope.$watch('currentNavItem', function (current, old) {
            $location.url("/" + current)
        });
    });