import { create, update, remove } from './api.js';
import { showToast, setModal, openModal, closeModal, badge, esc, statCard } from './utils.js';

//  STATS 
export function productsStats(data) {
  const avgPrice = data.length
    ? (data.reduce((a, p) => a + (p.price || 0), 0) / data.length).toFixed(2)
    : 0;
  const inStock = data.filter(p => p.availabilityStatus === 'In Stock').length;
  const cats = new Set(data.map(p => p.category)).size;

  return `<div class="stats-row">
    ${statCard('Total', data.length, 'accent')}
    ${statCard('Precio Promedio', `$${avgPrice}`, 'success')}
    ${statCard('En Stock', inStock, '')}
    ${statCard('Categorías', cats, 'warning')}
  </div>`;
}

//  FILTROS 
export function productsFilters(data, filterVal) {
  const cats = [...new Set(data.map(p => p.category).filter(Boolean))];
  return `<select id="filterSelect">
    <option value="">Todas las categorías</option>
    ${cats.map(c => `<option value="${c}" ${filterVal === c ? 'selected' : ''}>${esc(c)}</option>`).join('')}
  </select>`;
}

// ── TABLA ──────────────────────────────────
export function productsTable(data) {
  if (!data.length) return `<div class="empty"><div class="empty-icon">🔍</div><div class="empty-text">Sin resultados</div></div>`;
  return `<table>
    <thead><tr>
      <th>ID</th><th>Imagen</th><th>Título</th><th>Categoría</th>
      <th>Precio</th><th>Stock</th><th>Estado</th><th>Acciones</th>
    </tr></thead>
    <tbody>
    ${data.map(p => `<tr>
      <td class="td-id">#${p.id}</td>
      <td>${p.thumbnail
        ? `<img class="thumb" src="${esc(p.thumbnail)}" onerror="this.style.display='none'"/>`
        : `<div class="thumb-placeholder">📦</div>`}</td>
      <td><b>${esc(p.title)}</b><br><small style="color:var(--text2)">${esc(p.brand || '')}</small></td>
      <td>${badge(esc(p.category || '—'), 'blue')}</td>
      <td class="td-mono">$${p.price ?? 0}</td>
      <td class="td-mono">${p.stock ?? 0}</td>
      <td>${badge(esc(p.availabilityStatus || '—'), p.availabilityStatus === 'In Stock' ? 'green' : 'red')}</td>
      <td><div class="td-actions">
        <button class="btn btn-warning btn-sm btn-edit" data-id="${p.id}">✏️ Editar</button>
        <button class="btn btn-danger btn-sm btn-delete" data-id="${p.id}" data-name="${esc(p.title)}">🗑️</button>
      </div></td>
    </tr>`).join('')}
    </tbody>
  </table>`;
}

// ── FORMULARIO ─────────────────────────────
function productForm(p = null) {
  return `<div class="form-grid">
    <div class="form-group full">
      <label>Título</label>
      <input type="text" id="f_title" value="${esc(p?.title || '')}" placeholder="Nombre del producto"/>
    </div>
    <div class="form-group">
      <label>Precio</label>
      <input type="number" id="f_price" value="${p?.price ?? ''}" placeholder="0.00" step="0.01"/>
    </div>
    <div class="form-group">
      <label>Stock</label>
      <input type="number" id="f_stock" value="${p?.stock ?? ''}" placeholder="0"/>
    </div>
    <div class="form-group">
      <label>Categoría</label>
      <input type="text" id="f_category" value="${esc(p?.category || '')}" placeholder="electronics, beauty..."/>
    </div>
    <div class="form-group">
      <label>Marca</label>
      <input type="text" id="f_brand" value="${esc(p?.brand || '')}" placeholder="Brand"/>
    </div>
    <div class="form-group full">
      <label>Descripción</label>
      <textarea id="f_description" placeholder="Descripción...">${esc(p?.description || '')}</textarea>
    </div>
  </div>`;
}

function getProductFormData() {
  return {
    title:       document.getElementById('f_title')?.value.trim(),
    price:       parseFloat(document.getElementById('f_price')?.value) || 0,
    stock:       parseInt(document.getElementById('f_stock')?.value) || 0,
    category:    document.getElementById('f_category')?.value.trim(),
    brand:       document.getElementById('f_brand')?.value.trim(),
    description: document.getElementById('f_description')?.value.trim(),
  };
}

// ── CRUD HANDLERS ──────────────────────────
export function openAddProduct(onRefresh) {
  setModal({
    title: 'Nuevo Producto',
    body: productForm(),
    footer: `
      <button class="btn btn-ghost" id="btnCancelModal">Cancelar</button>
      <button class="btn btn-primary" id="btnSaveNew">💾 Guardar</button>`,
  });
  openModal();
  document.getElementById('btnCancelModal').onclick = closeModal;
  document.getElementById('btnSaveNew').onclick = async () => {
    try {
      await create('products', getProductFormData());
      closeModal();
      showToast('Producto creado exitosamente', 'success');
      onRefresh();
    } catch { showToast('Error al crear el producto', 'error'); }
  };
}

export function openEditProduct(item, onRefresh) {
  setModal({
    title: `Editar Producto #${item.id}`,
    body: productForm(item),
    footer: `
      <button class="btn btn-ghost" id="btnCancelModal">Cancelar</button>
      <button class="btn btn-warning" id="btnSaveEdit">✏️ Actualizar</button>`,
  });
  openModal();
  document.getElementById('btnCancelModal').onclick = closeModal;
  document.getElementById('btnSaveEdit').onclick = async () => {
    try {
      await update('products', item.id, getProductFormData());
      closeModal();
      showToast('Producto actualizado', 'success');
      onRefresh();
    } catch { showToast('Error al actualizar', 'error'); }
  };
}

export function confirmDeleteProduct(id, name, onRefresh) {
  setModal({
    title: 'Confirmar eliminación',
    body: `<div class="confirm-icon">🗑️</div>
      <div class="confirm-text">¿Eliminar <span class="confirm-name">"${esc(name)}"</span>?<br>Esta acción no se puede deshacer.</div>`,
    footer: `
      <button class="btn btn-ghost" id="btnCancelModal">Cancelar</button>
      <button class="btn btn-danger" id="btnConfirmDelete">🗑️ Eliminar</button>`,
  });
  openModal();
  document.getElementById('btnCancelModal').onclick = closeModal;
  document.getElementById('btnConfirmDelete').onclick = async () => {
    try {
      await remove('products', id);
      closeModal();
      showToast('Producto eliminado', 'success');
      onRefresh();
    } catch { showToast('Error al eliminar', 'error'); }
  };
}
