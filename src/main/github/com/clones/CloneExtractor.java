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
            var referenceFile = referenceCode.getEntityName();

            var duplicationList = resultsMap.get(referenceFile);
            if (duplicationList == null) {
                if (isAlreadyDuplicated(duplicate, resultsMap))
                    continue;
                duplicationList = new ArrayList<>();
            }

            duplicationList.add(duplicate);
            resultsMap.put(referenceFile, duplicationList);
        }

        return resultsMap;
    }

    private boolean isAlreadyDuplicated(Duplication duplicate, HashMap<String, List<Duplication>> resultsMap) {
        var refCodeFound = false;
        var dupCodeFound = false;

        for (var file : resultsMap.keySet()) {
            var duplications = resultsMap.get(file);
            for (var duplication : duplications) {
                var refComparer = new CodeFragmentComparer(duplication.getDuplicateCode(), duplicate.getReferenceCode());
                if (refComparer.areEqual())
                    refCodeFound = true;

                var dupComparer =  new CodeFragmentComparer(duplication.getDuplicateCode(), duplicate.getDuplicateCode());
                if (dupComparer.areEqual())
                    dupCodeFound = true;

                if (refCodeFound && dupCodeFound)
                    return true;
            }
        }

        return false;
    }
}
