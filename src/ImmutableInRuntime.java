public class ImmutableInRuntime {

  private String field;

  private volatile boolean immutable = false;

  public void makeImmutable() {
    immutable = true;
  }

  public String getField() {
    if (!immutable) {
      synchronized (this) {
        return field;
      }
    }
    return field;
  }

  synchronized public void setField(String field) throws MakeChangeToImmutableException {
    checkMutable();
    this.field = field;
  }

  private void checkMutable() throws MakeChangeToImmutableException {
    if (immutable) {
      throw new MakeChangeToImmutableException();
    }
  }

  public class MakeChangeToImmutableException extends Exception {

  }

}
