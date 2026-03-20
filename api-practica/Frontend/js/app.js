import { checkServerStatus, getAll } from './api.js';
import { SECTIONS, SECTION_META, PAGE_SIZE } from './const.js';
import { showToast, closeModal, renderPagination } from './utils.js';

// ── Módulos por sección ────────────────────
import {
  productsStats, productsFilters, productsTable,
  openAddProduct, openEditProduct, confirmDeleteProduct
} from './products.js';

import {
  usersStats, usersFilters, usersTable,
  openAddUser, openEditUser, confirmDeleteUser
} from './users.js';

import {
  postsStats, postsFilters, postsTable,
  openAddPost, openEditPost, confirmDeletePost
} from './posts.js';

import {
  cartsStats, cartsFilters, cartsTable,
  openAddCart, openEditCart, confirmDeleteCart
} from './carts.js';

// Mapeo de módulos 
const MODULE = {
  products: {
    stats: productsStats, filters: productsFilters, table: productsTable,
    openAdd: openAddProduct, openEdit: openEditProduct, confirmDelete: confirmDeleteProduct,
  },
  users: {
    stats: usersStats, filters: usersFilters, table: usersTable,
    openAdd: openAddUser, openEdit: openEditUser, confirmDelete: confirmDeleteUser,
  },
  posts: {
    stats: postsStats, filters: postsFilters, table: postsTable,
    openAdd: openAddPost, openEdit: openEditPost, confirmDelete: confirmDeletePost,
  },
  carts: {
    stats: cartsStats, filters: cartsFilters, table: cartsTable,
    openAdd: openAddCart, openEdit: openEditCart, confirmDelete: confirmDeleteCart,
  },
};

//Estado global
let state = {
  section:    'products',
  allData:    [],
  filtered:   [],
  page:       1,
  search:     '',
  filter:     '',
};

//  INIT
async function init() {
  document.getElementById('modalCloseBtn').onclick = closeModal;
  document.getElementById('modalOverlay').addEventListener('click', e => {
    if (e.target === document.getElementById('modalOverlay')) closeModal();
  });

  document.querySelectorAll('.nav-item').forEach(item => {
    item.addEventListener('click', () => switchSection(item.dataset.section));
  });

  // Delegación de eventos — se registra UNA sola vez aquí
  setupDelegation();

  await checkServer();
  await loadCounts();
  await switchSection('products');
}

// ESTADO DEL SERVIDOR 
async function checkServer() {
  const online = await checkServerStatus();
  const dot  = document.getElementById('statusDot');
  const text = document.getElementById('statusText');
  if (online) {
    dot.classList.remove('offline');
    text.textContent = 'localhost:3000 · online';
  } else {
    dot.classList.add('offline');
    text.textContent = 'localhost:3000 · offline';
  }
}

//  CONTADORES SIDEBAR 
async function loadCounts() {
  for (const s of SECTIONS) {
    try {
      const data = await getAll(s);
      document.getElementById(`count-${s}`).textContent = data.length;
    } catch {
      document.getElementById(`count-${s}`).textContent = '!';
    }
  }
}

// CAMBIO DE SECCIÓN
async function switchSection(section) {
  state = { ...state, section, page: 1, search: '', filter: '' };

  // Actualizar sidebar
  document.querySelectorAll('.nav-item').forEach(n => n.classList.remove('active'));
  document.getElementById(`nav-${section}`).classList.add('active');

  // Mostrar loading
  document.getElementById('mainContent').innerHTML =
    `<div class="loading"><div class="spinner"></div> Cargando ${section}...</div>`;

  await loadSection();
}

//  CARGA DE DATOS 
async function loadSection() {
  try {
    state.allData = await getAll(state.section);
    applyFilters();
  } catch {
    document.getElementById('mainContent').innerHTML = `
      <div class="empty">
        <div class="empty-icon">⚠️</div>
        <div class="empty-text">No se pudo conectar a <b>localhost:3000</b><br>
        Ejecuta: <code>npx json-server db.json</code></div>
      </div>`;
  }
}

// FILTROS Y BÚSQUEDA 
function applyFilters() {
  const { search, filter, allData } = state;
  state.filtered = allData.filter(item => {
    const str = JSON.stringify(item).toLowerCase();
    return (!search || str.includes(search.toLowerCase()))
        && (!filter || str.includes(filter.toLowerCase()));
  });
  state.page = 1;

  // Si ya hay layout renderizado, solo actualiza tabla y conteo
  // para no destruir el foco del input de búsqueda
  const tableArea = document.getElementById('tableArea');
  if (tableArea) {
    renderTableOnly();
  } else {
    renderFull();
  }
}

//  RENDER SOLO TABLA (sin tocar el input)
function renderTableOnly() {
  const { section, filtered, page, allData } = state;
  const mod = MODULE[section];
  const totalPages = Math.ceil(filtered.length / PAGE_SIZE);
  const pageData   = filtered.slice((page - 1) * PAGE_SIZE, page * PAGE_SIZE);
  const start      = (page - 1) * PAGE_SIZE + 1;
  const end        = Math.min(page * PAGE_SIZE, filtered.length);

  // Actualizar subtítulo
  const subtitle = document.getElementById('pageSubtitle');
  if (subtitle) subtitle.textContent = `${filtered.length} registros encontrados`;

  // Actualizar solo la tabla
  document.getElementById('tableArea').innerHTML = mod.table(pageData);

  // Actualizar paginación
  const paginationWrap = document.getElementById('paginationWrap');
  if (paginationWrap) {
    paginationWrap.innerHTML = renderPagination(page, totalPages);
    const info = document.getElementById('paginationInfo');
    if (info) info.textContent = `Mostrando ${start}–${end} de ${filtered.length}`;
  }

  bindTableEvents();
}

//  RENDER COMPLETO (primera carga o cambio de sección) 
function renderFull() {
  const { section, filtered, page, search, filter, allData } = state;
  const mod  = MODULE[section];
  const meta = SECTION_META[section];
  const totalPages = Math.ceil(filtered.length / PAGE_SIZE);
  const pageData   = filtered.slice((page - 1) * PAGE_SIZE, page * PAGE_SIZE);
  const start      = (page - 1) * PAGE_SIZE + 1;
  const end        = Math.min(page * PAGE_SIZE, filtered.length);

  document.getElementById('mainContent').innerHTML = `
    <div class="page-header">
      <div>
        <div class="page-title">${meta.label} <span>/</span></div>
        <div class="page-subtitle" id="pageSubtitle">${filtered.length} registros encontrados</div>
      </div>
      <button class="btn btn-primary" id="btnAdd">+ Nuevo ${meta.singular}</button>
    </div>

    <div class="url-bar">GET <span>http://localhost:3000/${section}</span></div>

    ${mod.stats(allData)}

    <div class="toolbar">
      <div class="search-wrap">
        <input type="text" id="searchInput" placeholder="Buscar..." value="${search}" autocomplete="off"/>
      </div>
      ${mod.filters(allData, filter)}
      <button class="btn btn-ghost btn-sm" id="btnRefresh">↻ Refrescar</button>
    </div>

    <div class="table-wrap">
      <div class="table-scroll" id="tableArea">
        ${mod.table(pageData)}
      </div>
      <div id="paginationWrap">
        ${renderPagination(page, totalPages)}
      </div>
    </div>`;

  const info = document.getElementById('paginationInfo');
  if (info) info.textContent = `Mostrando ${start}–${end} de ${filtered.length}`;

  // (eventos manejados por delegación en setupDelegation)
  bindTableEvents();
}

//  DELEGACIÓN DE EVENTOS (se registra UNA sola vez) 
function setupDelegation() {
  document.getElementById('mainContent').addEventListener('click', e => {
    const { section } = state;
    const mod = MODULE[section];
    const filtered = state.filtered;
    const totalPages = Math.ceil(filtered.length / PAGE_SIZE);

    // ── Paginación ──
    const pgBtn = e.target.closest('.pg-btn');
    if (pgBtn && !pgBtn.disabled) {
      const p = parseInt(pgBtn.dataset.page);
      if (!isNaN(p) && p >= 1 && p <= totalPages) {
        state.page = p;
        renderTableOnly();
        window.scrollTo({ top: 0, behavior: 'smooth' });
      }
      return;
    }

    //  Editar 
    const editBtn = e.target.closest('.btn-edit');
    if (editBtn) {
      const id   = parseInt(editBtn.dataset.id);
      const item = state.allData.find(d => Number(d.id) === id);
      if (item) mod.openEdit(item, () => loadSection());
      return;
    }

    //  Eliminar 
    const delBtn = e.target.closest('.btn-delete');
    if (delBtn) {
      const id   = parseInt(delBtn.dataset.id);
      const name = delBtn.dataset.name || `#${id}`;
      mod.confirmDelete(id, name, () => { loadSection(); loadCounts(); });
      return;
    }

    // Botón Agregar 
    const addBtn = e.target.closest('#btnAdd');
    if (addBtn) {
      mod.openAdd(() => { loadSection(); loadCounts(); });
      return;
    }

    // Refrescar 
    const refreshBtn = e.target.closest('#btnRefresh');
    if (refreshBtn) {
      loadSection().then(() => showToast('Datos actualizados', 'success'));
      return;
    }
  });

  // Búsqueda 
  document.getElementById('mainContent').addEventListener('input', e => {
    if (e.target.id === 'searchInput') {
      state.search = e.target.value;
      applyFilters();
    }
  });

  // Filtro select 
  document.getElementById('mainContent').addEventListener('change', e => {
    if (e.target.id === 'filterSelect') {
      state.filter = e.target.value;
      applyFilters();
    }
  });
}

//  BIND EVENTOS (ya no hace nada, delegación maneja todo) 
function bindTableEvents() {}

// RENDER (llamado externo) 
function render() {
  renderFull();
}

//  ARRANCAR 
init();
