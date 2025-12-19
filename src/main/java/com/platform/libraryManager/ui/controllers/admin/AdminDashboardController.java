package com.platform.libraryManager.ui.controllers.admin;


import com.platform.libraryManager.dataAccess.repositories.ClientRepository;
import com.platform.libraryManager.dataAccess.repositories.LibrarianRepository;
import com.platform.libraryManager.dataAccess.repositories.LibraryRepository;
import com.platform.libraryManager.logic.services.LoanService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/admin/dashboard" )
public class AdminDashboardController {

    private final LoanService loanService;
    private final LibraryRepository libraryRepository;
    private final ClientRepository clientRepository;
    private final LibrarianRepository librarianRepository;

    public AdminDashboardController(
            LoanService loanService,
            LibraryRepository libraryRepository,
            ClientRepository clientRepository,
            LibrarianRepository librarianRepository) {
        this.loanService = loanService;
        this.libraryRepository = libraryRepository;
        this.clientRepository = clientRepository;
        this.librarianRepository = librarianRepository;
    }

    @GetMapping()
    public String dashboard(Model model) {
        // --- 1. Get Logged-in User Info for the Header ---
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        String role = authentication.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("USER"); // Default role if none found

        model.addAttribute("username", username);
        model.addAttribute("role", role);

        // --- 2. Get Loan Statistics (using optimized service methods) ---
        Map<String, Long> byCategory = loanService.countLoansByCategory();
        Map<String, Long> byLibrary = loanService.countLoansByLibrary();

        List<String> categoryLabels = byCategory.keySet().stream().collect(Collectors.toList());
        List<Long> categoryValues = byCategory.values().stream().collect(Collectors.toList());

        List<String> libraryLabels = byLibrary.keySet().stream().collect(Collectors.toList());
        List<Long> libraryValues = byLibrary.values().stream().collect(Collectors.toList());

        long totalLoans = loanService.countTotalLoans(); // Use direct count from service

        // --- 3. Get Direct Counts from Repositories ---
        long totalLibraries = libraryRepository.count();
        long totalClients = clientRepository.count();
        long totalLibrarians = librarianRepository.count();

        // --- 4. Get Corrected Turnover Rate ---
        double turnover = loanService.computeTurnoverRate();

        // --- 5. Add All Attributes to the Model for Thymeleaf ---
        model.addAttribute("categoryLabels", categoryLabels);
        model.addAttribute("categoryValues", categoryValues);
        model.addAttribute("libraryLabels", libraryLabels);
        model.addAttribute("libraryValues", libraryValues);

        model.addAttribute("totalLoans", totalLoans);
        model.addAttribute("totalLibraries", totalLibraries);
        model.addAttribute("totalClients", totalClients);
        model.addAttribute("totalLibrarians", totalLibrarians);

        // Format turnover rate to two decimal places for display
        model.addAttribute("turnoverRate", String.format("%.2f", turnover));

        return "admin/dashboard";
    }

    @GetMapping("/export")
    public void exportCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv; charset=UTF-8");
        response.setHeader("Content-Disposition", "attachment; filename=\"admin-analytics.csv\"");

        try (PrintWriter writer = response.getWriter()) {
            // CSV Header
            writer.println("Metric,Key,Value");

            // --- Exporting new total counts ---
            writer.printf("Totals,TotalClients,%d\n", clientRepository.count());
            writer.printf("Totals,TotalLibrarians,%d\n", librarianRepository.count());
            writer.printf("Totals,TotalLibraries,%d\n", libraryRepository.count());
            writer.printf("Totals,TotalLoans,%d\n", loanService.countTotalLoans());

            // --- Exporting loan distribution ---
            Map<String, Long> byCategory = loanService.countLoansByCategory();
            for (Map.Entry<String, Long> entry : byCategory.entrySet()) {
                writer.printf("LoansByCategory,%s,%d\n", entry.getKey(), entry.getValue());
            }

            Map<String, Long> byLibrary = loanService.countLoansByLibrary();
            for (Map.Entry<String, Long> entry : byLibrary.entrySet()) {
                // Escape commas in library names to prevent CSV corruption
                String libraryName = entry.getKey().replace("\"", "\"\"");
                writer.printf("LoansByLibrary,\"%s\",%d\n", libraryName, entry.getValue());
            }

            // --- Exporting turnover rate ---
            writer.printf("Performance,TurnoverRate,%.2f\n", loanService.computeTurnoverRate());
        }
    }
}
