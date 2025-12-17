package com.platform.libraryManager.ui.controllers;

import com.platform.libraryManager.logic.services.LoanService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/dashboard")
public class AdminDashboardController {

    private final LoanService loanService;

    public AdminDashboardController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping()
    public String dashboard(Model model) {
        Map<String, Long> byCategory = loanService.countLoansByCategory();
        Map<String, Long> byLibrary = loanService.countLoansByLibrary();
        double turnover = loanService.computeTurnoverRate();

        // Prepare labels and values for Chart.js
        List<String> categoryLabels = byCategory.keySet().stream().collect(Collectors.toList());
        List<Long> categoryValues = byCategory.values().stream().collect(Collectors.toList());

        List<String> libraryLabels = byLibrary.keySet().stream().collect(Collectors.toList());
        List<Long> libraryValues = byLibrary.values().stream().collect(Collectors.toList());

        model.addAttribute("categoryLabels", categoryLabels);
        model.addAttribute("categoryValues", categoryValues);
        // total loans computed from category values to avoid Thymeleaf sum calls
        long totalLoans = categoryValues.stream().mapToLong(Long::longValue).sum();
        model.addAttribute("totalLoans", totalLoans);
        model.addAttribute("libraryLabels", libraryLabels);
        model.addAttribute("libraryValues", libraryValues);
        model.addAttribute("turnoverRate", String.format("%.2f", turnover));

        return "admin/dashboard";
    }

    @GetMapping("/export")
    public void exportCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=admin-analytics.csv");

        try (PrintWriter writer = response.getWriter()) {
            writer.println("Metric,Key,Value");

            Map<String, Long> byCategory = loanService.countLoansByCategory();
            for (Map.Entry<String, Long> e : byCategory.entrySet()) {
                writer.printf("LoansByCategory,%s,%d\n", e.getKey(), e.getValue());
            }

            Map<String, Long> byLibrary = loanService.countLoansByLibrary();
            for (Map.Entry<String, Long> e : byLibrary.entrySet()) {
                writer.printf("LoansByLibrary,%s,%d\n", e.getKey(), e.getValue());
            }

            writer.printf("TurnoverRate,percent,%s\n", String.format("%.2f", loanService.computeTurnoverRate()));
        }
    }
}
