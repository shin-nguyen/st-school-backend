package com.stschools.export_file.quizzes;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.stschools.entity.Blog;
import com.stschools.entity.Quiz;
import com.stschools.util.AbstractExporter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class QuizPdfExporter extends AbstractExporter {

	public void export(List<Quiz> quizzes, HttpServletResponse response) throws IOException {

		super.setResponseHeader(response, "application/pdf", ".pdf", "blogs_");

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph paragraph = new Paragraph("List of Quiz", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph);

		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);
//		table.setWidths(new float[] {1.2f, 3.5f, 3.0f, 3.0f, 3.0f, 1.7f});

		writeTableHeader(table);
		writeTableData(table, quizzes);

		document.add(table);

		document.close();
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Quiz ID", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Code", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Duration", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Course", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Created Time", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Update Time", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Status", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Deleted", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table, List<Quiz> quizzes) {
		for (Quiz quiz : quizzes) {
			table.addCell(String.valueOf(quiz.getId()));
			table.addCell(quiz.getName());
			table.addCell(quiz.getCode());
			table.addCell(quiz.getDuration());
			table.addCell(quiz.getCourse().getName());
			table.addCell(quiz.getCreatedTime());
			table.addCell(quiz.getUpdateTime());
			table.addCell(quiz.getStatus() ? "T" : "F");
			table.addCell(quiz.getIsDeleted() ? "T" : "F");
		}
	}
}
