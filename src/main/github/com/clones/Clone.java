package github.com.clones;

import lrg.dude.duplication.Duplication;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Clone {
    private final CloneType cloneType;
    private final String referenceFile;
    private final List<Duplication> duplications;

    public Clone(String referenceFile, List<Duplication> duplications) {
        this.referenceFile = referenceFile;
        this.duplications = duplications;
        this.cloneType = determineCloneType(duplications);
    }

    @Override
    public String toString() {
        StringBuilder cloneInfo = new StringBuilder("-------------------------------------\n" +
                "Clone " + referenceFile + " - " + cloneType +
                "\nSource files:\n");

        for (var sourceFile : getSourceFiles()) {
            cloneInfo.append(sourceFile.toString()).append("\n");
        }

        cloneInfo.append("-------------------------------------\n");
        return cloneInfo.toString();
    }

    public Set<CloneSourceFile> getSourceFiles() {
        var sourceFiles = new HashSet<CloneSourceFile>();

        for (var duplication : duplications) {
            var referenceFragment = duplication.getReferenceCode();
            var refSourceFile = new CloneSourceFile(duplication.getReferenceCode().getEntityName(), referenceFragment);

            if (!sourceFiles.contains(refSourceFile))
                sourceFiles.add(refSourceFile);

            sourceFiles.add(new CloneSourceFile(duplication.getDuplicateCode().getEntityName(),
                    duplication.getDuplicateCode()));
        }

        return sourceFiles;
    }

    private CloneType determineCloneType(List<Duplication> duplications) {
        for (var duplication : duplications) {
            if (!duplication.isSelfDuplication())
                return CloneType.External;
        }

        return CloneType.Internal;
    }

    public CloneType getCloneType() {
        return cloneType;
    }

    public String getReferenceFile() {
        return referenceFile;
    }

    public List<Duplication> getDuplications() {
        return duplications;
    }
}
