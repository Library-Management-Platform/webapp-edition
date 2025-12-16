package com.platform.libraryManager.ui.controllers;

import com.platform.libraryManager.dataAccess.models.Librarian;
import com.platform.libraryManager.dataAccess.models.Resource;
import com.platform.libraryManager.dataAccess.repositories.ResourceRepository;
import com.platform.libraryManager.logic.services.LoanService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/librarian")
public class LibrarianController {

	private final ResourceRepository resourceRepository;
	private final LoanService loanService;

	public LibrarianController(ResourceRepository resourceRepository, LoanService loanService) {
		this.resourceRepository = resourceRepository;
		this.loanService = loanService;
	}

	@GetMapping("/dashboard")
	public String dashboard(@AuthenticationPrincipal Librarian librarian, Model model) {
		List<Resource> resources = resourceRepository.findAll();

		model.addAttribute("resources", resources);
		model.addAttribute("totalItems", resources != null ? resources.size() : 0);
		model.addAttribute("pageSize", resources != null ? resources.size() : 0);
		model.addAttribute("totalPages", 1);
		model.addAttribute("currentPage", 0);

		return "librarian/dashboard";
	}
}
