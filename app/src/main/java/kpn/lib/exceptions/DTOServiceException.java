package kpn.lib.exceptions;

import kpn.lib.code.Code;

public final class DTOServiceException extends Exception {
    private final String[] args;

    public String[] getArgs() {
        return args;
    }

    public DTOServiceException(String message, String... args) {
        super(message);
        this.args = args;
    }

    public DTOServiceException(Code code, String... args){
        super(code.getValue());
        this.args = args;
    }
}
