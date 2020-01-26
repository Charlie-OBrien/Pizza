/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojos;

/**
 *
 * @author charl
 */
public class Size {
   private String sizeID;
    private String name;    //Small Medium Large
    


    public Size() {
        //keep as is
    }

    public Size(String sizeID, String name) {
        this.sizeID = sizeID;
        this.name = name;
    }

    public String getSizeID() {
        return sizeID;
    }

    public void setSizeID(String sizeID) {
        this.sizeID = sizeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    } 
}
