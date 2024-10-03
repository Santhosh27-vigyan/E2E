package Listener;
import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import finalCall.AddVasFinalCalls;
import finalCall.RemoveVasFinalCalls;
import finalCall.RevokeSuspensionFinalCalls;
import finalCall.SuspensionFinalCalls;
import finalCall.onboardingFinalCalls;
import resources.base;
public class testListener implements ITestListener {
	base b = new base();
	public static String className;
	public String Type;
	@Override
	public void onTestFailure(ITestResult result) {
//		className =result.getTestClass().getName();
//		try {
//			b.getScreenshot(result.getName()+" failed",GetType());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		ITestContext context = result.getTestContext();
        context.setAttribute("FailedTests", true);
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestContext context = result.getTestContext();
        context.setAttribute("SkippedTests", true);
	}
	@Override
	public void onTestSuccess(ITestResult result) {
//		className =result.getTestClass().getName();
//		System.out.println(GetType());
//		try {
//			b.getScreenshot(result.getName()+" success",GetType());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
	
//	public String GetType()
//	{
//		if(className.contains("onboarding"))
//		{
//			Type= onboardingFinalCalls.Type;
//		}
//		else if(className.contains("AddVas"))
//		{
//			Type= AddVasFinalCalls.type;
//		}
//		else if(className.contains("RemoveVas"))
//		{
//			Type= RemoveVasFinalCalls.type;
//		}
//		else if(className.contains("Suspension"))
//		{
//			Type= SuspensionFinalCalls.type;
//		}
//		else if(className.contains("Revoke"))
//		{
//			Type= RevokeSuspensionFinalCalls.type;
//		}
//		return Type;
//	}
}
