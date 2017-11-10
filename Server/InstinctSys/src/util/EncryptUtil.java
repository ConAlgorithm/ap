package util;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import org.apache.commons.codec.binary.Base64;

public final class EncryptUtil 
{
	private EncryptUtil()
	{
		
	}
	
	private static final String desKey = "Qingchun";

	public static final String DES_METHOD = "DES";
	public static final String MD5_METHOD = "MD5";

	public static String getMD5(String inputString) 
	{
		return encodeByMD5(inputString);
	}

	public static String getDES(String input) 
	{
		return encodeByDES(input, desKey);
	}

	public static String getDecryptedString(String encryptedString, String encryptMethod) 
	{
		if (encryptMethod.equals(DES_METHOD)) 
		{
			return decodeFromDES(encryptedString, desKey);
		}
		return null;
	}

	private static String encodeByMD5(String originString) 
	{
		if (originString == null) 
		{
			return null;
		}
		try 
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] results = md.digest(originString.getBytes("UTF-8"));
			return new String(results, "UTF-8");
		} 
		catch (Exception ex) 
		{
			throw new RuntimeException("MD5 error", ex);
		}
	}

	private static String encodeByDES(String input, String key) 
	{
		if (input == null) 
		{
			return null;
		}
		try 
		{
			IvParameterSpec iv = new IvParameterSpec(key.getBytes());
			DESKeySpec deKey = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(deKey);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
			byte[] result = cipher.doFinal(input.getBytes());
			return new String(Base64.encodeBase64(result));
		} 
		catch (Exception ex) 
		{
			throw new RuntimeException("DES encoding error", ex);
		}
	}

	private static String decodeFromDES(String input, String key) 
	{
		if (input == null) 
		{
			return null;
		}
		try 
		{
			IvParameterSpec iv = new IvParameterSpec(key.getBytes());
			DESKeySpec deKey = new DESKeySpec(key.getBytes());
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(deKey);
			Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
			byte[] result = cipher.doFinal(Base64.decodeBase64(input));
			return new String(result);
		} 
		catch (Exception ex) 
		{
			throw new RuntimeException("DES decoding error", ex);
		}
	}
}
