//package com.soul.tutorials;
//
//import java.io.UnsupportedEncodingException;
//
//import com.soul.tutorials.security.AESUtils;
//
//public class TestAESUtils {
//	
//	private String secryt_key = "hnns_01234561607";
//	private AESUtils cipher = null;
//	private String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><queryParam><serialNo>20160722173300</serialNo><enterpriseName>如皋市顶丰鞋厂</enterpriseName><userName>HNNSBANK</userName><userPasswd>HNNS123456789</userPasswd></queryParam>";
//	private String targetEncryptdata = "BNPe9ebVq/9DFjXjnbCl+9dc0tZuUXzrpJ8wgEVl4hSFp/cVCQ91YjRQ/U3zwaPWzMY12+3sTLHuR/lMFd/OsTn4FMejMMl8LCnIDjXjMjWwajVWNUlk4+C5H1m+JimUPkUKx1Y9DBXoFZNDJPPFDI/ifxZoZPv3IH76AV7t0QdPOkxrXTjvrJrKj+Vgpqn+cY70pTiTyN+g4gInmTAi45yJOe9NhvlCC3n9D8Qf3GDFu7CfoCrDSSDeKq9o277eUoPmKHY+UtPM6Sr3TJg0ryeSoh9zLrT6m9uoSaW7DTw=";
//
//	@Before
//	public void setUp() throws Exception {
//		try {
//			cipher = new AESUtils(secryt_key);
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@After
//	public void tearDown() throws Exception {
//	}
//
//	@Test
//	public void testEncryptData() throws Exception {
//		assertEquals(cipher.encryptData(data), targetEncryptdata);
//	}
//
//	@Test
//	public void testDecryptData() throws Exception {
//		assertEquals(cipher.decryptData(cipher.encryptData(data)), data);
//	}
//
//}
