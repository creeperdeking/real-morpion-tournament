/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaires.Messages;

import Utilitaires.Enums.EAction;

/**
 *
 * @author grosa
 */
public class Message {
    private EAction action;
    
    public Message(EAction action) {
        this.action = action;
    }
    
    public EAction getAction() {
        return this.action;
    }
}
