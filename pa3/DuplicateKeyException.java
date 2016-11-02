class DuplicateKeyException extends RuntimeException {
  //Parameterless Constructor
  public DuplicateKeyException() {}

  //Constructor that accepts a message
  public DuplicateKeyException(String message)
  {
    super(message);
  }
}
