package com.glenvasa.Fitness.App1.textUser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SmsRequest {

    //   @NotBlank  - look for comparable annotation / library jar
    private String phoneNumber; // destination phone number

    //   @NotBlank
    private String message;

    // this is what client will be sending
//    public SmsRequest(@JsonProperty("phoneNumber") String phoneNumber,
//                      @JsonProperty("message") String message) {
//         this.phoneNumber = phoneNumber;
//        this.message = message;
//    }

    public SmsRequest(String phoneNumber, String message) {
        this.phoneNumber = phoneNumber;
        this.message = message;
    }

    public SmsRequest(){

    }

//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public String getMessage() {
//        return message;
//    }

    @Override
    public String toString() {
        return "SmsRequest{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
