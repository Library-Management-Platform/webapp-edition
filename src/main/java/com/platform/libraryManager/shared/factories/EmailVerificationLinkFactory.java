package com.platform.libraryManager.shared.factories;

import com.platform.libraryManager.shared.helpers.DateHelper;
import com.platform.libraryManager.dataAccess.models.EmailVerificationLink;
import com.platform.libraryManager.dto.payloads.emailVerification.CreateEmailVerificationLinkPayload;

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
