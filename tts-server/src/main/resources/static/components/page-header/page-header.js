'use strict';
angular.module('app').component('pageHeader',{
    templateUrl:'components/page-header/page-header.html',
    bindings: {
        title: '@'
    },
});