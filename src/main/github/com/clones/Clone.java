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

    public Set<String> getSourceFiles() {
        var sourceFiles = new HashSet<String>();
        sourceFiles.add(referenceFile);

        for (var duplication : duplications) {
            // TODO: not sure about this...
            var referenceFile = duplication.getReferenceCode().getEntityName();
            var duplicatedFile = duplication.getDuplicateCode().getEntityName();

            // TODO: contains can be replaced with add but let me first debug it
            //noinspection RedundantCollectionOperation
            if (!sourceFiles.contains(referenceFile))
                sourceFiles.add(referenceFile);

            //noinspection RedundantCollectionOperation
            if (!sourceFiles.contains(duplicatedFile))
                sourceFiles.add(duplicatedFile);
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
