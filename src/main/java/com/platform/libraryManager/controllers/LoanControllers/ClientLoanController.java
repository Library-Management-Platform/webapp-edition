package com.platform.libraryManager.controllers.LoanControllers;

import com.platform.libraryManager.enums.ResourceStatusEnum;
import com.platform.libraryManager.models.Client;
import com.platform.libraryManager.models.Loan;
import com.platform.libraryManager.models.Library;
import com.platform.libraryManager.models.Resource;
import com.platform.libraryManager.repositories.LibraryRepository;
import com.platform.libraryManager.repositories.ResourceRepository;
import com.platform.libraryManager.services.ClientService;
import com.platform.libraryManager.services.LoanService;

import jakarta.servlet.http.HttpServletRequest;

import com.platform.libraryManager.payloads.client.GetUniqueClientPayload;
import com.platform.libraryManager.responses.endpoints.client.getUnique.GetUniqueClientResponse;
import com.platform.libraryManager.responses.endpoints.client.getUnique.GetUniqueClientSuccessResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.List;
import java.util.Collections;

@Controller
@RequestMapping("/client/loans")
public class ClientLoanController {

    private final LoanService loanService;
    private final ResourceRepository resourceRepository;
    private final LibraryRepository libraryRepository;
    private final ClientService clientService;

    public ClientLoanController(
            LoanService loanService,
            ResourceRepository resourceRepository,
            LibraryRepository libraryRepository,
            ClientService clientService) {
        this.loanService = loanService;
        this.resourceRepository = resourceRepository;
        this.libraryRepository = libraryRepository;
        this.clientService = clientService;
    }

    // ===============================
    // Utility to get authenticated client
    // ===============================
    private Client getAuthenticatedClient(Principal principal) {
        if (principal == null) {
            throw new IllegalStateException("User not authenticated. Please login.");
        }

        String username = principal.getName();
        GetUniqueClientPayload payload = new GetUniqueClientPayload(username);
        GetUniqueClientResponse response = clientService.getUniqueClient(payload);

        if (response instanceof GetUniqueClientSuccessResponse successResponse) {
            return successResponse.getClient();
        } else {
            throw new IllegalArgumentException("Client not found for username: " + username);
        }
    }

    // ===============================
    // View my loans
    // ===============================
    @GetMapping
    public String myLoans(Principal principal, Model model) {
        try {
            Client client = getAuthenticatedClient(principal);
            List<Loan> loans = loanService.getLoansForClient(client);
            model.addAttribute("loans", loans);
            // Ensure `resources` is always present for templates that reference it
            model.addAttribute("resources", Collections.emptyList());
            model.addAttribute("client", client);
            return "client/loans";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    // ===============================
    // Show reserve form
    // ===============================
    @GetMapping("/new")
    public String showNewLoanForm(Principal principal, Model model) {
        try {
            Client client = getAuthenticatedClient(principal);
            List<Resource> resources = resourceRepository.findAll();
            List<Library> libraries = libraryRepository.findAll();

            model.addAttribute("client", client);
            model.addAttribute("resources", resources);
            model.addAttribute("libraries", libraries);

            return "client/new-loan";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    // ===============================
    // Reserve a resource
    // ===============================
    @PostMapping("/reserve")
    public String reserveResource(
            Principal principal,
            @RequestParam Long resourceId,
            @RequestParam Long libraryId,
            RedirectAttributes redirectAttributes,
            HttpServletRequest request,
            Model model) {
        try {
            Client client = getAuthenticatedClient(principal);

            Resource resource = resourceRepository.findById(resourceId)
                    .orElseThrow(() -> new IllegalArgumentException("Resource not found with ID: " + resourceId));

            Library library = libraryRepository.findById(libraryId)
                    .orElseThrow(() -> new IllegalArgumentException("Library not found with ID: " + libraryId));

            if (!resource.getLibrary().getId().equals(library.getId())) {
                throw new IllegalArgumentException("Resource does not belong to the selected library");
            }

            loanService.reserveResource(client, resource, library);

            List<Loan> loans = loanService.getLoansForClient(client);
            model.addAttribute("loans", loans);
            model.addAttribute("client", client);
            model.addAttribute("resources", resourceRepository.findAll());
            model.addAttribute("totalItems", loans.size());
            model.addAttribute("pageSize", loans.size());
            model.addAttribute("totalPages", 1);
            model.addAttribute("currentPage", 0);

            return "client/loans";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            String referer = request.getHeader("Referer");
            return "redirect:" + (referer != null ? referer : "/client/resources");
        }
    }

    // ===============================
    // Close loan with feedback
    // ===============================
    @PostMapping("/{loanId}/close")
    public String closeLoan(
            @PathVariable Long loanId,
            Principal principal,
            @RequestParam Integer rating,
            @RequestParam String comment,
            RedirectAttributes redirectAttributes) {
        try {
            Client client = getAuthenticatedClient(principal);

            if (rating < 1 || rating > 5) {
                throw new IllegalArgumentException("Rating must be between 1 and 5");
            }

            loanService.closeLoan(loanId, client, rating, comment);

            redirectAttributes.addFlashAttribute("success", "Loan closed successfully with your feedback!");
            return "redirect:/client/loans";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/client/loans";
        }
    }
}