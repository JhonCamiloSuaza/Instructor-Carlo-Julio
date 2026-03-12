//  NAVEGACION 
function mostrarSeccion(id, botonActivo) {
  // Oculta todas las secciones
  document.querySelectorAll(".seccion").forEach(function(s) {
    s.classList.remove("visible");
  });
  // Quita el activo de todos los botones nav
  document.querySelectorAll("nav button").forEach(function(b) {
    b.classList.remove("activo");
  });
  // Muestra la sección seleccionada
  document.getElementById(id).classList.add("visible");
  botonActivo.classList.add("activo");
}

//  EJERCICIO 1 
document.getElementById("btnMostrar").addEventListener("click", function() {
  document.getElementById("mensaje").textContent = "Hola mundo desde JavaScript";
});

// EJERCICIO 2 
document.getElementById("btnSumar").addEventListener("click", function() {
  const num1 = document.getElementById("numero1").value;
  const num2 = document.getElementById("numero2").value;
  const suma = Number(num1) + Number(num2);
  document.getElementById("resultadoSuma").textContent = "resultado: " + suma;
});

//  EJERCICIO 3 
const seccionColor = document.getElementById("ej3");

document.getElementById("rojo").addEventListener("click", function() {
  seccionColor.style.backgroundColor = "#ff4d4d";
  seccionColor.style.color = "#0f0f0f";
  document.querySelector("#ej3 h2").style.color = "#0f0f0f";
});

document.getElementById("azul").addEventListener("click", function() {
  seccionColor.style.backgroundColor = "#4d79ff";
  seccionColor.style.color = "#ffffff";
  document.querySelector("#ej3 h2").style.color = "#ffffff";
});

document.getElementById("verde").addEventListener("click", function() {
  seccionColor.style.backgroundColor = "#4dff88";
  seccionColor.style.color = "#0f0f0f";
  document.querySelector("#ej3 h2").style.color = "#0f0f0f";
});
//  EJERCICIO 4 
let conteo = 0;

document.getElementById("btnContar").addEventListener("click", function() {
  if (conteo < 50) {
    conteo = conteo + 1;
    document.getElementById("contador").textContent = conteo;
    document.getElementById("mensajeContador").textContent = "";
  } else {
    document.getElementById("mensajeContador").textContent = "¡Máximo alcanzado: 50!";
  }
});

document.getElementById("btnReiniciar").addEventListener("click", function() {
  conteo = 0;
  document.getElementById("contador").textContent = "0";
  document.getElementById("mensajeContador").textContent = "";
});