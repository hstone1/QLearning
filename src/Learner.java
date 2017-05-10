import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by henry on 4/30/2017.
 */
public class Learner<S extends State> {
  private LearnableSystem<S> system;
  NeuralNetwork nn = new NeuralNetwork(6, 8, 8, 6);

  public Learner(LearnableSystem<S> system) {
    this.system = system;
  }

  public S playBest (S startingState) {
    
    //System.out.println(Arrays.toString(startingState.state()));
    Action<S>[] actions = system.getActions(startingState);
    double[] output = nn.run(startingState.state());
    //System.out.println(Arrays.toString(output));
    double max = -100000000;
    int index = -1;
    for (int i = 0; i < output.length; i++) {
      if (actions[i] != null && output[i] > max) {
        max = output[i];
        index = i;
      }
    }

    if(index != -1) {
      //System.out.println(Deck.cardName(index));
      //playBest(actions.get(0).getEnd());
      return actions[index].getEnd();
    }
    return null;
  }

  public void playLearn(S startingState, int itterations, double learningRate){
    for(int i = 0;i < itterations;i++){
      learn(startingState, learningRate);
      if(i % 5000 == 0){
        //System.out.println(i);
      }
    }
  }

  private void learn(S startingState, double learningRate) {
    Action<S>[] actions = system.getActions(startingState);

    boolean empty = true;
    for (int i = 0; i < actions.length; i++) {
      if (actions[i] != null) {
        empty = false;
        break;
      }
    }

    if (!empty) {
      Action<S> todo = null;
      int rand = 0;
      while (todo == null) {
        rand = (int)(Math.random() * actions.length);
        todo = actions[rand];
      }

      double reward = system.doAction(todo);

      double[] output = nn.run(todo.getEnd().state());
      Action<S>[] arr = system.getActions(todo.getEnd());
      double max = -(1 << 30);
      for(int i = 0;i < arr.length;i++){
        if (arr[i] != null) {
          if(output[i] > max){
            max = output[i];
          }
        }
      }
      if (max == -(1 << 30)) {
        max = 0;
      }
      
      //System.out.println(max);

      double newVal = reward + max;
      double[] target = nn.run(todo.getStart().state());
      target[rand] = newVal;
      nn.backprop(target, learningRate);

      learn(todo.getEnd(), learningRate);
    }
  }

//  public void print(){
//    for(Map.Entry<Action<S>, Double> weight : actionValues.entrySet()){
//      System.out.println(weight.getKey().getStart() + "      " + weight.getKey()
//          .getEnd() + "     " + weight.getValue());
//    }
//  }
//  double getValue(Action<S> a) {
//    if (actionValues.containsKey(a)) {
//      return actionValues.get(a);
//    } else {
//      actionValues.put(a, 0.0);
//      return 0;
//    }
//  }

  private <T> List<T> removeNull(T[] t){
    List<T> l = new LinkedList<>();
    for (int i = 0;i < t.length;i++) {
      if (t[i] != null) {
        l.add(t[i]);
      }
    }
    return l;
  }
}
