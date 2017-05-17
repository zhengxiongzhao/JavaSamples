package com.jason.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.OutputStream;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

/**
* @ClassName: FileCopyUtilsExample
* @Description: TODO(文件操作)
* @author Jason Chiu:CIHUnKnown@Gmail.com
* @date 2013-8-16 下午06:00:02
* 
*/
//在使用各种 Resource 接口的实现类加载文件资源后，经常需要对文件资源进行读取、拷贝、转存等不同类型的操作。您可以通过 Resource 接口所提供了方法完成这些功能，不过在大多数情况下，通过 Spring 为 Resource 所配备的工具类完成文件资源的操作将更加方便。
//文件内容拷贝
//第一个我们要认识的是 FileCopyUtils，它提供了许多一步式的静态操作方法，能够将文件内容拷贝到一个目标 byte[]、String 甚至一个输出流或输出文件中。下面的实例展示了 FileCopyUtils 具体使用方法：
//往往我们都通过直接操作 InputStream 读取文件的内容，但是流操作的代码是比较底层的，代码的面向对象性并不强。通过 FileCopyUtils 读取和拷贝文件内容易于操作且相当直观。如在 ① 处，我们通过 FileCopyUtils 的 copyToByteArray(File in) 方法就可以直接将文件内容读到一个 byte[] 中；另一个可用的方法是 copyToByteArray(InputStream in)，它将输入流读取到一个 byte[] 中。
//如果是文本文件，您可能希望将文件内容读取到 String 中，此时您可以使用 copyToString(Reader in) 方法，如 ② 所示。使用 FileReader 对 File 进行封装，或使用 InputStreamReader 对 InputStream 进行封装就可以了。
//FileCopyUtils 还提供了多个将文件内容拷贝到各种目标对象中的方法，这些方法包括：
//在实例中，我们虽然使用 Resource 加载文件资源，但 FileCopyUtils 本身和 Resource 没有任何关系，您完全可以在基于 JDK I/O API 的程序中使用这个工具类。
@SuppressWarnings("all")
public class FileCopyUtilsExample {
	public static void main(String[] args) throws Throwable {
		Resource res = new ClassPathResource("conf/file1.txt");
		// ① 将文件内容拷贝到一个 byte[] 中
		byte[] fileData = FileCopyUtils.copyToByteArray(res.getFile());
		// ② 将文件内容拷贝到一个 String 中
		String fileStr = FileCopyUtils.copyToString(new FileReader(res
				.getFile()));
		// ③ 将文件内容拷贝到另一个目标文件
		FileCopyUtils.copy(res.getFile(), new File(res.getFile().getParent()
				+ "/file2.txt"));

		// ④ 将文件内容拷贝到一个输出流中
		OutputStream os = new ByteArrayOutputStream();
		FileCopyUtils.copy(res.getInputStream(), os);
	}
}