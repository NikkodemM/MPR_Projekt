package pl.edu.pjatk.MPR_Projekt.exception;

public class CatAlreadyExistException extends RuntimeException {

    public CatAlreadyExistException() {
        super("Cat already exist");
    }
}
