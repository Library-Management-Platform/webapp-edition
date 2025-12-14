package com.platform.libraryManager.helpers;

import jakarta.annotation.Nullable;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

public abstract class RouteAttributeHelper {

    public static void addFlashAttribute(
            RedirectAttributes redirectAttributes,
            String attributeName,
            @Nullable Object object
    ) {
        redirectAttributes.addFlashAttribute(attributeName, object);
    }

    public static void addModelAttributes(Model model, Map<String, Object> modelAttributes) {
        model.addAllAttributes(modelAttributes);
    }

}
