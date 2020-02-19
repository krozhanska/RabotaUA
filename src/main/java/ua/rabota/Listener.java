package ua.rabota;

import org.testng.*;

public class Listener implements ITestListener, ISuiteListener {

    @Override

    public void onStart(ISuite arg0) {

        Reporter.log("Start execute Suite: " + arg0.getName(), true);

    }

    @Override

    public void onFinish(ISuite arg0) {

        Reporter.log("End execute Suite: " + arg0.getName(), true);

    }


    public void onStart(ITestContext arg0) {

        Reporter.log("\n Start Tests: " + arg0.getName(), true);

    }


    public void onFinish(ITestContext arg0) {

        Reporter.log(" End Tests: " + arg0.getName(), true);

    }


    public void onTestSuccess(ITestResult arg0) {

        printTestResults(arg0);

    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

    }


    public void onTestFailure(ITestResult arg0) {

        printTestResults(arg0);

    }


    public void onTestStart(ITestResult arg0) {
        Reporter.log(" Start simple test: " + arg0.getName(), true);
    }


    public void onTestSkipped(ITestResult arg0) {

        printTestResults(arg0);

    }


    private void printTestResults(ITestResult result) {

       // Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);

        if (result.getParameters().length != 0) {

            String params = new String();

            for (Object parameter : result.getParameters()) {

                params += parameter.toString() + ",";

            }

            Reporter.log("Test Method had the following parameters : " + params, true);

        }

        String status = null;

        switch (result.getStatus()) {

            case ITestResult.SUCCESS:

                status = "Pass";

                break;

            case ITestResult.FAILURE:

                status = "Failed";

                break;

            case ITestResult.SKIP:

                status = "Skipped";

        }

        Reporter.log("Test Status: " + status, true);

    }



}
