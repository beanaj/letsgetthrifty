/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author andrewjacobsen
 */
public interface UserI {
    public void User(String[] info, String accountID, String code, String payment, String hash);
    public void User(String accountID);
    public void register();
}
