package com.ktehun.springproj;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static String uploadURL = "E:\\\\html\\\\lecture\\\\springUplode";
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "index";
	}
	@RequestMapping(value = "/test",method = RequestMethod.GET)
	public String ajaxTest() {
		return "testJson";
	}
	@RequestMapping(value = "/uploadForm", method=RequestMethod.GET)
	public void uploadForm() {
		
	}
	
	@RequestMapping(value = "/uploadForm", method=RequestMethod.POST)
	public String uploadForm(HttpServletRequest request,MultipartFile uploadFile,Model model) throws IOException {
		System.out.println("파일이름 : "+uploadFile.getOriginalFilename());
		System.out.println("파일사이즈 : "+uploadFile.getSize());
		System.out.println("업로드 파일의 타입 : "+uploadFile.getContentType()); //파일의 MINE TYPE
		String uploadFileName= uploadFile(request,uploadFile.getOriginalFilename(),uploadFile.getBytes()); 
		
		System.out.println(request.getRealPath("resources/uploads/")+uploadFileName);
		
		model.addAttribute("saveFileName", uploadFileName);
		return "UplodResult";
		
		
	}
	private String uploadFile(HttpServletRequest request,String getOriginalFilename,byte[] file) throws IOException {
		UUID uuid = UUID.randomUUID();
		
		String savaName = uuid.toString() + "_"+getOriginalFilename;
		System.out.println("완성된 파일이름 : "+savaName);	
		File target = new File(request.getRealPath("resources/uploads"),savaName);
		FileCopyUtils.copy(file, target);
		return savaName;
	}
	}
