package cn.wlzy.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.wlzy.domain.ExamOption;
public class ReadExcel {
	public static List<ExamOption> readExcel(File file,String type) throws IOException, NullPointerException {
		if("xlsx".equals(type)){
			return readXlsx(file);
		} else if ("xls".equals(type)) {
			return readXls(file);
		}
		return null;
	}

	/**
	 * Read the Excel 2010
	 * @param path the path of the excel file
	 * @return
	 * @throws IOException
	 */
	public static List<ExamOption> readXlsx(File file) throws NullPointerException,IOException{
		InputStream is = new FileInputStream(file);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		ExamOption examOption = null;
		List<ExamOption> list = new ArrayList<ExamOption>();
		// 只需读取第一张表
		XSSFSheet xssfSheet=xssfWorkbook.getSheetAt(0);
		if(xssfSheet!=null&&xssfSheet.getLastRowNum()!=0){
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow != null) {
					examOption=new ExamOption();
					XSSFCell num = xssfRow.getCell(0);
					XSSFCell content = xssfRow.getCell(1);
					XSSFCell A = xssfRow.getCell(2);
					XSSFCell B = xssfRow.getCell(3);
					XSSFCell C = xssfRow.getCell(4);
					XSSFCell D = xssfRow.getCell(5);
					XSSFCell answer = xssfRow.getCell(6);
					if(num==null||content==null||A==null||B==null||C==null||D==null||answer==null){
						throw new NullPointerException("空指针异常");
					}
					//设值
					System.out.println(getValue(num));
					examOption.setNum(getValue(num));
					examOption.setContent(getValue(content));
					examOption.setA(getValue(A));
					examOption.setB(getValue(B));
					examOption.setC(getValue(C));
					examOption.setD(getValue(D));
					examOption.setAnswer(getValue(answer));
					list.add(examOption);

				}else{
					throw new NullPointerException("空指针异常");
				}
			}
		}else{
			throw new NullPointerException("空指针异常");
		}
		return list;
	}

	@SuppressWarnings("static-access")
	private static String getValue(XSSFCell xssfRow) {
		if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
			return String.valueOf(xssfRow.getBooleanCellValue());
		} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
			return String.valueOf(xssfRow.getNumericCellValue());
		} else {
			return String.valueOf(xssfRow.getStringCellValue());
		}
	}


	/**
	 * Read the Excel 2003-2007
	 * @param path the path of the Excel
	 * @return
	 * @throws IOException
	 */
	public static List<ExamOption> readXls(File file) throws IOException,NullPointerException{
		InputStream is = new FileInputStream(file);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		ExamOption examOption = null;
		List<ExamOption> list = new ArrayList<ExamOption>();
		// 只需读取第一张表
		HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
		if (hssfSheet != null&&hssfSheet.getLastRowNum()!=0) {
			// Read the Row
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					examOption = new ExamOption();
					HSSFCell num = hssfRow.getCell(0);
					HSSFCell content = hssfRow.getCell(1);
					HSSFCell A = hssfRow.getCell(2);
					HSSFCell B = hssfRow.getCell(3);
					HSSFCell C = hssfRow.getCell(4);
					HSSFCell D = hssfRow.getCell(5);
					HSSFCell answer = hssfRow.getCell(6);
					if(num==null||content==null||A==null||B==null||C==null||D==null||answer==null){
						throw new NullPointerException("空指针异常");
					}
					//设值
					System.out.println(getValue(num));
					examOption.setNum(getValue(num));
					examOption.setContent(getValue(content));
					examOption.setA(getValue(A));
					examOption.setB(getValue(B));
					examOption.setC(getValue(C));
					examOption.setD(getValue(D));
					examOption.setAnswer(getValue(answer));
					list.add(examOption);
				}else{
					throw new NullPointerException("空指针异常");
				}
			}
		}else{
			throw new NullPointerException("空指针异常");
		}
		return list;
	}


	@SuppressWarnings("static-access")
	private static String getValue(HSSFCell hssfCell) {
		if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(hssfCell.getBooleanCellValue());
		} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
			return String.valueOf(hssfCell.getNumericCellValue());
		} else {
			return String.valueOf(hssfCell.getStringCellValue());
		}
	}
}