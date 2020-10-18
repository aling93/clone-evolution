package github.com.clones;

import lrg.dude.duplication.Parameters;

import java.util.ArrayList;
import java.util.Arrays;

public class DudeParameters extends Parameters {
    private final String projectFolderPath;
    private final ArrayList<String> fileExtensionsToAnalyze;

    public DudeParameters(
            String projectFolderPath,
            ArrayList<String> fileExtensionsToAnalyze,
            int minLength,
            int maxLineBias,
            int minExactChunk,
            boolean considerComments) {
        super(minLength, maxLineBias, minExactChunk, considerComments);

        this.fileExtensionsToAnalyze = getFileExtensionsToAnalyze(fileExtensionsToAnalyze);
        this.projectFolderPath = projectFolderPath;
    }

    private ArrayList<String> getFileExtensionsToAnalyze(ArrayList<String> fileExtensionsToAnalyze) {
        if (fileExtensionsToAnalyze == null || fileExtensionsToAnalyze.size() == 0) {
            return new ArrayList<>(Arrays.asList("java",
                    "js",
                    "php",
                    "c",
                    "cc",
                    "cpp",
                    "h",
                    "hpp",
                    "cs",
                    "sql",
                    "lua",
                    "groovy"));
        }

        return fileExtensionsToAnalyze;
    }

    public String getProjectFolderPath() {
        return projectFolderPath;
    }

    public ArrayList<String> getFileExtensionsToAnalyze() {
        return fileExtensionsToAnalyze;
    }
}
