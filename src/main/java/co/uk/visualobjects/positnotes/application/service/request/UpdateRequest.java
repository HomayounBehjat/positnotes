package co.uk.visualobjects.positnotes.application.service.request;


import javax.validation.constraints.NotNull;

/**
 * This class acts as a wrapper for update parameters passed to the service layer.
 *
 * Controllers should create an instance of an UpdateRequest to invoke a update.
 * Enables a consistent implementation for access interceptors associated with the service layer
 */
public class UpdateRequest<T> extends ServiceRequest {

    @NotNull(message = "Null update in service request")
    private T update;

    public UpdateRequest(final T update) {
        this.update = update;
    }

    public T getUpdate() {
        return update;
    }

    public void setUpdate(final T update) {
        this.update = update;
    }
}
