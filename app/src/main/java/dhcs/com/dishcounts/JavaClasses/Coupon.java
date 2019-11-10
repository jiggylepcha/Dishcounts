package dhcs.com.dishcounts.JavaClasses;

import com.google.firebase.firestore.IgnoreExtraProperties;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

@IgnoreExtraProperties
public class Coupon {
    private String cloudID;
    private String couponType;
    private String couponCode;
    private String discount_percent;
    private String discountUpto;
    private String platform;
    private Date validTill;
    private String message;

    public Coupon(){

    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Coupon(String couponType, String couponCode, String discount_percent, String discountUpto, String platform, Date validTill, String message){
        this.couponType = couponType;
        this.couponCode = couponCode;
        this.discount_percent = discount_percent;
        this.discountUpto = discountUpto;
        this.validTill = validTill;
        this.platform = platform;
        this.message=message;
    }

    public String getCloudID() {
        return cloudID;
    }

    public void setCloudID(String cloudID) {
        this.cloudID = cloudID;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public String getDiscountUpto() {
        return discountUpto;
    }

    public void setDiscountUpto(String discountUpto) {
        this.discountUpto = discountUpto;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Date getValidTill() {
        return validTill;
    }

    public void setValidTill(Date validTill) {
        this.validTill = validTill;
    }

    public String getCouponCode() {
        return couponCode;
    }

    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }

    public String getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(String discount_percent) {
        this.discount_percent = discount_percent;
    }

    public String getDate(){
        if(validTill!=null) {
            LocalDate localDate = this.validTill.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int year = localDate.getYear();
            int month = localDate.getMonthValue();
            int day = localDate.getDayOfMonth();
            return (day + "/" + month);
        }
        return "NA";
    }
}
