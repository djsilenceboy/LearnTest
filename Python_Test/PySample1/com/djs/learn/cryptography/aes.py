'''
Created on Apr 13, 2018

@author: dj
'''
from binascii import hexlify
from os import urandom

from cryptography.hazmat.backends import default_backend
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes


def block():
    # Python's crypto PRNG.
    k = urandom(16)
    print("k =", hexlify(k))
    # AES-128.
    cipher = Cipher(algorithms.AES(k), modes.ECB(), backend=default_backend())

    # Plaintext block 128 bit.
    p = b'\x00' * 16

    # Encrypt.
    aes_encrypt = cipher.encryptor()
    c = aes_encrypt.update(p) + aes_encrypt.finalize()
    print("enc({0}) = {1}".format(hexlify(p), hexlify(c)))

    # Decrypt.
    aes_decrypt = cipher.decryptor()
    p2 = aes_decrypt.update(c) + aes_decrypt.finalize()
    print("dec({0}) = {1}".format(hexlify(c), hexlify(p2)))


if __name__ == '__main__':
    block()
