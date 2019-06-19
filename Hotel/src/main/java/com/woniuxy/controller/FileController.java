package com.woniuxy.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/file")
public class FileController {
	

	@RequestMapping("/upload")
	public String upload(MultipartFile headImg,HttpServletRequest request) throws IllegalStateException, IOException {
		//获取文件名
		String fileName = headImg.getOriginalFilename();//文件名
		System.out.println(fileName);
		
		//获取当前项目的路径
		String path = request.getServletContext().getRealPath("");
		System.out.println(path);
		File currentPjo = new File(path);//当前项目的根路径
		//获取上一级目录
		File webapps = currentPjo.getParentFile();
		//获取保存文件的文件夹
		File upload = new File(webapps, "upload");
		//判断文件夹是否存在
		if (!upload.exists()) {
			upload.mkdirs();
		}
		//获取新文件名
		String newFileName = UUID.randomUUID().toString()+
				fileName.substring(fileName.lastIndexOf("."));
		//获取新文件的路径
		String newFilePath = upload.getAbsolutePath()+File.separator+newFileName;
		//得到新文件的file对象
		File newFile = new File(newFilePath);
		//保存到本地
		headImg.transferTo(newFile);
		return "上传成功";
	}
}
