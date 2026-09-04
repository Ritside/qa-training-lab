class ShoppingCart {
  constructor() {
    this.items = [];
  }

  addItem(name, price, quantity = 1) {
    if (price < 0) throw new Error('Price cannot be negative');
    if (quantity <= 0) throw new Error('Quantity must be positive');
    this.items.push({ name, price, quantity });
  }

  removeItem(name) {
    this.items = this.items.filter(item => item.name !== name);
  }

  getTotal() {
    return this.items.reduce((sum, item) => sum + item.price * item.quantity, 0);
  }

  itemCount() {
    return this.items.reduce((sum, item) => sum + item.quantity, 0);
  }

  isEmpty() {
    return this.items.length === 0;
  }
}

module.exports = ShoppingCart;