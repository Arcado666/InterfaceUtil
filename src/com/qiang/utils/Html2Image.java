package com.qiang.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import gui.ava.html.image.generator.HtmlImageGenerator;
/**
 * 使用google的html2image工程把html转成图片
 * @author Administrator
 *
 */
public class Html2Image {
	/**
	 * 把html转成图片，
	 * @param in
	 * @param charsetName
	 * @param file  图片存储的地址，只填名字默认在根目录下
	 * @throws IOException
	 */
	public Html2Image(File in, String charsetName ,String file) throws IOException{
		Document document = Jsoup.parse(in, charsetName);
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		//不能转整个页面html的字符串，此处默认只转body
		imageGenerator.loadHtml(document.body().toString());
		imageGenerator.saveAsImage(file);
	}
	/**
	 * 把网站转成图片
	 * @param url
	 * @param file
	 */
	public Html2Image(String url,String file){
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		imageGenerator.loadUrl(url);
		imageGenerator.saveAsImage(file);
	}
	public Html2Image(URL url,String file){
		HtmlImageGenerator imageGenerator = new HtmlImageGenerator();
		imageGenerator.loadUrl(url);
		imageGenerator.saveAsImage(file);
	}
	public static void main(String[] args){
		try {
			new Html2Image(new File("D:/workspace/IwjwUser/test-output/html/overview.html"), "UTF-8", "C:\\Users\\Administrator\\Desktop\\GIT\\test.jpg");
//			new Html2Image("http://www.iwjw.com", "C:\\Users\\Administrator\\Desktop\\GIT\\test.png");
			new Html2Image(new URL("http://www.iwjw.com"), "C:\\Users\\Administrator\\Desktop\\GIT\\test2.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
