package co.taqat.call.test;

import junit.framework.Assert;

import co.taqat.call.CallActivity;
import co.taqat.call.LinphoneActivity;
import org.linphone.core.LinphoneCall;

import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * @author Sylvain Berfini
 */
public class History extends SampleTest {
	
	@SmallTest
	@MediumTest
	@LargeTest
	public void testACheckForTestCallInHistory() {		
		goToHistory();

		Assert.assertTrue(solo.searchText(aContext.getString(co.taqat.call.R.string.today)));
		Assert.assertTrue(solo.searchText(iContext.getString(co.taqat.call.test.R.string.account_test_calls_login)));
	}
	
	@MediumTest
	@LargeTest
	public void testBFilterMissedCalls() {		
		goToHistory();

		solo.clickOnView(solo.getView(co.taqat.call.R.id.missed_calls));
		Assert.assertTrue(solo.searchText(aContext.getString(co.taqat.call.R.string.no_missed_call_history)));
	}
	
	public void testCCallBackFromHistory() {
		goToHistory();
		
		solo.clickOnText(iContext.getString(co.taqat.call.test.R.string.account_test_calls_login));
		
		solo.waitForActivity("InCallActivity", 5000);
		solo.assertCurrentActivity("Expected InCall Activity", CallActivity.class);
		
		solo.sleep(2000);
		Assert.assertEquals(1, LinphoneTestManager.getLc().getCallsNb());
		waitForCallState(LinphoneTestManager.getLc().getCalls()[0],LinphoneCall.State.StreamsRunning);
		
		solo.clickOnView(solo.getView(co.taqat.call.R.id.hang_up));
		solo.waitForActivity("LinphoneActivity", 5000);
		solo.assertCurrentActivity("Expected Linphone Activity", LinphoneActivity.class);
	}
	
	@MediumTest
	@LargeTest
	public void testDDeleteOne() {		
		goToHistory();

		solo.clickOnView(solo.getView(co.taqat.call.R.id.edit));
		solo.sleep(500);
		solo.clickOnCheckBox(1);
		solo.clickOnView(solo.getView(co.taqat.call.R.id.delete));
		solo.sleep(500);
		solo.clickOnView(solo.getView(co.taqat.call.R.id.delete_button));
	}

	@SmallTest
	@MediumTest
	@LargeTest
	public void testEDeleteAll() {		
		goToHistory();

		solo.clickOnView(solo.getView(co.taqat.call.R.id.edit));
		solo.clickOnView(solo.getView(co.taqat.call.R.id.select_all));
		solo.clickOnView(solo.getView(co.taqat.call.R.id.delete));
		solo.sleep(500);
		solo.clickOnView(solo.getView(co.taqat.call.R.id.delete_button));

		Assert.assertTrue(solo.searchText(aContext.getString(co.taqat.call.R.string.no_call_history)));
	}
	
	private void goToHistory() {
		solo.waitForActivity("LinphoneActivity", 2000);
		solo.assertCurrentActivity("Expected Linphone Activity", LinphoneActivity.class);
		
		solo.clickOnView(solo.getView(co.taqat.call.R.id.history));
	}
}
