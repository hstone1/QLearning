import java.util.Arrays;

public class Test {
  public static void main(String[] args) {
    NeuralNetwork nn = NeuralNetworkIO.load("best.nn");
    System.out.println(Arrays.toString(nn.run(new double[]{7, 0, 0, 3, 0, 0})));
  }
}
