import akka.actor.{Actor,Props,ActorSystem}
// TODO
//
// Make this an actor and write a message handler for at least the
// set method.
//
object Coordinator {
  def init(im: Image, of: String) = {
    image = im
    outfile = of
    waiting = im.width * im.height
  }

  // Number of pixels we're waiting for to be set.
  var waiting = 0
  var outfile: String = null
  var image: Image = null

  // TODO: make set a message
  def set(x: Int, y: Int, c: Colour) = {
    image(x, y) = c
    waiting -= 1
    println("set x:" + x + " y:" +y  + " color:" + c + " waiting:" + waiting)
    if (waiting == 0) {

      //no more pixels left to process?
      print
    }
  }

  def print = {
    println("print here")
    assert(waiting == 0)
    image.print(outfile)
  }
}

class Coordinator() extends Actor{
  //Initialise when the actor is created

  def receive = {
    case (image: Image, outfile: String) => Coordinator.init(image,outfile)
    case (x: Int, y: Int, colour: Colour) => Coordinator.set(x,y,colour)
  }
}
