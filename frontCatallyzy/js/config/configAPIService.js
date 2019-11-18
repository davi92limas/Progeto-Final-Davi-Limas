angular.module("app").value("configAPI", {
    resourceClientes: "http://localhost:8080/api/clientes",
    resourceCamisas: "http://localhost:8080/api/camisas",
    resourceFuncionarios: "http://localhost:8080/api/funcionarios",
    resourceVendas: "http://localhost:8080/api/vendas",
    resourceMarcas: "http://localhost:8080/api/marcas"
});