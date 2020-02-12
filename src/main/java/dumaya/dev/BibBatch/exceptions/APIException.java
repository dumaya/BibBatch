package dumaya.dev.BibBatch.exceptions;


public class APIException extends RuntimeException {

    public APIException(String operation, String message, String stack) {
        super("Erreur de communication avec l'API. Appel service : " + operation + " Message :" + message + " StackTrace : " + stack);
    }
}
