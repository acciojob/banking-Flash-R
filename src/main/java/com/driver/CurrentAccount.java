package com.driver;

public class CurrentAccount extends BankAccount{
    private String tradeLicenseId; //consists of Uppercase English characters only

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;

        if(balance < 5000)
            throw new Exception("Insufficient Balance");
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        if(validate(tradeLicenseId)) return; //if the license Id has no duplicate

        int freq[] = new int[26];
        int n = tradeLicenseId.length();
//        finding the frequency of each character
        for(int i = 0; i < n; i++ )
            freq[tradeLicenseId.charAt(i) - 'A']++;

//       finding the character with the maximum number of occurance
        int max = 0, letter = 0;
        for (int i = 0; i < 26; i++){
            if (freq[i] > max){
                max = freq[i];
                letter = i;
            }
        }
//        throwing exception if we are unable to rearrange
        if(max > (n +1)/2)
            throw new Exception("Valid License can not be generated");

// if it is possible we rearrange the license at boot even and odd positions basing on their max frequences
        char ch[] = new char[n];

        for (int i = 0; i < n; i+=2){
            if(freq[letter] > 0){
                ch[i] = (char) (letter + 'A');
                freq[letter]--;
            }else{
                for (int j = 0; j < 26; j++ ){
                    if(freq[j] > 0){
                        letter = j;
                        break;
                    }
                }
                ch[i] = (char) (letter + 'A');
                freq[letter]--;
            }
        }
//for odd positions
        for (int i = 1; i < n; i+=2){
            if (freq[letter] > 0){
                ch[i] = (char) (letter + 'A');
                freq[letter]--;
            }else{
                for (int j = 0; j < 26; j++ ){
                    if(freq[j] > 0){
                        letter = j;
                        break;
                    }
                }
                ch[i] = (char) (letter + 'A');
                freq[letter]--;
            }
        }

        tradeLicenseId = String.valueOf(ch);



    }
    public boolean validate(String license){
        int temp = 0;
        for(int i = 0; i < license.length() -1; i++){
            if(license.charAt(i) == license.charAt(i +1))
                break;
            else if(license.charAt(i) != license.charAt(i + 1))
                temp++;
        }
        if(temp == license.length() -1) return true;
        return false;
    }

}
