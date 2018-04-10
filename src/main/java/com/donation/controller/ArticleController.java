package com.donation.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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

import com.donation.constant.ContentTypeEnum;
import com.donation.constant.LoginVariable;
import com.donation.entity.ArticleContent;
import com.donation.entity.Articles;
import com.donation.entity.Users;
import com.donation.query.ArticlePageQuery;
import com.donation.query.ArticleQuery;
import com.donation.query.LayUIQuery;
import com.donation.service.ArticleCommentService;
import com.donation.service.ArticleContentService;
import com.donation.service.ArticlesService;
import com.donation.vo.ArticleCommentVO;
import com.donation.vo.ArticleVO;
import com.donation.vo.LayUIVO;

@Controller
@RequestMapping("/article")
public class ArticleController {
	@Autowired
	private ArticlesService articlesService;
	@Autowired
	private ArticleContentService articleContentService;
	@Autowired
	private ArticleCommentService articleCommentService;
	
	@RequestMapping("/list")
	@ResponseBody
	public LayUIVO getArticleList(LayUIQuery query){
		return articlesService.getArticleList(query);
	}
	
	@RequestMapping("/editArticle")
	public String editArticle(Long id, HttpServletRequest request){
		request.setAttribute("editArticleId", id);
		return "admin/edit_article";
	}
	
	@RequestMapping("/getOneArticle")
	@ResponseBody
	public ArticleVO getOneArticle(Long id){
		return articlesService.getArticleVOById(id);
	}
	
	@RequestMapping("/editOneArticle")
	@ResponseBody
	public String editOneArticle(Articles article){
		int flag = articlesService.updateArticleById(article);
		if(flag > 0){
			return "修改成功";
		}
		return "修改失败，请稍后再试";
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
        Articles article = new Articles();
        Users loginUser = (Users) request.getSession().getAttribute(LoginVariable.LOGIN_USER);
        article.setPublisherId(loginUser.getId());
        article.setPublishingLocation(loginUser.getAddress().split("市")[0]+"市");
        article.setReleaseDate(new Date());
        articlesService.addArticle(article);
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
            	content.setArticleId(article.getId());
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
                	article.setTitle(characters.toString());
                	articlesService.updateArticleById(article);
                }
                sequence++;
            }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("data", "文章上传失败，请稍后再试");
	    	return result;
		}
		
		result.put("data", "文章上传成功");
    	return result;
	}
	/**
	 * 获取最新或最热的用户区域文章列表
	 * 显示最新的limit条文章
	 * 本来方法可以放回map对象增加提示，可是我却没有这么做，想看看会有什么大的弊端
	 */
	@PostMapping("/showNewArticles")
	@ResponseBody
	public List<Articles> showNewArticles(HttpServletRequest request, String view, Integer limit){
		Users loginUser = (Users) request.getSession().getAttribute(LoginVariable.LOGIN_USER);
		ArticleQuery query = new ArticleQuery();
		if(loginUser != null && loginUser.getAddress() != null && !"".equals(loginUser.getAddress())){
			query.setArea(loginUser.getAddress().split("市")[0]+"市");
		}
		query.setView(view);
		query.setSkipRows(0);
		query.setLimit(limit);
		return articlesService.getArticleList(query);
	}
	/**
	 * 展示文章
	 * @param request
	 * @return
	 */
	@RequestMapping("/showArticle")
	public String showArticle(HttpServletRequest request, Long id){
		request.setAttribute("articleId", id);
		//每点击一次文章的连接就表示该文章的浏览量加一
		Articles article = articlesService.getArticleById(id);
		article.setPageView(article.getPageView()+1);
		articlesService.updateArticleById(article);
		return "show_article";
	}
	/**
	 * 获取文章的所有内容以便在详情页展示
	 * @param id
	 * @return
	 */
	@PostMapping("/getArticle")
	@ResponseBody
	public Map<String, Object>getArticle(Long id){
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("article", articlesService.getArticleVOById(id));	//文章信息
		result.put("content", articleContentService.getContentByArticleId(id));	//文章内容
		return result;
	}
	/**
	 * 登录后可以对文章添加评论
	 * @param comment
	 * @param request
	 * @return
	 */
	@PostMapping("/addComment")
	@ResponseBody
	public ArticleCommentVO addComment(ArticleCommentVO comment, HttpServletRequest request){
		Users loginUser = (Users) request.getSession().getAttribute(LoginVariable.LOGIN_USER);
		comment.setUserId(loginUser.getId());
		comment.setTime(new Date());
		articleCommentService.addComment(comment);
		comment.setUserName(loginUser.getNickname());
		return comment;
	}
	
	@PostMapping("/showComment")
	@ResponseBody
	public List<ArticleCommentVO> showComment(Long articleId){
		return articleCommentService.getCommentListByArticleId(articleId);
	}
	/**
	 * 分页获取文章（突然想起，业务逻辑实现还是写在service层比较好- -）
	 * @param query
	 * @return
	 */
	@PostMapping("/allArticles")
	@ResponseBody
	public Map<String, Object> getAllArticles(ArticlePageQuery query){
		
		return articlesService.getAllArticles(query);
	}

}
