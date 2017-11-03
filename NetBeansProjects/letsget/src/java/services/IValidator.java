/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author andrewjacobsen
 * This is used to create a boolean response as well as an error message if the response is a failure/false.
 * 
 * Implement this class by overriding the functions to whatever specific validation purpose is needed.
 */
public interface IValidator {
    public Boolean isValid(String[] information);
    public String errorMessage();
}
