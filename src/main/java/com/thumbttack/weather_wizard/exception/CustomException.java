package com.thumbttack.weather_wizard.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.ui.ModelMap;

import static com.thumbttack.weather_wizard.util.Utils.MODEL_ATR_ERROR_MESSAGE;

@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {
    @Getter
    @Setter
    private String errorText;

    @Getter
    @Setter
    private ModelMap model;

    public CustomException(Exception e, ModelMap model) {
        this.errorText = e.getMessage();
        this.model = model;
    }
}
