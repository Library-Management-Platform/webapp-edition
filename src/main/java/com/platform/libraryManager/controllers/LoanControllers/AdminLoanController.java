package com.platform.libraryManager.controllers.LoanControllers;

import com.platform.libraryManager.models.Loan;
import java.security.Principal;
import com.platform.libraryManager.services.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
@RequestMapping("/admin/loans")
public class AdminLoanController {


private final LoanService loanService;


public AdminLoanController(LoanService loanService) {
this.loanService = loanService;
}


// View all active loans
@GetMapping
public String allActiveLoans(Principal principal, Model model) {
List<Loan> loans = loanService.getAllActiveLoans();
model.addAttribute("loans", loans);
return "admin/loans";
}


// View overdue loans
@GetMapping("/overdue")
public String overdueLoans(Principal principal, Model model) {
List<Loan> loans = loanService.getOverdueLoans();
model.addAttribute("loans", loans);
return "admin/overdue-loans";
}
}