// Spencer Albrecht
// salbrech
// PA3
// KeyNotFoundException.java
// Custom class for my custom exception that comes with a
// default no parameter constructor as well as one that can
// display a message
class KeyNotFoundException extends RuntimeException {
  //Parameterless Constructor
  public KeyNotFoundException() {}

  //Constructor that accepts a message
  public KeyNotFoundException(String message)
  {
    super(message);
  }
}
