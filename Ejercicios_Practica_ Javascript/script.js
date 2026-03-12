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

// EJERCICIO 5 
document.getElementById("btnAgregar").addEventListener("click", function() {

  const texto = document.getElementById("inputTarea").value;

  if (texto === "") {
    alert("Escribe una tarea primero");
    return;
  }

  const li = document.createElement("li");
  li.textContent = texto;

  const btnEliminar = document.createElement("button");
  btnEliminar.textContent = "✕";
  btnEliminar.classList.add("btn-eliminar");

  btnEliminar.addEventListener("click", function() {
    li.remove();
  });

  li.appendChild(btnEliminar);

  document.getElementById("listaTareas").appendChild(li);
  document.getElementById("inputTarea").value = "";

});

// EJERCICIO 6 
document.getElementById("btnEnviar").addEventListener("click", function() {


  const nombre = document.getElementById("nombre").value;
  const correo = document.getElementById("correo").value;
  const edad   = document.getElementById("edad").value;

  const mensaje = document.getElementById("mensajeFormulario");

  if (nombre === "" || correo === "" || edad === "") {
    mensaje.textContent = "⚠️ Debe completar todos los campos";
    mensaje.style.color = "#ff4d4d";
    return;
  }

  mensaje.textContent = "✅ Formulario enviado correctamente";
  mensaje.style.color = "#4dff88";

});

// EJERCICIO 7 


function calcular(operacion) {

  const num1 = Number(document.getElementById("num1").value);
  const num2 = Number(document.getElementById("num2").value);
  const resultado = document.getElementById("resultadoCalc");

  if (document.getElementById("num1").value === "" ||
      document.getElementById("num2").value === "") {
    resultado.textContent = "⚠️ Ingresa los dos números";
    resultado.style.color = "#ff4d4d";
    return;
  }


  let res;
  if (operacion === "+") res = num1 + num2;
  if (operacion === "-") res = num1 - num2;
  if (operacion === "*") res = num1 * num2;
  if (operacion === "/") {
    if (num2 === 0) {
      resultado.textContent = "⚠️ No se puede dividir entre 0";
      resultado.style.color = "#ff4d4d";
      return;
    }
    res = num1 / num2;
  }


  resultado.textContent = "Resultado: " + res;
  resultado.style.color = "#4dff88";
}


document.getElementById("btnSuma").addEventListener("click",     function() { calcular("+"); });
document.getElementById("btnResta").addEventListener("click",    function() { calcular("-"); });
document.getElementById("btnMulti").addEventListener("click",    function() { calcular("*"); });
document.getElementById("btnDivision").addEventListener("click", function() { calcular("/"); });

// ---- EJERCICIO 8 ----
document.getElementById("btnAgregar8").addEventListener("click", function() {

  const texto = document.getElementById("inputTarea8").value;

  if (texto === "") {
    alert("Escribe una tarea primero");
    return;
  }

  const li = document.createElement("li");
  li.textContent = texto;

  const btnEliminar = document.createElement("button");
  btnEliminar.textContent = "Eliminar";
  btnEliminar.classList.add("btn-eliminar8");

  btnEliminar.addEventListener("click", function() {
    li.remove();
  });

  li.appendChild(btnEliminar);

  document.getElementById("listaTareas8").appendChild(li);

  document.getElementById("inputTarea8").value = "";

});


// ---- EJERCICIO 9 ----
let segundos = 0;
let intervalo = null;

// 1. Iniciar
document.getElementById("btnIniciar").addEventListener("click", function() {

  // Evita que se inicie doble
  if (intervalo !== null) return;

  intervalo = setInterval(function() {
    segundos = segundos + 1;
    document.getElementById("tiempoDisplay").textContent = segundos;
  }, 1000);

});

// 2.pausa el temporizador
document.getElementById("btnDetener").addEventListener("click", function() {
  clearInterval(intervalo);
  intervalo = null;
});

// 3. Reiniciar
document.getElementById("btnReiniciarTimer").addEventListener("click", function() {
  clearInterval(intervalo);
  intervalo = null;
  segundos = 0;
  document.getElementById("tiempoDisplay").textContent = "0";
});