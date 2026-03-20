import { create, update, remove } from './api.js';
import { showToast, setModal, openModal, closeModal, badge, esc, statCard } from './utils.js';

export function postsStats(data) {
  const totalViews = data.reduce((a, p) => a + (p.views || 0), 0);
  const totalLikes = data.reduce((a, p) => a + ((p.reactions?.likes) || 0), 0);
  return `<div class="stats-row">
    ${statCard('Total Posts', data.length, 'accent')}
    ${statCard('Total Vistas', totalViews.toLocaleString(), 'success')}
    ${statCard('Total Likes', totalLikes.toLocaleString(), 'warning')}
  </div>`;
}

export function postsFilters() { return ''; }

export function postsTable(data) {
  if (!data.length) return `<div class="empty"><div class="empty-icon">🔍</div><div class="empty-text">Sin resultados</div></div>`;
  return `<table>
    <thead><tr>
      <th>ID</th><th>Título</th><th>Tags</th><th>Vistas</th><th>Likes</th><th>User ID</th><th>Acciones</th>
    </tr></thead>
    <tbody>
    ${data.map(p => `<tr>
      <td class="td-id">#${p.id}</td>
      <td style="max-width:220px"><b>${esc(p.title || '—')}</b></td>
      <td>${(p.tags || []).slice(0, 3).map(t => badge(esc(t), 'blue')).join(' ')}</td>
      <td class="td-mono">${p.views ?? 0}</td>
      <td class="td-mono">${(p.reactions?.likes) ?? 0} 👍</td>
      <td class="td-mono">${p.userId ?? '—'}</td>
      <td><div class="td-actions">
        <button class="btn btn-warning btn-sm btn-edit" data-id="${p.id}">✏️ Editar</button>
        <button class="btn btn-danger btn-sm btn-delete" data-id="${p.id}" data-name="${esc(p.title?.slice(0,30))}">🗑️</button>
      </div></td>
    </tr>`).join('')}
    </tbody>
  </table>`;
}

function postForm(p = null) {
  return `<div class="form-grid">
    <div class="form-group full">
      <label>Título</label>
      <input type="text" id="f_title" value="${esc(p?.title || '')}" placeholder="Título del post"/>
    </div>
    <div class="form-group full">
      <label>Contenido</label>
      <textarea id="f_body" placeholder="Contenido del post...">${esc(p?.body || '')}</textarea>
    </div>
    <div class="form-group">
      <label>Tags (separados por coma)</label>
      <input type="text" id="f_tags" value="${esc((p?.tags || []).join(', '))}" placeholder="tag1, tag2"/>
    </div>
    <div class="form-group">
      <label>User ID</label>
      <input type="number" id="f_userId" value="${p?.userId ?? ''}" placeholder="1"/>
    </div>
  </div>`;
}

function getPostFormData() {
  return {
    title:  document.getElementById('f_title')?.value.trim(),
    body:   document.getElementById('f_body')?.value.trim(),
    tags:   (document.getElementById('f_tags')?.value || '').split(',').map(t => t.trim()).filter(Boolean),
    userId: parseInt(document.getElementById('f_userId')?.value) || 1,
  };
}

export function openAddPost(onRefresh) {
  setModal({
    title: 'Nuevo Post',
    body: postForm(),
    footer: `<button class="btn btn-ghost" id="btnCancelModal">Cancelar</button>
             <button class="btn btn-primary" id="btnSaveNew">💾 Guardar</button>`,
  });
  openModal();
  document.getElementById('btnCancelModal').onclick = closeModal;
  document.getElementById('btnSaveNew').onclick = async () => {
    try {
      await create('posts', getPostFormData());
      closeModal(); showToast('Post creado', 'success'); onRefresh();
    } catch { showToast('Error al crear el post', 'error'); }
  };
}

export function openEditPost(item, onRefresh) {
  setModal({
    title: `Editar Post #${item.id}`,
    body: postForm(item),
    footer: `<button class="btn btn-ghost" id="btnCancelModal">Cancelar</button>
             <button class="btn btn-warning" id="btnSaveEdit">✏️ Actualizar</button>`,
  });
  openModal();
  document.getElementById('btnCancelModal').onclick = closeModal;
  document.getElementById('btnSaveEdit').onclick = async () => {
    try {
      await update('posts', item.id, getPostFormData());
      closeModal(); showToast('Post actualizado', 'success'); onRefresh();
    } catch { showToast('Error al actualizar', 'error'); }
  };
}

export function confirmDeletePost(id, name, onRefresh) {
  setModal({
    title: 'Confirmar eliminación',
    body: `<div class="confirm-icon">🗑️</div>
      <div class="confirm-text">¿Eliminar post <span class="confirm-name">"${esc(name)}"</span>?</div>`,
    footer: `<button class="btn btn-ghost" id="btnCancelModal">Cancelar</button>
             <button class="btn btn-danger" id="btnConfirmDelete">🗑️ Eliminar</button>`,
  });
  openModal();
  document.getElementById('btnCancelModal').onclick = closeModal;
  document.getElementById('btnConfirmDelete').onclick = async () => {
    try {
      await remove('posts', id);
      closeModal(); showToast('Post eliminado', 'success'); onRefresh();
    } catch { showToast('Error al eliminar', 'error'); }
  };
}
