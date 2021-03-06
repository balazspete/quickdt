package quickdt;

import java.io.PrintStream;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: janie
 * Date: 6/26/13
 * Time: 3:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class Tree implements PredictiveModel {
    static final long serialVersionUID = 56394564395635672L;

    public final Node node;

    protected Tree(Node tree) {
        this.node = tree;
    }

    @Override
    public double getProbability(Attributes attributes, Serializable classification) {
        Leaf leaf = node.getLeaf(attributes);
        return leaf.getProbability(classification);
    }

    @Override
    public void dump(PrintStream printStream) {
        node.dump(printStream);
    }

    @Override
    public Serializable getClassificationByMaxProb(Attributes attributes) {
        Leaf leaf = node.getLeaf(attributes);
        return leaf.getBestClassification();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Tree tree = (Tree) o;

        if (!node.equals(tree.node)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return node.hashCode();
    }
}
