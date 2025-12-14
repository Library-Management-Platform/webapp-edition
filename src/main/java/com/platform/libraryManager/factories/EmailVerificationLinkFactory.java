package com.platform.libraryManager.factories;

import com.platform.libraryManager.helpers.DateHelper;
import com.platform.libraryManager.models.EmailVerificationLink;
import com.platform.libraryManager.payloads.emailVerification.CreateEmailVerificationLinkPayload;

public abstract class EmailVerificationLinkFactory {


    public static EmailVerificationLink create(CreateEmailVerificationLinkPayload createEmailVerificationPayload) {

        return new EmailVerificationLink(
                createEmailVerificationPayload.getToken(),
                createEmailVerificationPayload.getUser(),
                DateHelper.getCurrentLocalDateTime(),
                DateHelper.getLocalDateTimePlusHours(3)
        );
    }
}
