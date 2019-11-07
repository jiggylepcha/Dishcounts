package android.com.dishcounts.JavaClasses;

public class Coupon {
    private String cloudID;
    private String couponType;
    private String discountPercentage;
    private String discountUpto;
    private String platform;
    private String validTill;

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

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
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

    public String getValidTill() {
        return validTill;
    }

    public void setValidTill(String validTill) {
        this.validTill = validTill;
    }
}
