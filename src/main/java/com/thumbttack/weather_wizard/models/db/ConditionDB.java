package com.thumbttack.weather_wizard.models.db;

import com.thumbttack.weather_wizard.models.yahoo.Condition;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@NoArgsConstructor
public class ConditionDB extends Condition {

    private ConditionDB(String code, String date, String temp, String text) {
        this.condition = new Condition(code, date, temp, text);
    }

    public ConditionDB(Condition condition) {
        this(condition.getCode(), condition.getDate(), condition.getTemp(), condition.getText());
    }

    @Embedded
    Condition condition;
}

