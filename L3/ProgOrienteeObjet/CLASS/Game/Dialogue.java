package CLASS.Game;

public class Dialogue {                             //class dedicated to the edition of dialogue
    private String firstMeet = "";
    private String questStart = "";
    private String questComplete = "";
    private String afterQuest = "";
    private String beardTalk = "";
    
    public void setFirstMeet (String s){            //set the dialogue in the attribute firstmeet
        this.firstMeet = s;
    }

    public void setQuestStart (String s){           //set the dialogue in the attribute questStart
        this.questStart = s;
    }

    public void setQuestComplete (String s){        //set the dialogue in the attribute questComplete
        this.questComplete = s;
    }

    public void setAfterQuest(String s){            //set the dialogue in the attribute afterQuest
        this.afterQuest = s;
    }

    public void setBeardTalk (String s){            //set the dialogue in the attribute beardTalk
        this.beardTalk = s;
    }

    public String getFirstMeet(){                   //get the dialogue in the attribute firstMeet
        return this.firstMeet;
    }

    public String getQuestStart(){                  //get the dialogue in the attribute questStart
        return this.questStart;
    }

    public String getQuestComplete(){               //get the dialogue in the attribute questComplete
        return this.questComplete;
    }

    public String getAfterQuest(){                  //get the dialogue in the attribute afterQuest
        return this.afterQuest;
    }

    public String getBeardTalk(){                   //get the dialogue in the attribute beardTalk
        return this.beardTalk;
    }
}
