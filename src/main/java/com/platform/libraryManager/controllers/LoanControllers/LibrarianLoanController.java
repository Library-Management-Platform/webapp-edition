package com.platform.libraryManager.controllers.LoanControllers;

import com.platform.libraryManager.models.Librarian;
import com.platform.libraryManager.models.Library;
import com.platform.libraryManager.models.Loan;
import com.platform.libraryManager.services.LoanService;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal; // Import nécessaire


import java.util.List;


@Controller
@RequestMapping("/librarian/loans")
public class LibrarianLoanController {


private final LoanService loanService;


public LibrarianLoanController(LoanService loanService) {
this.loanService = loanService;
}


// Dashboard: active loans
@GetMapping
public String libraryLoans(@AuthenticationPrincipal Librarian librarian, Model model) {
Library library = librarian.getLibrary();
List<Loan> loans = loanService.getActiveLoansForLibrary(library);
model.addAttribute("loans", loans);
return "librarian/loans";
}


// Validate borrowing
@PostMapping("/{loanId}/borrow")
public String borrow(@PathVariable Long loanId,
@AuthenticationPrincipal Librarian librarian) {
loanService.borrowResource(loanId, librarian, 14);
return "redirect:/librarian/loans";
}


// Validate return
@PostMapping("/{loanId}/return")
public String returnLoan(@PathVariable Long loanId,
@AuthenticationPrincipal Librarian librarian) {
loanService.returnResource(loanId, librarian);
return "redirect:/librarian/loans";
}
}
