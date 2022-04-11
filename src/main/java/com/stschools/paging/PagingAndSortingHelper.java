package com.stschools.paging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagingAndSortingHelper {
	private ModelAndViewContainer model;
	private String listName;
	private String sortField;
	private String sortDir; //->asc || des
	private String keyword;

	//
	public void updateModelAttributes(int pageNum, Page<?> page) {
		List<?> listItems = page.getContent();
		int pageSize = page.getSize();


		//Basic paging
		long startCount = (pageNum - 1) * pageSize + 1;
		long endCount = startCount + pageSize - 1;
		if (endCount > page.getTotalElements()) {
			endCount = page.getTotalElements();
		}
		
		model.addAttribute("currentPage", pageNum);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("startCount", startCount);
		model.addAttribute("endCount", endCount);
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute(listName, listItems);
	}
	
	public void listEntities(int pageNum, int pageSize, SearchRepository<?, Integer> repo) {
		Pageable pageable = createPageable(pageSize, pageNum);
		Page<?> page = null;
		
		if (keyword != null) {
			page = repo.findAll(keyword, pageable);
		} else {
			page = repo.findAll(pageable);
		}
		
		updateModelAttributes(pageNum, page);		
	}

	//
	public Pageable createPageable(int pageSize, int pageNum) {
		Sort sort = Sort.by(sortField);
		sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
		return PageRequest.of(pageNum - 1, pageSize, sort);		
	}	
}
