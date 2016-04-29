package org.springframework.data.neo4j.integration.conversion.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Use test suite to ensure order in which test classes are run
 */
@RunWith(Suite.class)
@Suite.SuiteClasses(value = {
        // in real world FirstTest would be two different tests extending same base class
        FirstTest.class,
        // has different spring configuration - will initialise 2nd Driver, leads to closing first driver instance
        // this test itself runs fine
        SecondTest.class,
        // this test fails
        FirstTest.class
})
public class TestSuite {
}
