package com.platform.libraryManager.utils.helpers;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

public abstract class RedirectHelper {




    public static String redirect(String route) { return route; }


    public static String addFlashAttributesAndRedirect(
            RedirectAttributes redirectAttributes,
            Map<String, Object> flashAttributes,
            String redirectRoute
    ) {
        for(Map.Entry<String, Object> flashAttribute : flashAttributes.entrySet()) RouteAttributeHelper.addFlashAttribute(
                redirectAttributes,
                flashAttribute.getKey(),
                flashAttribute.getValue()
        );

        return redirectRoute;
    }



}
