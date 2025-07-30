package com.tmb.listeners;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import com.tmb.constants.FrameworkConstants;
import com.tmb.utils.DataProviderUtils;
import com.tmb.utils.ExcelUtils;

/**
 * Implements {@link org.testng.IAnnotationTransformer} to leverage certain
 * functionality like updating the annotations of test methods at runtime.
 * 
 * <pre>
 * Please make sure to add the listener details in the testng.xml file
 * </pre>
 * 
 * <pre>
 * <b>
 * <a href=
"https://www.youtube.com/channel/UC6PTXUHb6j4Oxf0ccdRI11A">Testing Mini Bytes Youtube channel</a>
 * </b>
 * </pre>
 * 
 * Jan 21, 2021
 * 
 * @author Amuthan Sakthivel
 * @version 1.0
 * @since 1.0
 * @see com.tmb.utils.DataProviderUtils
 */
public class AnnotationTransformer implements IAnnotationTransformer {

	/**
	 * Helps in setting the dataprovider, dataprovider class and retry analyser
	 * annotation to all the test methods at run time.
	 */
	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		annotation.setDataProvider("getData");
		annotation.setDataProviderClass(DataProviderUtils.class);
		annotation.setRetryAnalyzer(RetryFailedTests.class);

		List<Map<String, String>> list = ExcelUtils.getTestDetails(FrameworkConstants.getRunmangerDatasheet());
		for (int i = 0; i < list.size(); i++) {
			String[] groupArr = null;
			if (testMethod.getName().equalsIgnoreCase(list.get(i).get("testname"))) {
				if (list.get(i).get("execute").equalsIgnoreCase("yes")) {
					annotation.setDescription(list.get(i).get("testdescription"));
					String countStr = list.get(i).get("count");
					annotation.setInvocationCount(
							countStr == null || countStr.isEmpty() ? 1 : Integer.parseInt(countStr));
					if (!list.get(i).get("groups").isEmpty()) {
						if (list.get(i).get("groups").contains(",")) {
							groupArr = list.get(i).get("groups").split(",");
						} else {
							groupArr = new String[] { list.get(i).get("groups") };
						}
						annotation.setGroups(groupArr);
					}

				}
				break;
			}

		}
	}

}
