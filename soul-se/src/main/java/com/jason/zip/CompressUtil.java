package com.jason.zip;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import sun.security.action.GetPropertyAction;

/**
 * 用ZIP压缩和解压缩文件或目录
 */
public class CompressUtil
{

	static {

	}

	/**
	 * 压缩文件或者目录
	 * 
	 * @param baseDirName
	 *            压缩的根目录
	 * @param fileName
	 *            根目录下待压缩的文件或文件夹名， 星号*表示压缩根目录下的全部文件。
	 * @param targetFileName
	 *            目标ZIP文件
	 */
	public static void zipFile(String baseDirName, String fileName,
			String targetFileName)
	{
		// 检测根目录是否存在
		if (baseDirName == null) {
			System.out.println("压缩失败，根目录不存在：" + baseDirName);
			return;
		}
		File baseDir = new File(baseDirName);
		if (!baseDir.exists() || (!baseDir.isDirectory())) {
			System.out.println("压缩失败，根目录不存在：" + baseDirName);
			return;
		}
		String baseDirPath = baseDir.getAbsolutePath();
		// 目标文件
		File targetFile = new File(targetFileName);
		try {
			// 创建一个zip输出流来压缩数据并写入到zip文件
			ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
					targetFile));
			if (fileName.equals("*")) {
				// 将baseDir目录下的所有文件压缩到ZIP
				CompressUtil.dirToZip(baseDirPath, baseDir, out);
			} else {
				File file = new File(baseDir, fileName);
				if (file.isFile()) {
					CompressUtil.fileToZip(baseDirPath, file, out);
				} else {
					CompressUtil.dirToZip(baseDirPath, file, out);
				}
			}
			out.close();
			System.out.println("压缩文件成功，目标文件名：" + targetFileName);
		} catch (IOException e) {
			System.out.println("压缩失败：" + e);
			e.printStackTrace();
		}
	}

	/**
	 * 解压缩ZIP文件，将ZIP文件里的内容解压到targetDIR目录下
	 * 
	 * @param zipName
	 *            待解压缩的ZIP文件名
	 * @param targetBaseDirName
	 *            目标目录
	 */

	public static void upzipFile(String zipFileName, String targetBaseDirName)
	{
		if (!targetBaseDirName.endsWith(File.separator)) {
			targetBaseDirName += File.separator;
		}
		try {
			System.setProperty("sun.zip.encoding", "GBK");
			System.out.println(java.security.AccessController
					.doPrivileged(new GetPropertyAction("sun.zip.encoding")));
			ZipInputStream inputStream = new ZipInputStream(
					new FileInputStream(new File(zipFileName)));
			byte[] buf = new byte[1024];
			int len;
			String targetFileName;
			ZipEntry entry = null;
			while ((entry = inputStream.getNextEntry()) != null) {
				targetFileName = targetBaseDirName + entry.getName();
				if (entry.isDirectory()) {
					File dirFile = new File(targetFileName);
					dirFile.mkdirs();
					continue;
				} else {
					new File(targetFileName).getParentFile().mkdirs();
				}
				System.out.println("创建文件：" + targetFileName);
				BufferedOutputStream outputStream = new BufferedOutputStream(
						new FileOutputStream(new File(targetFileName)));
				while ((len = inputStream.read(buf)) != -1) {
					outputStream.write(buf, 0, len);
					outputStream.flush();
				}
				outputStream.close();
				inputStream.closeEntry();
			}
			inputStream.close();
			System.out.println("解压缩文件成功！");
		} catch (IOException err) {
			System.err.println("解压缩文件失败: " + err);
		}
	}

	private static void dirToZip(String baseDirPath, File dir,
			ZipOutputStream out)
	{
		if (dir.isDirectory()) {
			File[] files = dir.listFiles();
			if (files.length == 0) {
				ZipEntry entry = new ZipEntry(getEntryName(baseDirPath, dir));
				try {
					out.putNextEntry(entry);
					out.closeEntry();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					CompressUtil.fileToZip(baseDirPath, files[i], out);
				} else {
					CompressUtil.dirToZip(baseDirPath, files[i], out);
				}
			}
		}
	}

	private static void fileToZip(String baseDirPath, File file,
			ZipOutputStream out)
	{
		FileInputStream in = null;
		ZipEntry entry = null;
		byte[] buffer = new byte[4096];
		int bytes_read;
		if (file.isFile()) {
			try {
				in = new FileInputStream(file);
				entry = new ZipEntry(getEntryName(baseDirPath, file));
				out.putNextEntry(entry);
				while ((bytes_read = in.read(buffer)) != -1) {
					out.write(buffer, 0, bytes_read);
				}
				out.closeEntry();
				in.close();
				System.out.println("添加文件" + file.getAbsolutePath()
						+ "被到ZIP文件中！");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static String getEntryName(String baseDirPath, File file)
	{
		if (!baseDirPath.endsWith(File.separator)) {
			baseDirPath += File.separator;
		}
		String filePath = file.getAbsolutePath();
		if (file.isDirectory()) {
			filePath += "/";
		}
		int index = filePath.indexOf(baseDirPath);
		return filePath.substring(index + baseDirPath.length());
	}

	public static void main(String[] args)
	{
		/*
		 * //压缩C盘下的temp目录，压缩后的文件是C:/temp.zip String baseDirName = "C:/"; String
		 * fileName = "temp/"; String zipFileName = "C:/temp.zip";
		 * CompressUtil.zipFile(baseDirName, fileName, zipFileName);
		 * //将刚创建的ZIP文件解压缩到D盘的temp目录下 System.out.println(); fileName =
		 * "D:/temp"; CompressUtil.upzipFile(zipFileName, fileName);
		 */
	

		// String zipFileName="E:/tmsdata/app/4/1377152250947.zip";
		// String targetBaseDirName="E:/tmsdata/app/4/1377152250947";
//		String zipFileName = "E:/tmsdata/app/4/1377152250947/config.zip";
//		String targetBaseDirName = "E:/tmsdata/app/4/1377152250947/config";

//		 String zipFileName="D:/电.zip";
//		 String targetBaseDirName="D:/电";
		 String zipFileName="D:/电/config.zip";
		 String targetBaseDirName="D:/电/config";
		 
		CompressUtil.upzipFile(zipFileName, targetBaseDirName);
		// CompressUtil.upzipFile(targetBaseDirName+File.separator+"config.zip",
		// targetBaseDirName+File.separator+"config");
	}
}
