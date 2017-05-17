package com.jason.se.io.encoding;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.junit.Test;


public class CharacterEncoding
{	
	public static String getDefaultEncoding(){
		String charsetName=Charset.defaultCharset().name();
		if(charsetName==null||charsetName.trim().length()<1){
			charsetName=System.getProperty("file.encoding");
		}
		return charsetName;
	}
	
	@Test
	public void StringEncoding() throws UnsupportedEncodingException{
		String src="字符集";
		byte[] bt = src.getBytes("GBK"); //使用指定字符集进行编码  
		byte[] bt1 = src.getBytes();//编码默认系统字符集进行编码  
		String desc1=new String(bt1,0,bt1.length,"UTF-8");//解码
		String desc2=new String(bt,"GBK");//解码：用什么字符集编码就用什么字符集解码
		System.out.println((bt.length/2==bt1.length/3)+"?"+bt+"="+desc2+":"+desc1); 
		
		char han='汉';
		System.out.format("%x",(short)han);//6c49
		System.out.println((char)0x6c49);
	}
	
	
	public  static void main1(String[] args) throws UnsupportedEncodingException
	{
		String str="我们";
		byte[] bt=str.getBytes("UTF-8");//[B@69066caf
		System.out.println(new String(bt,"GBK"));
		System.out.println(str+":"+bt);
		
		
		System.out.println(System.getProperty("sun.jnu.encoding"));//GBK
		System.out.println(System.getProperty("file.encoding"));//UTF-8
		
		
		/*
	     Properties properties = System.getProperties();
		properties.setProperty("sun.jnu.encoding","ISO8859-1");
		properties.setProperty("file.encoding","ISO8859-1");
		System.out.println(properties.getProperty("sun.jnu.encoding"));//UTF-8
		System.out.println(properties.getProperty("file.encoding"));//GBK
*/
       
		/*Iterator<Entry<Object, Object>> iterator = properties.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<java.lang.Object,java.lang.Object> entry = (Map.Entry<java.lang.Object,java.lang.Object>) iterator
					.next();
			System.out.println(entry.getKey()+":"+entry.getValue());
		} */
			
		/*java.runtime.name:Java(TM) SE Runtime Environment
		sun.boot.library.path:C:\Program Files\Java\jdk1.6.0_45\jre\bin
		java.vm.version:20.45-b01
		java.vm.vendor:Sun Microsystems Inc.
		java.vendor.url:http://java.sun.com/
		path.separator:;
		java.vm.name:Java HotSpot(TM) 64-Bit Server VM
		file.encoding.pkg:sun.io
		sun.java.launcher:SUN_STANDARD
		user.country:CN
		sun.os.patch.level:Service Pack 1
		java.vm.specification.name:Java Virtual Machine Specification
		user.dir:E:\JasonWorkspace\Jason\Jason-se
		java.runtime.version:1.6.0_45-b06
		java.awt.graphicsenv:sun.awt.Win32GraphicsEnvironment
		java.endorsed.dirs:C:\Program Files\Java\jdk1.6.0_45\jre\lib\endorsed
		os.arch:amd64
		java.io.tmpdir:C:\Users\ADMINI~1\AppData\Local\Temp\
		line.separator:

		java.vm.specification.vendor:Sun Microsystems Inc.
		user.variant:
		os.name:Windows 7
		sun.jnu.encoding:GBK
		java.library.path:C:\Program Files\Java\jdk1.6.0_45\bin;C:\windows\Sun\Java\bin;C:\windows\system32;C:\windows;C:/Program Files/Java/jdk1.6.0_45/bin/../jre/bin/server;C:/Program Files/Java/jdk1.6.0_45/bin/../jre/bin;C:/Program Files/Java/jdk1.6.0_45/bin/../jre/lib/amd64;D:\app\Administrator\product\11.2.0\dbhome_1\bin;C:\Program Files (x86)\AMD APP\bin\x86_64;C:\Program Files (x86)\AMD APP\bin\x86;C:\windows\system32;C:\windows;C:\windows\System32\Wbem;C:\windows\System32\WindowsPowerShell\v1.0\;C:\Program Files (x86)\Intel\OpenCL SDK\2.0\bin\x86;C:\Program Files (x86)\Intel\OpenCL SDK\2.0\bin\x64;C:\Program Files (x86)\Intel\Services\IPT\;C:\Program Files (x86)\ATI Technologies\ATI.ACE\Core-Static;C:\Program Files\Java\jdk1.6.0_45\bin;C:\Program Files\Java\jdk1.6.0_45\jre\bin;D:\ProgramFiles\apache-maven-3.0.5\bin;D:\ProgramFiles\MySQL\MySQL Server 5.6\bin;D:\ProgramFiles\redis-2.4.5-win32-win64\64bit;D:\PROGRA~2\IBM\SQLLIB\BIN;D:\PROGRA~2\IBM\SQLLIB\FUNCTION;D:\PROGRA~2\IBM\SQLLIB\SAMPLES\REPL;D:\ProgramFiles\nexus-2.5-bundle\nexus-2.5.0-04\bin\jsw\windows-x86-64;C:\Program Files\SlikSvn\bin\;C:\Program Files\TortoiseSVN\bin;D:\ProgramFiles\Microsoft Visual Studio\Common\Tools\WinNT;D:\ProgramFiles\Microsoft Visual Studio\Common\MSDev98\Bin;D:\ProgramFiles\Microsoft Visual Studio\Common\Tools;D:\ProgramFiles\Microsoft Visual Studio\VC98\bin;C:\Program Files (x86)\SSH Communications Security\SSH Secure Shell;D:\ProgramFiles\eclipse;;.
		java.specification.name:Java Platform API Specification
		java.class.version:50.0
		sun.management.compiler:HotSpot 64-Bit Tiered Compilers
		os.version:6.1
		user.home:D:\MyDocument
		user.timezone:
		java.awt.printerjob:sun.awt.windows.WPrinterJob
		file.encoding:UTF-8
		java.specification.version:1.6
		java.class.path:E:\JasonWorkspace\Jason\Jason-se\target\classes;E:\auxiliary\repository\c3p0\c3p0\0.9.1.2\c3p0-0.9.1.2.jar;E:\auxiliary\repository\com\oracle\ojdbc6\11.2.0.1.0\ojdbc6-11.2.0.1.0.jar;E:\auxiliary\repository\javax\mail\mail\1.4.5\mail-1.4.5.jar;E:\auxiliary\repository\javax\activation\activation\1.1\activation-1.1.jar;E:\auxiliary\repository\java3d\vecmath\1.3.1\vecmath-1.3.1.jar;E:\auxiliary\repository\java3d\j3d-core\1.3.1\j3d-core-1.3.1.jar;E:\auxiliary\repository\java3d\j3d-core-utils\1.3.1\j3d-core-utils-1.3.1.jar;E:\auxiliary\repository\org\apache\poi\poi\3.8\poi-3.8.jar;E:\auxiliary\repository\commons-codec\commons-codec\1.5\commons-codec-1.5.jar;E:\auxiliary\repository\bouncycastle\bcprov-jdk14\138\bcprov-jdk14-138.jar;E:\auxiliary\repository\com\lowagie\itext\4.2.1\itext-4.2.1.jar;E:\auxiliary\repository\org\bouncycastle\bctsp-jdk14\1.38\bctsp-jdk14-1.38.jar;E:\auxiliary\repository\org\bouncycastle\bcmail-jdk14\1.38\bcmail-jdk14-1.38.jar;E:\auxiliary\repository\dom4j\dom4j\1.6.1\dom4j-1.6.1.jar;E:\auxiliary\repository\xml-apis\xml-apis\1.0.b2\xml-apis-1.0.b2.jar;E:\auxiliary\repository\jfree\jfreechart\1.0.12\jfreechart-1.0.12.jar;E:\auxiliary\repository\jfree\jcommon\1.0.15\jcommon-1.0.15.jar;E:\auxiliary\repository\org\swinglabs\pdf-renderer\1.0.5\pdf-renderer-1.0.5.jar;E:\auxiliary\repository\org\bouncycastle\bcprov-jdk14\1.49\bcprov-jdk14-1.49.jar;E:\auxiliary\repository\javax\servlet\javax.servlet-api\3.0.1\javax.servlet-api-3.0.1.jar;E:\auxiliary\repository\javax\media\jmf\2.1.1e\jmf-2.1.1e.jar;E:\auxiliary\repository\pdfbox\pdfbox\0.7.3\pdfbox-0.7.3.jar;E:\auxiliary\repository\org\fontbox\fontbox\0.1.0\fontbox-0.1.0.jar;E:\auxiliary\repository\org\jempbox\jempbox\0.2.0\jempbox-0.2.0.jar;E:\auxiliary\repository\bouncycastle\bcmail-jdk14\139\bcmail-jdk14-139.jar;E:\auxiliary\repository\commons-cli\commons-cli\1.2\commons-cli-1.2.jar
		user.name:Administrator
		java.vm.specification.version:1.0
		sun.java.command:com.jason.se.io.encoding.StringEncoding
		java.home:C:\Program Files\Java\jdk1.6.0_45\jre
		sun.arch.data.model:64
		user.language:zh
		java.specification.vendor:Sun Microsystems Inc.
		awt.toolkit:sun.awt.windows.WToolkit
		java.vm.info:mixed mode
		java.version:1.6.0_45
		java.ext.dirs:C:\Program Files\Java\jdk1.6.0_45\jre\lib\ext;C:\windows\Sun\Java\lib\ext
		sun.boot.class.path:C:\Program Files\Java\jdk1.6.0_45\jre\lib\resources.jar;C:\Program Files\Java\jdk1.6.0_45\jre\lib\rt.jar;C:\Program Files\Java\jdk1.6.0_45\jre\lib\sunrsasign.jar;C:\Program Files\Java\jdk1.6.0_45\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.6.0_45\jre\lib\jce.jar;C:\Program Files\Java\jdk1.6.0_45\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.6.0_45\jre\lib\modules\jdk.boot.jar;C:\Program Files\Java\jdk1.6.0_45\jre\classes
		java.vendor:Sun Microsystems Inc.
		file.separator:\
		java.vendor.url.bug:http://java.sun.com/cgi-bin/bugreport.cgi
		sun.io.unicode.encoding:UnicodeLittle
		sun.cpu.endian:little
		sun.desktop:windows
		sun.cpu.isalist:amd64*/
	}
	
	
}
