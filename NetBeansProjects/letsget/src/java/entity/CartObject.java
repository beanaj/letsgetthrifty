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
public class CartObject {
    public String isbn;
    public int quantity;
    
    public boolean isEqual(CartObject book){
        if(this.isbn.equals(book.isbn)){
            return true;
        }else{
            return false;
        }
    }
}
