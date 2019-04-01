// THIS JUST FEATURES MORE PRINT LINES 
// HOW I TESTED TO MAKE CERTAIN ALL METHODS WERE WORKING



import java.util.Random;
import java.util.*;


public class testPrison {

  //True : the prisoner betrayed.
  //False : the prisoner stayed silent.
  final static boolean BETRAY = true;
  final static boolean SILENT = false;

  public static void main(String[] args){
  
 
    //The last choice of each prisoner.

    boolean lastChoicePrisonerA = BETRAY; //Set initially to BETRAYED for testing
    boolean lastChoicePrisonerB;


    int count = 0;
    int betraycountA = 0;
    int betraycountB = 0;
    int totalscoreA = 0;
    int totalscoreB = 0;
    

   
    
    while(count<10){
    

    //list.add(BETRAY);
    count ++;
    System.out.println(count);


     lastChoicePrisonerB = titfortat(lastChoicePrisonerA);
  
    lastChoicePrisonerA =OnemoreBetray(betraycountB,betraycountA);


      if (countbetray(lastChoicePrisonerA) == BETRAY){
        betraycountA ++;
      }
      if (countbetray(lastChoicePrisonerB) == BETRAY){
        betraycountB ++;
      }
      
    
    countbetray(lastChoicePrisonerB);
    countbetray(lastChoicePrisonerA);
    scorePrisonerB(lastChoicePrisonerA, lastChoicePrisonerB);
    scorePrisonerA(lastChoicePrisonerA, lastChoicePrisonerB);
    totalscoreB += scorePrisonerB(lastChoicePrisonerA, lastChoicePrisonerB);
    totalscoreA += scorePrisonerA(lastChoicePrisonerA, lastChoicePrisonerB);
    System.out.println("betray a:" + betraycountA + "betraycountB" + betraycountB);

   System.out.println("Prisoner A score: " + totalscoreA + " Prisoner B score: " + totalscoreB);


  }
 if (totalscoreB > totalscoreA){
    System.out.println("Prisoner A has won with fewer prison years");

   }
   else{
    System.out.println("Prisoner B has won with fewer prison years");
   }

}

 public static boolean OnemoreBetray(int betraycountOpponent, int betraycountSelf){
      if (betraycountOpponent>betraycountSelf){
        return BETRAY;
      }
      else{
        return SILENT;
      }
      
  }


  public static boolean alwaysComply(){
      return SILENT;
  }

 public static boolean alwaysBetray(){
      return BETRAY;
  }

  public static boolean countbetray(boolean lastChoice){
      if(lastChoice == BETRAY){
        return BETRAY;
      }
      else
        return SILENT;
  }


 public static boolean titfortat(boolean lastChoice){
      if(lastChoice == BETRAY){
        return BETRAY;
      }
      else
        return SILENT;
  }

 public static Boolean holdGrudge(int betrayCounter){

      if (betrayCounter == 0){
        return SILENT;
      }
      else {
        return BETRAY;
      }

  }

  public static boolean Alternate(int count){      
      
      if (count% 2 != 0){
      
        return BETRAY;        

      }
      else {
  
        return SILENT;
        
      
    }

     
       
     
 
  }




 public static int scorePrisonerB(boolean choiceA, boolean choiceB){
  int Bscore = 0;
  int count = 0;
 
 
   if (choiceA == BETRAY && choiceB == SILENT){
    Bscore += 3;
    System.out.println("Prisoner B chose to be silent: " + Bscore + " years");

  }
  else if (choiceA == SILENT && choiceB == BETRAY){
    Bscore += 0;

    System.out.println("Prisoner B chose to betray: " + Bscore + " years");

  }
  else if (choiceA == BETRAY && choiceB == BETRAY){
    Bscore += 2;

    System.out.println("Prisoner B chose to betray: " + Bscore + " years");

  }
  else { // both are silent
    Bscore ++;
    
    System.out.println("Prisoner B chose to be silent: " + Bscore + " years");

  }

  return Bscore;

 }

public static int scorePrisonerA(boolean choiceA, boolean choiceB){
  int Ascore = 0;

   if (choiceA == BETRAY && choiceB == SILENT){

    Ascore += 0;
    
    System.out.println("Prisoner A chose to betray: " + Ascore + " years");
  }
  else if (choiceA == SILENT && choiceB == BETRAY){

    Ascore += 3;
    System.out.println("Prisoner A chose to be silent: " + Ascore + " years");
  }
  else if (choiceA == BETRAY && choiceB == BETRAY){
    
    Ascore += 2;
   
    System.out.println("Prisoner A chose to betray: " + Ascore + " years");
  }
  else { // both are silent
   
    Ascore++;

    System.out.println("Prisoner A chose to be silent: " + Ascore + " years");
  }

  return Ascore;
 }
 














}




 
