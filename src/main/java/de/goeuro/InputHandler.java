package de.goeuro;

/**
 * Collects input from user via command line and presents proper success/error messages.
 *
 * This is an implementation of the "UI" layer defined by Robert C. Martin
 * in https://blog.8thlight.com/uncle-bob/2012/08/13/the-clean-architecture.html
 */
public interface InputHandler {
    void fireCsvCreation(String[] args);
}
