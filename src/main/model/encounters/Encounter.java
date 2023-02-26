package model.encounters;

import model.event.ResultAction;

/*
    An Encounter represents an event on an adventure with two outcomes
 */
public abstract class Encounter {
    protected ResultAction results;

    public abstract void runEncounter();

    public ResultAction getResults() {
        return results;
    }

    public void setResults(ResultAction results) {
        this.results = results;
    }

}
