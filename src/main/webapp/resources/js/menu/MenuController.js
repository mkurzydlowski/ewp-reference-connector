angular.module('menu').controller('MenuController', function ($scope, $location) {
    $scope.rootMenu = [{name:'Organization',
                        subItems:[{name:'Institution', page:'institution'},
                                  {name:'Coordinator', page:'coordinator'},
                                  {name:'Person', page:'person'}]},
                       {name:'Course', 
                        subItems:[{name:'Academic term', page:'academicterm'},
                                  {name:'Learning opp. spec.', page:'los'},
                                  {name:'Learning opp. instance', page:'loi'}]},
                       {name:'IIA',
                        subItems:[{name:'IIA', page:'iia'},
                        {name:'IIA Partner', page:'iiapartner'}]},
                       {name:'Mobility', page:'mobility'},
                       {name:'Other Connectors',
                        subItems:[{name:'Echo', page:'echo'},
                                  {name:'Discovery', page:'manifest'}]}];

    $scope.selectedRootItem = '';
    $scope.selectHomeItem = function() {
        $scope.selectedRootItem = '';
        $location.path('home');
    };
    $scope.selectRootItem = function(item) {
        if ($scope.selectedRootItem !== item) {
            $scope.selectedRootItem = item;
            if (item.page) {
                $location.path(item.page);
                $scope.selectedSubItem = '';
            }
        }
    };

    $scope.selectSubItem = function(item) {
        $scope.selectedSubItem = item;
        $location.path(item.page);
    };
});
