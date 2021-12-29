package com.sgu.chat.entity;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class Group {

    public String user_1;
    public String user_2;
    public boolean accept_pairing_1 = false;
    public boolean accept_pairing_2 = false;

    public Group(String user_1, String user_2) {
        this.user_1 = user_1;
        this.user_2 = user_2;
    }
    
    public void setAccept_pairing_1(String nickname, boolean accept_pairing) {
        if (this.user_1.equals(nickname)) {
            this.accept_pairing_1 = accept_pairing;
        }
    }
    
    public void setAccept_pairing_2(String nickname, boolean accept_pairing) {
        if (this.user_2.equals(nickname)) {
            this.accept_pairing_2 = accept_pairing;
        }
    }
    
    public int inGroup(String nickname){
        if (nickname == user_1 || nickname == user_2){
            return 1;
        }
        else{
            return 0;
        }
    }
}