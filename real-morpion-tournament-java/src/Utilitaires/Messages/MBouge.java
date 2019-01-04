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
    private int delta_x;
    private int delta_y;
    
    public MBouge(EAction action, int delta_x, int delta_y) {
        super(action);
        this.delta_x = delta_x;
        this.delta_y = delta_y;
    }

    /**
     * @return the delta_x
     */
    public int getDelta_x() {
        return delta_x;
    }

    /**
     * @return the delta_y
     */
    public int getDelta_y() {
        return delta_y;
    }
}
