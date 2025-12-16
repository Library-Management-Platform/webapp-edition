package com.platform.libraryManager.dataAccess.enums;

public enum LoanStatusEnum {
    RESERVED, //Resource has been reserved but not yet borrowed.
    BORROWED,  //Resource has been assigned to the user.
    IN_PROGRESS, //Resource is currently being borrowed.
    RETURNED, //Resource has been returned but not yet verified.
    CLOSED //Loan is completed and feedback (if any) has been processed.
}
