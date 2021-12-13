package com.stschools.export_file.blogs;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.stschools.entity.Blog;
import com.stschools.entity.User;
import com.stschools.util.AbstractExporter;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BlogPdfExporter extends AbstractExporter {

	public void export(List<Blog> blogs, HttpServletResponse response) throws IOException {

		super.setResponseHeader(response, "application/pdf", ".pdf", "blogs_");

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph paragraph = new Paragraph("List of Blog", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph);

		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);
//		table.setWidths(new float[] {1.2f, 3.5f, 3.0f, 3.0f, 3.0f, 1.7f});

		writeTableHeader(table);
		writeTableData(table, blogs);

		document.add(table);

		document.close();
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Blog ID", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Title", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Content", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("View", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Image", font));
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

	private void writeTableData(PdfPTable table, List<Blog> blogs) {
		for (Blog blog : blogs) {
			table.addCell(String.valueOf(blog.getId()));
			table.addCell(blog.getTitle());
			table.addCell(blog.getContent());
			table.addCell(String.valueOf(blog.getView()));
			table.addCell(blog.getImage());
			table.addCell(blog.getCreatedTime());
			table.addCell(blog.getUpdateTime());
			table.addCell(blog.getStatus() ? "T" : "F");
			table.addCell(blog.getIsDeleted() ? "T" : "F");
		}
	}
}
