package quickdt;

import java.io.Serializable;

public class Instance extends AbstractInstance implements Serializable {

    private static final long serialVersionUID = -932048363529904511L;

    private Instance() {

	}

    public static Instance create(final String classification, final Serializable... inputs) {
        return create(classification, 1.0, inputs);
    }

    public static Instance create(final String classification, final double weight, final Serializable... inputs) {
		final HashMapAttributes a = new HashMapAttributes();
		for (int x = 0; x < inputs.length; x += 2) {
			a.put((String) inputs[x], inputs[x + 1]);
		}
		return new Instance(a, classification);
	}

    public Instance(final Attributes attributes, final Serializable classification) {
        this(attributes, classification, 1.0);
    }

    public Instance(final Attributes attributes, final Serializable classification, final double weight) {
		this.attributes = attributes;
		this.classification = classification;
        this.weight = weight;
    }

    @Override
    public Attributes getAttributes() {
        return attributes;
    }

    @Override
    public Serializable getClassification() {
        return classification;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    private Attributes attributes;
	private Serializable classification;
    private double weight;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Instance instance = (Instance) o;

        if (Double.compare(instance.weight, weight) != 0) return false;
        if (!attributes.equals(instance.attributes)) return false;
        if (!classification.equals(instance.classification)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = attributes.hashCode();
        result = 31 * result + classification.hashCode();
        temp = Double.doubleToLongBits(weight);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
	public String toString() {
		final StringBuilder builder = new StringBuilder();
		builder.append("[attributes=");
		builder.append(attributes);
		builder.append(", classification=");
		builder.append(classification);
        if (weight != 1.0) {
            builder.append(", weight=");
            		builder.append(weight);
        }
		builder.append("]");
		return builder.toString();
	}
}
