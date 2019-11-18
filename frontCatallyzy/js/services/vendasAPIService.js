angular.module("app").factory("vendasAPI", function ($http, configAPI) {
    var _getVendas = function () {
        return $http.get(configAPI.resourceVendas);
    };

    var _saveVenda = function (venda) {
        if (!!venda.idVenda) {
            return $http.put(configAPI.resourceVendas, venda)
        }
        return $http.post(configAPI.resourceVendas, venda);
    };

    var _removeVenda = function (vendaParaRemover) {
        var url = configAPI.resourceVendas + "/" + vendaParaRemover.idVenda;
        return $http.delete(url);
    }

    return {
        getVendas: _getVendas,
        saveVenda: _saveVenda,
        removeVenda: _removeVenda
    };
});