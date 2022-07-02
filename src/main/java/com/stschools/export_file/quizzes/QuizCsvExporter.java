package com.stschools.export_file.quizzes;

import com.stschools.entity.Blog;
import com.stschools.entity.Quiz;
import com.stschools.util.AbstractExporter;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

public class QuizCsvExporter extends AbstractExporter {

	public void export(List<Quiz> quizzes, HttpServletResponse response) throws IOException {

		super.setResponseHeader(response, "text/csv", ".csv", "quizes_");

		Writer writer = new OutputStreamWriter(response.getOutputStream(), "utf-8");
		writer.write('\uFEFF');

		ICsvBeanWriter csvWriter = new CsvBeanWriter(writer, CsvPreference.STANDARD_PREFERENCE);

		String[] csvHeader = { "Quiz ID", "Name", "Code","Duration", "Created Time","Update Time", "Status", "Deleted"};
		String[] fieldMapping = { "id", "name", "code","duration", "createdTime","updateTime",	"status", "isDeleted"};

		csvWriter.writeHeader(csvHeader);

		for (Quiz quiz : quizzes) {
			csvWriter.write(quiz, fieldMapping);
		}

		csvWriter.close();
	}
}
