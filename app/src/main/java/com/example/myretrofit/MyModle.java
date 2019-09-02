package com.example.myretrofit;

public class MyModle {
    public String TestName;
    public String TestData;

    public MyModle(String testName, String testData) {
        TestName = testName;
        TestData = testData;
    }

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String testName) {
        TestName = testName;
    }

    public String getTestData() {
        return TestData;
    }

    public void setTestData(String testData) {
        TestData = testData;
    }
}
