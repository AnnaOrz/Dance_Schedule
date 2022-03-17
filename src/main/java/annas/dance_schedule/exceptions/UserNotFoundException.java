package annas.dance_schedule.exceptions;


public class UserNotFoundException extends Exception {


    public UserNotFoundException( ) {
        super("Nie znaleziono użytkownika");
    }
}
