import java.util.Random;
import java.util.*;


public class iterPrison {

  //True : the prisoner betrayed.
  //False : the prisoner stayed silent.
  final static boolean BETRAY = true;
  final static boolean SILENT = false;

  public static void main(String[] args){
  
  
  
    //The last choice of each prisoner.

    
 ArrayList<String> strats = new ArrayList<String>(); 
 strats.add("alternate");
 strats.add("always betray");
 strats.add("tit for tat");
 strats.add("hold grudge");
 strats.add("one more betray");
//String [] strats = {"alternate", "always betray" , "tit for tat" , "hold grudge" , "one more betray"};

   for (int i =0; i < strats.size() ; i++){
      for (int j = 0; i < strats.size() ; j++){
        String stratA = strats.get(i);
        String stratB = strats.get(i);
        testing (stratA,stratB);

      }

   }
}





// assign for iterations

public static void testing (String stratA, String stratB){

    
    
    boolean lastChoicePrisonerA = BETRAY; //Set initially to BETRAYED for testing
    boolean lastChoicePrisonerB = BETRAY;

    int count = 0;
    int betraycountA = 0;
    int betraycountB = 0;
    int totalscoreA = 0;
    int totalscoreB = 0;
    String winner;


    while(count<10){
    


    count ++;

    countbetray(lastChoicePrisonerB);
    countbetray(lastChoicePrisonerA);
    
    scorePrisonerB(lastChoicePrisonerA, lastChoicePrisonerB);
    scorePrisonerA(lastChoicePrisonerA, lastChoicePrisonerB);
    totalscoreB += scorePrisonerB(lastChoicePrisonerA, lastChoicePrisonerB);
    totalscoreA += scorePrisonerA(lastChoicePrisonerA, lastChoicePrisonerB);

      if (countbetray(lastChoicePrisonerA) == BETRAY){
        betraycountA ++;
      }
      else if (countbetray(lastChoicePrisonerB) == BETRAY){
        betraycountB ++;
      }
   


   // STRATS A
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

// STRATSB

    if (stratB.equals("alternate")){
    lastChoicePrisonerA = Alternate(count);
  }
  else if (stratB.equals("always betray")){
    lastChoicePrisonerA = alwaysBetray();
  }
  else if (stratB.equals("tit for tat")){
    lastChoicePrisonerA = titfortat(lastChoicePrisonerA);
  }

  else if (stratB.equals("hold grudge")){
      lastChoicePrisonerA = holdGrudge(betraycountA); 
    }
  else if (stratB.equals("one more betray")){
      lastChoicePrisonerA = OnemoreBetray(betraycountA, betraycountB);
    }

  }
 if (totalscoreB > totalscoreA){

   System.out.println("Prisoner A has won with fewer prison years");
 

   }
   else{
    System.out.println("Prisoner B has won with fewer prison years");

   }
   System.out.println("Prisoner A score: " + totalscoreA + ", Prisoner B score: " + totalscoreB);





}
















// METHODS

// pick either always comply or betray



 public static boolean alwaysBetray(){
      return BETRAY;
  }

  public static boolean Alternate(int count){           
      if (count% 2 != 0){     
        return BETRAY;        
      }
      else { 
        return SILENT;    
    }
       
  }


// helper for one more betray
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

// player always keeps betray count one above opponents
public static boolean OnemoreBetray(int betraycountOpponent, int betraycountSelf){
      if (betraycountOpponent>=betraycountSelf){
        return BETRAY;
      }
      else{
        return SILENT;
      }
      
  }




public static Boolean holdGrudge(int betrayCounter){

      if (betrayCounter == 0){
        return SILENT;
      }
      else {
        return BETRAY;
      }

  }



// SCORING

public static int scorePrisonerB(boolean choiceA, boolean choiceB){
  int Bscore = 0;
  int count = 0;
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
