angular.module("app").filter("name", function() {
    return function(input) {
        if(!input) {
            return;
        }
        var partesDoNome = input.split(" ");
        var nomesFormatados = partesDoNome.map(function(parte) {
            if (/(de|da)/.test(parte)) return parte;
            return parte.charAt(0).toUpperCase() + parte.substring(1).toLowerCase();
        });
        return nomesFormatados.join(" ");
    };
});