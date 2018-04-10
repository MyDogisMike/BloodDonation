package com.donation.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.donation.constant.ActivityStatusEnum;
import com.donation.constant.ContentTypeEnum;
import com.donation.constant.LoginVariable;
import com.donation.entity.Activity;
import com.donation.entity.ArticleContent;
import com.donation.entity.Users;
import com.donation.query.ArticlePageQuery;
import com.donation.query.LayUIQuery;
import com.donation.service.ActivityService;
import com.donation.service.ArticleContentService;
import com.donation.vo.ActivityVO;
import com.donation.vo.LayUIVO;

@Controller
@RequestMapping("/activity")
public class ActivityController {
	@Autowired
	private ActivityService activityService;
	@Autowired
	private ArticleContentService articleContentService;
	
	@RequestMapping("/list")
	@ResponseBody
	public LayUIVO getArticleList(LayUIQuery query){
		return activityService.getActivityList(query);
	}
	
	@RequestMapping("/editActivity")
	public String editActivity(Long id, HttpServletRequest request){
		request.setAttribute("editActivityId", id);
		return "admin/edit_activity";
	}
	
	@RequestMapping("/getOneActivity")
	@ResponseBody
	public ActivityVO getOneActivity(Long id){
		return activityService.getActivityVOById(id);
	}
	
	@RequestMapping("/editOneActivity")
	@ResponseBody
	public String editOneActivity(Activity activity, String start, String end){
		SimpleDateFormat fmt=new SimpleDateFormat("yyyy-MM-dd");
		try {
			activity.setEndDate(fmt.parse(end));
			activity.setStartDate(fmt.parse(start));
		} catch (ParseException e) {
			return "修改失败，请稍后再试";
		}
		int flag = activityService.updateActivityById(activity);
		if(flag > 0){
			return "修改成功";
		}
		return "修改失败，请稍后再试";
	}
	
	/**
	 * 展示文章
	 * @param request
	 * @return
	 */
	@RequestMapping("/showActivity")
	public String showAcitity(HttpServletRequest request, Long id){
		request.setAttribute("activityId", id);
		//每点击一次文章的连接就表示该文章的浏览量加一
		Activity acitity = activityService.getActivityById(id);
		acitity.setPageView(acitity.getPageView()+1);
		activityService.updateActivityById(acitity);
		return "show_activity";
	}
	
	/**
	 * 获取活动的所有内容以便在详情页展示
	 * @param id
	 * @return
	 */
	@PostMapping("/getActivity")
	@ResponseBody
	public Map<String, Object>getActivity(Long id){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("activity", activityService.getActivityVOById(id));	//文章信息
		result.put("content", articleContentService.getContentByActivityId(id));	//文章内容
		return result;
	}
	
	/**
	 * 分页获取活动（突然想起，业务逻辑实现还是写在service层比较好- -）
	 * @param query
	 * @return
	 */
	@PostMapping("/allActivity")
	@ResponseBody
	public Map<String, Object> getAllActivitys(ArticlePageQuery query){
		
		return activityService.getAllActivitys(query);
	}
	
	/**
	 * word类型的文章上传
	 * @param file
	 * @param request
	 * @return
	 */
	@PostMapping("/upload")
	@ResponseBody
	public Map<String, Object> articleUpload(MultipartFile file, HttpServletRequest request){
		String savePath = "D://articlePicture//";
		Map result = new HashMap<String, Object>();
		String wordType = "doc,docx";
		//允许上传的文件最大大小(100M,单位为byte)
	    int maxSize = 1024*1024*100;
	    if(file.getSize() > maxSize){
	    	result.put("data", "上传的Word文档要小于10M");
	    	return result;
	    }
		String fileName = file.getOriginalFilename();
		//获取文件名称
        String name = fileName.substring(fileName.lastIndexOf("\\") + 1,
            fileName.lastIndexOf("."));
        //获取文件后缀名
        String extName = fileName.substring(fileName.indexOf(".") + 1).toLowerCase().trim();
        if (!wordType.contains(extName)){
        	result.put("data", "只能上传Word文档");
	    	return result;
        }
        Activity activity = new Activity();
        Users loginUser = (Users) request.getSession().getAttribute(LoginVariable.LOGIN_USER);
        activity.setPublisherId(loginUser.getId());
        activity.setPublishingLocation(loginUser.getAddress().split("市")[0]+"市");
        activity.setReleaseDate(new Date());
        activity.setPersonCount(0L);
        activity.setStatus(ActivityStatusEnum.PREPARE.getValue());
        activityService.addActivity(activity);
		try {
			InputStream inputStream = file.getInputStream();
			XWPFDocument xDocument = new XWPFDocument(inputStream);
			List<XWPFParagraph> paragraphs = xDocument.getParagraphs();  
            List<XWPFPictureData> pictures = xDocument.getAllPictures();
            Map<String, String> map = new HashMap<String, String>();
            for(XWPFPictureData picture : pictures){
                
                String id = picture.getParent().getRelationId(picture);  
                File folder = new File(savePath);  
                if (!folder.exists()) {  
                    folder.mkdirs();  
                }  
                String rawName = picture.getFileName();  
                String fileExt = rawName.substring(rawName.lastIndexOf("."));  
                String newName = System.currentTimeMillis() + UUID.randomUUID().toString() + fileExt;  
                File saveFile = new File(savePath + File.separator + newName);  
                @SuppressWarnings("resource")  
                FileOutputStream fos = new FileOutputStream(saveFile);   
                fos.write(picture.getData());  
                //System.out.println(id);  
                //System.out.println(saveFile.getAbsolutePath());  
                //map.put(id, saveFile.getAbsolutePath());  
                map.put(id, newName);  
            }
            //String text = "";  
            int sequence = 0;	//表示第几行，0是标题行
            for(XWPFParagraph paragraph : paragraphs){  //处理段落
            	
                //System.out.println(paragraph.getParagraphText()); 
            	StringBuffer characters = new StringBuffer();
            	ArticleContent content = new ArticleContent();
            	content.setArticleId(activity.getId());
            	content.setBelong(1);
                List<XWPFRun> runs = paragraph.getRuns();  
                for(XWPFRun run : runs){  //遍历每一段的行
                    /*System.out.println(run.getCTR().xmlText());*/  
                    if(run.getCTR().xmlText().indexOf("<w:drawing>")!=-1){  //处理图片
                        String runXmlText = run.getCTR().xmlText();  
                        int rIdIndex = runXmlText.indexOf("r:embed");  
                        int rIdEndIndex = runXmlText.indexOf("/>", rIdIndex);  
                        String rIdText = runXmlText.substring(rIdIndex, rIdEndIndex);  
                        //System.out.println(rIdText.split("\"")[1].substring("rId".length()));  
                        String id = rIdText.split("\"")[1];  
                       // System.out.println(map.get(id));  
                        //text = text +"<img src = '"+map.get(id)+"'/>";  
                        content.setType(ContentTypeEnum.picture.getValue());
                        content.setContent(map.get(id));
                        content.setSequence(sequence);
                        articleContentService.addContent(content);
                        characters.setLength(0);
                    }else{  //处理文字
                        //text = text + run;
                    	characters.append(run);
                    }  
                }  
                if(characters.length() > 0 && sequence != 0){
                	content.setType(ContentTypeEnum.character.getValue());
                    content.setContent(characters.toString());
                    content.setSequence(sequence);
                    articleContentService.addContent(content);
                }else if(sequence == 0){
                	activity.setTitle(characters.toString());
                	activityService.updateActivityById(activity);
                }
                sequence++;
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("data", "活动上传失败，请稍后再试");
	    	return result;
		}
		
		result.put("data", "活动上传成功");
    	return result;
	}

}
