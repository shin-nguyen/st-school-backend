package com.stschools.export_file.courses;


import com.stschools.entity.Course;
import com.stschools.entity.User;
import com.stschools.util.AbstractExporter;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CourseExcelExporter extends AbstractExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public CourseExcelExporter() {
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Courses");
		XSSFRow row = sheet.createRow(0);

		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		cellStyle.setFont(font);

		createCell(row, 0, "Course Id", cellStyle);
		createCell(row, 1, "Name", cellStyle);
		createCell(row, 2, "Description", cellStyle);
		createCell(row, 3, "Language", cellStyle);
		createCell(row, 4, "Price", cellStyle);
		createCell(row, 5, "Image", cellStyle);
	}

	private void createCell(XSSFRow row, int columnIndex, Object value, CellStyle style) {
		XSSFCell cell = row.createCell(columnIndex);
		sheet.autoSizeColumn(columnIndex);

		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Long) {
			cell.setCellValue((Long) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else if (value instanceof Date) {
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(value);

			cell.setCellValue(strDate);
		} else {
			cell.setCellValue((String) value);
		}

		cell.setCellStyle(style);
	}

	public void export(List<Course> courses, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "application/octet-stream", ".xlsx", "course_");

		writeHeaderLine();
		writeDataLines(courses);

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	private void writeDataLines(List<Course> courses) {
		int rowIndex = 1;

		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		cellStyle.setFont(font);

		for (Course course : courses) {
			XSSFRow row = sheet.createRow(rowIndex++);
			int columnIndex = 0;

			createCell(row, columnIndex++, course.getId(), cellStyle);
			createCell(row, columnIndex++, course.getName(), cellStyle);
			createCell(row, columnIndex++, course.getDescription(), cellStyle);
			createCell(row, columnIndex++, course.getLanguage(), cellStyle);
			createCell(row, columnIndex++, course.getPrice(), cellStyle);
			createCell(row, columnIndex++, course.getImage(), cellStyle);
		}
	}
}
