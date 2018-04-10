package com.donation.utils;



import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.ClientAnchor.AnchorType;
import org.apache.poi.ss.util.CellRangeAddress;


/**
 *
 * 
 * 
 * @param <T>
 *            应用泛型，代表任意一个符合javabean风格的类
 *            注意这里为了简单起见，boolean型的属性xxx的get器方式为getXxx()，而不是isXxx()
 *            byte[]表示jpg格式的图片数据
 */
public class ExcelExport<T> {

    public void exportExcel(Collection<T> dataset, OutputStream out) {
        exportExcel("测试poi导出excel文档", null, dataset, out, "yyyy-MM-dd", null);
    }

    public void exportExcel(String[] headers, Collection<T> dataset, OutputStream out) {
        exportExcel("测试poi导出excel文档", headers, dataset, out, "yyyy-MM-dd", null);
    }

    public void exportExcel(String[] headers, Collection<T> dataset, OutputStream out, String pattern) {
        exportExcel("测试poi导出excel文档", headers, dataset, out, pattern, null);
    }

    public void exportExcel(String[] headers, Collection<T> dataset, OutputStream out, String pattern,
            List<String> usefulFields) {
        exportExcel("测试poi导出excel文档", headers, dataset, out, pattern, usefulFields);
    }

    /**
     * 这是一个通用的方法，利用了java的反射机制，可以将放置在java集合中并且符合一定条件的数据以excel的形式输出到指定IO设备上
     *
     * @param title
     *            表格标题名
     * @param headers
     *            表格属性列名数组
     * @param dataset
     *            需要显示的数据集合，集合中一定要放置符合javabean风格的类的对象。
     *            此方法支持的javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
     * @param out
     *            与输出设备关联的流对象，可以将excel文档导出到本地文件中或者网络中
     * @param pattern
     *            如果有时间数据，设定输出格式。默认为"yyyy-MM-dd"
     */
    public void exportExcel(String title, String[] headers, Collection<T> dataset, OutputStream out, String pattern,
            List<String> usefulFields) {
        // 声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth(20);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.WHITE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setWrapText(true);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.BLACK.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style2.setWrapText(true);	//设置换行
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setColor(HSSFColor.BLACK.index);
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前样式
        style2.setFont(font2);

        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 定义注释的大小和位置，详见文档
        // HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0,
        // 0, 0, 0, (short) 4, 2, (short) 6, 5));
        // 设置注释内容
        // comment.setString(new HSSFRichTextString("可以在poi中添加注释！"));
        // 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容。
        // comment.setAuthor("leno");

        // 产生标题（第一行）
        HSSFRow titleRow = sheet.createRow(0);
        titleRow.setHeight((short)450);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, usefulFields.size() - 1));
        HSSFCell titleCell = titleRow.createCell(0);
        titleCell.setCellStyle(style);
        
        if(title == null || title.equals("")){
        	HSSFRichTextString titleText = new HSSFRichTextString("报名表");
            titleCell.setCellValue(titleText);
        }else{
        	HSSFRichTextString titleText = new HSSFRichTextString(title);
            titleCell.setCellValue(titleText);
        }
        
        
        // 产生表格标题行（第2行）
        HSSFRow row = sheet.createRow(1);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 遍历集合数据，产生数据行
        Iterator<T> it = dataset.iterator();
        int index = 1; //第3行开始导出数据
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            T t = it.next();
            // 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
            Class<?> clazz = t.getClass();
            List<Field> fieldList = new ArrayList<>();
            for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    fieldList.add(field);
                }
            }
            int usefulIndex = 0;
            for (int i = 0; i < fieldList.size(); i++) {
                Field field = fieldList.get(i);
                String fieldName = field.getName();
                String getMethodName = null;
                if (usefulFields != null && !usefulFields.contains(fieldName)) {
                    continue;
                } else {
                    getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    for (String usefulField : usefulFields) {
                        if (usefulField.equals(fieldName)) {
                            break;
                        }
                        usefulIndex++;
                    }
                }
                HSSFCell cell = row.createCell(usefulIndex);
                usefulIndex = 0;
                cell.setCellStyle(style2);
                try {
                    Class<?> tCls = t.getClass();
                    Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
                    Object value = getMethod.invoke(t, new Object[] {});
                    // 判断值的类型后进行强制类型转换
                    if (value != null) {
                        String textValue = null;
                        if (value instanceof Boolean) {
                            Boolean bValue = (Boolean) value;
                            textValue = "男";
                            if (!bValue) {
                                textValue = "女";
                            }
                        } else if (value instanceof Date) {
                            Date date = (Date) value;
                            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                            textValue = sdf.format(date);
                        } else if (value instanceof byte[]) {
                            // 有图片时，设置行高为60px;
                            row.setHeightInPoints(60);
                            // 设置图片所在列宽度为80px,注意这里单位的一个换算
                            sheet.setColumnWidth(i, (short) (35.7 * 80));
                            // sheet.autoSizeColumn(i);
                            byte[] bsValue = (byte[]) value;
                            HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6,
                                    index);
                            anchor.setAnchorType(AnchorType.MOVE_DONT_RESIZE);
                            patriarch.createPicture(anchor,
                                    workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
                        } else {
                            // 其他数据类型都当作字符串简单处理
                            textValue = value.toString();
                        }
                        // 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
                        if (textValue != null) {
                            Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                            Matcher matcher = p.matcher(textValue);
                            if (matcher.matches()) {
                                // 是数字当作double处理
                                cell.setCellValue(Double.parseDouble(textValue));
                            } else {
                                HSSFRichTextString richString = new HSSFRichTextString(textValue);
                                HSSFFont font3 = workbook.createFont();
                                font3.setColor(HSSFColor.BLACK.index);
                                richString.applyFont(font3);
                                cell.setCellValue(richString);
                            }
                        }
                    }
                } catch (SecurityException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } finally {
                    // 清理资源
                }
            }
//            usefulIndex = 0;
//            for(String str : usefulFields){
//            	if("".equals(str)){
//            		break;
//            	}
//                usefulIndex++;
//            }
//            
//            HSSFCell cell = row.createCell(usefulIndex);
//            cell.setCellStyle(style2);
        }
        try {
            workbook.write(out);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   
}
