angular.module("app").factory("funcionariosAPI", function ($http, configAPI) {
    var _getFuncionarios = function () {
        return $http.get(configAPI.resourceFuncionarios);
    };
    var _saveFuncionario = function (funcionario) {
        if (!!funcionario.idFuncionario) {
            return $http.put(configAPI.resourceFuncionarios, funcionario)
        }
        return $http.post(configAPI.resourceFuncionarios, funcionario);
    };

    var _removeFuncionario = function (funcionarioParaRemover) {
        var url = configAPI.resourceFuncionarios + "/" + funcionarioParaRemover.idFuncionario;
        return $http.delete(url);
    }

    return {
        getFuncionarios: _getFuncionarios,
        saveFuncionario: _saveFuncionario,
        removeFuncionario: _removeFuncionario
    };
});