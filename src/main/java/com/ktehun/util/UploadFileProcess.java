package com.ktehun.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;




public class UploadFileProcess {
	
	
	public static final Logger logger = LoggerFactory.getLogger(UploadFileProcess.class);
	/**
	 *@Method Name : uploadFile
	 *@작성일	2021. 3. 4.
	 *@작성자 goott6
	 *@변경이력 :
	 *@Method 설명 : 업로드된 파일을 기반으로 실제 저당되는 파일이 썸네일을 생성하든 , 파일 아이콘을 생성하든
	 *@param uploadPath
	 *@param originalName
	 *@param fileData
	 *@return
	 *@throws IOException
	 */
	public static String uploadFile(String uploadPath,String originalName,byte[] fileData) throws IOException {
		UUID uuid =UUID.randomUUID();
		
		String saveName = uuid.toString()+ "_" +originalName;
		String savePath = calPath(uploadPath);
		
		File target = new File(uploadPath + savePath,saveName);
		FileCopyUtils.copy(fileData,target);
		
 		String ext = originalName.substring(originalName.lastIndexOf(".")+1);
 		
 		String uploadFileNmame = null;
 		if(MediaConfirm.getMediaType(ext) != null) {
 			uploadFileNmame = makeThumbnail(uploadPath, savePath, saveName);
 		}else {
 			uploadFileNmame = makeIcon(uploadPath, savePath, saveName);
 		}
 		return uploadFileNmame;
	}

	private static String makeIcon(String uploadPath, String savePath, String saveName) {
		String iconName = uploadPath + savePath + File.separator+saveName;
		iconName.substring(uploadPath.length()).replace(File.separatorChar, '/');
		
		return iconName;
	}

	//************************************ 현재 연/월/일을 폴더로 만들기 위해 처리 ************************************
	/**
	* @Method Name : makeDir
	 * @작성일 : 2021. 3. 4.
	 * @작성자 goott6
	 * @변경이력 :
	 * @Method 설명 : 현재 날짜를 연/월/일/을 폴더로 만들기 위해 경로 처리
	 * @param uploadPath
	 * @return 하위 폴더 (연/월/일) 경로 반환
 */
	public static String calPath(String uploadPath) {
		Calendar cal = Calendar.getInstance();
		
		String yearPath = File.separator + (cal.get(Calendar.YEAR)+""); // 	\2021
		String monthPath = yearPath + new DecimalFormat("00").format(File.separator + ((cal.get(Calendar.MONTH)+1)+"")); // \03
		String datePath = monthPath + new DecimalFormat("00").format(File.separator+(cal.get(Calendar.DATE)+"")); // \04
		
		makeDir(uploadPath,yearPath,monthPath,datePath);
	//*********************************************************************************************************
		logger.info(datePath);
		
		
		return datePath;

		
		
	}
	
	/**
	 * 단축키 : alt + shift + j
	 * @Method Name : makeDir
	 * @작성일 : 2021. 3. 4.
	 * @작성자 goott6
	 * @변경이력 :
	 * @Method 설명 :
	 * @param uploadPath
	 * @param paths
	 */
	private static void makeDir(String uploadPath, String... paths) {
		//String... paths는 가변인자. String타입의 yearPath, MonthPath,datePath변수의 갑을 Paths의 배열의 값으로 받는다
		if(new File(uploadPath + paths[paths.length-1]).exists()) { //해당 경로에 해당 폴더가 존재하면... 돌아간다
			return;
			
		}
		for(String path :paths) {
			File dirPath = new File(uploadPath + path);
			//
			if(!dirPath.exists()) {
				dirPath.mkdir();
			}
		}
	}
	
	/**
	 *@Method Name : makeThumbnail
	 *@작성일	2021. 3. 4.
	 *@작성자 goott6
	 *@변경이력 : 
	 *@Method 설명 : 업로드 경로의 파일을 찾아 이미지 파일이면 썸네일을 만든다
	 *@param uploadPath : 서버의 파일 업로드 실제 경로
	 *@param path : 업로드 경로의 하위 연월일 경로
	 *@param fileName : 업로드된 파일 이름
	 *@returnt : 서버의 업로드 폴더 경로를 제외한 하위 경로 + 파일 이름 반환
	 *@throws IOException = write할 떄
	 */
	private static String makeThumbnail(String uploadPath,String path,String fileName) throws IOException {
	BufferedImage sourceImg = 	ImageIO.read(new File(uploadPath+path+fileName));
	BufferedImage destImg = Scalr.resize(sourceImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT,100); //높이가 100px 리사이징
	
	String thumbnailName = uploadPath + path + File.separator+"thumb_"+fileName;
	File newThumbFile = new File(thumbnailName);
	
	String ext = fileName.substring(fileName.lastIndexOf(".")+1);
	
	ImageIO.write(destImg, ext.toLowerCase(), newThumbFile);
	
	return thumbnailName.substring(uploadPath.length()).replace(File.separatorChar, '/');
	
	}
	
}
