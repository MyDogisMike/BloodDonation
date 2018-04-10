package com.donation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFPictureData;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

public class DocTest {
	public static void doxc() throws InvalidFormatException {  
        String importPath = "I://123.docx";  
        String absolutePath = "D://articlePicture//";  
        try {  
            FileInputStream inputStream = new FileInputStream(importPath);  
            XWPFDocument xDocument = new XWPFDocument(inputStream);  
            List<XWPFParagraph> paragraphs = xDocument.getParagraphs();  
            List<XWPFPictureData> pictures = xDocument.getAllPictures();  
            Map<String, String> map = new HashMap<String, String>();  
            for(XWPFPictureData picture : pictures){
                              
                String id = picture.getParent().getRelationId(picture);  
                File folder = new File(absolutePath);  
                if (!folder.exists()) {  
                    folder.mkdirs();  
                }  
                String rawName = picture.getFileName();  
                String fileExt = rawName.substring(rawName.lastIndexOf("."));  
                String newName = System.currentTimeMillis() + UUID.randomUUID().toString() + fileExt;  
                File saveFile = new File(absolutePath + File.separator + newName);  
                @SuppressWarnings("resource")  
                FileOutputStream fos = new FileOutputStream(saveFile);   
                fos.write(picture.getData());  
                System.out.println(id);  
                System.out.println(saveFile.getAbsolutePath());  
                map.put(id, saveFile.getAbsolutePath());  
            }  
            String text = "";  
            for(XWPFParagraph paragraph : paragraphs){  
                //System.out.println(paragraph.getParagraphText());  
                List<XWPFRun> runs = paragraph.getRuns();  
                for(XWPFRun run : runs){  
                    /*System.out.println(run.getCTR().xmlText());*/  
                    if(run.getCTR().xmlText().indexOf("<w:drawing>")!=-1){  
                        String runXmlText = run.getCTR().xmlText();  
                        int rIdIndex = runXmlText.indexOf("r:embed");  
                        int rIdEndIndex = runXmlText.indexOf("/>", rIdIndex);  
                        String rIdText = runXmlText.substring(rIdIndex, rIdEndIndex);  
                        System.out.println(rIdText.split("\"")[1].substring("rId".length()));  
                        String id = rIdText.split("\"")[1];  
                        System.out.println(map.get(id));  
                        text = text +"<img src = '"+map.get(id)+"'/>";  
                    }else{  
                        text = text + run;  
                    }  
                }  
            }  
            System.out.println(text);  
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
    }  
	public static void main(String[] args) {
		try {
			doxc();
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
