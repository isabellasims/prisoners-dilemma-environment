import java.util.Random;
import java.util.*;

public class Prison {

  //True : the prisoner betrayed.
  //False : the prisoner stayed silent.
  final static boolean BETRAY = true;
  final static boolean SILENT = false;

  /**
   * This is the main method which prints out the final results of testing
   * @param args unused
   * @return no return value
   */  public static void main(String[] args){

    String winner;
    String stratA;
    String stratB;
    String[] strats = {"alternate","always betray","tit for tat","hold grudge","one more betray"}; // basically assigning each function to a string in a list, this makes iterations easier. Functions are assigned a string value inside a while loop
    List<String> winners = new ArrayList<>();

    // iterating through the list for all possible combinations of strategies for both prisoner A and prisoner B
    // nested for loop: outer for loop (i) determines strategy A, inner for loop (j) determines strategy B
    for (int i =0; i < strats.length ; i++){
      for (int j = 0; j < strats.length ; j++){
        stratA = strats[i];
        stratB = strats[j];
        winner = playgame(stratA,stratB); // returns winning strategy
        System.out.println(stratA + " vs. " + stratB + " : " + winner); // print the winning strategy of round
        winners.add(winner); // append to a list of winners
      }

   }

    System.out.println(" ");
    System.out.println("-------------");
    System.out.println("ROUND RESULTS");
    System.out.println("-------------");

    // used a built in java method that counts the frequency of a list for you
    // this is checking how many times each string appears in the list of winners 
    System.out.println(" ");
    System.out.println("-------------");
    System.out.println("FINAL RESULTS");
    System.out.println("-------------");
    System.out.println("alternate wins: " + Collections.frequency(winners, "alternate"));
    System.out.println("always betray wins: " + Collections.frequency(winners, "always betray"));
    System.out.println("tit for tat wins: " + Collections.frequency(winners, "tit for tat"));
    System.out.println("hold grudge wins: " + Collections.frequency(winners, "hold grudge"));
    System.out.println("one more betray wins: " + Collections.frequency(winners, "one more betray"));


} // end main method



/**
 * This method runs 100 iterations of each strategy pairing and returns the string of winner
 * when play game is called in the main method it returns the winning strategy, which is then printed by the main method
 * @param String StratA strategy of Prisoner A
 * @param String StratB strategy of Prisoner B
 * @return String returns winning strategy
 */
  public static String playgame(String stratA, String stratB){
    int count = 0;
    int betraycountA = 0;
    int betraycountB = 0;
    int totalscoreA = 0;
    int totalscoreB = 0;
    boolean lastChoicePrisonerA = BETRAY; //Set initially to BETRAYED for testing
    boolean lastChoicePrisonerB = BETRAY;
    
  
    while (count < 101){
    
      count ++;


      // Functions assigned  to a string value 
      // Iterations assign each strategy to choices of A or B

      // ASSIGNING A STRATEGIES

      if (stratA.equals("alternate")){
        lastChoicePrisonerA = Alternate(count);
        }
      else if (stratA.equals("always betray")){
        lastChoicePrisonerA = alwaysBetray();
        }
      else if (stratA.equals("tit for tat")){
        lastChoicePrisonerA = titfortat(lastChoicePrisonerB);
        }
      else if (stratA.equals("hold grudge")){
        lastChoicePrisonerA = holdGrudge(betraycountB); 
        }
      else if (stratA.equals("one more betray")){
      lastChoicePrisonerA = OnemoreBetray(betraycountB, betraycountA);
        }


      // ASSIGNING B STRATEGIES

      if (stratB.equals("alternate")){
        lastChoicePrisonerB = Alternate(count);
      }
      else if (stratB.equals("always betray")){
        lastChoicePrisonerB = alwaysBetray();
      }
      else if (stratB.equals("tit for tat")){
        lastChoicePrisonerB = titfortat(lastChoicePrisonerA);
      }
      else if (stratB.equals("hold grudge")){
        lastChoicePrisonerB = holdGrudge(betraycountA); 
      }
      else if (stratB.equals("one more betray")){
        lastChoicePrisonerB = OnemoreBetray(betraycountA, betraycountB);
      }



      if (countbetray(lastChoicePrisonerA) == BETRAY){
        betraycountA ++;
      }
      else if (countbetray(lastChoicePrisonerB) == BETRAY){
        betraycountB ++;
      }
        
      totalscoreB += scorePrisonerB(lastChoicePrisonerA, lastChoicePrisonerB);
      totalscoreA += scorePrisonerA(lastChoicePrisonerA, lastChoicePrisonerB);
    
} // end while loop
  

    // DETERMINE WINNING STRATEGY

    if (totalscoreB > totalscoreA){
      return stratA;
    }
    else if (totalscoreB < totalscoreA){
      return stratB;
    }
   else {
    return "tie";
    }
} 


// STRATEGY METHODS

/**
 * The method always betrays on every iteration
 * @return boolean prisoner choice
 */
  public static boolean alwaysBetray(){
      return BETRAY;
  }


/**
 * this method alternate answers each turn
 * @param int count is used to alternate between silent and betray every iteration
 * @return boolean prisoner choice
 */
  public static boolean Alternate(int count){           
      if (count% 2 != 0){     
        return BETRAY;        
      }
      else { 
        return SILENT;    
    }
       
  }


/**
 * this method is a helper for one more betray, it is called in a variable that counts the times a player has betrayed (countbetray)
 * @param boolean last choice this perameter decides what choice method one more betray will be based on
 * @return boolean prisoner choice
 */
  public static boolean countbetray(boolean lastChoice){
      if(lastChoice == BETRAY){
        return BETRAY;
      }
      else
        return SILENT;
  }



/**
 * this method always keeps the players betray count one above their opponents 
 * @param int betray count opponent , a variable that counts the users opponents count of betrays
 * @param int betray count self , a variable that counts the users own count of betrays
 * @return boolean prisoner choice, if the user has betrayed less than the oppenent, the user will betray. Else, they stay silent
 */
  public static boolean OnemoreBetray(int betraycountOpponent, int betraycountSelf){
      if (betraycountOpponent>=betraycountSelf){
        return BETRAY;
      }
      else{
        return SILENT;
      }
      
  }

/**
 * This method copies the last choice of the opponent
 * @param boolean last choice this perameter decides what choice the prisoner will make based on its opponents answser
 * @return boolean prisoner choice
 */
  public static boolean titfortat(boolean lastChoice){
      if(lastChoice == BETRAY){
        return BETRAY;
      }
      else
        return SILENT;
}


/**
 * In this method, silent is always returned until the opponent betrays once. If the opponent betrays just once, the player returns silent for the remainder of the game
 * @param int betray counter
 * @return boolean prisoner choice
 */
  public static Boolean holdGrudge(int betrayCounter){
      if (betrayCounter == 0){
        return SILENT;
      }
      else {
        return BETRAY;
      }
  }


// SCORING
/**
 * scoring methods are identical but specified for either A or B
 * @param boolean choice A 
 * @param boolean choice B
 * @return int score 
 */
  public static int scorePrisonerB(boolean choiceA, boolean choiceB){
    int Bscore = 0;
    if (choiceA == BETRAY && choiceB == SILENT){
      Bscore += 3;
    }
    else if (choiceA == SILENT && choiceB == BETRAY){
      Bscore += 0;
    }
    else if (choiceA == BETRAY && choiceB == BETRAY){
      Bscore += 2;
    }
    else { 
      Bscore ++;    
    }
    return Bscore;
    }


  public static int scorePrisonerA(boolean choiceA, boolean choiceB){
     int Ascore = 0;
     if (choiceA == BETRAY && choiceB == SILENT){
        Ascore += 0;    
      }
     else if (choiceA == SILENT && choiceB == BETRAY){
        Ascore += 3;
      }
     else if (choiceA == BETRAY && choiceB == BETRAY){    
        Ascore += 2;
      }
     else {   
        Ascore++;
      }
      return Ascore;
     }

}
