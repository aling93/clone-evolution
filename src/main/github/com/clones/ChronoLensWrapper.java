package github.com.clones;

import org.apache.commons.lang3.StringUtils;
import org.chronolens.core.repository.InteractiveRepository;
import org.chronolens.core.repository.Repository;

import java.io.File;

public class ChronoLensWrapper {
    private String sourceStorageDir, repositoryPath;

    public ChronoLensWrapper(String sourceStorageDir, String repositoryPath)
    {
        if (StringUtils.isEmpty(sourceStorageDir))
            sourceStorageDir = ".clone-evolution";

        this.sourceStorageDir = sourceStorageDir;
        this.repositoryPath = repositoryPath;
    }

    public void DoSomething() {
        var dir = new File(repositoryPath);
        Repository repository = InteractiveRepository.connect(dir);
        var headId = repository.getHeadId();
        System.out.println(headId);
    }

}
