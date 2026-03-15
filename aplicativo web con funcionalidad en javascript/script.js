
// NAVEGACIÓN

function mostrarProblema(id, boton) {
  document.querySelectorAll(".problema").forEach(p => p.classList.remove("visible"));
  document.querySelectorAll(".nav-btn").forEach(b => b.classList.remove("activo"));
  document.getElementById(id).classList.add("visible");
  boton.classList.add("activo");
}


// UTILIDAD — mensaje de éxito o error

function mostrarMensaje(id, texto, tipo) {
  const el = document.getElementById(id);
  el.textContent = texto;
  el.style.color = tipo === "exito" ? "#4dff88" : "#ff4d4d";
}


// UTILIDAD — actualiza un select con datos de un objeto

function actualizarSelect(selectId, datos, labelKey) {
  const select = document.getElementById(selectId);
  const valorActual = select.value;
  select.innerHTML = '<option value="">-- Selecciona --</option>';
  Object.keys(datos).forEach(key => {
    const option = document.createElement("option");
    option.value = key;
    option.textContent = datos[key][labelKey] || key;
    select.appendChild(option);
  });
  if (valorActual) select.value = valorActual;
}


// PROBLEMA 1 — SISTEMA DE CUENTAS BANCARIAS

// Clase que representa una cuenta bancaria
class CuentaBancaria {
  constructor(nombre, saldoInicial) {
    this.nombre    = nombre;
    this.saldo     = saldoInicial;
    this.historial = [];
    this.registrar("Apertura de cuenta", saldoInicial);
  }
  // Método — depositar dinero
  depositar(monto) {
    this.saldo += monto;
    this.registrar("Depósito", monto);
  }
  // Método — retirar dinero (falso si no hay saldo)
  retirar(monto) {
    if (monto > this.saldo) return false;
    this.saldo -= monto;
    this.registrar("Retiro", monto);
    return true;
  }
  // Método — guarda movimiento en historial
  registrar(tipo, monto) {
    const fecha = new Date().toLocaleString("es-CO");
    this.historial.push({ tipo, monto, saldo: this.saldo, fecha });
  }
}

const bancoClientes = {};

// Crea un nuevo cliente
function bancoCrearCliente() {
  const nombre = document.getElementById("b-nombre").value.trim();
  const saldo  = Number(document.getElementById("b-saldo").value);
  if (!nombre) return mostrarMensaje("b-msg-crear", "⚠️ Ingresa el nombre del cliente", "error");
  if (bancoClientes[nombre]) return mostrarMensaje("b-msg-crear", "⚠️ Ya existe ese cliente", "error");
  if (isNaN(saldo) || saldo < 0) return mostrarMensaje("b-msg-crear", "⚠️ Saldo inicial inválido", "error");
  bancoClientes[nombre] = new CuentaBancaria(nombre, saldo);
  actualizarSelect("b-select-op", bancoClientes, "nombre");
  actualizarSelect("b-select-saldo", bancoClientes, "nombre");
  bancoDibujarLista();
  mostrarMensaje("b-msg-crear", `✅ Cliente "${nombre}" creado con $${saldo.toLocaleString()}`, "exito");
  document.getElementById("b-nombre").value = "";
  document.getElementById("b-saldo").value  = "";
}

// Deposita o retira según el tipo
function bancoOperacion(tipo) {
  const nombre = document.getElementById("b-select-op").value;
  const monto  = Number(document.getElementById("b-monto").value);
  if (!nombre) return mostrarMensaje("b-msg-op", "⚠️ Selecciona un cliente", "error");
  if (!monto || monto <= 0) return mostrarMensaje("b-msg-op", "⚠️ Monto inválido", "error");
  const cuenta = bancoClientes[nombre];
  if (tipo === "deposito") {
    cuenta.depositar(monto);
    mostrarMensaje("b-msg-op", `✅ Depósito $${monto.toLocaleString()} — Saldo: $${cuenta.saldo.toLocaleString()}`, "exito");
  } else {
    const ok = cuenta.retirar(monto);
    if (!ok) return mostrarMensaje("b-msg-op", `⚠️ Saldo insuficiente. Disponible: $${cuenta.saldo.toLocaleString()}`, "error");
    mostrarMensaje("b-msg-op", `✅ Retiro $${monto.toLocaleString()} — Saldo: $${cuenta.saldo.toLocaleString()}`, "exito");
  }
  bancoDibujarLista();
  document.getElementById("b-monto").value = "";
}

// Consulta el saldo del cliente
function bancoConsultarSaldo() {
  const nombre  = document.getElementById("b-select-saldo").value;
  const tarjeta = document.getElementById("b-tarjeta-saldo");
  if (!nombre) { tarjeta.classList.add("oculto"); return; }
  const cuenta = bancoClientes[nombre];
  document.getElementById("b-nombre-saldo").textContent = `👤 ${cuenta.nombre}`;
  document.getElementById("b-valor-saldo").textContent  = `$${cuenta.saldo.toLocaleString()}`;
  tarjeta.classList.remove("oculto");
}

// Dibuja lista de clientes con botón eliminar
function bancoDibujarLista() {
  const lista = document.getElementById("b-lista-clientes");
  lista.innerHTML = "";
  Object.values(bancoClientes).forEach(c => {
    const li = document.createElement("li");
    li.classList.add("lista-item");
    li.innerHTML = `
      <span>👤 ${c.nombre}</span>
      <span class="badge-verde">$${c.saldo.toLocaleString()}</span>
      <button class="btn-mini btn-rojo" onclick="bancoEliminar('${c.nombre}')">Eliminar</button>
    `;
    lista.appendChild(li);
  });
}

// Elimina un cliente del banco
function bancoEliminar(nombre) {
  delete bancoClientes[nombre];
  actualizarSelect("b-select-op", bancoClientes, "nombre");
  actualizarSelect("b-select-saldo", bancoClientes, "nombre");
  bancoDibujarLista();
}


// PROBLEMA 2 — INVENTARIO DE TIENDA

// Clase que representa un producto
class Producto {
  constructor(nombre, precio, cantidad) {
    this.nombre   = nombre;
    this.precio   = precio;
    this.cantidad = cantidad;
  }
  // Método — calcula valor total del producto
  valorTotal() { return this.precio * this.cantidad; }
}

const inventario = {};

function invRegistrar() {
  const nombre   = document.getElementById("inv-nombre").value.trim();
  const precio   = Number(document.getElementById("inv-precio").value);
  const cantidad = Number(document.getElementById("inv-cantidad").value);
  if (!nombre) return mostrarMensaje("inv-msg", "⚠️ Ingresa el nombre", "error");
  if (inventario[nombre]) return mostrarMensaje("inv-msg", "⚠️ Ese producto ya existe", "error");
  if (!precio || precio <= 0) return mostrarMensaje("inv-msg", "⚠️ Precio inválido", "error");
  if (!cantidad || cantidad <= 0) return mostrarMensaje("inv-msg", "⚠️ Cantidad inválida", "error");
  inventario[nombre] = new Producto(nombre, precio, cantidad);
  invDibujarLista();
  mostrarMensaje("inv-msg", `✅ Producto "${nombre}" registrado`, "exito");
  document.getElementById("inv-nombre").value    = "";
  document.getElementById("inv-precio").value   = "";
  document.getElementById("inv-cantidad").value = "";
}

function invCalcularTotal() {
  const total = Object.values(inventario).reduce((acc, p) => acc + p.valorTotal(), 0);
  document.getElementById("inv-total-valor").textContent = `$${total.toLocaleString()}`;
  document.getElementById("inv-total").classList.remove("oculto");
}

function invDibujarLista() {
  const lista = document.getElementById("inv-lista");
  lista.innerHTML = "";
  Object.values(inventario).forEach(p => {
    const li = document.createElement("li");
    li.classList.add("lista-item");
    li.innerHTML = `
      <span>📦 ${p.nombre}</span>
      <span>Precio: <strong>$${p.precio.toLocaleString()}</strong></span>
      <span>Cantidad: <strong>${p.cantidad}</strong></span>
      <span class="badge-cyan">$${p.valorTotal().toLocaleString()}</span>
      <button class="btn-mini btn-rojo" onclick="invEliminar('${p.nombre}')">Eliminar</button>
    `;
    lista.appendChild(li);
  });
}

// Elimina un producto del inventario
function invEliminar(nombre) {
  delete inventario[nombre];
  invDibujarLista();
}


// PROBLEMA 3 — REGISTRO DE ESTUDIANTES

// Clase que representa un estudiante
class Estudiante {
  constructor(nombre, identificacion, nota) {
    this.nombre         = nombre;
    this.identificacion = identificacion;
    this.nota           = nota;
  }
  // Método — verifica si aprobó (nota >= 3.0)
  aprobo() { return this.nota >= 3.0; }
  // Método — retorna estado del estudiante
  estado() { return this.aprobo() ? "✅ Aprobado" : "❌ Reprobado"; }
}

const estudiantes = {};

function estRegistrar() {
  const nombre = document.getElementById("est-nombre").value.trim();
  const id     = document.getElementById("est-id").value.trim();
  const nota   = Number(document.getElementById("est-nota").value);
  if (!nombre) return mostrarMensaje("est-msg", "⚠️ Ingresa el nombre", "error");
  if (!id) return mostrarMensaje("est-msg", "⚠️ Ingresa la identificación", "error");
  if (estudiantes[id]) return mostrarMensaje("est-msg", "⚠️ Ya existe ese estudiante", "error");
  if (isNaN(nota) || nota < 0 || nota > 5) return mostrarMensaje("est-msg", "⚠️ Nota entre 0 y 5", "error");
  estudiantes[id] = new Estudiante(nombre, id, nota);
  actualizarSelect("est-select", estudiantes, "nombre");
  estDibujarLista();
  mostrarMensaje("est-msg", `✅ "${nombre}" registrado`, "exito");
  document.getElementById("est-nombre").value = "";
  document.getElementById("est-id").value     = "";
  document.getElementById("est-nota").value   = "";
}

function estConsultar() {
  const id        = document.getElementById("est-select").value;
  const resultado = document.getElementById("est-resultado");
  if (!id) { resultado.classList.add("oculto"); return; }
  const est = estudiantes[id];
  resultado.classList.remove("oculto");
  resultado.innerHTML = `
    <p>👤 <strong>${est.nombre}</strong></p>
    <p>🆔 ID: ${est.identificacion}</p>
    <p>📊 Nota: <strong>${est.nota}</strong></p>
    <p style="font-size:20px; margin-top:10px;">${est.estado()}</p>
  `;
  resultado.style.borderColor = est.aprobo() ? "#4dff88" : "#ff4d4d";
}

function estDibujarLista() {
  const lista = document.getElementById("est-lista");
  lista.innerHTML = "";
  Object.values(estudiantes).forEach(e => {
    const li = document.createElement("li");
    li.classList.add("lista-item");
    li.innerHTML = `
      <span>👤 ${e.nombre}</span>
      <span>ID: ${e.identificacion}</span>
      <span>Nota: <strong>${e.nota}</strong></span>
      <span class="${e.aprobo() ? 'badge-verde' : 'badge-rojo'}">${e.estado()}</span>
      <button class="btn-mini btn-rojo" onclick="estEliminar('${e.identificacion}')">Eliminar</button>
    `;
    lista.appendChild(li);
  });
}

// Elimina un estudiante
function estEliminar(id) {
  delete estudiantes[id];
  actualizarSelect("est-select", estudiantes, "nombre");
  estDibujarLista();
}


// PROBLEMA 4 — CARRITO DE COMPRAS


// Clase que representa el carrito
class Carrito {
  constructor() { this.productos = []; }
  // Método — agrega producto
  agregar(nombre, precio) {
    this.productos.push({ id: Date.now(), nombre, precio });
  }
  // Método — elimina producto por ID
  eliminar(id) {
    this.productos = this.productos.filter(p => p.id !== id);
  }
  // Método — calcula total
  total() { return this.productos.reduce((acc, p) => acc + p.precio, 0); }
}

const carrito = new Carrito();

function carritoAgregar() {
  const nombre = document.getElementById("carr-nombre").value.trim();
  const precio = Number(document.getElementById("carr-precio").value);
  if (!nombre) return mostrarMensaje("carr-msg", "⚠️ Ingresa el nombre", "error");
  if (!precio || precio <= 0) return mostrarMensaje("carr-msg", "⚠️ Precio inválido", "error");
  carrito.agregar(nombre, precio);
  carritoDibujarLista();
  mostrarMensaje("carr-msg", `✅ "${nombre}" agregado`, "exito");
  document.getElementById("carr-nombre").value = "";
  document.getElementById("carr-precio").value = "";
}

function carritoEliminar(id) {
  carrito.eliminar(id);
  carritoDibujarLista();
}

function carritoDibujarLista() {
  const lista = document.getElementById("carr-lista");
  lista.innerHTML = "";
  carrito.productos.forEach(p => {
    const li = document.createElement("li");
    li.classList.add("lista-item");
    li.innerHTML = `
      <span>🛍️ ${p.nombre}</span>
      <span class="badge-cyan">$${p.precio.toLocaleString()}</span>
      <button class="btn-mini btn-rojo" onclick="carritoEliminar(${p.id})">Eliminar</button>
    `;
    lista.appendChild(li);
  });
  document.getElementById("carr-total").textContent = `$${carrito.total().toLocaleString()}`;
}


// PROBLEMA 5 — CONTROL DE VEHÍCULOS

// Clase que representa un vehículo
class Vehiculo {
  constructor(placa, modelo) {
    this.placa     = placa;
    this.modelo    = modelo;
    this.velocidad = 0;
  }
  // Método — aumenta velocidad
  acelerar(cantidad) { this.velocidad += cantidad; }
  // Método — disminuye velocidad (mínimo 0)
  frenar(cantidad) {
    this.velocidad -= cantidad;
    if (this.velocidad < 0) this.velocidad = 0;
  }
  // Método — estado actual del vehículo
  estado() {
    if (this.velocidad === 0) return "🔴 Detenido";
    if (this.velocidad <= 60) return "🟡 Velocidad moderada";
    return "🟢 Alta velocidad";
  }
}

const vehiculos = {};

function vehRegistrar() {
  const placa  = document.getElementById("veh-placa").value.trim().toUpperCase();
  const modelo = document.getElementById("veh-modelo").value.trim();
  if (!placa) return mostrarMensaje("veh-msg", "⚠️ Ingresa la placa", "error");
  if (!modelo) return mostrarMensaje("veh-msg", "⚠️ Ingresa el modelo", "error");
  if (vehiculos[placa]) return mostrarMensaje("veh-msg", "⚠️ Ya existe ese vehículo", "error");
  vehiculos[placa] = new Vehiculo(placa, modelo);
  actualizarSelect("veh-select", vehiculos, "modelo");
  vehDibujarLista();
  mostrarMensaje("veh-msg", `✅ "${placa}" registrado`, "exito");
  document.getElementById("veh-placa").value  = "";
  document.getElementById("veh-modelo").value = "";
}

function vehCambiarVelocidad(accion) {
  const placa    = document.getElementById("veh-select").value;
  const cantidad = Number(document.getElementById("veh-velocidad").value);
  if (!placa) return mostrarMensaje("veh-msg-op", "⚠️ Selecciona un vehículo", "error");
  if (!cantidad || cantidad <= 0) return mostrarMensaje("veh-msg-op", "⚠️ Cantidad inválida", "error");
  const veh = vehiculos[placa];
  accion === "acelerar" ? veh.acelerar(cantidad) : veh.frenar(cantidad);
  vehDibujarLista();
  mostrarMensaje("veh-msg-op", `✅ ${veh.modelo} — ${veh.velocidad} km/h`, "exito");
  document.getElementById("veh-velocidad").value = "";
}

function vehDibujarLista() {
  const lista = document.getElementById("veh-lista");
  lista.innerHTML = "";
  Object.values(vehiculos).forEach(v => {
    const li = document.createElement("li");
    li.classList.add("lista-item");
    li.innerHTML = `
      <span>🚗 ${v.modelo}</span>
      <span>Placa: <strong>${v.placa}</strong></span>
      <span>Velocidad: <strong>${v.velocidad} km/h</strong></span>
      <span class="badge-cyan">${v.estado()}</span>
      <button class="btn-mini btn-rojo" onclick="vehEliminar('${v.placa}')">Eliminar</button>
    `;
    lista.appendChild(li);
  });
}

// Elimina un vehículo
function vehEliminar(placa) {
  delete vehiculos[placa];
  actualizarSelect("veh-select", vehiculos, "modelo");
  vehDibujarLista();
}


// PROBLEMA 6 — SISTEMA DE BIBLIOTECA


// Clase que representa un libro
class Libro {
  constructor(titulo, autor) {
    this.titulo     = titulo;
    this.autor      = autor;
    this.disponible = true;
  }
  // Método — presta el libro
  prestar() {
    if (!this.disponible) return false;
    this.disponible = false;
    return true;
  }
  // Método — devuelve el libro
  devolver() {
    if (this.disponible) return false;
    this.disponible = true;
    return true;
  }
  // Método — estado del libro
  estado() { return this.disponible ? "✅ Disponible" : "❌ Prestado"; }
}

const biblioteca = {};

function libRegistrar() {
  const titulo = document.getElementById("lib-titulo").value.trim();
  const autor  = document.getElementById("lib-autor").value.trim();
  if (!titulo) return mostrarMensaje("lib-msg", "⚠️ Ingresa el título", "error");
  if (!autor) return mostrarMensaje("lib-msg", "⚠️ Ingresa el autor", "error");
  if (biblioteca[titulo]) return mostrarMensaje("lib-msg", "⚠️ Ese libro ya existe", "error");
  biblioteca[titulo] = new Libro(titulo, autor);
  actualizarSelect("lib-select", biblioteca, "titulo");
  libDibujarLista();
  mostrarMensaje("lib-msg", `✅ "${titulo}" registrado`, "exito");
  document.getElementById("lib-titulo").value = "";
  document.getElementById("lib-autor").value  = "";
}

function libPrestar() {
  const titulo = document.getElementById("lib-select").value;
  if (!titulo) return mostrarMensaje("lib-msg-op", "⚠️ Selecciona un libro", "error");
  const ok = biblioteca[titulo].prestar();
  if (!ok) return mostrarMensaje("lib-msg-op", `⚠️ "${titulo}" ya está prestado`, "error");
  libDibujarLista();
  mostrarMensaje("lib-msg-op", `✅ "${titulo}" prestado`, "exito");
}

function libDevolver() {
  const titulo = document.getElementById("lib-select").value;
  if (!titulo) return mostrarMensaje("lib-msg-op", "⚠️ Selecciona un libro", "error");
  const ok = biblioteca[titulo].devolver();
  if (!ok) return mostrarMensaje("lib-msg-op", `⚠️ "${titulo}" ya está disponible`, "error");
  libDibujarLista();
  mostrarMensaje("lib-msg-op", `✅ "${titulo}" devuelto`, "exito");
}

function libDibujarLista() {
  const lista = document.getElementById("lib-lista");
  lista.innerHTML = "";
  Object.values(biblioteca).forEach(l => {
    const li = document.createElement("li");
    li.classList.add("lista-item");
    li.innerHTML = `
      <span>📖 ${l.titulo}</span>
      <span>Autor: <strong>${l.autor}</strong></span>
      <span class="${l.disponible ? 'badge-verde' : 'badge-rojo'}">${l.estado()}</span>
      <button class="btn-mini btn-rojo" onclick="libEliminar('${l.titulo}')">Eliminar</button>
    `;
    lista.appendChild(li);
  });
}

// Elimina un libro
function libEliminar(titulo) {
  delete biblioteca[titulo];
  actualizarSelect("lib-select", biblioteca, "titulo");
  libDibujarLista();
}