
//  CONSTANTES DE LA APLICACIÓN


export const API_BASE_URL = 'http://localhost:3000';

export const SECTIONS = ['products', 'users', 'posts', 'carts'];

export const PAGE_SIZE = 15;

export const SECTION_META = {
  products: { label: 'Products', icon: '📦', singular: 'Producto' },
  users:    { label: 'Users',    icon: '👤', singular: 'Usuario'  },
  posts:    { label: 'Posts',    icon: '📝', singular: 'Post'     },
  carts:    { label: 'Carts',    icon: '🛒', singular: 'Carrito'  },
};

export const TOAST_DURATION = 3500; // ms
