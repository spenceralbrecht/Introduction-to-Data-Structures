//-----------------------------------------------------------------------------
// Spencer Albrecht
// salbrech
// QueueEmptyException.java
// PA4
// Provides constructors for custom exception class
//-----------------------------------------------------------------------------

class QueueEmptyException extends RuntimeException {
  // constructor without parameters
  public QueueEmptyException() {
  }
  // constructor with message
  public QueueEmptyException(String message) {
    super(message);
  }

}
