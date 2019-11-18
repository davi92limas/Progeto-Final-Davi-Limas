angular.module("app").controller("camisasController", function ($scope, camisasAPI, marcasAPI) {
    $scope.tituloApp = "Lista de camisas";
    $scope.camisas = [];
    var carregaCamisas = function () {
        camisasAPI.getCamisas()
            .then(function (response) {
                $scope.camisas = response.data;
            })
            .catch(function (response) {
                var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                $scope.mensagemDeErro = !!response.data.mensagem ? response.data.mensagem : mensagem;
            });
    };
    $scope.marcas = [];
    var carregaMarcas = function () {
        marcasAPI.getMarcas()
            .then(function (response) {
                $scope.marcas = response.data;
            })
            .catch(function (response) {
                var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                $scope.mensagemDeErro = mensagem;
            });
    };
    $scope.adicionarCamisa = function (camisa) {
        var novoCamisa = angular.copy(camisa);
        camisasAPI.saveCamisa(novoCamisa)
            .then(function (response) {
                delete $scope.camisa;
                $scope.camisaForm.$setPristine();
                carregaCamisas();
            })
            .catch(function (response) {
                var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                $scope.mensagemDeErro = !!response.data.mensagem ? response.data.mensagem : mensagem;
            });
    };

    $scope.removerCamisa = function (camisaParaRemover) {
        if (!confirm('Deseja realmente excluir?')) {
            return;
        };
        camisasAPI.removeCamisa(camisaParaRemover)
            .then(function (response) {
               
            })
            .catch(function (response) {
                var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                $scope.mensagemDeErro = !!response.data.mensagem ? response.data.mensagem : mensagem;
                carregaCamisas();
            });
    };
    $scope.editarCamisa = function (camisaParaEditar) {
        $scope.camisa = angular.copy(camisaParaEditar);
    };
    $scope.temCamisaSelecionado = function (camisas) {
        return camisas.some(function (item) {
            return item.selecionado;
        });
    };
    $scope.ordenarPor = function (nomeDoCampo) {
        $scope.campoParaOrdenacao = nomeDoCampo;
        $scope.direcaoDaOrdenacao = !$scope.direcaoDaOrdenacao;
    };
    carregaCamisas();
    carregaMarcas();
});