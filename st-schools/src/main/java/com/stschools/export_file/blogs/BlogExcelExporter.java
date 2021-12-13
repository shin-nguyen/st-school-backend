package com.stschools.export_file.blogs;


import com.stschools.entity.Blog;
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

public class BlogExcelExporter extends AbstractExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public BlogExcelExporter() {
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Blogs");
		XSSFRow row = sheet.createRow(0);

		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		cellStyle.setFont(font);

		createCell(row, 0, "Blog Id", cellStyle);
		createCell(row, 1, "Title", cellStyle);
		createCell(row, 2, "Content", cellStyle);
		createCell(row, 3, "View", cellStyle);
		createCell(row, 4, "Image", cellStyle);
		createCell(row, 5, "Created Time", cellStyle);
		createCell(row, 6, "Update Time", cellStyle);
		createCell(row, 7, "Status", cellStyle);
		createCell(row, 8, "Deleted", cellStyle);
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

	public void export(List<Blog> blogs, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "application/octet-stream", ".xlsx", "blogs_");

		writeHeaderLine();
		writeDataLines(blogs);

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	private void writeDataLines(List<Blog> blogs) {
		int rowIndex = 1;

		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		cellStyle.setFont(font);

		for (Blog blog : blogs) {
			XSSFRow row = sheet.createRow(rowIndex++);
			int columnIndex = 0;

			createCell(row, columnIndex++, blog.getId(), cellStyle);
			createCell(row, columnIndex++, blog.getTitle(), cellStyle);
			createCell(row, columnIndex++, blog.getContent(), cellStyle);
			createCell(row, columnIndex++, blog.getView(), cellStyle);
			createCell(row, columnIndex++, blog.getImage(), cellStyle);
			createCell(row, columnIndex++, blog.getCreatedTime(), cellStyle);
			createCell(row, columnIndex++, blog.getUpdateTime(), cellStyle);
			createCell(row, columnIndex++, blog.getStatus(), cellStyle);
			createCell(row, columnIndex++, blog.getIsDeleted(), cellStyle);
		}
	}
}
