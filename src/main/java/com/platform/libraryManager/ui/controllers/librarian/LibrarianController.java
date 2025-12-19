package com.platform.libraryManager.ui.controllers.librarian;

import com.platform.libraryManager.dataAccess.models.Librarian;
import com.platform.libraryManager.dataAccess.models.Resource;
import com.platform.libraryManager.dataAccess.repositories.LibrarianRepository;
import com.platform.libraryManager.dataAccess.repositories.ResourceRepository;
import com.platform.libraryManager.logic.services.LoanService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/librarian/dashboard")
public class LibrarianController {

	private final ResourceRepository resourceRepository;
	private final LoanService loanService;
    private final LibrarianRepository librarianRepository;

	public LibrarianController(ResourceRepository resourceRepository, LoanService loanService, LibrarianRepository librarianRepository) {
		this.resourceRepository = resourceRepository;
		this.loanService = loanService;
        this.librarianRepository = librarianRepository;
	}

	@GetMapping
public String dashboard(
        org.springframework.security.core.Authentication authentication,
        Model model
) {

    // Not authenticated (extra safety)
    if (authentication == null || !authentication.isAuthenticated()) {
        model.addAttribute("resources", List.of());
        model.addAttribute("totalItems", 0);
        model.addAttribute("pageSize", 0);
        model.addAttribute("totalPages", 0);
        model.addAttribute("currentPage", 0);
        return "librarian/dashboard";
    }

    // ✅ Username extracted correctly (String principal)
    String username = authentication.getName(); // "librarian"

    // ✅ Reload Librarian entity from DB
    Librarian librarian = librarianRepository
            .findByUsername(username)
            .orElse(null);

    if (librarian == null || librarian.getLibrary() == null) { // No librarian or no library assigned
        model.addAttribute("resources", List.of());
        model.addAttribute("totalItems", 0);
        model.addAttribute("pageSize", 0);
        model.addAttribute("totalPages", 0);
        model.addAttribute("currentPage", 0);
        return "librarian/dashboard";
    }

    Long libraryId = librarian.getLibrary().getId();

    Page<Resource> resourcePage =
            resourceRepository.findByLibraryId(libraryId, Pageable.unpaged());

    model.addAttribute("resources", resourcePage.getContent());
    model.addAttribute("totalItems", resourcePage.getTotalElements());
    model.addAttribute("pageSize", resourcePage.getSize());
    model.addAttribute("totalPages", resourcePage.getTotalPages());
    model.addAttribute("currentPage", resourcePage.getNumber());

    return "librarian/dashboard";
}


}
