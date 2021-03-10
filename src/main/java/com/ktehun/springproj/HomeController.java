package com.ktehun.springproj;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ktehun.service.BoardService;
import com.ktehun.util.MediaConfirm;
import com.ktehun.util.UploadFileProcess;
import com.sun.net.httpserver.HttpExchange;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Inject
	private BoardService bercice;
	
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
	
	
	@RequestMapping(value="/doInterceptor",method = RequestMethod.POST)
	public String doInterceptor(Locale locale,Model model) throws Exception{
		System.out.println("/doInterceptor가 Post 방식으로");
		
		model.addAttribute("boardList",bercice.listAll());
		return "home.jsp";
	}
	@RequestMapping(value="/uploadAjax" , method = RequestMethod.GET)
	public void uploadAjax() {
		
	}
	


	@RequestMapping(value = "/uploadAjax" , method = RequestMethod.POST,produces = "text/plain; charset=UTF-8")
	public ResponseEntity<String> uploadAjax(HttpServletRequest request,MultipartFile file, Model model) {
		System.out.println("파일이름 : "+file.getOriginalFilename());
		System.out.println("파일사이즈 : "+file.getSize());
		System.out.println("업로드 파일의 타입 : "+file.getContentType()); //파일의 MINE TYPE
		try {
			UploadFileProcess.uploadFile("resources/uploads/", file.getOriginalFilename(), file.getBytes());
			return new ResponseEntity<String>(file.getOriginalFilename(),HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@ResponseBody // byte[] 의 데이터(파일 데이터)가 web에 그대로 전송 되도록
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(HttpServletRequest request,String fileName) throws IOException{
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		
		
		String ext = fileName.substring(fileName.lastIndexOf(".")+1); // 확장자 추출
		 
		MediaType mType = MediaConfirm.getMediaType(ext); // 이미지 파일인지 아닌지 검사하기 위해
		HttpHeaders header = new HttpHeaders(); // 헤더 객체 생성(데이터 패킷의 header에 MIME type 정보를 붙이기 위해
		
		String path = request.getSession().getServletContext().getRealPath("resores/uploads");
		fileName = fileName.replace('/',File.separatorChar);
		logger.info(path+fileName);
		in = new FileInputStream(path + fileName); // 서버의 하드디스크에서 업로드된 파일을 읽어오는 스트림 객체
		
		
		if(mType != null) {
			header.setContentType(mType); // header에 MIME type 정보를 붙이기 위해
		}else {
			fileName = fileName.substring(fileName.indexOf("_")+1); // UUID_다음 originalFileName을 얻어옴
			header.setContentType(MediaType.APPLICATION_OCTET_STREAM); // 다운로드 가능한 파일임(헤더에 MIME 정보 붙임)
			header.add("Content-Disposition","attatchment ,filenane=\"" // 다운로드 될 수 있도록 일종의 링크 제공
			+new String(fileName.getBytes("UTF-8"),"ISO-8859-1")+"\"");
			
		}
		try {
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),header,HttpStatus.CREATED); // 파일을 읽은 뒤 데이터를 그대로 전송
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			entity = new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		}finally {
			in.close();
		}
		return entity;
		
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
	@RequestMapping(value = "/deleteFile", method=RequestMethod.POST)
	public ResponseEntity<String> deleteFile(HttpServletRequest request,String fileName){
		
		  String ext = fileName.substring(fileName.indexOf("_")+1);
		  String path = request.getSession().getServletContext().getRealPath("resources/uploads");
	      System.out.println(path);
	      
	      MediaType mType = MediaConfirm.getMediaType(ext);
	      String tmp = fileName.replace("thumb_","");
	      
	      String originalFile = path+tmp;
	      String thumbFile = path+fileName;
	      
	      originalFile = originalFile.replace('/',File.separatorChar);
	      thumbFile = thumbFile.replace('/',File.separatorChar);
	      
	     logger.info("삭제할 파일 (Original : "+originalFile+", thumb : "+thumbFile);
	     
	     if(mType != null) {
	    	new File(originalFile).delete();
	    	new File(thumbFile).delete();
	     }
	     
	     // DB제거
	     return new ResponseEntity<String>("Success",HttpStatus.OK);
	}
		
	private String uploadFile(HttpServletRequest request, String originalFilename, byte[] file) throws IOException {
	      // UUID : Universal Unique ID
	      UUID uuid = UUID.randomUUID();
	      
	      String saveName = uuid.toString() + "_" + originalFilename;
	      System.out.println("완성된 파일 이름 : " + saveName);
	      
	      String path = request.getSession().getServletContext().getRealPath("resources/uploads");
	      System.out.println(path);
	      File target = new File(request.getSession().getServletContext().getRealPath("resources/uploads"), saveName);
	      FileCopyUtils.copy(file, target); // 복사
	      return saveName;
	   }
	}
