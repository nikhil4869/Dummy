package com.example.demo.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class MvcGlobalExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public String handleClientError(HttpClientErrorException ex,
                                    RedirectAttributes redirectAttributes) {

        String errorMessage = extractMessage(ex.getResponseBodyAsString());

        // If login error → redirect login
        if (ex.getStatusCode().value() == 401) {
            redirectAttributes.addFlashAttribute("error", errorMessage);
            return "redirect:/login";
        }

        // If register error → redirect register
        redirectAttributes.addFlashAttribute("error", errorMessage);
        return "redirect:/register";
    }

    private String extractMessage(String responseBody) {
        try {
            int start = responseBody.indexOf("message");
            if (start != -1) {
                int colon = responseBody.indexOf(":", start);
                int comma = responseBody.indexOf(",", colon);
                if (comma == -1) comma = responseBody.indexOf("}", colon);
                return responseBody.substring(colon + 2, comma - 1);
            }
        } catch (Exception ignored) {}

        return "Operation failed";
    }
}