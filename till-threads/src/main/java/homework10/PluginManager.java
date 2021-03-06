package homework10;

import javax.annotation.Nullable;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author artem
 */
public class PluginManager {

    private final String pluginsRootDirectory;
    private static final String PLUGIN_DIRECTORY_TEMPLATE = "%s/%s/";

    public PluginManager(String pluginsRootDirectory) {
        this.pluginsRootDirectory = "file:///" + pluginsRootDirectory;
    }

    @Nullable
    public Plugin load(String pluginName, String pluginClassName) {
        // здесь скомпиленные классы плагина должны подгружаться в наше приложение
        String pluginDirectory = String.format(PLUGIN_DIRECTORY_TEMPLATE, pluginsRootDirectory, pluginName);
        File file = new File(pluginDirectory);
        try {
            URL url = file.toURI().toURL();
            URL[] urls = new URL[]{url};
            URLClassLoader urlClassLoader = new MyClassLoader(urls);
            Object plugin = urlClassLoader.loadClass(pluginClassName).newInstance();
            return (Plugin) plugin;
        } catch (MalformedURLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    class MyClassLoader extends URLClassLoader {

        public MyClassLoader(URL[] urls) {
            super(urls);
        }

        // переопределяем loadClass для загрузки нашего класса
        @Override
        public Class<?> loadClass(String name) throws ClassNotFoundException {
            try {
                return findClass(name);
            } catch (ClassNotFoundException e) {
                return super.loadClass(name);
            }
        }
    }

}
