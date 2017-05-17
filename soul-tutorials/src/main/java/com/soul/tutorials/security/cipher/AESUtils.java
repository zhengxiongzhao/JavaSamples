package com.soul.tutorials.security.cipher;


import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class AESUtils {  
  
    /** 
     * Key algorithm
     */  
    private static final String ALGORITHM = "AES";  
    /** 
     * Encryption algorithm/Working mode/Filling way
     */  
    private static final String ALGORITHM_STR = "AES/ECB/PKCS5Padding";  
  
    /** 
     * SecretKeySpec class is KeySpec interface instance class, Used to construct the secret key specification
     */  
    private SecretKeySpec key;  
  
    public AESUtils(String hexKey) throws UnsupportedEncodingException {  
        key = new SecretKeySpec(hexKey.getBytes("UTF-8"), ALGORITHM);  
    }  
  
    public static String getEncoding(String text){
    	InputStreamReader r = new InputStreamReader( new ByteArrayInputStream(text.getBytes()));
    	return r.getEncoding();
    }
    
    /** 
     * AES Encrypt 
     * @param data 
     * @return 
     * @throws Exception 
     */  
    public String encryptData(String data) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR); 
        cipher.init(Cipher.ENCRYPT_MODE, key);
        // return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        return DatatypeConverter.printBase64Binary(cipher.doFinal(data.getBytes("UTF-8")));
    }  
  
    /** 
     * AES Decrypt
     * @param base64Data 
     * @return 
     * @throws Exception 
     */  
    public String decryptData(String base64Data) throws Exception{  
        Cipher cipher = Cipher.getInstance(ALGORITHM_STR);  
        cipher.init(Cipher.DECRYPT_MODE, key);  
        // return new String(cipher.doFinal(Base64.getDecoder().decode(base64Data)));
        return new String(cipher.doFinal(DatatypeConverter.parseBase64Binary((base64Data))));
    }  
  
    private static byte[] hex2byte(String s) {  
        if (s.length() % 2 == 0) {  
            return hex2byte (s.getBytes(), 0, s.length() >> 1);  
        } else {  
            return hex2byte("0"+s);  
        }  
    }  
  
    private static byte[] hex2byte (byte[] b, int offset, int len) {  
        byte[] d = new byte[len];  
        for (int i=0; i<len*2; i++) {  
            int shift = i%2 == 1 ? 0 : 4;  
            d[i>>1] |= Character.digit((char) b[offset+i], 16) << shift;  
        }  
        return d;  
    }  
}  
