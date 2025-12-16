package com.platform.libraryManager.ui.controllers.LoanControllers;

import com.platform.libraryManager.dataAccess.models.Librarian;
import com.platform.libraryManager.dataAccess.models.Library;
import com.platform.libraryManager.dataAccess.models.Loan;
import com.platform.libraryManager.logic.services.LoanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.platform.libraryManager.logic.services.UserService;
import com.platform.libraryManager.dto.payloads.user.GetUniqueUserPayload;
import com.platform.libraryManager.dto.responses.endpoints.user.getUnique.GetUniqueUserResponse;
import com.platform.libraryManager.dto.responses.endpoints.user.getUnique.GetUniqueUserSuccessResponse;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;


import java.util.List;


@Controller
@RequestMapping("/librarian/loans")
public class LibrarianLoanController {

	private static final Logger logger = LoggerFactory.getLogger(LibrarianLoanController.class);

	private final LoanService loanService;
	private final UserService userService;

	public LibrarianLoanController(LoanService loanService, UserService userService) {
		this.loanService = loanService;
		this.userService = userService;
	}


// Dashboard: active loans
@GetMapping
public String libraryLoans(java.security.Principal principal, Model model) {
		try {
			if (principal == null) {
				model.addAttribute("error", "Not authenticated");
				return "error";
			}

			String username = principal.getName();
			logger.info("librarian libraryLoans called by principal={}", username);

			GetUniqueUserResponse resp = userService.getUniqueUser(new GetUniqueUserPayload(username));
			if (resp instanceof GetUniqueUserSuccessResponse success) {
				if (success.getUser() instanceof Librarian librarian) {
					Library library = librarian.getLibrary();
					logger.info("found librarian id={} libraryId={}", librarian.getId(), library != null ? library.getId() : "null");

					List<Loan> loans = loanService.getActiveLoansForLibrary(library);
					logger.info("loans found for libraryId={}: {}", library != null ? library.getId() : "null", loans != null ? loans.size() : 0);

					model.addAttribute("loans", loans);
					return "librarian/loans";
				} else {
					logger.warn("user returned but is not a Librarian: {}", success.getUser().getClass().getName());
				}
			} else {
				logger.warn("userService.getUniqueUser returned non-success for username={}", username);
			}

			model.addAttribute("error", "Librarian not found");
			return "error";
		} catch (Exception e) {
			logger.error("error in libraryLoans", e);
			model.addAttribute("error", e.getMessage());
			return "error";
		}
}


// Validate borrowing
@PostMapping("/{loanId}/borrow")
public String borrow(@PathVariable Long loanId, java.security.Principal principal, Model model) {
	try {
		if (principal == null) {
			model.addAttribute("error", "Not authenticated");
			return "error";
		}
		String username = principal.getName();
		GetUniqueUserResponse resp = userService.getUniqueUser(new GetUniqueUserPayload(username));
		if (resp instanceof GetUniqueUserSuccessResponse success && success.getUser() instanceof Librarian librarian) {
			loanService.borrowResource(loanId, librarian, 14);
			return "redirect:/librarian/loans";
		}
		model.addAttribute("error", "Librarian not found");
		return "error";
	} catch (Exception e) {
		logger.error("error borrowing loan {}", loanId, e);
		model.addAttribute("error", e.getMessage());
		return "error";
	}
}


// Validate return
@PostMapping("/{loanId}/return")
public String returnLoan(@PathVariable Long loanId, java.security.Principal principal, Model model) {
	try {
		if (principal == null) {
			model.addAttribute("error", "Not authenticated");
			return "error";
		}
		String username = principal.getName();
		GetUniqueUserResponse resp = userService.getUniqueUser(new GetUniqueUserPayload(username));
		if (resp instanceof GetUniqueUserSuccessResponse success && success.getUser() instanceof Librarian librarian) {
			loanService.returnResource(loanId, librarian);
			return "redirect:/librarian/loans";
		}
		model.addAttribute("error", "Librarian not found");
		return "error";
	} catch (Exception e) {
		logger.error("error returning loan {}", loanId, e);
		model.addAttribute("error", e.getMessage());
		return "error";
	}
}
}
