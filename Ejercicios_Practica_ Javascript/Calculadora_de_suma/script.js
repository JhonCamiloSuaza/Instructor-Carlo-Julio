const boton = document.getElementById("btnSumar");

boton.addEventListener("click", function() {

  const num1 = document.getElementById("numero1").value;
  const num2 = document.getElementById("numero2").value;

  const suma = Number(num1) + Number(num2);

  document.getElementById("resultado").textContent = "resultado: " + suma;

});