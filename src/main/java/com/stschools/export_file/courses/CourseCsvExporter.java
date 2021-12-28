package com.stschools.export_file.courses;


import com.stschools.entity.Course;
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

public class CourseCsvExporter extends AbstractExporter {

	public void export(List<Course> courses, HttpServletResponse response) throws IOException {

		super.setResponseHeader(response, "text/csv", ".csv", "course_");

		Writer writer = new OutputStreamWriter(response.getOutputStream(), "utf-8");
		writer.write('\uFEFF');

		ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);

		String[] csvHeader = { "Course ID", "Name", "Description","Language", "Price", "Image"};
		String[] fieldMapping = { "id", "name", "description","language", "price", "image"};

		csvWriter.writeHeader(csvHeader);

		for (Course course : courses) {
			csvWriter.write(course, fieldMapping);
		}

		csvWriter.close();
	}
}
