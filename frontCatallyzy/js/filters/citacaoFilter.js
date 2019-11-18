angular.module("app").filter("citacao", function() {
    return function(input) {
        if (!input) {
            return;
        }
        var partesDoNome = input.split(" ");
        if (partesDoNome.length < 2) return partesDoNome[0].charAt(0).toUpperCase() + partesDoNome[0].substring(1).toLowerCase();
        var ultimoNome = partesDoNome.pop();
        partesDoNome = partesDoNome.reverse();
        var nomesFormatados = partesDoNome.map(function(nome) {
            if (/(da|de|do)/.test(nome)) return nome;
            return nome.charAt(0).toUpperCase() + nome.substring(1).toLowerCase();
        });
        return ultimoNome.toUpperCase() + '. ' + nomesFormatados.join(" ");
    };
});