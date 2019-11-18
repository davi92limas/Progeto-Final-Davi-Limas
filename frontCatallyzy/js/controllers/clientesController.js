angular.module("app").controller("clientesController", function ($scope, clientesAPI) {

    $scope.tituloApp = "Lista de clientes";
    $scope.clientes = [];
    var carregarClientes = function () {
        clientesAPI.getClientes()
            .then(function (response) {
                $scope.clientes = response.data;
            })
            .catch(function (response) {
                var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                $scope.mensagemDeErro = !!response.data.mensagem ? response.data.mensagem : mensagem;
            });
    };

    $scope.adicionarCliente = function (cliente) {
        var novoCliente = angular.copy(cliente);
        novoCliente.ativo = !novoCliente.ativo ? 0 : novoCliente.ativo;
        clientesAPI.saveCliente(novoCliente)
            .then(function (response) {
                delete $scope.cliente;
                $scope.clienteForm.$setPristine();
                carregarClientes();
            })
            .catch(function (response) {
                var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                $scope.mensagemDeErro = !!response.data.mensagem ? response.data.mensagem : mensagem;
            });
    };
    $scope.removerClientes = function (clientes) {
        var clientesParaRemover = clientes.filter(function (item) {
            return item.selecionado;
        });
        clientesParaRemover.forEach(function (item) {
            clientesAPI.removeCliente(item)
                .then(function (response) {
                    carregarClientes();
                })
                .catch(function (response) {
                    var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                    $scope.mensagemDeErro = !!response.data.mensagem ? response.data.mensagem : mensagem;
                });
        });
    };
    $scope.removerCliente = function (clienteParaRemover) {
        clientesAPI.removeCliente(clienteParaRemover)
            .then(function (response) {
                carregarClientes();
            })
            .catch(function (response) {
                var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                $scope.mensagemDeErro = !!response.data.mensagem ? response.data.mensagem : mensagem;
            });
    };
    $scope.editarCliente = function (clienteParaEditar) {
        $scope.cliente = angular.copy(clienteParaEditar);
        $scope.cliente.dataCadastro = convertData($scope.cliente.dataCadastro);
    };
    var convertData = function (dataLong) {
        if (!dataLong) {
            return;
        }
        return new Date(dataLong);
    };
    $scope.temClienteselecionado = function (clientes) {
        return clientes.some(function (item) {
            return item.selecionado;
        });
    };
    $scope.ordenarPor = function (nomeDoCampo) {
        $scope.campoParaOrdenacao = nomeDoCampo;
        $scope.direcaoDaOrdenacao = !$scope.direcaoDaOrdenacao;
    };
    carregarClientes();
});