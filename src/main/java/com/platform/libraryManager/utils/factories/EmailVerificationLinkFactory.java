package com.platform.libraryManager.utils.factories;

import com.platform.libraryManager.utils.helpers.DateHelper;
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
