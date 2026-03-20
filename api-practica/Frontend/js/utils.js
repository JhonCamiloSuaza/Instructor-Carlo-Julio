import { TOAST_DURATION } from './const.js';

// ── TOAST ──────────────────────────────────
/**
 * Muestra una notificación tipo toast
 * @param {string} msg   - Mensaje a mostrar
 * @param {'success'|'error'|'info'} type
 */
export function showToast(msg, type = 'info') {
  const icons = { success: '✅', error: '❌', info: 'ℹ️' };
  const container = document.getElementById('toastContainer');
  const el = document.createElement('div');
  el.className = `toast ${type}`;
  el.innerHTML = `<span>${icons[type]}</span><span>${msg}</span>`;
  container.appendChild(el);
  setTimeout(() => el.remove(), TOAST_DURATION);
}

// ── MODAL ──────────────────────────────────
export function openModal() {
  document.getElementById('modalOverlay').classList.add('open');
}

export function closeModal() {
  document.getElementById('modalOverlay').classList.remove('open');
}

export function setModal({ title, body, footer }) {
  document.getElementById('modalTitle').textContent = title;
  document.getElementById('modalBody').innerHTML = body;
  document.getElementById('modalFooter').innerHTML = footer;
}

// ── LOADING ────────────────────────────────
export function showLoading(msg = 'Cargando...') {
  return `<div class="loading"><div class="spinner"></div>${msg}</div>`;
}

// ── BADGE ──────────────────────────────────
export function badge(text, color = 'blue') {
  return `<span class="badge badge-${color}">${text}</span>`;
}

// ── ESCAPE HTML ────────────────────────────
export function esc(str) {
  return String(str ?? '').replace(/[&<>"']/g, c => ({
    '&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'
  }[c]));
}

// ── PAGINACIÓN ─────────────────────────────
export function renderPagination(current, total, onPage) {
  if (total <= 1) return '';
  let pages = '';
  for (let i = 1; i <= total; i++) {
    if (i === 1 || i === total || Math.abs(i - current) <= 1) {
      pages += `<button class="pg-btn ${i === current ? 'active' : ''}" data-page="${i}">${i}</button>`;
    } else if (Math.abs(i - current) === 2) {
      pages += `<button class="pg-btn" disabled>…</button>`;
    }
  }
  const html = `
    <div class="pagination" id="paginationBar">
      <div class="pagination-info" id="paginationInfo"></div>
      <div class="pagination-btns">
        <button class="pg-btn" data-page="${current - 1}" ${current === 1 ? 'disabled' : ''}>‹</button>
        ${pages}
        <button class="pg-btn" data-page="${current + 1}" ${current === total ? 'disabled' : ''}>›</button>
      </div>
    </div>`;
  return html;
}

// STATS CARD 
export function statCard(label, value, color = '') {
  return `<div class="stat-card">
    <div class="stat-label">${label}</div>
    <div class="stat-value ${color}">${value}</div>
  </div>`;
}
