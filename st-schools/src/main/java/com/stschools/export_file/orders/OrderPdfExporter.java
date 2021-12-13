package com.stschools.export_file.orders;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.stschools.entity.Order;
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

public class OrderPdfExporter extends AbstractExporter {

	public void export(List<Order> orders, HttpServletResponse response) throws IOException {

		super.setResponseHeader(response, "application/pdf", ".pdf", "users_");

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph paragraph = new Paragraph("List of Order", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph);

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);
//		table.setWidths(new float[] {1.2f, 3.5f, 3.0f, 3.0f, 3.0f, 1.7f});

		writeTableHeader(table);
		writeTableData(table, orders);

		document.add(table);

		document.close();
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Order ID", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("User Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Course Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Created Time", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Update Time", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table, List<Order> orders) {
		for (Order order : orders) {
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			table.addCell(String.valueOf(order.getId()));
			table.addCell(order.getUserName());
			table.addCell(order.getCourseName());
			table.addCell(order.getCreatedTime());
			table.addCell(order.getUpdateTime());
		}
	}
}
