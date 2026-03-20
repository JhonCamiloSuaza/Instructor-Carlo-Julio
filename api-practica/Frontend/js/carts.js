import { create, update, remove } from './api.js';
import { showToast, setModal, openModal, closeModal, esc, statCard } from './utils.js';

export function cartsStats(data) {
  const totalAmt = data.reduce((a, c) => a + (c.total || 0), 0).toFixed(2);
  const totalDisc = data.reduce((a, c) => a + (c.discountedTotal || 0), 0).toFixed(2);
  return `<div class="stats-row">
    ${statCard('Total Carritos', data.length, 'accent')}
    ${statCard('Valor Bruto', `$${Number(totalAmt).toLocaleString()}`, 'success')}
    ${statCard('Valor Descontado', `$${Number(totalDisc).toLocaleString()}`, 'warning')}
  </div>`;
}

export function cartsFilters() { return ''; }

export function cartsTable(data) {
  if (!data.length) return `<div class="empty"><div class="empty-icon">🔍</div><div class="empty-text">Sin resultados</div></div>`;
  return `<table>
    <thead><tr>
      <th>ID</th><th>User ID</th><th>Productos</th><th>Total</th><th>Descontado</th><th>Cantidad</th><th>Acciones</th>
    </tr></thead>
    <tbody>
    ${data.map(c => `<tr>
      <td class="td-id">#${c.id}</td>
      <td class="td-mono">${c.userId ?? '—'}</td>
      <td class="td-mono">${c.totalProducts ?? 0} items</td>
      <td class="td-mono" style="color:var(--success)">$${c.total ?? 0}</td>
      <td class="td-mono" style="color:var(--accent2)">$${c.discountedTotal ?? 0}</td>
      <td class="td-mono">${c.totalQuantity ?? 0}</td>
      <td><div class="td-actions">
        <button class="btn btn-warning btn-sm btn-edit" data-id="${c.id}">✏️ Editar</button>
        <button class="btn btn-danger btn-sm btn-delete" data-id="${c.id}" data-name="Carrito #${c.id}">🗑️</button>
      </div></td>
    </tr>`).join('')}
    </tbody>
  </table>`;
}

function cartForm(c = null) {
  return `<div class="form-grid">
    <div class="form-group">
      <label>User ID</label>
      <input type="number" id="f_userId" value="${c?.userId ?? ''}" placeholder="1"/>
    </div>
    <div class="form-group">
      <label>Total Productos</label>
      <input type="number" id="f_totalProducts" value="${c?.totalProducts ?? ''}" placeholder="0"/>
    </div>
    <div class="form-group">
      <label>Total ($)</label>
      <input type="number" id="f_total" value="${c?.total ?? ''}" placeholder="0.00" step="0.01"/>
    </div>
    <div class="form-group">
      <label>Total Descontado ($)</label>
      <input type="number" id="f_discountedTotal" value="${c?.discountedTotal ?? ''}" placeholder="0.00" step="0.01"/>
    </div>
  </div>`;
}

function getCartFormData() {
  return {
    userId:          parseInt(document.getElementById('f_userId')?.value) || 1,
    totalProducts:   parseInt(document.getElementById('f_totalProducts')?.value) || 0,
    total:           parseFloat(document.getElementById('f_total')?.value) || 0,
    discountedTotal: parseFloat(document.getElementById('f_discountedTotal')?.value) || 0,
  };
}

export function openAddCart(onRefresh) {
  setModal({
    title: 'Nuevo Carrito',
    body: cartForm(),
    footer: `<button class="btn btn-ghost" id="btnCancelModal">Cancelar</button>
             <button class="btn btn-primary" id="btnSaveNew">💾 Guardar</button>`,
  });
  openModal();
  document.getElementById('btnCancelModal').onclick = closeModal;
  document.getElementById('btnSaveNew').onclick = async () => {
    try {
      await create('carts', getCartFormData());
      closeModal(); showToast('Carrito creado', 'success'); onRefresh();
    } catch { showToast('Error al crear el carrito', 'error'); }
  };
}

export function openEditCart(item, onRefresh) {
  setModal({
    title: `Editar Carrito #${item.id}`,
    body: cartForm(item),
    footer: `<button class="btn btn-ghost" id="btnCancelModal">Cancelar</button>
             <button class="btn btn-warning" id="btnSaveEdit">✏️ Actualizar</button>`,
  });
  openModal();
  document.getElementById('btnCancelModal').onclick = closeModal;
  document.getElementById('btnSaveEdit').onclick = async () => {
    try {
      await update('carts', item.id, getCartFormData());
      closeModal(); showToast('Carrito actualizado', 'success'); onRefresh();
    } catch { showToast('Error al actualizar', 'error'); }
  };
}

export function confirmDeleteCart(id, name, onRefresh) {
  setModal({
    title: 'Confirmar eliminación',
    body: `<div class="confirm-icon">🗑️</div>
      <div class="confirm-text">¿Eliminar <span class="confirm-name">${esc(name)}</span>?</div>`,
    footer: `<button class="btn btn-ghost" id="btnCancelModal">Cancelar</button>
             <button class="btn btn-danger" id="btnConfirmDelete">🗑️ Eliminar</button>`,
  });
  openModal();
  document.getElementById('btnCancelModal').onclick = closeModal;
  document.getElementById('btnConfirmDelete').onclick = async () => {
    try {
      await remove('carts', id);
      closeModal(); showToast('Carrito eliminado', 'success'); onRefresh();
    } catch { showToast('Error al eliminar', 'error'); }
  };
}
