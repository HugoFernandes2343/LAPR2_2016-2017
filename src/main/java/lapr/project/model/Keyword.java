package lapr.project.model;

/**
 * Represents a keyword.
 *
 * @author by Nuno Bettencourt [nmb@isep.ipp.pt] on 29/05/16.
 */
public class Keyword {

	/**
	 * Keyword representation.
	 */
	private String value = "";

	/**
	 * Default empty constructor.
	 */
	public Keyword() {

	}

	/**
	 * Constructor for Keyword Class.
	 *
	 * @param keyword Keyword being used.
	 */
	public Keyword(String keyword) {
		this.value = keyword;
	}

	/**
	 * Obtain keyword value.
	 *
	 * @return Keyword Value
	 */
	private String getValue() {
		return this.value;
	}

	@Override
	public int hashCode() {
		return getValue().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Keyword)) {
			return false;
		}

		Keyword that = (Keyword) o;

		return getValue().equals(that.getValue());

	}
}
