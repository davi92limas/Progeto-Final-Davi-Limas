angular.module("app").controller("marcasController", function ($scope, marcasAPI) {
    $scope.tituloApp = "Lista de marcas";
    $scope.marcas = [];
    var carregarMarcas = function () {
        marcasAPI.getMarcas()
            .then(function (response) {
                $scope.marcas = response.data;
            })
            .catch(function (response) {
                var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                $scope.mensagemDeErro = !!response.data.mensagem ? response.data.mensagem : mensagem;
            });
    };

    $scope.adicionarMarca = function (marca) {
        var novaMarca = angular.copy(marca);
        marcasAPI.saveMarca(novaMarca)
            .then(function (response) {
                delete $scope.marca;
                $scope.marcaForm.$setPristine();
                carregarMarcas();
            })
            .catch(function (response) {
                var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                $scope.mensagemDeErro = !!response.data.mensagem ? response.data.mensagem : mensagem;
            });
    };

    $scope.removerMarca = function (marcaParaRemover) {
        if (!confirm('Deseja realmente excluir?')) {
            return;
        };
        marcasAPI.removeMarca(marcaParaRemover)
            .then(function (response) {
                carregarMarcas();
            })
            .catch(function (response) {
                var mensagem = "Deu erro: " + response.status + " - " + response.statusText;
                $scope.mensagemDeErro = !!response.data.mensagem ? response.data.mensagem : mensagem;
            });
    };
    $scope.editarMarca = function (marcaParaEditar) {
        $scope.marca = angular.copy(marcaParaEditar);
    };
    $scope.temMarcaSelecionado = function (emarcas) {
        return marcas.some(function (item) {
            return item.selecionado;
        });
    };
    $scope.ordenarPor = function (nomeDoCampo) {
        $scope.campoParaOrdenacao = nomeDoCampo;
        $scope.direcaoDaOrdenacao = !$scope.direcaoDaOrdenacao;
    };
    carregarMarcas();
});