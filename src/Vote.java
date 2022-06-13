import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Beyza
 */
public class Vote {
    
    private String selectedPartyName;
    private int selectedPartyIndex;
    private String[] participationArray ; // katılımcının bilgileri
    private List <String> listofChosenCandidates = new ArrayList<> (); //adayların isimleri
    private List<Vote> allVotes =new ArrayList<> ();
    private String candidateName;

    public Vote(String selectedPartyName, int selectedPartyIndex, String[] participationArray, String candidateName) {
        this.selectedPartyName = selectedPartyName;
        this.selectedPartyIndex = selectedPartyIndex;
        this.participationArray = participationArray;
        this.candidateName = candidateName;
    }
    public Vote(String selectedPartyName,int selectedPartyIndex ,String candidateName) {
        this.selectedPartyName = selectedPartyName;
        this.selectedPartyIndex = selectedPartyIndex;
        this.candidateName = candidateName;
    }

    

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }
    
    Statistics partyTemp = new Statistics();
    private String [] allParties = partyTemp.List(); //bütün partileri aldım
    
    Candidate candidateTemp = new Candidate();
    private String [] allCandidates = candidateTemp.candidateList(); //bütün adayları aldım
    
    static int numberOfParticipants;
    
     public static int [] votesOfParties = new int[12];  // oy sayıları
     public static int [] votesOfCandidates = new int[336];
    
    public static int [] votingRateOfParties = new int[12]; //sonra belki çevirebilirsem doublea çeviririm
     public static int[] votingRateOfCandidates = new int[336];
   
    
    
    
    public Vote(){
        
    }
    public String[] participation(){
       ParticipationFrame informationofParticipant = new ParticipationFrame();
       String [] participantArray = informationofParticipant.personParticipation();
       return participationArray;
    }
    
    public List <String> listofChosenCandidates(List list){
        listofChosenCandidates = list;
        return listofChosenCandidates;
    }

    public String getSelectedPartyName() {
        return this.selectedPartyName;
    }

    public void setSelectedPartyName(String selectedPartyName) {
        this.selectedPartyName = selectedPartyName;
    }

    public String[] getParticipationArray() {
        return this.participationArray;
    }

    public void setParticipationArray(String[] participationArray) {
        this.participationArray = participationArray;
    }

    public List<String> getListofChosenCandidates() {
        return listofChosenCandidates;
    }

    public int getSelectedPartyIndex() {
        return this.selectedPartyIndex;
    }

    public void setSelectedPartyIndex(int selectedPartyIndex) {
        this.selectedPartyIndex = selectedPartyIndex;
    }

    public void setListofChosenCandidates(List<String> listofChosenCandidates) {
        this.listofChosenCandidates = listofChosenCandidates;
    }
    
     public void SubmitVote() throws IOException
     {
         //{gender}-{Age}-{Profession}-{Education}-{PartName}-{Candidate Name}
            File file = new File("votes.txt");
            if(!file.exists()){
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                }     
            }
            
            FileWriter fileWriter = new FileWriter(file,true);
             
            fileWriter.append(this.participationArray[0]+"_"
                                       +this.participationArray[1]+"_"
                                       +this.participationArray[2]+"_"
                                       +this.participationArray[3]+"_"
                                       +this.getSelectedPartyName()+"_"
                                       +this.getSelectedPartyIndex()+"_"
                                       +this.getCandidateName()+"\n");
            fileWriter.close();
     }
    public void getVotesfromFile(){
        String line = "";
        int i=0;
        
        try{
            FileInputStream fStream = new FileInputStream("votes.txt");
            DataInputStream dStream = new DataInputStream(fStream);
            BufferedReader bReader = new BufferedReader(new InputStreamReader(dStream));
            //split stringi parçalara böler 
            while((line = bReader.readLine())!=null){
                String partyName = line.split("_")[4]; //textte kullanıcı tarafından verilen oyların hangi partiye ait olduğunu çekip partyName e atıyorum
               
                String index =line.split("_")[5]; 
                int partyIndex = Integer.parseInt(index);
                
                String candidateName = line.split("_")[6]; //textte oy verilen adayların isimlerini çekiyorum
                Vote userVote = new Vote(partyName,partyIndex,candidateName); //dosyadan aldığımız bilgileri userVote objesine
                this.allVotes.add(userVote); 
                 //textten aldığımız bilgileri allVotesa ekledim (list),split sayesinde 

            }
            dStream.close(); // okuma durumunu kapatmak için
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
   }
    
   public double GetPartyStatistics(String partyName){
        double partyVoteCount=0;
        //textten aldığım bilgileri allVotesa yüklemiştim (getVotesfromFile()) 
        for (int j = 0; j < this.allVotes.size() ; j++) {
            Vote currentVote = this.allVotes.get(j); // listimi Vote tipinden currentVote objesine  atıyorum (selectedPartyname,candidateName)
            if(currentVote.selectedPartyName.equals(partyName)) 
                partyVoteCount++;
        }
          
       return (partyVoteCount/this.allVotes.size())*100; //parti yüzdelik hesabı
   }
  
   
   
   public List<String> GetVotedCandidatesofParty(String partyName){
        List<String> candidates = new ArrayList <String>();
        
        for (int j = 0; j < this.allVotes.size(); j++) {
            Vote currentVote = this.allVotes.get(j); //listimi Vote tipinden currentVote objesine  atıyorum (selectedPartyname,candidateName)
            if(currentVote.selectedPartyName.equals(partyName))
            {
                String[] candidateNames= currentVote.candidateName.split("-"); //birden fazla adaya oy verildiği için, candidateNames arrayi 
                for (int i = 1; i < candidateNames.length; i++) {
                    candidates.add(candidateNames[i]); //candidate nameleri çekip listime ekledim
                }
            }   
                
        }
          
       return candidates; //candidate listimi döndürüyor.
   }
    public double GetCandidateStatistics(String partyName,int partyIndex,String candidateName){
        double partyVoteCount=0;
        double candidateVoteCount=0;
        for (int j = 0; j < this.allVotes.size() ; j++) {
            Vote currentVote = this.allVotes.get(j); //
            if(currentVote.selectedPartyName.equals(partyName) && partyIndex>=0){
                partyVoteCount++;
            }
            
           
        }  
      return (partyVoteCount/(28*partyVoteCount))*100;
   }
  /*  public double GetCandidateStatistics(String partyName,int partyIndex ,String candidateName){
       
        double candidateVoteCount=0;
       
        List<String> candidatesList = GetVotedCandidatesofParty(partyName);
        if(partyIndex >= 0){
            for (int j = 0; j < candidatesList.size(); j++) {
               // if(candidatesList.get(j).equals(candidateName)){
                    candidateVoteCount++;
                //}
            }
        } else if(partyIndex == -1){
            for (int j = 0; j < candidatesList.size(); j++){
                if(candidatesList.get(j).equals(candidateName)){
                    candidateVoteCount++;
                }
            }
          }   
       return (candidateVoteCount/candidatesList.size())*100;
   }   */
    
}
