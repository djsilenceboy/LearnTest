from typing import Final

a:Final = 1
print("a =", a)

a = 2
print("a =", a)

# Run following command to check.
# $ mypy TestFinal.py
# TestFinal.py:6: error: Cannot assign to final name "a"  [misc]
# Found 1 error in 1 file (checked 1 source file)
