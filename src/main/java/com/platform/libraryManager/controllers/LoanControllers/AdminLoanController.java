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
        List<Loan> overdue = loanService.getOverdueLoans();
        model.addAttribute("loans", loans);
        model.addAttribute("overdueLoans", overdue);
        return "admin/pages/manage-loans/loans";
    }

    // View overdue loans
    @GetMapping("/overdue")
    public String overdueLoans(Principal principal, Model model) {
        List<Loan> loans = loanService.getOverdueLoans();
        model.addAttribute("loans", loans);
        return "admin/pages/manage-loans/overdue-loans";
    }

    // Admin borrow action
    @PostMapping("/{loanId}/borrow")
    public String adminBorrow(@PathVariable Long loanId) {
        loanService.adminBorrowResource(loanId, 14);
        return "redirect:/admin/loans";
    }

    // Admin return action
    @PostMapping("/{loanId}/return")
    public String adminReturn(@PathVariable Long loanId) {
        loanService.adminReturnResource(loanId);
        return "redirect:/admin/loans";
    }
}