package com.platform.libraryManager.models;


import com.platform.libraryManager.enums.LoanStatusEnum;
import com.platform.libraryManager.enums.ResourceCategoryEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
public class Loan {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;

    @ManyToOne @JoinColumn(name = "client_id") private Client client;
    @ManyToOne @JoinColumn(name = "resource_id") private Resource resource;

    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;

    @Enumerated(EnumType.STRING) private LoanStatusEnum status;

    public Loan() {}

    public Loan(
            Client client,
            Resource resource,
            LocalDateTime startDate,
            LocalDateTime dueDate
    ) {
        setClient(client);
        setResource(resource);
        setStartDate(startDate);
        setDueDate(dueDate);
        setStatus(LoanStatusEnum.RESERVED);
    }


    public Loan(
            Long id,
            Client client,
            Resource resource,
            LocalDateTime startDate,
            LocalDateTime dueDate,
            LocalDateTime returnDate,
            LoanStatusEnum status
    ) {
        setId(id);
        setClient(client);
        setResource(resource);
        setStartDate(startDate);
        setDueDate(dueDate);
        setDueDate(returnDate);
        setStatus(status);

    }


    public Long getId() { return id; }
    private void setId(Long id) { this.id = id; }

    public Client getClient() { return client; }
    private void setClient(Client client) { this.client = client; }

    public Resource getResource() { return resource; }
    private void setResource(Resource resource) { this.resource = resource; }

    public LocalDateTime getStartDate() { return startDate; }
    private void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getDueDate() { return dueDate; }
    private void setDueDate(LocalDateTime dueDate) { this.dueDate = dueDate; }

    public LocalDateTime getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }

    public LoanStatusEnum getStatus() { return status; }
    public void setStatus(LoanStatusEnum status) { this.status = status; }

}
