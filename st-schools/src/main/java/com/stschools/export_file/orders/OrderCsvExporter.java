package com.stschools.export_file.orders;


import com.stschools.entity.Order;
import com.stschools.entity.User;
import com.stschools.util.AbstractExporter;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

public class OrderCsvExporter extends AbstractExporter {

	public void export(List<Order> orders, HttpServletResponse response) throws IOException {

		super.setResponseHeader(response, "text/csv", ".csv", "order_");

		Writer writer = new OutputStreamWriter(response.getOutputStream(), "utf-8");
		writer.write('\uFEFF');

		ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);

		String[] csvHeader = { "Order ID","User Name", "Course Name", "Created Time" ,"Update Time"};
		String[] fieldMapping = { "id", "userName", "courseName","createdTime", "updateTime"};

		csvWriter.writeHeader(csvHeader);

		for (Order order : orders) {
			csvWriter.write(order, fieldMapping);
		}

		csvWriter.close();
	}
}
