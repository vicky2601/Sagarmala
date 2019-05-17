package org.sagarmala.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.sagarmala.bean.RoleAuthorizationBean;
import org.sagarmala.bean.UserBean;
import org.sagarmala.model.RoleAuthorization;
import org.sagarmala.model.UserDetail;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CommonUtility {

	public static DateFormat df = new SimpleDateFormat("dd-MMM-YYYY");

	@SuppressWarnings("unused")
	public static Date getCurrentDateTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss:SSS");
		LocalDateTime currentDateTime = LocalDateTime.now();
		Instant instant = currentDateTime.toInstant(ZoneOffset.UTC);
		return Date.from(instant);
	}

	public static UserDetail convertUserBean(UserBean userBean) {

		UserDetail userDetail = new UserDetail();

		if (userBean.getUserName() != null && !userBean.getUserName().isEmpty()) {
			userDetail.setUserName(userBean.getUserName());
		}

		if (userBean.getToken() != null && !userBean.getToken().isEmpty()) {
			userDetail.setResetToken(userBean.getToken());
		}
		return userDetail;
	}

	public static List<RoleAuthorization> convertRoleAuthorizationBean(
			List<RoleAuthorizationBean> roleAuthorizationBean) {
		List<RoleAuthorization> authorization = new ArrayList<RoleAuthorization>();
		for (int i = 0; i < roleAuthorizationBean.size(); i++) {
			RoleAuthorization authorization2 = new RoleAuthorization();
			authorization2.setId(roleAuthorizationBean.get(i).getId());
			authorization2.setOrgRoleId(roleAuthorizationBean.get(i).getRoleID());
			authorization2.setMenuID(roleAuthorizationBean.get(i).getMenuID());
			authorization2.setAddRight(roleAuthorizationBean.get(i).getAddRight());
			authorization2.setEditRight(roleAuthorizationBean.get(i).getEditRight());
			authorization2.setViewRight(roleAuthorizationBean.get(i).getViewRight());
			authorization2.setQuery(roleAuthorizationBean.get(i).getQuery());
			authorization2.setApproveRight(roleAuthorizationBean.get(i).getApproveRight());
			authorization2.setDeleteRight(roleAuthorizationBean.get(i).getDeleteRight());
			authorization.add(authorization2);
		}

		return authorization;
	}

	@SuppressWarnings("resource")
	public static String createExcelFile(Map<Integer, Object[]> data, String location) {
		String fileName = RandomStringUtils.randomAlphanumeric(5) + ".xlsx";
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Data");
		// Create a CellStyle with the font
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
		Set<Integer> keyset = data.keySet();
		int rownum = 0;
		for (Integer key : keyset) {
			Row row = sheet.createRow(rownum++);
			Object[] objArr = data.get(key);
			int cellnum = 0;
			for (Object obj : objArr) {
				Cell cell = row.createCell(cellnum++);
				cell.setCellStyle(headerCellStyle);
				if (obj instanceof String)
					cell.setCellValue((String) obj);
				else if (obj instanceof Integer)
					cell.setCellValue((Integer) obj);
				else if (obj instanceof Date)
					cell.setCellValue(df.format((Date) obj));
			}
		}
		try {
			FileOutputStream out = new FileOutputStream(new File(location + fileName));
			workbook.write(out);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public static String createPdfFile(Map<Integer, Object[]> data, String location) {
		String fileName = "";
		Document document = new Document();
		try {
			Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
			Font small = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL);
			fileName = RandomStringUtils.randomAlphanumeric(5) + ".pdf";
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(location + fileName));
			document.open();
			Set<Integer> keyset = data.keySet();
			Object[] objArr = data.get(0);
			PdfPTable table = new PdfPTable(objArr.length); // 3 columns.
			table.setWidthPercentage(100); // Width 100%
			table.setSpacingBefore(10f); // Space before table
			table.setSpacingAfter(10f); // Space after table
			PdfPCell cell1 = null;
			boolean flag = true;
			for (Integer key : keyset) {
				objArr = data.get(key);
				for (Object obj : objArr) {
					if (flag) {
						if (obj instanceof String)
							cell1 = new PdfPCell(new Paragraph((String) obj, subFont));
						else if (obj instanceof Integer) {
							cell1 = new PdfPCell(new Paragraph("" + (Integer) obj, subFont));
							System.out.println("Else if " + obj);
						} else if (obj instanceof Date)
							cell1 = new PdfPCell(new Paragraph(df.format((Date) obj), subFont));
					} else {
						if (obj instanceof String)
							cell1 = new PdfPCell(new Paragraph((String) obj, small));
						else if (obj instanceof Integer) {
							cell1 = new PdfPCell(new Paragraph("" + (Integer) obj, small));
							System.out.println("Else if " + obj);
						} else if (obj instanceof Date)
							cell1 = new PdfPCell(new Paragraph(df.format((Date) obj), small));
					}
					cell1.setBorderColor(BaseColor.BLACK);
					cell1.setPaddingLeft(10);
					cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
					cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
					table.addCell(cell1);
				}
				flag = false;
			}
			document.add(table);
			document.close();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

}
