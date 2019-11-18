angular.module("app").controller("funcionariosController", function ($scope, funcionariosAPI) {

    $scope.tituloApp = "Lista de funcionarios";
    $scope.funcionarios = [];
    var carregarFuncionarios = function () {
        funcionariosAPI.getFuncionarios()
            .then(function (response) {
                $scope.funcionarios = response.data;
            })
            .catch(function (response) {
                var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                $scope.mensagemDeErro = !!response.data.mensagem ? response.data.mensagem : mensagem;
            });
    };

    $scope.adicionarFuncionario = function (funcionario) {
        var novoFuncionario = angular.copy(funcionario);
        novoFuncionario.ativo = !novoFuncionario.ativo ? 0 : novoFuncionario.ativo;
        funcionariosAPI.saveFuncionario(novoFuncionario)
            .then(function (response) {
                delete $scope.funcionario;
                $scope.funcionarioForm.$setPristine();
                carregarFuncionarios();
            })
            .catch(function (response) {
                var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                $scope.mensagemDeErro = !!response.data.mensagem ? response.data.mensagem : mensagem;
            });
    };
    $scope.removerFuncionarios = function (funcionarios) {
        var funcionariosParaRemover = funcionarios.filter(function (item) {
            return item.selecionado;
        });
        funcionariosParaRemover.forEach(function (item) {
            funcionariosAPI.removeFuncionario(item)
                .then(function (response) {
                    carregarFuncionarios();
                })
                .catch(function (response) {
                    var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                    $scope.mensagemDeErro = !!response.data.mensagem ? response.data.mensagem : mensagem;
                });
        });
    };
    $scope.removerFuncionario = function (funcionarioParaRemover) {
        funcionariosAPI.removerFuncionario(funcionarioParaRemover)
            .then(function (response) {
                carregarFuncionarios();
            })
            .catch(function (response) {
                var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                $scope.mensagemDeErro = !!response.data.mensagem ? response.data.mensagem : mensagem;
            });
    };
    $scope.editarFuncionario = function (funcionarioParaEditar) {
        $scope.funcionario = angular.copy(funcionarioParaEditar);
        $scope.funcionario.dataCadastro = convertData($scope.funcionario.dataCadastro);
    };
    var convertData = function (dataLong) {
        if (!dataLong) {
            return;

        }
        return new Date(dataLong);
    };
    $scope.temFuncionarioselecionado = function (funcionarios) {
        return funcionarios.some(function (item) {
            return item.selecionado;
        });
    };
    $scope.ordenarPor = function (nomeDoCampo) {
        $scope.campoParaOrdenacao = nomeDoCampo;
        $scope.direcaoDaOrdenacao = !$scope.direcaoDaOrdenacao;
    };
    carregarFuncionarios();
});