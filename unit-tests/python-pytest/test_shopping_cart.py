import pytest
from shopping_cart import ShoppingCart


@pytest.fixture
def cart():
    """Fixture: crea un carrito nuevo antes de cada test."""
    return ShoppingCart()


def test_carrito_vacio_al_crear(cart):
    assert cart.is_empty() is True
    assert cart.get_total() == 0

def test_agregar_item_calcula_total_correcto(cart):
    cart.add_item("Laptop", 500, 1)
    assert cart.get_total() == 500
    assert cart.is_empty() is False

def test_agregar_multiples_items(cart):
    cart.add_item("Mouse", 20, 2)
    cart.add_item("Teclado", 50, 1)
    assert cart.get_total() == 90  # (20*2) + (50*1)
    assert cart.item_count() == 3

def test_remover_item(cart):
    cart.add_item("Monitor", 200, 1)
    cart.remove_item("Monitor")
    assert cart.is_empty() is True

def test_precio_negativo_lanza_error(cart):
    with pytest.raises(ValueError):
        cart.add_item("Producto", -10, 1)

def test_cantidad_cero_lanza_error(cart):
    with pytest.raises(ValueError):
        cart.add_item("Producto", 10, 0)