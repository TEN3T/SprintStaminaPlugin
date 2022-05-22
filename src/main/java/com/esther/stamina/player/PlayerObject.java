package com.esther.stamina.player;

public class PlayerObject {

    private int STAMINA_DECREASE_VALUE = 5; //default value : 5
    private int STAMINA_INCREASE_VALUE_WORK = 5; //default value : 5
    private int STAMINA_INCREASE_VALUE_STOP = 10; //default value : 10
    private int STAMINA_MAX_VALUE = 100; //default value : 100, MIN value : 50
    private boolean STAMINA_STATE = true; //default value : true
    private boolean MOVE_STATE = false; //move : true, freeze : false


    public int getSTAMINA_DECREASE_VALUE() {
        return this.STAMINA_DECREASE_VALUE;
    }

    public void setSTAMINA_DECREASE_VALUE(int STAMINA_INCREASE_RATE) {
        this.STAMINA_DECREASE_VALUE = STAMINA_INCREASE_RATE;
    }

    public int getSTAMINA_INCREASE_VALUE_WORK() {
        return this.STAMINA_INCREASE_VALUE_WORK;
    }

    public void setSTAMINA_INCREASE_VALUE_WORK(int STAMINA_DECREASE_RATE_WORK) {
        this.STAMINA_INCREASE_VALUE_WORK = STAMINA_DECREASE_RATE_WORK;
    }

    public int getSTAMINA_INCREASE_VALUE_STOP() {
        return this.STAMINA_INCREASE_VALUE_STOP;
    }

    public void setSTAMINA_INCREASE_VALUE_STOP(int STAMINA_INCREASE_VALUE_STOP){
        this.STAMINA_INCREASE_VALUE_STOP = STAMINA_INCREASE_VALUE_STOP;
    }

    public boolean getMOVE_STATE(){
        return this.MOVE_STATE;
    }

    public void setMOVE_STATE(boolean MOVE_STATE){
        this.MOVE_STATE = MOVE_STATE;
    }


    public int getSTAMINA_MAX_VALUE() {
        return this.STAMINA_MAX_VALUE;
    }

    public void setSTAMINA_MAX_VALUE(int STAMINA_MAX_VALUE) {
        this.STAMINA_MAX_VALUE = STAMINA_MAX_VALUE;
    }

    public boolean getSTAMINA_STATE() {
        return this.STAMINA_STATE;
    }

    public void setSTAMINA_STATE(boolean STAMINA_STATE) {
        this.STAMINA_STATE = STAMINA_STATE;
    }
}
