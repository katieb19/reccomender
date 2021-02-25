package src;

/*
 * Interface for a "row" of a datatable, which is a collection of values for
 * a set of attributes.
 */
public interface IAttributeDatum {

  /**
   * Looks up the value associated with the named attribute.
   *
   * @param attributeName - String - wanted attribute
   */
  public Object getValueOf(String attributeName);
}
