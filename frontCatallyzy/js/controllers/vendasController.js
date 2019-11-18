angular.module("app").controller("vendasController",
    function ($scope, $http, $injector) {
        $scope.app = "Cadastro de Vendas";

        $scope.vendas = [];
        $scope.clientes = [];
        $scope.funcionarios = [];
        $scope.Camisas = [];

        var VendasService = $injector.get('vendasAPI');
        var ClientesService = $injector.get('clientesAPI');
        var FuncionariosService = $injector.get('funcionariosAPI');
        var CamisasService = $injector.get('camisasAPI');

        function _carregarClientes() {
            ClientesService.getClientes()
                .then(function (response) {
                    $scope.clientes = response.data;
                })
                .catch(function (response) {
                    $scope.mensagemErro = "Ocorreu um problema ao consultar as clientes: "
                        + response.status + " - " + response.statusText + " " + response.data;
                });
        }

        function _carregarFuncionarios() {
            FuncionariosService.getFuncionarios()
                .then(function (response) {
                    $scope.funcionarios = response.data;
                })
                .catch(function (response) {
                    $scope.mensagemErro = "Ocorreu um problema ao consultar os funcionarios: "
                        + response.status + " - " + response.statusText + " " + response.data;
                });
        };

        function _carregarCamisas() {
            CamisasService.getCamisas()
                .then(function (response) {
                    $scope.camisas = response.data;
                })
                .catch(function (response) {
                    $scope.mensagemErro = "Ocorreu um problema ao consultar as camisas: "
                        + response.status + " - " + response.statusText + " " + response.data;
                });
        };

        function _carregarVendas() {
            VendasService.getVendas()
                .then(function (response) {
                    $scope.vendas = response.data;
                })
                .catch(function (response) {
                    $scope.mensagemErro = "Ocorreu um problema ao consultar os vendas: "
                        + response.status + " - " + response.statusText + " " + response.data;
                });
        };

        $scope.salvarVenda = function (venda) {
            console.log(venda);
            _salvarVenda(venda);
        };

        function _salvarVenda(registroVenda) {
            VendasService.saveVenda(registroVenda)
                .then(function (response) {
                    delete $scope.venda;
                    $scope.vendaForm.$setPristine();
                    _carregarVendas();
                })
                .catch(function (response) {
                    $scope.mensagemErro = "Ocorreu um problema ao salvar o venda: "
                        + response.status + " - " + response.statusText + " " + response.data;
                });
        };

        function _removerVenda(venda) {
            VendasService.removerVenda(venda)
                .then(function (response) {

                    _carregarVendas();

                })
                .catch(function (response) {
                    $scope.mensagemErro = "Ocorreu um problema ao remover o venda: "
                        + response.status + " - " + response.statusText + " " + response.data;
                });
        }

        $scope.removerVendas = function (vendas) {
            vendas.forEach(function (venda) {
                if (venda.selecionado) {
                    _removerVenda(venda);
                }
            });
        };

        $scope.totalizarVenda = function (venda) {
            var total = 0;
            if (!!venda.camisa1) {
                $scope.camisas.forEach(function (camisa) {
                    console.log(camisa);
                    if (venda.camisa1.idCamisa == camisa.idCamisa) {
                        total = total + camisa.preco;
                    }
                });
            }
            if (!!venda.camisa2) {
                $scope.camisas.forEach(function (camisa) {
                    if (venda.camisa2.idCamisa == camisa.idCamisa) {
                        total = total + camisa.preco;
                    }
                });
            }
            if (!!venda.camisa3) {
                $scope.camisas.forEach(function (camisa) {
                    if (venda.camisa3.idCamisa == camisa.idCamisa) {
                        total = total + camisa.preco;
                    }
                });
            }
            console.log(total);
            $scope.venda.totalVenda = total;
        };

        $scope.removerVenda = function (venda) {
            _removerVenda(venda);
        };

        $scope.editarVenda = function (venda) {
            venda.dataVenda = new Date(venda.dataVenda);
            $scope.venda = angular.copy(venda);
        };

        $scope.isVendaSelecionado = function (vendas) {
            return vendas.some(function (venda) {
                return venda.selecionado;
            });
        };

        $scope.ordenarPor = function (campo) {
            $scope.criterioDeOrdenacao = campo;
            $scope.direcaoDaOrdenacao = !$scope.direcaoDaOrdenacao;
        };

        _carregarCamisas();
        _carregarFuncionarios();
        _carregarClientes();
        _carregarVendas();
    });