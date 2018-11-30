package com.thumbttack.weather_wizard.models.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thumbttack.weather_wizard.models.yahoo.Condition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@javax.persistence.Entity
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class ConditionDB implements Comparable<ConditionDB>, Serializable {

    public ConditionDB(Condition condition) {
        this.code = condition.getCode();
        this.date = condition.getDate();
        this.temp = condition.getTemp();
        this.text = condition.getText();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    Long _id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "cityInfoName")
    @Setter
    @Getter
    @NonNull
    CityInfoDB cityInfoName;

    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private String date;
    @Getter
    @Setter
    private String temp;
    @Getter
    @Setter
    private String text;

    @Override
    public int compareTo(ConditionDB o) {
        return date.compareTo(o.date);
    }
}

