import re

def validate_email(email):
    """Valida formato básico de email."""
    if not email or not isinstance(email, str):
        return False
    pattern = r'^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'
    return re.match(pattern, email) is not None


def validate_password(password):
    """
    Contraseña válida si:
    - Tiene al menos 8 caracteres
    - Contiene al menos una mayúscula, una minúscula y un dígito
    """
    if not password or not isinstance(password, str):
        return False
    if len(password) < 8:
        return False
    has_upper = any(c.isupper() for c in password)
    has_lower = any(c.islower() for c in password)
    has_digit = any(c.isdigit() for c in password)
    return has_upper and has_lower and has_digit