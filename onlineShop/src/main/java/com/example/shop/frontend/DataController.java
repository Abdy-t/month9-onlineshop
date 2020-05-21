package com.example.shop.frontend;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DataController {

    private final MessageSource messageSource;

    String localizeErrorMessage(String errorCode) {
        var locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(errorCode, null, locale);
//        getResourceBundle().getMessage("exception.npe", null, Locale.getDefault());
    }

    @GetMapping("/test")
    public String test() {
        return localizeErrorMessage("rest.error.test");
    }
}
