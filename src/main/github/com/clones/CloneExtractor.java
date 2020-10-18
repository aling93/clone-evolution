package github.com.clones;

import lrg.dude.duplication.Duplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CloneExtractor {

    public List<Clone> extract(Duplication[] duplications) {
        if (duplications == null || duplications.length == 0)
            return new ArrayList<>();

        var map = buildDuplicationMap(duplications);
        return extractClonesFromMap(map);
    }

    private ArrayList<Clone> extractClonesFromMap(HashMap<String, List<Duplication>> map) {
        var clones = new ArrayList<Clone>();
        for (var file: map.keySet()) {
            var clone = new Clone(file, map.get(file));
            clones.add(clone);
        }

        return clones;
    }

    private HashMap<String, List<Duplication>> buildDuplicationMap(Duplication[] duplications) {
        var resultsMap = new HashMap<String, List<Duplication>>();

        for (var duplicate : duplications) {
            var referenceCode = duplicate.getReferenceCode();
            var duplicatedCode = duplicate.getDuplicateCode();

            var referenceFile = referenceCode.getEntityName();
            var duplicatedFile = duplicatedCode.getEntityName();

            var duplicationList = resultsMap.get(referenceFile);
            if (duplicationList == null)
                duplicationList = new ArrayList<>();
            duplicationList.add(duplicate);

            resultsMap.put(referenceFile, duplicationList);

            if (duplicate.isSelfDuplication())
                continue;

            // TODO: not really sure what this does...
            var secondaryFileDuplications = resultsMap.get(duplicatedFile);
            if (secondaryFileDuplications == null)
                secondaryFileDuplications = new ArrayList<>();

            secondaryFileDuplications.add(duplicate);
            resultsMap.put(duplicatedFile, secondaryFileDuplications);
        }

        return resultsMap;
    }
}
