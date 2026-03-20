import { create, update, remove } from './api.js';
import { showToast, setModal, openModal, closeModal, badge, esc, statCard } from './utils.js';

// ── STATS ──────────────────────────────────
export function usersStats(data) {
  const admins = data.filter(u => u.role === 'admin').length;
  const ages = data.map(u => u.age).filter(Boolean);
  const avgAge = ages.length ? (ages.reduce((a, b) => a + b, 0) / ages.length).toFixed(0) : '—';
  const mods = data.filter(u => u.role === 'moderator').length;

  return `<div class="stats-row">
    ${statCard('Total', data.length, 'accent')}
    ${statCard('Admins', admins, 'danger')}
    ${statCard('Moderadores', mods, 'warning')}
    ${statCard('Edad Promedio', avgAge, '')}
  </div>`;
}

// ── FILTROS ────────────────────────────────
export function usersFilters(data, filterVal) {
  const roles = [...new Set(data.map(u => u.role).filter(Boolean))];
  return `<select id="filterSelect">
    <option value="">Todos los roles</option>
    ${roles.map(r => `<option value="${r}" ${filterVal === r ? 'selected' : ''}>${esc(r)}</option>`).join('')}
  </select>`;
}

// ── TABLA ──────────────────────────────────
export function usersTable(data) {
  if (!data.length) return `<div class="empty"><div class="empty-icon">🔍</div><div class="empty-text">Sin resultados</div></div>`;
  return `<table>
    <thead><tr>
      <th>ID</th><th>Avatar</th><th>Nombre</th><th>Email</th>
      <th>Username</th><th>Rol</th><th>Edad</th><th>Acciones</th>
    </tr></thead>
    <tbody>
    ${data.map(u => `<tr>
      <td class="td-id">#${u.id}</td>
      <td>${u.image
        ? `<img class="avatar" src="${esc(u.image)}" onerror="this.style.display='none'"/>`
        : `<div class="thumb-placeholder">👤</div>`}</td>
      <td><b>${esc(u.firstName)} ${esc(u.lastName)}</b></td>
      <td class="td-mono" style="font-size:.75rem">${esc(u.email || '—')}</td>
      <td class="td-mono">${esc(u.username || '—')}</td>
      <td>${badge(esc(u.role || '—'), u.role === 'admin' ? 'red' : u.role === 'moderator' ? 'yellow' : 'green')}</td>
      <td class="td-mono">${u.age ?? '—'}</td>
      <td><div class="td-actions">
        <button class="btn btn-warning btn-sm btn-edit" data-id="${u.id}">✏️ Editar</button>
        <button class="btn btn-danger btn-sm btn-delete" data-id="${u.id}" data-name="${esc(u.firstName)} ${esc(u.lastName)}">🗑️</button>
      </div></td>
    </tr>`).join('')}
    </tbody>
  </table>`;
}

//  FORMULARIO 
function userForm(u = null) {
  return `<div class="form-grid">
    <div class="form-group">
      <label>Nombre</label>
      <input type="text" id="f_firstName" value="${esc(u?.firstName || '')}" placeholder="John"/>
    </div>
    <div class="form-group">
      <label>Apellido</label>
      <input type="text" id="f_lastName" value="${esc(u?.lastName || '')}" placeholder="Doe"/>
    </div>
    <div class="form-group full">
      <label>Email</label>
      <input type="email" id="f_email" value="${esc(u?.email || '')}" placeholder="john@mail.com"/>
    </div>
    <div class="form-group">
      <label>Username</label>
      <input type="text" id="f_username" value="${esc(u?.username || '')}" placeholder="johndoe"/>
    </div>
    <div class="form-group">
      <label>Edad</label>
      <input type="number" id="f_age" value="${u?.age ?? ''}" placeholder="25"/>
    </div>
  </div>`;
}

function getUserFormData() {
  return {
    firstName: document.getElementById('f_firstName')?.value.trim(),
    lastName:  document.getElementById('f_lastName')?.value.trim(),
    email:     document.getElementById('f_email')?.value.trim(),
    username:  document.getElementById('f_username')?.value.trim(),
    age:       parseInt(document.getElementById('f_age')?.value) || 0,
  };
}

//  CRUD HANDLERS 
export function openAddUser(onRefresh) {
  setModal({
    title: 'Nuevo Usuario',
    body: userForm(),
    footer: `
      <button class="btn btn-ghost" id="btnCancelModal">Cancelar</button>
      <button class="btn btn-primary" id="btnSaveNew">💾 Guardar</button>`,
  });
  openModal();
  document.getElementById('btnCancelModal').onclick = closeModal;
  document.getElementById('btnSaveNew').onclick = async () => {
    try {
      await create('users', getUserFormData());
      closeModal();
      showToast('Usuario creado exitosamente', 'success');
      onRefresh();
    } catch { showToast('Error al crear el usuario', 'error'); }
  };
}

export function openEditUser(item, onRefresh) {
  setModal({
    title: `Editar Usuario #${item.id}`,
    body: userForm(item),
    footer: `
      <button class="btn btn-ghost" id="btnCancelModal">Cancelar</button>
      <button class="btn btn-warning" id="btnSaveEdit">✏️ Actualizar</button>`,
  });
  openModal();
  document.getElementById('btnCancelModal').onclick = closeModal;
  document.getElementById('btnSaveEdit').onclick = async () => {
    try {
      await update('users', item.id, getUserFormData());
      closeModal();
      showToast('Usuario actualizado', 'success');
      onRefresh();
    } catch { showToast('Error al actualizar', 'error'); }
  };
}

export function confirmDeleteUser(id, name, onRefresh) {
  setModal({
    title: 'Confirmar eliminación',
    body: `<div class="confirm-icon">🗑️</div>
      <div class="confirm-text">¿Eliminar a <span class="confirm-name">"${esc(name)}"</span>?<br>Esta acción no se puede deshacer.</div>`,
    footer: `
      <button class="btn btn-ghost" id="btnCancelModal">Cancelar</button>
      <button class="btn btn-danger" id="btnConfirmDelete">🗑️ Eliminar</button>`,
  });
  openModal();
  document.getElementById('btnCancelModal').onclick = closeModal;
  document.getElementById('btnConfirmDelete').onclick = async () => {
    try {
      await remove('users', id);
      closeModal();
      showToast('Usuario eliminado', 'success');
      onRefresh();
    } catch { showToast('Error al eliminar', 'error'); }
  };
}
