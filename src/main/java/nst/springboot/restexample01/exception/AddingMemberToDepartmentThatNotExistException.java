package nst.springboot.restexample01.exception;

public class AddingMemberToDepartmentThatNotExistException extends RuntimeException{

    public AddingMemberToDepartmentThatNotExistException(String message) {
        super(message);
    }
}