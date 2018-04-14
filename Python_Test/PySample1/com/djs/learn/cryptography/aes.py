'''
Created on Apr 13, 2018

@author: dj
'''
from binascii import hexlify
from os import urandom
from struct import unpack

from Crypto.Cipher import AES
from Crypto.Util import Counter
from cryptography.hazmat.backends import default_backend
from cryptography.hazmat.primitives.ciphers import Cipher, algorithms, modes


BLOCKLEN = 16


def blocks(data):
    split = [hexlify(data[i:i + BLOCKLEN])
             for i in range(0, len(data), BLOCKLEN)]
    return b' ' .join(split)


def get_key():
    # Python's crypto PRNG.
    key = urandom(BLOCKLEN)
    print("key =", hexlify(key))
    return key


def cipher_ecb(key):
    # AES-128.
    return Cipher(algorithms.AES(key), modes.ECB(), backend=default_backend())


def cipher_cbc(key, initial_value):
    # AES-128.
    return Cipher(algorithms.AES(key), modes.CBC(initial_value), backend=default_backend())


def cipher_ctr(key, nonce):
    counter = Counter.new(BLOCKLEN * 8, initial_value=nonce)
    return AES.new(key, AES.MODE_CTR, counter=counter)


def ecb_block_1():
    # Plaintext block 128 bit.
    p = b'\x00' * BLOCKLEN

    cipher = cipher_ecb(get_key())

    # Encrypt.
    aes_encrypt = cipher.encryptor()
    c = aes_encrypt.update(p) + aes_encrypt.finalize()
    print("enc({0}) = {1}".format(hexlify(p), hexlify(c)))

    # Decrypt.
    aes_decrypt = cipher.decryptor()
    p2 = aes_decrypt.update(c) + aes_decrypt.finalize()
    print("dec({0}) = {1}".format(hexlify(c), hexlify(p2)))


def ecb_block_2():
    # Plaintext block 128 bit.
    p = b'\x00' * BLOCKLEN * 2

    cipher = cipher_ecb(get_key())

    # Encrypt.
    aes_encrypt = cipher.encryptor()
    c = aes_encrypt.update(p) + aes_encrypt.finalize()
    print("enc({0}) = {1}".format(blocks(p), blocks(c)))


def cbc_block_1():
    # Plaintext block 128 bit.
    p = b'\x00' * BLOCKLEN * 2

    key = get_key()

    # Encrypt.
    cipher = cipher_cbc(key, get_key())
    aes_encrypt = cipher.encryptor()
    c = aes_encrypt.update(p) + aes_encrypt.finalize()
    print("enc({0}) = {1}".format(blocks(p), blocks(c)))

    print("-" * 40)

    # Encrypt.
    cipher2 = cipher_cbc(key, get_key())
    aes_encrypt2 = cipher2.encryptor()
    c2 = aes_encrypt2.update(p) + aes_encrypt2.finalize()
    print("enc({0}) = {1}".format(blocks(p), blocks(c2)))


def ctr_block_1():
    # Plaintext.
    p = b'\x00\x01\x02\x03'

    key = get_key()
    # Pick a starting value for the counter.
    nonce = unpack("<Q", urandom(8))[0]

    # Encrypt.
    cipher = cipher_ctr(key, nonce)
    c = cipher.encrypt(p)
    print("enc({0}) = {1}".format(hexlify(p), hexlify(c)))

    # Decrypt.
    # Must recreate one instance, even with save key and nonce.
    cipher2 = cipher_ctr(key, nonce)
    p2 = cipher2.encrypt(c)
    print("dec({0}) = {1}".format(hexlify(c), hexlify(p2)))


if __name__ == '__main__':
    print("-" * 80)
    ecb_block_1()
    print("-" * 80)
    ecb_block_2()
    print("-" * 80)
    cbc_block_1()
    print("-" * 80)
    ctr_block_1()
    print("-" * 80)
