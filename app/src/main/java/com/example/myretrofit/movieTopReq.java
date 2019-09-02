package com.example.myretrofit;

public class movieTopReq {
    public String resultcode;
    public String reason;
    public Object result;

    public movieTopReq(String resultcode, String reason, Object result) {
        this.resultcode = resultcode;
        this.reason = reason;
        this.result = result;
    }

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public Object getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "movieTopReq{" +
                "resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
