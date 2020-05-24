package hello;

public class Greeter {

    Counter counter = new Counter();

    public String sayHello(String name) {
        int length;

        try {
            length = counter.count(name);
        } catch (Exception x) {
            return "Oops";
        }

        if (length == 1) {
            return "I've never met anyone like you, " + name + ", welcome!";
        } else if (length > 1 && length < 20) {
            return String.format("Welcome, %s! Your name length is %d letters.", name, length);
        } else {
            return "That name seems to be invalid!";
        }

    }
}