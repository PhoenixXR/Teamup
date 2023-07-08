package com.example.Teamup_web.entity;

public class Info {
    private String kook;
    private String lv;
    private String num;
    private String t;

    public String getKook(){
        return kook;
    }

    public void setKook(String kook) {
        this.kook = kook;
    }

    public String getLv() {
        return lv;
    }

    public void setLv(String lv) {
        this.lv = lv;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    /**
     * 可以传达android端后拆开增加固定符号
     * @return
     */
    public String getAll(){
        return t+"|"+kook+"|"+lv+"|"+num;
    }
}
