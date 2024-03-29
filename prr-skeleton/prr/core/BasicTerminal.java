package prr.core;

import java.lang.Math; 

public class BasicTerminal extends Terminal{


   /**
     * Constructor
     */ 

    public BasicTerminal(String id, String clientId){ 
        super(id, clientId,"BASIC");
    }

    /**
      * @return the basic terminal in format of string
      */


    @Override
    public String toString(){
        if(this.getFriends().isEmpty()){
            return("BASIC"+"|"+this.getTerminalID()+"|"+this.getTerminalClientID()+"|"+this.getTerminalMode().name()+"|"+Long.toString(Math.round(this.getTerminalPayments()))
            +"|"+Long.toString(Math.round(this.getTerminalDebts())));
        }
        else{
            return("BASIC"+"|"+this.getTerminalID()+"|"+this.getTerminalClientID()+"|"+this.getTerminalMode().name()+"|"+Long.toString(Math.round(this.getTerminalPayments()))
            +"|"+Long.toString(Math.round(this.getTerminalDebts()))+"|"+
            this.getSortedFriends().toString().substring(1, getSortedFriends().toString().length()-1).replace(" ", "")).toString();
        }
    }
}