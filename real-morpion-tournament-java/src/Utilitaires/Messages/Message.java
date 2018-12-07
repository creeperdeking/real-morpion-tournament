/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitaires.Messages;

import Utilitaires.Enums.EActions;

/**
 *
 * @author grosa
 */
public class Message {
    private EActions action;
    
    public Message(EActions action) {
        this.action = action;
    }
    
    public EActions getAction() {
        return this.action;
    }
}
