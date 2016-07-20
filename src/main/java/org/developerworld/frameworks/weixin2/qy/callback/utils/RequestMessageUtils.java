package org.developerworld.frameworks.weixin2.qy.callback.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
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
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.developerworld.frameworks.weixin2.qy.callback.dto.DecryptMessage;
import org.developerworld.frameworks.weixin2.qy.callback.dto.EncryptMessage;
import org.developerworld.frameworks.weixin2.qy.callback.dto.enums.EventType;
import org.developerworld.frameworks.weixin2.qy.callback.dto.enums.MsgType;
import org.developerworld.frameworks.weixin2.qy.callback.dto.req.AbstractReq;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * 请求消息工具类
 * 
 * @author Roy Huang
 *
 */
public class RequestMessageUtils {

	private final static Charset CHARSET = Charset.forName("utf-8");

	private static DocumentBuilderFactory documentBuilderFactory;
	private static DocumentBuilder documentBuilder;

	/**
	 * 获取xml文档构建器
	 * 
	 * @return
	 * @throws ParserConfigurationException
	 */
	private static DocumentBuilder getDocumentBuilder() throws ParserConfigurationException {
		if (documentBuilder == null) {
			if (documentBuilderFactory == null)
				documentBuilderFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
		}
		return documentBuilder;
	}

	/**
	 * 校验接入信息
	 * 
	 * @param msgSignature
	 * @param timestamp
	 * @param nonce
	 * @param echostr
	 * @param token
	 * @return
	 */
	public static boolean verifyJoinUpMsgSignature(String msgSignature, String timestamp, String nonce, String echostr,
			String token) {
		// 构建验证数组
		String[] validateArr = new String[] { token, timestamp, nonce, echostr };
		// 字典排序
		Arrays.sort(validateArr);
		// 构建验证字符串
		String validateStr = "";
		for (String validate : validateArr)
			validateStr += validate;
		// sha1加密
		validateStr = DigestUtils.sha1Hex(validateStr);
		return msgSignature.equals(validateStr);
	}

	/**
	 * 解密消息
	 * 
	 * @param encodingAESKey
	 * @param message
	 * @return
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 */
	public static DecryptMessage decryptMessage(String encodingAESKey, String message)
			throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException,
			BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
		// 转换为base64字节
		byte[] aesKey = Base64.decodeBase64(encodingAESKey + "=");
		// 设置解密模式为AES的CBC模式
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		SecretKeySpec secretKeySpec = new SecretKeySpec(aesKey, "AES");
		IvParameterSpec ivParameterSpec = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
		// 使用BASE64对密文进行解码
		byte[] encrypted = Base64.decodeBase64(message);
		// 解密
		byte[] original = cipher.doFinal(encrypted);
		// 去除补位字符
		byte[] bytes = PKCS7EncoderUtils.decode(original);
		// 分离16位随机字符串,网络字节序和corpId
		String random = new String(Arrays.copyOfRange(bytes, 0, 16), CHARSET);
		byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);
		int msgLen = recoverNetworkBytesOrder(networkOrder);
		String msg = new String(Arrays.copyOfRange(bytes, 20, 20 + msgLen), CHARSET);
		String corpId = new String(Arrays.copyOfRange(bytes, 20 + msgLen, bytes.length), CHARSET);
		return new DecryptMessage(encodingAESKey, message, random, msgLen, msg, corpId);
	}

	/**
	 * 还原4个字节的网络字节序
	 * 
	 * @param orderBytes
	 * @return
	 */
	private static int recoverNetworkBytesOrder(byte[] orderBytes) {
		int sourceNumber = 0;
		for (int i = 0; i < 4; i++) {
			sourceNumber <<= 8;
			sourceNumber |= orderBytes[i] & 0xff;
		}
		return sourceNumber;
	}

	/**
	 * 根据xml内容构建解密消息对象
	 * 
	 * @param messageReqXml
	 * @return
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 */
	public static EncryptMessage buildEncryptMessage(String messageReqXml)
			throws SAXException, IOException, ParserConfigurationException {
		StringReader sr = new StringReader(messageReqXml);
		InputSource is = new InputSource(sr);
		Document document = getDocumentBuilder().parse(is);
		Element root = document.getDocumentElement();
		NodeList nodelist1 = root.getElementsByTagName("ToUserName");
		NodeList nodelist2 = root.getElementsByTagName("AgentID");
		NodeList nodelist3 = root.getElementsByTagName("Encrypt");
		return new EncryptMessage(nodelist1.item(0).getTextContent(),
				Integer.valueOf(nodelist2.item(0).getTextContent()), nodelist3.item(0).getTextContent());
	}

	/**
	 * 校验请求消息信息
	 * 
	 * @param token
	 * @param timestamp
	 * @param nonce
	 * @param encrypt
	 * @param msgSignature
	 * @return
	 */
	public static boolean verifyMsgSignature(String token, String timestamp, String nonce, String encrypt,
			String msgSignature) {
		String[] validateArr = new String[] { token, timestamp, nonce, encrypt };
		// 字符串排序
		Arrays.sort(validateArr);
		// 构建验证字符串
		String validateStr = "";
		for (String validate : validateArr)
			validateStr += validate;
		// sha1加密
		validateStr = DigestUtils.sha1Hex(validateStr);
		return msgSignature.equals(validateStr);
	}

	/**
	 * 构建请求消息对象
	 * 
	 * @param messageReqXml
	 * @return
	 * @throws IOException
	 * @throws JAXBException
	 */
	public static <T extends AbstractReq> T buildReq(String messageReqXml) throws IOException, JAXBException {
		T rst = null;
		MsgType msgType = getMessageReqMsgType(messageReqXml);
		if (msgType != null) {
			Class<? extends AbstractReq> reqClass = null;
			// 事件消息
			if (msgType.equals(MsgType.EVENT)) {
				EventType eventType = getMessageReqEventType(messageReqXml);
				reqClass = eventType.getEventReqClass();
			}
			// 非事件消息类型
			else
				reqClass = msgType.getReqClass();
			rst = (T) unMarshal(messageReqXml, reqClass);
		}
		return rst;
	}

	/**
	 * xml反序列化
	 * @param xml
	 * @param classType
	 * @return
	 * @throws JAXBException
	 * @throws IOException
	 */
	public static <T> T unMarshal(String xml, Class<T> classType) throws JAXBException, IOException {
		T rst = null;
		JAXBContext context = JAXBContext.newInstance(classType);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		InputStream is = IOUtils.toInputStream(xml);
		try {
			rst = (T) unmarshaller.unmarshal(is);
		} finally {
			is.close();
		}
		return rst;
	}

	/**
	 * 获取消息类型
	 * 
	 * @param messageReqXml
	 * @return
	 */
	public static MsgType getMessageReqMsgType(String messageReqXml) {
		MsgType rst = null;
		String beginKey = "<MsgType><![CDATA[";
		int begin = messageReqXml.indexOf(beginKey);
		if (begin != -1) {
			String endKey = "]]></MsgType>";
			int end = messageReqXml.indexOf(endKey, begin + beginKey.length());
			if (end != -1)
				rst = MsgType.valueOfValue(messageReqXml.substring(begin + beginKey.length(), end));
		}
		return rst;
	}

	/**
	 * 获取消息事件类型
	 * 
	 * @param messageReqXml
	 * @return
	 */
	public static EventType getMessageReqEventType(String messageReqXml) {
		EventType rst = null;
		String beginKey = "<Event><![CDATA[";
		int begin = messageReqXml.indexOf(beginKey);
		if (begin != -1) {
			String endKey = "]]></Event>";
			int end = messageReqXml.indexOf(endKey, begin + beginKey.length());
			if (end != -1)
				rst = EventType.valueOfValue(messageReqXml.substring(begin + beginKey.length(), end));
		}
		return rst;
	}

}
