package com.ponagayba.projects.model.test;

import java.util.ArrayList;
import java.util.List;

public class Answer {

    private List<String> chosenOptions;

    public Answer() {
        this.chosenOptions = new ArrayList<>();
    }

    public List<String> getChosenOptions() {
        return chosenOptions;
    }

    public void setChosenOptions(List<String> chosenOptions) {
        this.chosenOptions = chosenOptions;
    }
}
