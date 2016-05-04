package de.goeuro.ui;

import de.goeuro.useCase.InputHandler;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class CommandLineInputHandler implements InputHandler {

    private String[] args;

    public CommandLineInputHandler(String[] args) {
        this.args = args;
    }

    @Override
    public Boolean hasUserProvidedInput() {
        return (args.length > 0) && (isNotBlank(args[0]));
    }

    @Override
    public String extractCity() {
        if (!hasUserProvidedInput()) { {
            return null;
        }}

        return args[0];
    }
}
