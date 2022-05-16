import annotation.Inject;
import utility.CollectionManager;
import utility.ConsoleManager;
import utility.FileManager;

import java.io.IOException;

public class Session {
    @Inject
    private CollectionManager collectionManager;
    @Inject
    private FileManager fileManager;

    public void startUp() throws IOException {
        fileManager.setEnv("LABA");
        collectionManager.loadCollection();
        while (true) {
            ConsoleManager.interactiveMode();
        }
    }
}
