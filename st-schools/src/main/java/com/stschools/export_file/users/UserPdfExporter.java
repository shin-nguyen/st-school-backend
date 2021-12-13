package com.stschools.export_file.users;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
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

public class UserPdfExporter extends AbstractExporter {

	public void export(List<User> listUsers, HttpServletResponse response) throws IOException {

		super.setResponseHeader(response, "application/pdf", ".pdf", "users_");

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph paragraph = new Paragraph("List of User", font);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(paragraph);

		PdfPTable table = new PdfPTable(12);
		table.setWidthPercentage(100f);
		table.setSpacingBefore(10);
//		table.setWidths(new float[] {1.2f, 3.5f, 3.0f, 3.0f, 3.0f, 1.7f});

		writeTableHeader(table);
		writeTableData(table, listUsers);

		document.add(table);

		document.close();
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("User ID", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("E-mail", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("First Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Last Name", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Birth Day", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Address", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Phone", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Created Time", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Update Time", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Avatar", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Roles", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Active", font));
		table.addCell(cell);
	}

	private void writeTableData(PdfPTable table, List<User> listUsers) {
		for (User user : listUsers) {
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			table.addCell(String.valueOf(user.getId()));
			table.addCell(user.getEmail());
			table.addCell(user.getFirstName());
			table.addCell(user.getLastName());

			if (user.getBirthday() != null)
				table.addCell(dateFormat.format(user.getBirthday()));
			else
				table.addCell("");
			table.addCell(user.getAddress());
			table.addCell(user.getPhone());

			if (user.getCreatedTime() != null)
				table.addCell(user.getCreatedTime());
			else
				table.addCell("");
			if (user.getUpdateTime() != null)
				table.addCell(user.getUpdateTime());
			else
				table.addCell("");
			table.addCell(user.getAvatar());
			table.addCell(user.getRoles().toString());
			table.addCell(user.isActive() ? "T" : "F");
		}
	}
}
