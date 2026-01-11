/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java
 */
package id.ac.unpas.praktikumpemograman2.modul09;

import java.io.Serializable;

/**
 * @author Flavio Boniperti Oktaviola Zyoffy
 */
public class UserConfig implements Serializable {
    private String username;
    private int fontsize;
    
    public String getUsername() 
    {
        return username;
    }
    
    public void setUsername(String username) 
    {
        this.username = username;
    }
    
    public int getFontsize() 
    {
        return fontsize;
    }
    
    public void setFontsize(int fontsize)
    {
        this.fontsize = fontsize;
    }
}
