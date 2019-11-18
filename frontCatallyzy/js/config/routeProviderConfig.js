angular.module("app").config(AppConfig);

AppConfig.$inject = ['$routeProvider'];
function AppConfig($routeProvider) {
    $routeProvider
        .when('/', {
            templateUrl: 'home.html',
            controller: 'homeController'
        })
        .when('/clientes', {
            templateUrl: 'views/clientes.html',
            controller: 'clientesController'
        })
        .when('/camisas', {
            templateUrl: 'views/camisas.html',
            controller: 'camisasController'
        })

        .when('/marcas', {
            templateUrl: 'views/marcas.html',
            controller: 'marcasController'
        })

        .when('/funcionarios', {
            templateUrl: 'views/funcionarios.html',
            controller: 'funcionariosController'
        })
        .when('/vendas', {
            templateUrl: 'views/vendas.html',
            controller: 'vendasController'
        })
        .when('/meusContatos', {
            templateUrl: 'views/meusContatos.html'

        })

        .when('/funcionarioDoMes', {
            templateUrl: 'views/funcionarioDoMes.html'

        })
        .when('/marcasDeCamisa', {
            templateUrl: 'views/marcasDeCamisa.html'

        })
        .otherwise({ redirectTo: "/" });
}

