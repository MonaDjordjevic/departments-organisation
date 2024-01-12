/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nst.springboot.restexample01.exception;

/**
 *
 * @author student2
 */
public class MemberAlreadyExistException extends RuntimeException{

    public MemberAlreadyExistException(String message) {
        super(message);
    }
}
