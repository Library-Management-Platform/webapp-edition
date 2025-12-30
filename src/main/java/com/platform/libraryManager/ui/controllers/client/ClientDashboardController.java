package com.platform.libraryManager.ui.controllers.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class ClientDashboardController {

    @GetMapping("/dashboard")
    public String dashboard() { return "client/browse-resources"; }


}
