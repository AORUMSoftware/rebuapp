document.addEventListener('DOMContentLoaded', function() {
	var elems = document.querySelectorAll('.sidenav');
	var instances = M.Sidenav.init(elems, {});
});
document.addEventListener('DOMContentLoaded', function() {
	var elems = document.querySelectorAll('.tooltipped');
	var instances = M.Tooltip.init(elems, {});
});
// parametro `a` opcional, ex: `tipo=1&nome=espada#custo=0`
// o valor padrão é da URL atual
// function GetQueryString(a) {
// a = a
// || window.location.search.substr(1).split('&').concat(
// window.location.hash.substr(1).split("&"));
//
// if (typeof a === "string")
// a = a.split("#").join("&").split("&");
//
// // se não há valores, retorna um objeto vazio
// if (!a)
// return {};
//
// var b = {};
// for (var i = 0; i < a.length; ++i) {
// // obtem array com chave/valor
// var p = a[i].split('=');
//
// // se não houver valor, ignora o parametro
// if (p.length != 2)
// continue;
//
// // adiciona a propriedade chave ao objeto de retorno
// // com o valor decodificado, substituindo `+` por ` `
// // para aceitar URLs codificadas com `+` ao invés de `%20`
// b[p[0]] = decodeURIComponent(p[1].replace(/\+/g, " "));
// }
// // retorna o objeto criado
// return b;
// }

// window.addEventListener("load", function(e) {
//
// var queries = GetQueryString()
//
// if (queries["code"]) {
//
// switch (parseInt(queries["code"])) {
//
// case 1:
// // Login incorreto
// console.log("Usuário ou senha incorretos.")
// break;
// case 2:
// // Erro no login
// console.log("Ocorreu um problema durante o login.")
// break;
// }
// }
// })
