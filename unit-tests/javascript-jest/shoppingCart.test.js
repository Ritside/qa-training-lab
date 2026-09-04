const ShoppingCart = require('./shoppingCart');

describe('ShoppingCart', () => {
  let cart;

  beforeEach(() => {
    cart = new ShoppingCart();
  });

  test('carrito vacío al crear', () => {
    expect(cart.isEmpty()).toBe(true);
    expect(cart.getTotal()).toBe(0);
  });

  test('agregar item calcula total correcto', () => {
    cart.addItem('Laptop', 500, 1);
    expect(cart.getTotal()).toBe(500);
    expect(cart.isEmpty()).toBe(false);
  });

  test('agregar múltiples items', () => {
    cart.addItem('Mouse', 20, 2);
    cart.addItem('Teclado', 50, 1);
    expect(cart.getTotal()).toBe(90);
    expect(cart.itemCount()).toBe(3);
  });

  test('remover item', () => {
    cart.addItem('Monitor', 200, 1);
    cart.removeItem('Monitor');
    expect(cart.isEmpty()).toBe(true);
  });

  test('precio negativo lanza error', () => {
    expect(() => cart.addItem('Producto', -10, 1)).toThrow('Price cannot be negative');
  });

  test('cantidad cero lanza error', () => {
    expect(() => cart.addItem('Producto', 10, 0)).toThrow('Quantity must be positive');
  });
});