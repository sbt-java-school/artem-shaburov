package homework10;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author artem
 */
public class PluginManagerTests {

    private static final String PLUGINS_ROOT_DIR_NAME = "src/main/resources/plugins/";

    @Test
    public void classLoaderTest() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        PluginManager pluginManager = new PluginManager(PLUGINS_ROOT_DIR_NAME);
        Plugin plugin = pluginManager.load("homework10", "PluginImpl");
        if (plugin != null) {
            plugin.doSomething();
        }
        Assert.assertNotNull(plugin);
    }

}
