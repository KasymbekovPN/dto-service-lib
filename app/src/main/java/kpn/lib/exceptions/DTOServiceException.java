package kpn.lib.exceptions;

public final class DTOServiceException extends Exception {
    private final String[] args;

    public String[] getArgs() {
        return args;
    }

    public DTOServiceException(String message, String... args) {
        super(message);
        this.args = args;
    }
}
