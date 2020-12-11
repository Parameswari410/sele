//MasterParam
package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.TestBase;

public class Tests extends TestBase{
	
	@Test
	public static void test() {
		
		boolean status = true;
		Assert.assertEquals(false, status);
	}

}
