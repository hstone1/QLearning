import java.util.Arrays;

public class Deck implements State{
  // 0 - copper
  // 1 - silver 
  // 2 - gold
  // 3 - estate
  // 4 - dutchy
  // 5 - province
  int[] deck;
  int deckSize = 10;
  public Deck(){
    deck = new int[]{7, 0, 0, 3, 0, 0};
  }
  
  public Deck(Deck d, int cardId){
    deck = new int[6];
    for(int i = 0;i < 6;i++){
      deck[i] = d.deck[i];
    }
    deck[cardId]++;
    deckSize = d.deckSize + 1;
  }
  @Override
  public double[] state() {
    double[] state = new double[6];
    for(int i = 0;i < 6;i++){
      state[i] = deck[i] / 10.0;
    }
    return state;
  }
  
  public int hand(){
    int gold = 0;
    for(int t = 0;t < 5;t++){
      int cardNum = (int) (Math.random() * deckSize);
      for(int i = 0;i < 3;i++){
        if (cardNum < deck[i]) {
          gold += (i + 1);
          break;
        } else {
          cardNum -= deck[i];
        }
      }
    }
   
    return gold;
  }
  
  public int vp(){
    return deck[3] * 1 + deck[4] * 3 + deck[5] * 6;
  }
  
  @Override
  public boolean equals(Object o){
    if(o instanceof Deck){
      Deck d = (Deck) o;
      for(int i = 0;i < 6;i++){
        if(d.deck[i] != deck[i]){
          return false;
        }
      }
      return true;
    }
    return false;
  }
  
  @Override
  public int hashCode(){
    return Arrays.hashCode(deck);
  }
  
  public static String cardName(int id) {
    switch(id) {
      case 0: return "Copper";
      case 1: return "Silver";
      case 2: return "Gold";
      case 3: return "Estate";
      case 4: return "Dutchy";
      case 5: return "Province";
      default: return "Not A Card!!!";
    }
  }
}
