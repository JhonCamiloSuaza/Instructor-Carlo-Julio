import { API_BASE_URL } from './const.js';

/** Verifica si el servidor está en línea*/
export async function checkServerStatus() {
  try {
    const res = await fetch(`${API_BASE_URL}/products?_limit=1`);
    return res.ok;
  } catch {
    return false;
  }
}

/*
* Obtiene TODOS los registros de un recurso
 * @param {string} resource - 'products' | 'users' | 'posts' | 'carts'*/
export async function getAll(resource) {
  const res = await fetch(`${API_BASE_URL}/${resource}`);
  if (!res.ok) throw new Error(`Error ${res.status} al obtener ${resource}`);
  return res.json();
}

/* * Obtiene UN registro por ID*/
export async function getById(resource, id) {
  const res = await fetch(`${API_BASE_URL}/${resource}/${id}`);
  if (!res.ok) throw new Error(`Registro no encontrado`);
  return res.json();
}

/** CREA un nuevo registro (POST)*/
export async function create(resource, data) {
  const res = await fetch(`${API_BASE_URL}/${resource}`, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  });
  if (!res.ok) throw new Error(`Error al crear en ${resource}`);
  return res.json();
}

/* ACTUALIZA un registro existente (PATCH)*/
export async function update(resource, id, data) {
  const res = await fetch(`${API_BASE_URL}/${resource}/${id}`, {
    method: 'PATCH',
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify(data),
  });
  if (!res.ok) throw new Error(`Error al actualizar registro #${id}`);
  return res.json();
}

/* ELIMINA un registro (DELETE)*/
export async function remove(resource, id) {
  const res = await fetch(`${API_BASE_URL}/${resource}/${id}`, {
    method: 'DELETE',
  });
  if (!res.ok) throw new Error(`Error al eliminar registro #${id}`);
  return true;
}
