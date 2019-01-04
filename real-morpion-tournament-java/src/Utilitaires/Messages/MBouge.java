/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaires.Messages;

import Utilitaires.Enums.EAction;

/**
 *
 * @author creeps
 */
public class MBouge extends Message {
    private int deltaX;
    private int deltaY;
    
    public MBouge(EAction action, int deltaX, int deltaY) {
        super(action);
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    /**
     * @return the delta_x
     */
    public int getDeltaX() {
        return deltaX;
    }

    /**
     * @return the delta_y
     */
    public int getDeltaY() {
        return deltaY;
    }
}
