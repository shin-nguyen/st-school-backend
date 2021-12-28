package com.stschools.import_file.blogs;

import com.stschools.entity.Blog;
import com.stschools.repository.UserRepository;
import com.stschools.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BlogExcelImporter {

	private final UserRepository userRepository;

	public List<Blog> parseExcelFile(InputStream is) {
		try {
			Workbook workbook = new XSSFWorkbook(is);

			Sheet sheetBlogs = workbook.getSheet("Blogs");
			List<Blog> blogs = getInforListBlog(sheetBlogs);

			// Close WorkBook
			workbook.close();
			return blogs;
		} catch (IOException e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}

	public List getInforListBlog(Sheet sheetBlogs) throws IOException {
		// get iterator of sheet
		Iterator rows = sheetBlogs.iterator();

		List<Blog> blogs = new ArrayList();

		// number row of sheet
		int rowNumber = 0;

		while (rows.hasNext()) {
			Row currentRow = (Row) rows.next();

			// skip header
			if (rowNumber == 0) {
				rowNumber++;
				continue;
			}

			Iterator cellsInRow = currentRow.iterator();

			Blog blog = new Blog();

			int cellIndex = 0;
			List listAnswer = new ArrayList();
			while (cellsInRow.hasNext()) {
				Cell currentCell = (Cell) cellsInRow.next();
				switch (cellIndex) {
					case 0:// get title of qiz
						blog.setTitle(currentCell.getStringCellValue());
						break;
					case 1:// get description
						blog.setContent(currentCell.getStringCellValue());
						break;
					case 2:// get education level
						blog.setImage(currentCell.getStringCellValue());
						break;
					case 3:// get max attempts
						blog.setUser(userRepository.findByEmail(currentCell.getStringCellValue()));
						break;
					case 4:// get education level
						blog.setStatus(currentCell.getBooleanCellValue());
						break;

					default:// get answers

						break;
				}

				cellIndex++;
			}
			blogs.add(blog);
		}
//		System.out.println(blogs.toString());
		return blogs;
	}

//	public static void main(String[] args) throws FileNotFoundException {
//		File file = new File("E:\\Book1.xlsx");
//		InputStream inputStream = new FileInputStream(file);
//
//		parseExcelFile(inputStream);
//	}
}
