package kpn.lib.code;

public enum Code {
    EXECUTOR_DELETING_ALL_UNSUPPORTED("executor.deleting.all.unsupported"),
    EXECUTOR_DELETING_BY_ID_UNSUPPORTED("executor.deleting.byId.unsupported"),
    EXECUTOR_LOADING_ALL_UNSUPPORTED("executor.loading.all.unsupported"),
    EXECUTOR_LOADING_BY_ID_UNSUPPORTED("executor.loading.byId.unsupported"),
    EXECUTOR_PREDICATE_UNSUPPORTED("executor.predicate.unsupported"),
    EXECUTOR_SAVING_UNSUPPORTED("executor.saving.unsupported"),

    SERVICE_DELETING_ALL_UNSUPPORTED("service.deleting.all.unsupported"),
    SERVICE_DELETING_BY_ID_UNSUPPORTED("service.deleting.byId.unsupported"),
    SERVICE_LOADING_ALL_UNSUPPORTED("service.loading.all.unsupported"),
    SERVICE_LOADING_BY_ID_UNSUPPORTED("service.loading.byId.unsupported"),
    SERVICE_PREDICATE_UNSUPPORTED("service.predicate.unsupported"),
    SERVICE_SAVING_UNSUPPORTED("service.saving.unsupported");

    private final String value;

    private Code(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
