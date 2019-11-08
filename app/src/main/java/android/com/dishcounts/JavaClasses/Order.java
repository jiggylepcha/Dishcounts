package android.com.dishcounts.JavaClasses;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@IgnoreExtraProperties
public class Order {
    private String post_message;
    private Date timeout;

    public Order(String post_message, Date timeout){
        this.post_message = post_message;
        this.timeout = timeout;
    }

    public String getPost_message() {
        return post_message;
    }

    public void setPost_message(String post_message) {
        this.post_message = post_message;
    }

    public Date getTimeout() {
        return timeout;
    }

    public void setTimeout(Date timeout) {
        this.timeout = timeout;
    }

    public String getOrderDate(){
        LocalDate localDate = this.timeout.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int year = localDate.getYear();
        int month = localDate.getMonthValue();
        int day = localDate.getDayOfMonth();

        LocalTime localTime = this.timeout.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        int hour = localTime.getHour();
        int minute = localTime.getMinute();

        return (hour + ":" + minute +" "+ day + "/" + month);
    }
}
