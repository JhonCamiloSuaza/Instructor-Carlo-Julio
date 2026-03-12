const btnRojo  = document.getElementById("rojo");
const btnAzul  = document.getElementById("azul");
const btnVerde = document.getElementById("verde");


btnRojo.addEventListener("click", function() {
  document.body.style.backgroundColor = "red";
  btnRojo.style.backgroundColor = "red";
  btnAzul.style.backgroundColor = "";
  btnVerde.style.backgroundColor = "";
});


btnAzul.addEventListener("click", function() {
  document.body.style.backgroundColor = "blue";
  btnAzul.style.backgroundColor = "blue";
  btnRojo.style.backgroundColor = "";
  btnVerde.style.backgroundColor = "";
});

btnVerde.addEventListener("click", function() {
  document.body.style.backgroundColor = "green";
  btnVerde.style.backgroundColor = "green";
  btnRojo.style.backgroundColor = "";
  btnAzul.style.backgroundColor = "";
});