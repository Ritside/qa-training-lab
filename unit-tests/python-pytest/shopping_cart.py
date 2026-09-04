class ShoppingCart:
    def __init__(self):
        self.items = []

    def add_item(self, name, price, quantity=1):
        if price < 0:
            raise ValueError("Price cannot be negative")
        if quantity <= 0:
            raise ValueError("Quantity must be positive")
        self.items.append({"name": name, "price": price, "quantity": quantity})

    def remove_item(self, name):
        self.items = [item for item in self.items if item["name"] != name]

    def get_total(self):
        return sum(item["price"] * item["quantity"] for item in self.items)

    def item_count(self):
        return sum(item["quantity"] for item in self.items)

    def is_empty(self):
        return len(self.items) == 0