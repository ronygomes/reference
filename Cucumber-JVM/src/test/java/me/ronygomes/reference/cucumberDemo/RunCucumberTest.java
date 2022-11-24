package me.ronygomes.reference.cucumberDemo;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.core.options.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = PLUGIN_PUBLISH_QUIET_PROPERTY_NAME , value = "true")
@ConfigurationParameter(key = SNIPPET_TYPE_PROPERTY_NAME , value = "camelcase")
@ConfigurationParameter(key = OBJECT_FACTORY_PROPERTY_NAME , value = "me.ronygomes.reference.cucumberDemo.CustomObjectFactory")
/* Report will be saved as HTML in target/cucumber.html */
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "html:target/cucumber.html")
/*
 * Older version had following annotation for configuration
 * @CucumberOptions(plugin = {"pretty", "summary", "html:target/cucumber"}, snippets = CAMELCASE)
 */

/*
 * Uncommenting following file will filter by tag
 * @ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "@slow and not @hook_enabled")
*/
public class RunCucumberTest {
}
