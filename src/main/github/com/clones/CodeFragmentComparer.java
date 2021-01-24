package github.com.clones;

import lrg.dude.duplication.CodeFragment;

public class CodeFragmentComparer {
    private CodeFragment sourceCodeFragment;
    private CodeFragment destinationCodeFragment;

    public CodeFragmentComparer(CodeFragment sourceCode, CodeFragment destinationCode)
    {
        sourceCodeFragment = sourceCode;
        destinationCodeFragment = destinationCode;
    }

    public boolean areEqual()
    {
        return sourceCodeFragment.getEntityName().equals(destinationCodeFragment.getEntityName())
                && sourceCodeFragment.getBeginLine() == destinationCodeFragment.getBeginLine()
                && sourceCodeFragment.getEndLine() == destinationCodeFragment.getEndLine();
    }
}
