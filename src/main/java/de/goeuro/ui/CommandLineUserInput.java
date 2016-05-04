package de.goeuro.ui;

import de.goeuro.useCase.UserInput;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class CommandLineUserInput implements UserInput {

    private String[] args;

    public CommandLineUserInput(String[] args) {
        this.args = args;
    }

    @Override
    public Boolean isPresent() {
        return (args.length > 0) && (isNotBlank(args[0]));
    }

    @Override
    public String get() {
        if (!isPresent()) { {
            return null;
        }}

        return args[0];
    }
}
