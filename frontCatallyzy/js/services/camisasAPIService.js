angular.module("app").factory("camisasAPI", function ($http, configAPI) {
    var _getCamisas = function () {
        return $http.get(configAPI.resourceCamisas);
    };

    var _saveCamisa = function (camisa) {
        if (!!camisa.idCamisa) {
            return $http.put(configAPI.resourcecamisas, camisa)
        }
        return $http.post(configAPI.resourceCamisas, camisa);
    };

    var _removeCamisa = function (camisaParaRemover) {
        var url = configAPI.resourceCamisas + "/" + camisaParaRemover.idCamisa;
        return $http.delete(url);
    }

    return {
        getCamisas: _getCamisas,
        saveCamisa: _saveCamisa,
        removeCamisa: _removeCamisa
    };
});