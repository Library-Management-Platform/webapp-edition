package com.platform.libraryManager.helpers;

import jakarta.annotation.Nullable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

public abstract class RedirectHelper {


    public static void addFlashAttribute(
            RedirectAttributes redirectAttributes,
            String attributeName,
            @Nullable Object object
    ) {
        redirectAttributes.addFlashAttribute(attributeName, object);
    }

    public static String redirect(String route) { return route; }


    public static String addFlashAttributesAndRedirect(
            RedirectAttributes redirectAttributes,
            Map<String, Object> flasAttributes,
            String redirectRoute
    ) {
        for(Map.Entry<String, Object> flasAttribute : flasAttributes.entrySet()) addFlashAttribute(
                redirectAttributes,
                flasAttribute.getKey(),
                flasAttribute.getValue()
        );

        return redirectRoute;
    }
}
