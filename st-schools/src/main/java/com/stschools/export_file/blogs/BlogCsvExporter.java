package com.stschools.export_file.blogs;

import com.stschools.entity.Blog;
import com.stschools.util.AbstractExporter;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

public class BlogCsvExporter extends AbstractExporter {

	public void export(List<Blog> blogs, HttpServletResponse response) throws IOException {

		super.setResponseHeader(response, "text/csv", ".csv", "blogs_");

		Writer writer = new OutputStreamWriter(response.getOutputStream(), "utf-8");
		writer.write('\uFEFF');

		ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);

		String[] csvHeader = { "Blog ID", "Title", "Content","View", "Image", "Created Time","Update Time", "Status", "Deleted"};
		String[] fieldMapping = { "id", "title", "content","view", "image", "createdTime","updateTime",	"status", "isDeleted"};

		csvWriter.writeHeader(csvHeader);

		for (Blog blog : blogs) {
			csvWriter.write(blog, fieldMapping);
		}

		csvWriter.close();
	}
}
