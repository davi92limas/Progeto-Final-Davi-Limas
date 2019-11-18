angular.module("app").factory("marcasAPI", function ($http, configAPI) {
    var _getMarcas = function () {
        return $http.get(configAPI.resourceMarcas);
    };

    var _saveMarca = function (marca) {
        if (!!marca.idMarca) {
            return $http.put(configAPI.resourceMarcas, marca)
        }
        return $http.post(configAPI.resourceMarcas, marca);
    };

    var _removeMarca = function (marcaParaRemover) {
        var url = configAPI.resourceMarcas + "/" + marcaParaRemover.idMarca;
        return $http.delete(url);
    }

    return {
        getMarcas: _getMarcas,
        saveMarca: _saveMarca,
        removeMarca: _removeMarca
    };
});