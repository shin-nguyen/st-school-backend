package com.stschools.export_file.quizzes;


import com.stschools.entity.Blog;
import com.stschools.entity.Quiz;
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

public class QuizExcelExporter extends AbstractExporter {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;

	public QuizExcelExporter() {
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Quizes");
		XSSFRow row = sheet.createRow(0);

		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		cellStyle.setFont(font);

		createCell(row, 0, "Quiz Id", cellStyle);
		createCell(row, 1, "Name", cellStyle);
		createCell(row, 2, "Code", cellStyle);
		createCell(row, 3, "Course", cellStyle);
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
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(value);

			cell.setCellValue(strDate);
		} else {
			cell.setCellValue((String) value);
		}

		cell.setCellStyle(style);
	}

	public void export(List<Quiz> quizzes, HttpServletResponse response) throws IOException {
		super.setResponseHeader(response, "application/octet-stream", ".xlsx", "quizes_");

		writeHeaderLine();
		writeDataLines(quizzes);

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();

	}

	private void writeDataLines(List<Quiz> quizzes) {
		int rowIndex = 1;

		XSSFCellStyle cellStyle = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		cellStyle.setFont(font);

		for (Quiz quiz : quizzes) {
			XSSFRow row = sheet.createRow(rowIndex++);
			int columnIndex = 0;

			createCell(row, columnIndex++, quiz.getId(), cellStyle);
			createCell(row, columnIndex++, quiz.getName(), cellStyle);
			createCell(row, columnIndex++, quiz.getCode(), cellStyle);
			createCell(row, columnIndex++, quiz.getDuration(), cellStyle);
			createCell(row, columnIndex++, quiz.getCourse().getName(), cellStyle);
			createCell(row, columnIndex++, quiz.getCreatedTime(), cellStyle);
			createCell(row, columnIndex++, quiz.getUpdateTime(), cellStyle);
			createCell(row, columnIndex++, quiz.getStatus(), cellStyle);
			createCell(row, columnIndex++, quiz.getIsDeleted(), cellStyle);
		}
	}
}
