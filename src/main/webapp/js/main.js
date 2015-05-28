var springular = angular.module("springular", []);

springular.controller("itemsController", function ($scope, itemServiceHttpFacade) {

    $scope.setCurrent = function(selectableItem) {
        itemServiceHttpFacade.setSelected(selectableItem)
            .success(function (data, status, headers, config){
                $scope.items = data;
            });
    };

    var retrieveItems = function() {
        itemServiceHttpFacade.getItems()
            .success(function (data, status, headers, config){
                $scope.items = data;
            });
    };

    retrieveItems();

});

springular.factory("itemServiceHttpFacade", function ($http) {

    var _getItems = function () {
        return $http.get("/rest/items");
    }

    var _setSelected = function (selectableItem) {
        return $http.get("/rest/change_item", {
            params: {
                name: selectableItem.name
            }
        });
    }

    return {
        getItems: _getItems,
        setSelected: _setSelected
    }

});