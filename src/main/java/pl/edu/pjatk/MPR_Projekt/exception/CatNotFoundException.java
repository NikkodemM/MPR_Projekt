package pl.edu.pjatk.MPR_Projekt.exception;

public class CatNotFoundException extends RuntimeException {

    public CatNotFoundException(Long id) {
        super("Cat not found");
    }
}