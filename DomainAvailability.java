/* 
Name:Elizabeth Pursell
Date: 4/24/2022
CSE 007 Spring 2022: Homework 7
Produce DomainAvailability program that will create tools to validate a domain and check its availability
IDE Used: Visual Studio Code
*/
public class DomainAvailability {
    //create private members for class
    private String [] validTLD = {".com", ".net", ".org", ".info", ".biz", ".name", ".pro"};
    private String [] restrictedTLD = {".biz", ".name", ".pro"};
    private String [] registeredDomains = {"apple.com", "oracle.com", "comcast.com", "comcast.net", "comcast.org", "comcast.info", "comcast.biz", "nonprofit.org", "home.biz", "intel.com", "information.info", "RunningYourOwn.net", "google.com", "bing.com", "Elizabeth.net", "BigBucks.biz", "traveling.info", "coding.org", "food.net", "netflix.com", "hulu.com", "water.org"};
    private int numRegisteredDomains = registeredDomains.length;
    private String domainName;

    public DomainAvailability(String domainName){        //default constructor; set above strings to lowercase using next method
        this.domainName = domainName.toLowerCase();
        changeStringsToLowerCase(validTLD);
        changeStringsToLowerCase(restrictedTLD);
        changeStringsToLowerCase(registeredDomains);
    }
    public void changeStringsToLowerCase(String [] array){      //converts array of strings to lowercase
        for(int index = 0; index < array.length; index++){
            array[index] = array[index].toLowerCase();
        }
    }
    public int checkIfDomainNameIsValid(String domainName){     //call next methods to check each part valid
        int periodPosition = getPeriodPosition(domainName);
        if(periodPosition < 0){     //no periods or too many periods
            return -1;
        }
        if(periodPosition == 0 || periodPosition == domainName.length() - 1){       //period is first or last
            return -2;
        }
        if(!hasValidSecondLevel(getSld(domainName))){       //invalid SLD
            return -3;
        }
        if(!checkIfTldIsValid(getTld(domainName))){         //invalid TLD
            return -4;
        }
        return 0;       //valid domain name
    }
    public int getPeriodPosition(String search){        //method to get the position of the domain's periods
        int periodPosition = -1;
        int periodCounter = 0;
        for(int index = 0; index < search.length(); index++){
            if(search.charAt(index) == '.'){
                periodPosition = index;
                periodCounter++;
            }
        }
        if(periodCounter == 1){
            return periodPosition;
        }
        return -1;      //returns -1 if too many or not enough periods
    }
    public boolean hasValidSecondLevel(String sld){     //method to check validity of SLD; SLD is before period
        if(sld.length() < 1 || sld.length() > 63){      //checks amount of characters
            return false;
        }
        if(sld.charAt(0) == '-' || sld.charAt(sld.length() - 1) == '-'){        //checks first and last character for -
            return false;
        }
        for(int index = 0; index < sld.length(); index++){      //checks all characters for 0 - 9, a - z, A - Z
            if((sld.charAt(index) < '0' || sld.charAt(index) > '9') && (sld.charAt(index) < 'a' || sld.charAt(index) > 'z') && (sld.charAt(index) < 'A' || sld.charAt(index) > 'Z')){
                if(sld.charAt(index) == '-'){       //allows dashes
                    continue;
                }
                else{
                    return false;
                }
            }
        }
        return true;
    }
    public boolean checkIfTldIsValid(String tld){       //method to check validity of TLD; TLD is after period
        if(tld.charAt(0) != '.'){       //verifies first char as period
            return false;
        }
        for(int index = 1; index < tld.length(); index++){      //checks other char for 0 - 9, a - z, A - Z
            if((tld.charAt(index) < '0' || tld.charAt(index) > '9') && (tld.charAt(index) < 'a' || tld.charAt(index) > 'z') && (tld.charAt(index) < 'A' || tld.charAt(index) > 'Z')){
                return false;
            }
        }
        return true;
    }
    public boolean hasAvailableDomainName(String domainName){       //method to check availability of domain name
        for(int index = 0; index < numRegisteredDomains; index++){
            if(domainName.equals(registeredDomains[index])){
                return false;
            }
        }
        return true;
    }
    public String getSimilarDomainNames(String domainName){     //method to get similar domain names
        String sld = getSld(domainName);
        String tld = getTld(domainName);
        String similarDomainNames = "";
        for(int index = 0; index < restrictedTLD.length; index++){      //returns blank string if tld is restricted
            if(tld.equals(restrictedTLD[index])){
                return "";
            }
        }
        for(int index = 0; index < validTLD.length; index++){       //creates string of all similar domain names
            String tempDomainName = sld.concat(validTLD[index]);
            for(int i = 0; i < registeredDomains.length; i++){
                if(tempDomainName.equals(registeredDomains[i])){
                    tempDomainName = "";        //does not add similar domain if already registered
                }
            }
            if(tempDomainName != ""){
                similarDomainNames = similarDomainNames.concat(tempDomainName + ", ");
            }
        }
        return similarDomainNames;
    }
    public String getTld(String domainName){        //method to get domain's TLD
        int periodPosition = getPeriodPosition(domainName);
        String tld = "";
        for(int index = 0; index < domainName.length(); index++){
            if(index >= periodPosition){
                tld = tld + domainName.charAt(index);
            }
        }
        return tld;
    }
    public String getSld(String domainName){        //method to get domain's SLD
        int periodPosition = getPeriodPosition(domainName);
        String sld = "";
        for(int index = 0; index < domainName.length(); index++){
            if(index < periodPosition){
                sld = sld + domainName.charAt(index);
            }
        }
        return sld;
    }
    public String getDomainName(){      //method to get domain name
        return domainName;
    }
}
