package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.testsA.LoginTests;
import com.udacity.jwdnd.course1.cloudstorage.testsA.SignUpTests;
import com.udacity.jwdnd.course1.cloudstorage.testsB.CredentialCRUDTests;
import com.udacity.jwdnd.course1.cloudstorage.testsB.FileCRUDTests;
import com.udacity.jwdnd.course1.cloudstorage.testsB.NoteCRUDTests;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

/**
 * @author RafaelBizi
 * @created 07/07/2021 - 08:46
 * @project nanodegree_project_1
 */

@RunWith(JUnitPlatform.class)
@SelectPackages("com.udacity.jwdnd.course1.cloudstorage")
@SelectClasses({SignUpTests.class, LoginTests.class,
                FileCRUDTests.class, NoteCRUDTests.class,
                CredentialCRUDTests.class})
public class SuiteTests {
}
