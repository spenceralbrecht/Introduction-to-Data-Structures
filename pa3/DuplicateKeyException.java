// Spencer Albrecht
// salbrech
// PA3
// DuplicateKeyException.java
// Custom class for my custom exception that comes with a
// default no parameter constructor as well as one that can
// display a message
class DuplicateKeyException extends RuntimeException {
  //Parameterless Constructor
  public DuplicateKeyException() {}

  //Constructor that accepts a message
  public DuplicateKeyException(String message)
  {
    super(message);
  }
}
