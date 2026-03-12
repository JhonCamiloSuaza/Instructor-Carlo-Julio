
let conteo = 0;

const btn = document.getElementById("btnContar");
const btnReiniciar = document.getElementById("btnReiniciar");

btn.addEventListener("click", function() {

  if (conteo < 50) {
    conteo = conteo + 1;
    document.getElementById("contador").textContent = "Clics: " + conteo;
  } else {
    document.getElementById("contador").textContent = "¡Máximo alcanzado: 50!";
  }

});


btnReiniciar.addEventListener("click", function() {
  conteo = 0;
  document.getElementById("contador").textContent = "Clics: 0";
});