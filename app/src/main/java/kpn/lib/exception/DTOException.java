package kpn.lib.exception;

import kpn.lib.code.ErrorCode;

public final class DTOException extends Exception {
    private final String[] args;

    public String[] getArgs() {
        return args;
    }

    public DTOException(String message, String... args) {
        super(message);
        this.args = args;
    }

    public DTOException(ErrorCode code, String... args){
        super(code.getValue());
        this.args = args;
    }
}
