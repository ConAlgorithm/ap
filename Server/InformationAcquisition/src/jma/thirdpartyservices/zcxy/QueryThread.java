package jma.thirdpartyservices.zcxy;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * Encrypt utilities for 中诚信源Web Service
 *
 */
public class QueryThread {
  public static byte[] decode(String key, byte[] data) {
    try {
      DESKeySpec ks = new DESKeySpec(key.getBytes());
      SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
      Key secretKey = factory.generateSecret(ks);
      Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      IvParameterSpec iv = new IvParameterSpec(key.getBytes());
      cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
      return cipher.doFinal(data);
    } catch (InvalidKeyException e) {
      throw new RuntimeException(e);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    } catch (InvalidKeySpecException e) {
      throw new RuntimeException(e);
    } catch (NoSuchPaddingException e) {
      throw new RuntimeException(e);
    } catch (InvalidAlgorithmParameterException e) {
      throw new RuntimeException(e);
    } catch (IllegalBlockSizeException e) {
      throw new RuntimeException(e);
    } catch (BadPaddingException e) {
      throw new RuntimeException(e);
    }
  }

  public static byte[] encode(String key, byte[] data) {
    try {
      DESKeySpec dks = new DESKeySpec(key.getBytes());
      SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
      Key secrKey = keyFactory.generateSecret(dks);
      Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
      IvParameterSpec iv = new IvParameterSpec(key.getBytes());
      cipher.init(Cipher.ENCRYPT_MODE, secrKey, iv);
      return cipher.doFinal(data);
    } catch (InvalidKeyException e) {
      throw new RuntimeException(e);
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    } catch (InvalidKeySpecException e) {
      throw new RuntimeException(e);
    } catch (NoSuchPaddingException e) {
      throw new RuntimeException(e);
    } catch (InvalidAlgorithmParameterException e) {
      throw new RuntimeException(e);
    } catch (IllegalBlockSizeException e) {
      throw new RuntimeException(e);
    } catch (BadPaddingException e) {
      throw new RuntimeException(e);
    }
  }
}
