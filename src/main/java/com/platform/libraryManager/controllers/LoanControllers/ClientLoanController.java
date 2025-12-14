package com.platform.libraryManager.controllers.LoanControllers;

import com.platform.libraryManager.models.Client;
import java.security.Principal;
import com.platform.libraryManager.payloads.clientPayloads.GetUniqueClientPayload;
import com.platform.libraryManager.responses.endpoints.client.getUnique.GetUniqueClientResponse;
import com.platform.libraryManager.responses.endpoints.client.getUnique.GetUniqueClientSuccessResponse;
import com.platform.libraryManager.services.ClientService;
import com.platform.libraryManager.models.Loan;
import com.platform.libraryManager.models.Library;
import com.platform.libraryManager.models.Resource;
import com.platform.libraryManager.repositories.LibraryRepository;
import com.platform.libraryManager.repositories.ResourceRepository;
import com.platform.libraryManager.services.LoanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/client/loans")
public class ClientLoanController {

    private final LoanService loanService;
    private final ResourceRepository resourceRepository;
    private final LibraryRepository libraryRepository;
    private final ClientService clientService; // Ajout de ClientService

    public ClientLoanController(
            LoanService loanService,
            ResourceRepository resourceRepository,
            LibraryRepository libraryRepository,
            ClientService clientService // Injection de ClientService
    ) {
        this.loanService = loanService;
        this.resourceRepository = resourceRepository;
        this.libraryRepository = libraryRepository;
        this.clientService = clientService; // Initialisation
    }

    // Méthode utilitaire pour récupérer le client à partir du nom d'utilisateur (Principal)
    private Client getClientFromPrincipal(Principal principal) {
        if (principal == null) {
            throw new IllegalStateException("Utilisateur non authentifié.");
        }
        String username = principal.getName();
        
        // Création du payload en utilisant le constructeur avec le nom d'utilisateur
        GetUniqueClientPayload payload = new GetUniqueClientPayload(username); 

        GetUniqueClientResponse response = clientService.getUniqueClient(payload);

        if (response instanceof GetUniqueClientSuccessResponse successResponse) {
            return successResponse.getClient();
        } else {
            // Gérer l'erreur si le client n'est pas trouvé
            throw new IllegalArgumentException("Client non trouvé pour l'utilisateur: " + username);
        }
    }

    // ===============================
    // View my loans
    // ===============================
    @GetMapping
    public String myLoans(
            Principal principal, // Récupération de l'utilisateur connecté
            Model model
    ) {
        Client client = getClientFromPrincipal(principal); // Récupération du client
        List<Loan> loans = loanService.getLoansForClient(client);
        model.addAttribute("loans", loans);
        return "client/loans";
    }

    // ===============================
    // Reserve a resource
    // ===============================
    @PostMapping("/reserve")
    public String reserveResource(
            Principal principal, // Récupération de l'utilisateur connecté
            @RequestParam Long resourceId,
            @RequestParam Long libraryId
    ) {
        Client client = getClientFromPrincipal(principal); // Récupération du client
        Resource resource = resourceRepository.findById(resourceId)
                .orElseThrow(() -> new IllegalArgumentException("Resource not found"));

        Library library = libraryRepository.findById(libraryId)
                .orElseThrow(() -> new IllegalArgumentException("Library not found"));

        loanService.reserveResource(client, resource, library);
        return "redirect:/client/loans";
    }

    // ===============================
    // Close loan with feedback
    // ===============================
    @PostMapping("/{loanId}/close")
    public String closeLoan(
            @PathVariable Long loanId,
            Principal principal, // Récupération de l'utilisateur connecté
            @RequestParam Integer rating,
            @RequestParam String comment
    ) {
        Client client = getClientFromPrincipal(principal); // Récupération du client
        loanService.closeLoan(loanId, client, rating, comment);
        return "redirect:/client/loans";
    }
}
