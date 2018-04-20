package com.now.util;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;

public class RSAUtils {
	
	private RSAUtils() {}

	private static final String CHARSET = "UTF-8";
	
	private static final String RSA_ALGORITHM = "RSA";
	
	public static Map<String, String> createKeys(int keySize) {
		KeyPairGenerator kpg;
		try {
			kpg = KeyPairGenerator.getInstance(RSA_ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("No Such Algorithm --> " + RSA_ALGORITHM);
		}
		
		kpg.initialize(keySize);
		KeyPair keyPair = kpg.generateKeyPair();
		PublicKey publicKey = keyPair.getPublic();
		String publicKeyStr = Base64.encodeBase64URLSafeString(publicKey.getEncoded());
		
		PrivateKey privateKey = keyPair.getPrivate();
		String privateKeyStr = Base64.encodeBase64URLSafeString(privateKey.getEncoded());
		Map<String, String> map = new HashMap<String, String>();
		map.put("publicKey", publicKeyStr);
		map.put("privateKey", privateKeyStr);
		return map;
	}
	/**
	 * 获取公钥
	 * @param publicKey，密钥字符串（经过base64编码）
	 * @return
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeySpecException 
	 */
	public static RSAPublicKey getPublicKey(String publicKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
		//通过X509编码的Key指令获得公钥对象
		KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
		PublicKey publicKey2 = keyFactory.generatePublic(x509EncodedKeySpec);
		return (RSAPublicKey) publicKey2;
	}
	
	/**
	 * 获取私钥
	 * @param privateKey
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 */
    public static RSAPrivateKey getPrivateKey(String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        //通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }
	
	/**
	 * 公钥加密
	 * @param data
	 * @param publicKey
	 * @return
	 */
	public static String publicEncrypt(String data, RSAPublicKey publicKey) {
		try {
			Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, publicKey);
			return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), publicKey.getModulus().bitLength()));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException e) {
			throw new RuntimeException("加密数据：[" + data + "] 时出现异常", e);
		}
	}
	
	/**
	 * 公钥解密
	 * @param data
	 * @param publicKey
	 * @return
	 */
	public static String publicDecrypt(String data, RSAPublicKey publicKey) {
		try {
			Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, publicKey);
			return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), publicKey.getModulus().bitLength()), CHARSET);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException e) {
			throw new RuntimeException("解密数据：[" + data + "] 时出现异常", e);
		}
	}
	
	/**
	 * 私钥加密
	 * @param data
	 * @param privateKey
	 * @return
	 */
	public static String privateEncrypt(String data, RSAPrivateKey privateKey) {
		try {
			Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
			cipher.init(Cipher.ENCRYPT_MODE, privateKey);
			return Base64.encodeBase64URLSafeString(rsaSplitCodec(cipher, Cipher.ENCRYPT_MODE, data.getBytes(CHARSET), privateKey.getModulus().bitLength()));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException e) {
			throw new RuntimeException("加密数据：[" + data + "] 时出现异常", e);
		}
	}
	
	/**
	 * 私钥解密
	 * @param data
	 * @param privateKey
	 * @return
	 */
	public static String privateDecrypt(String data, RSAPrivateKey privateKey) {
		try {
			Cipher cipher = Cipher.getInstance(RSA_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			return new String(rsaSplitCodec(cipher, Cipher.DECRYPT_MODE, Base64.decodeBase64(data), privateKey.getModulus().bitLength()), CHARSET);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException e) {
			throw new RuntimeException("解密数据：[" + data + "] 时出现异常", e);
		}
	
	}
	
	private static byte[] rsaSplitCodec(Cipher cipher, int mode, byte[] bytes, int keySize) {
		int maxBlock = 0;
		if(mode == Cipher.DECRYPT_MODE) {
			maxBlock = keySize/8;
		} else {
			maxBlock = keySize/8 - 11;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int offSet = 0;
		byte[] buf;
		int i = 0;
		try {
			while(bytes.length > offSet) {
				if((bytes.length - offSet) > maxBlock) {
					buf = cipher.doFinal(bytes, offSet, maxBlock);
				}  else {
					buf = cipher.doFinal(bytes, offSet, bytes.length - offSet);
				}
				baos.write(buf, 0, buf.length);
				i++;
				offSet = i * maxBlock;
			}
		} catch (IllegalBlockSizeException | BadPaddingException e) {
			throw new RuntimeException("加密/解密阈值为：[" + maxBlock + "]的数据时发生异常", e);
		}
		byte[] resultDatas = baos.toByteArray();
		IOUtils.closeQuietly(baos);
		return resultDatas;
	}
	
	public static void main(String[] args) throws Exception {
		Map<String, String> keys = RSAUtils.createKeys(512);
		String publicKey = keys.get("publicKey");
		String privateKey = keys.get("privateKey");
		System.err.println("publicKey: " + publicKey);
		System.err.println("privateKey: " + privateKey);
		System.out.println();
		
		//需加密的数据
		String data = "root";
		System.out.println("明文：" + data);
		System.out.println("明文大小：(" + data.getBytes().length + ")Bytes");
		System.out.println();
		
		//公钥加密后的数据
		String encryptData = RSAUtils.publicEncrypt(data, RSAUtils.getPublicKey(publicKey));
		System.out.println("公钥加密密文：" + encryptData);
		System.out.println();
		
		//私钥解密的数据
		String decryptData = RSAUtils.privateDecrypt(encryptData, RSAUtils.getPrivateKey(privateKey));
		System.out.println("解密后数据：" + decryptData);
	}
}
