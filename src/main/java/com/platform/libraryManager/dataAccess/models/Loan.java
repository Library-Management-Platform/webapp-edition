package com.platform.libraryManager.dataAccess.models;

import com.platform.libraryManager.dataAccess.enums.LoanStatusEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ---- Relations ----
    @ManyToOne(optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(optional = false)
    @JoinColumn(name = "resource_id", nullable = false)
    private Resource resource;

    @ManyToOne(optional = false)
    @JoinColumn(name = "library_id", nullable = false)
    private Library library;

    // ---- Dates ----
    @Column(nullable = false)
    private LocalDateTime reservationDate;

    private LocalDateTime borrowDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnDate;

    // ---- Workflow Status ----
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LoanStatusEnum status;

    // ---- Feedback ----
    private Integer rating;

    @Column(length = 1000)
    private String comment;

    // ---- AVAILABILITY FLAG ----
    @Column(nullable = false)
    private boolean availabilityNotified = false;

    // ---- Constructors ----
    Loan() {
    }

    public Loan(Client client, Resource resource, Library library) {
        this.client = client;
        this.resource = resource;
        this.library = library;
        this.reservationDate = LocalDateTime.now();
        this.status = LoanStatusEnum.RESERVED;
        this.availabilityNotified = false;
    }

    // ---- Business Methods ----
    public void markAsBorrowed(int loanDurationDays) {
        if (status != LoanStatusEnum.RESERVED) {
            throw new IllegalStateException("Only reserved loans can be borrowed");
        }
        this.status = LoanStatusEnum.IN_PROGRESS;
        this.borrowDate = LocalDateTime.now();
        this.dueDate = borrowDate.plusDays(loanDurationDays);
    }

    public void markAsReturned() {
        if (status != LoanStatusEnum.IN_PROGRESS) {
            throw new IllegalStateException("Only active loans can be returned");
        }
        this.status = LoanStatusEnum.RETURNED;
        this.returnDate = LocalDateTime.now();
    }

    public void closeLoan(Integer rating, String comment) {
        if (status != LoanStatusEnum.RETURNED) {
            throw new IllegalStateException("Loan must be returned before closure");
        }
        this.rating = rating;
        this.comment = comment;
        this.status = LoanStatusEnum.CLOSED;
    }

    // ---- Getter / Setter for availabilityNotified ----
    public boolean isAvailabilityNotified() {
        return availabilityNotified;
    }

    public void setAvailabilityNotified(boolean availabilityNotified) {
        this.availabilityNotified = availabilityNotified;
    }

    // ---- Other getters/setters ----
    public Long getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Resource getResource() {
        return resource;
    }

    public Library getLibrary() {
        return library;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public LocalDateTime getBorrowDate() {
        return borrowDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public LoanStatusEnum getStatus() {
        return status;
    }

    public Integer getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public void setStatus(LoanStatusEnum status) {
        this.status = status;
    }
}
