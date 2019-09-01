package com.spring.mvc.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	private static final String subPath = "\\src\\main\\webapp\\public\\test_dir\\uploaded";
	
	public static void approvalFile(MultipartFile filecontent) {
		System.out.println("saving...");
		OutputStream os = null;
		InputStream inputStream = null;
		String fileName = null;
		try {
			inputStream = filecontent.getInputStream();
			fileName = filecontent.getOriginalFilename();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			String path = localPath() + subPath;
			System.out.println("saving " + fileName + " to " + path);
			// 保存到临时文件
			// 1K的数据缓冲
			byte[] bs = new byte[1024];
			// 读取到的数据长度
			int len;
			// 输出的文件流保存到本地文件
			File tempFile = new File(path);
			if (!tempFile.exists()) {
				tempFile.mkdirs();
			}
			os = new FileOutputStream(tempFile.getPath() + File.separator + fileName);
			// 开始读取
			while ((len = inputStream.read(bs)) != -1) {
				os.write(bs, 0, len);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 完毕，关闭所有链接
			try {
				os.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("saved");
	}

	private static String localPath() throws IOException {
		return (new File("")).getCanonicalPath();
	}

	public static void main(String[] args) throws IOException {
		String path = localPath() + subPath;
		System.out.println(path);
	}
}