class KeyNotFoundException extends RuntimeException {
  //Parameterless Constructor
  public KeyNotFoundException() {}

  //Constructor that accepts a message
  public KeyNotFoundException(String message)
  {
    super(message);
  }
}
