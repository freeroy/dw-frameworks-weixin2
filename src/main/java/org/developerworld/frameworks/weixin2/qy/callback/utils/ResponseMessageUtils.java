package org.developerworld.frameworks.weixin2.qy.callback.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.developerworld.frameworks.weixin2.qy.callback.dto.rep.AbstractRep;

public class ResponseMessageUtils {

	private final static Charset CHARSET = Charset.forName("utf-8");

	/**
	 * 根据响应对象，构建xml消息
	 * 
	 * @param req
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	public static String buildMessageRepXml(AbstractRep rep) throws JAXBException, IOException {
		return marshal(rep);
	}

	/**
	 * xml反序列化
	 * 
	 * @param xml
	 * @param classType
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static String marshal(Object object) throws JAXBException, IOException {
		String rst = null;
		JAXBContext context = JAXBContext.newInstance(object.getClass());
		Marshaller marshaller = context.createMarshaller();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			marshaller.marshal(object, baos);
			rst = baos.toString();
		} finally {
			baos.close();
		}
		return rst;
	}

	/**
	 * 加密消息
	 * 
	 * @param random
	 * @param timestamp
	 * @param nonce
	 * @param corpId
	 * @param encodingAESKey
	 * @param token
	 * @param srcRepXml
	 * @return
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String encryptSrcRepXml(String random, String timestamp, String nonce, String corpId,
			String encodingAESKey, String token, String srcRepXml)
					throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException,
					InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		// 加密
		String encrypt = encrypt(random, corpId, encodingAESKey, srcRepXml);
		// 构建签名
		String signature = buildSignature(token, timestamp, nonce, encrypt);
		// 生成发送的xml
		return generate(encrypt, signature, timestamp, nonce);
	}

	/**
	 * 加密
	 * 
	 * @param random
	 * @param corpId
	 * @param encodingAESKey
	 * @param srcReqXml
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	private static String encrypt(String random, String corpId, String encodingAESKey, String srcReqXml)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		// 转换为base64字节
		byte[] aesKey = Base64.decodeBase64(encodingAESKey + "=");
		byte[] randomStrBytes = random.getBytes(CHARSET);
		byte[] textBytes = srcReqXml.getBytes(CHARSET);
		byte[] networkBytesOrder = getNetworkBytesOrder(textBytes.length);
		byte[] corpidBytes = corpId.getBytes(CHARSET);
		// randomStr + networkBytesOrder + text + corpid
		ByteGroup byteCollector = new ByteGroup();
		byteCollector.addBytes(randomStrBytes);
		byteCollector.addBytes(networkBytesOrder);
		byteCollector.addBytes(textBytes);
		byteCollector.addBytes(corpidBytes);
		// ... + pad: 使用自定义的填充方式对明文进行补位填充
		byte[] padBytes = PKCS7EncoderUtils.encode(byteCollector.size());
		byteCollector.addBytes(padBytes);
		// 获得最终的字节流, 未加密
		byte[] unencrypted = byteCollector.toBytes();
		// 设置加密模式为AES的CBC模式
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, "AES");
		IvParameterSpec ivParameterSpec = new IvParameterSpec(aesKey, 0, 16);
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
		// 加密
		byte[] encrypted = cipher.doFinal(unencrypted);
		// 使用BASE64对加密后的字符串进行编码
		return Base64.encodeBase64String(encrypted);
	}

	/**
	 * 生成4个字节的网络字节序
	 * 
	 * @param sourceNumber
	 * @return
	 */
	private static byte[] getNetworkBytesOrder(int sourceNumber) {
		byte[] orderBytes = new byte[4];
		orderBytes[3] = (byte) (sourceNumber & 0xFF);
		orderBytes[2] = (byte) (sourceNumber >> 8 & 0xFF);
		orderBytes[1] = (byte) (sourceNumber >> 16 & 0xFF);
		orderBytes[0] = (byte) (sourceNumber >> 24 & 0xFF);
		return orderBytes;
	}

	/**
	 * 用SHA1算法生成安全签名
	 * 
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @param encrypt
	 * @return
	 */
	private static String buildSignature(String token, String timestamp, String nonce, String encrypt) {
		String[] array = new String[] { token, timestamp, nonce, encrypt };
		StringBuffer sb = new StringBuffer();
		// 字符串排序
		Arrays.sort(array);
		for (int i = 0; i < 4; i++)
			sb.append(array[i]);
		return DigestUtils.sha1Hex(sb.toString());
	}

	/**
	 * 生成xml消息
	 * 
	 * @param encrypt
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	private static String generate(String encrypt, String signature, String timestamp, String nonce) {
		String format = "<xml>\n" + "<Encrypt><![CDATA[%1$s]]></Encrypt>\n"
				+ "<MsgSignature><![CDATA[%2$s]]></MsgSignature>\n" + "<TimeStamp>%3$s</TimeStamp>\n"
				+ "<Nonce><![CDATA[%4$s]]></Nonce>\n" + "</xml>";
		return String.format(format, encrypt, signature, timestamp, nonce);
	}
}
