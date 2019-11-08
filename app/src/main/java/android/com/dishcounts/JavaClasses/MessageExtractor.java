package android.com.dishcounts.JavaClasses;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageExtractor {
    public static void main(String args[])
    {
        List<String> messages=new ArrayList<>();

        try
        {
            FileInputStream fis = new FileInputStream("messages");
            ObjectInputStream ois = new ObjectInputStream(fis);

            messages = (ArrayList) ois.readObject();

            ois.close();
            fis.close();
        }
        catch (IOException ioe)
        {
            ioe.printStackTrace();
            return;
        }
        catch (ClassNotFoundException c)
        {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }

        int count=0;

        /*for(String message: messages)
        {
            count++;
        }*/
        System.out.println(count);
        /*String messages[]=new String[5];
        messages[0]="Enjoy 40% off on all orders. Use Code ZOMATO";
        messages[1]="Rs. 30 off on all orders. Use Code ZOMATO";
        messages[2]="Hello default message";
        messages[3]="30% cashback on all orders. Use Code ZOMATO";
        messages[4]="Get Rs. 30 cashback on all orders. Use Code ZOMATO";*/
        int UploadCount=0;
        for(String message: messages)
        {
            if(message!=null)
            {
                String pattern1=".*Zomato.*|.*Uber Eats.*|.*Swiggy.*";
                Pattern pattern= Pattern.compile(pattern1,Pattern.CASE_INSENSITIVE);
                Matcher matcher=pattern.matcher(message);
                if(matcher.find())
                {
                    count++;
                    System.out.println(message);
                    //System.out.println(matcher.group(0));

                    // Extract Coupon Code
                    message=message.replaceAll("'","");
                    String codePattern = "(\\bCode \\b[_A-Z0-9a-z]*\\b|\\bCode: \\b[?_A-Z0-9a-z]*\\b)";
                    Pattern p=Pattern.compile(codePattern,Pattern.CASE_INSENSITIVE);
                    Matcher m=p.matcher(message);
                    boolean couponFound=false;
                    if(m.find())
                    {
                        String theGroup = m.group(1);
                        System.out.println("hi "+theGroup);
                    }
                    System.out.println();
                    System.out.println();

                    if(couponFound) {

                        // Extract Discount

                        String percentDiscountPattern = "(\\b[0-9]*% \\bOFF)";
                        p = Pattern.compile(percentDiscountPattern, Pattern.CASE_INSENSITIVE);
                        m = p.matcher(message);
                        if (m.find()) {
                            String theGroup = m.group(1);
                            System.out.println("hi Percent Discount " + theGroup);
                        }
                        System.out.println();
                        //System.out.println();

                        String moneyDiscountPattern = "(\\bRs. [0-9]* off\\b|\\bRs [0-9]* off\\b|\\bRs.[0-9]* off\\b|\\bRs[0-9]* off\\b)";
                        p = Pattern.compile(moneyDiscountPattern, Pattern.CASE_INSENSITIVE);
                        m = p.matcher(message);
                        if (m.find()) {
                            String theGroup = m.group(1);
                            System.out.println("hi Cash Discount " + theGroup);
                        }
                        System.out.println();
                        //System.out.println();

                        // Extract Cashback

                        String percentCashbackPattern = "(\\b[0-9]*% \\bcashback)";
                        p = Pattern.compile(percentCashbackPattern, Pattern.CASE_INSENSITIVE);
                        m = p.matcher(message);
                        if (m.find()) {
                            String theGroup = m.group(1);
                            System.out.println("hi Percent Cashback " + theGroup);
                        }
                        System.out.println();
                        //System.out.println();

                        String moneyCashbackPattern = "(\\bRs. [0-9]* cashback\\b|\\bRs [0-9]* cashback\\b)";
                        p = Pattern.compile(moneyCashbackPattern, Pattern.CASE_INSENSITIVE);
                        m = p.matcher(message);
                        if (m.find()) {
                            String theGroup = m.group(1);
                            System.out.println("hi Cash Cashback " + theGroup);
                        }

                        System.out.println();
                        //System.out.println();
                        List<String> months = new ArrayList<>();
                        months.add("Jan");
                        months.add("January");
                        months.add("Feb");
                        months.add("February");
                        months.add("Mar");
                        months.add("March");
                        months.add("Apr");
                        months.add("April");
                        months.add("May");
                        months.add("Jun");
                        months.add("June");
                        months.add("Jul");
                        months.add("July");
                        months.add("Aug");
                        months.add("August");
                        months.add("Sep");
                        months.add("September");
                        months.add("Oct");
                        months.add("October");
                        months.add("Nov");
                        months.add("November");
                        months.add("Dec");
                        months.add("December");
                        boolean found = false;
                        for (String month : months) {
                            //System.out.println(month);
                            String expiryPattern = "(\\b" + month + " [0-9]?[0-9]\\b|\\b[0-9]?[0-9] " + month + "\\b|\\b" + month + " [0-9]?[0-9](th|st)\\b|\\b[0-9]?[0-9](th|st) " + month + "\\b|\\b[0-9]?[0-9]/[0-9]?[0-9])";
                            //String expiryPattern="(\\bJan [0-9]?[0-9]\\b|\\b[0-9]?[0-9] Jan\\b)";
                            p = Pattern.compile(expiryPattern, Pattern.CASE_INSENSITIVE);
                            //System.out.println(expiryPattern+" "+p);
                            m = p.matcher(message);
                            if (m.find()) {
                                found = true;
                                String theGroup = m.group(1);
                                System.out.println("hi Valid Till " + theGroup);
                            }
                            if (found)
                                break;
                        }
                        System.out.println();
                    }

                }
            }
        }
        System.out.println(count);

    }

    public static Map<String, Object> couponExtractor(String message) throws ParseException {
        if (message != null) {
            String pattern1 = "(\\bZomato\\b|\\bUber Eats\\b|\\bSwiggy\\b)";
            Pattern pattern = Pattern.compile(pattern1, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(message);
            String platform = "";
            if (matcher.find()) {

                platform = matcher.group(1);

                // Extract Coupon Code
                message = message.replaceAll("'", "");
                String codePattern = "(\\bCode:? \\b[?_A-Z0-9a-z]*\\b)";
                Pattern p = Pattern.compile(codePattern, Pattern.CASE_INSENSITIVE);
                Matcher m = p.matcher(message);
                String couponCode = "";
                boolean couponFound = false;
                if (m.find()) {
                    couponFound = true;
                    couponCode = m.group(1);
                    //System.out.println(couponCode.toLowerCase().equals("is")+"BOOOO");
                    if (couponCode.split(" ")[1].toLowerCase().equals("is"))
                        couponFound = false;

                }
                System.out.println();
                System.out.println();

                // Extract Discount
                if (couponFound) {
                    //count++;
                    System.out.println(message);
                    System.out.println("hi " + couponCode.split(" ")[1]);

                    //DocumentReference docRef = db.collection("all_coupons").document("test" + (++UploadCount));

                    Map<String, Object> data = new HashMap<>();

                    data.put("platform", platform.toUpperCase());
                    data.put("coupon_code", couponCode.split(" ")[1]);
                    data.put("message", message);

                    String percentDiscountPattern = "(\\b[0-9]*% \\bOFF)";
                    p = Pattern.compile(percentDiscountPattern, Pattern.CASE_INSENSITIVE);
                    m = p.matcher(message);
                    if (m.find()) {
                        String theGroup = m.group(1);
                        //System.out.println("hi Percent Discount " + theGroup);
                        String split = theGroup.split(" ")[0];
                        System.out.println("hi Percent Discount" + split.substring(0, split.length() - 1));
                        data.put("coupon_type", "discount");
                        data.put("discount_percent", split.substring(0, split.length() - 1));
                    } else {
                        data.put("discount_percent", "NA");
                    }
                    System.out.println();
                    //System.out.println();

                    String moneyDiscountPattern = "(\\bRs.? ?[0-9]* off\\b)";
                    p = Pattern.compile(moneyDiscountPattern, Pattern.CASE_INSENSITIVE);
                    m = p.matcher(message);
                    if (m.find()) {
                        String theGroup = m.group(1);

                        data.put("coupon_type", "discount");
                        String split[] = theGroup.split(" ");
                        if (split.length == 2) {
                            if (split[0].charAt(2) == '.') {
                                data.put("discount_cash", split[0].substring(3));
                                System.out.println("hi Cash Discount " + split[0].substring(3));
                            } else {
                                data.put("discount_cash", split[0].substring(2));
                                System.out.println("hi Cash Discount " + split[0].substring(2));
                            }
                        } else {
                            data.put("discount_cash", split[1]);
                            System.out.println("hi Cash Discount " + split[1]);
                        }

                    } else {
                        moneyDiscountPattern = "(\\bSave Rs.? ?[0-9]*\\b)";
                        p = Pattern.compile(moneyDiscountPattern, Pattern.CASE_INSENSITIVE);
                        m = p.matcher(message);
                        if (m.find()) {
                            String theGroup = m.group(1);

                            data.put("coupon_type", "discount");
                            String split[] = theGroup.split(" ");
                            if (split.length == 2) {
                                if (split[1].charAt(2) == '.') {
                                    data.put("discount_cash", split[1].substring(3));
                                    System.out.println("hi Cash Discount " + split[1].substring(3));
                                } else {
                                    data.put("discount_cash", split[1].substring(2));
                                    System.out.println("hi Cash Discount " + split[1].substring(2));
                                }
                            } else {
                                data.put("discount_cash", split[2]);
                                System.out.println("hi Cash Discount " + split[2]);
                            }

                        } else {
                            data.put("discount_cash", "NA");
                        }
                    }
                    System.out.println();
                    //System.out.println();

                    // Extract Cashback

                    String percentCashbackPattern = "(\\b[0-9]*% \\bcashback)";
                    p = Pattern.compile(percentCashbackPattern, Pattern.CASE_INSENSITIVE);
                    m = p.matcher(message);
                    if (m.find()) {
                        data.put("coupon_type", "cashback");

                        String theGroup = m.group(1);
                        String split = theGroup.split(" ")[0];
                        System.out.println("hi Percent Cashback " + split.substring(0, split.length() - 1));
                        data.put("cashback_percent", split.substring(0, split.length() - 1));
                    } else {
                        data.put("cashback_percent", "NA");
                    }
                    System.out.println();
                    //System.out.println();

                    String moneyCashbackPattern = "(\\bRs.? ?[0-9]* cashback\\b)";
                    p = Pattern.compile(moneyCashbackPattern, Pattern.CASE_INSENSITIVE);
                    m = p.matcher(message);
                    if (m.find()) {
                        String theGroup = m.group(1);

                        data.put("coupon_type", "cashback");
                        String split[] = theGroup.split(" ");
                        if (split.length == 2) {
                            if (split[0].charAt(2) == '.') {
                                data.put("cashback_cash", split[0].substring(3));
                                System.out.println("hi Cash Cashback " + split[0].substring(3));
                            } else {
                                data.put("cashback_cash", split[0].substring(2));
                                System.out.println("hi Cash Cashback " + split[0].substring(2));
                            }
                        } else {
                            data.put("cashback_cash", split[1]);
                            System.out.println("hi Cash Cashback " + split[1]);
                        }
                    } else {
                        data.put("cashback_cash", "NA");
                    }
                    System.out.println();

                    // Extract Valid Till

                    //System.out.println();
                    List<String> months = new ArrayList<>();
                    months.add("Jan");
                    months.add("January");
                    months.add("Feb");
                    months.add("February");
                    months.add("Mar");
                    months.add("March");
                    months.add("Apr");
                    months.add("April");
                    months.add("May");
                    months.add("Jun");
                    months.add("June");
                    months.add("Jul");
                    months.add("July");
                    months.add("Aug");
                    months.add("August");
                    months.add("Sep");
                    months.add("September");
                    months.add("Oct");
                    months.add("October");
                    months.add("Nov");
                    months.add("November");
                    months.add("Dec");
                    months.add("December");
                    HashMap<String, String> monthNum = new HashMap<>();
                    monthNum.put("Jan", "01");
                    monthNum.put("January", "01");
                    monthNum.put("Feb", "02");
                    monthNum.put("February", "02");
                    monthNum.put("Mar", "03");
                    monthNum.put("March", "03");
                    monthNum.put("Apr", "04");
                    monthNum.put("April", "04");
                    monthNum.put("May", "05");
                    monthNum.put("Jun", "06");
                    monthNum.put("June", "06");
                    monthNum.put("Jul", "07");
                    monthNum.put("July", "07");
                    monthNum.put("Aug", "08");
                    monthNum.put("August", "08");
                    monthNum.put("Sep", "09");
                    monthNum.put("September", "09");
                    monthNum.put("Oct", "10");
                    monthNum.put("October", "10");
                    monthNum.put("Nov", "11");
                    monthNum.put("November", "11");
                    monthNum.put("Dec", "12");
                    monthNum.put("December", "12");


                    boolean found = false;
                    for (String month : months) {
                        //System.out.println(month);
                        String expiryPattern = "(\\b" + month + " [0-9]?[0-9]\\b|\\b[0-9]?[0-9] " + month + "\\b|\\b" + month + " [0-9]?[0-9](th|st)\\b|\\b[0-9]?[0-9](th|st) " + month + "\\b|\\b[0-9]?[0-9]/[0-9]?[0-9])";
                        //String expiryPattern="(\\bJan [0-9]?[0-9]\\b|\\b[0-9]?[0-9] Jan\\b)";
                        p = Pattern.compile(expiryPattern, Pattern.CASE_INSENSITIVE);
                        //System.out.println(expiryPattern+" "+p);
                        m = p.matcher(message);
                        if (m.find()) {
                            found = true;
                            String theGroup = m.group(1);
                            System.out.println("hi Valid Till " + theGroup);
                            String split[] = theGroup.split(" ");
                            String date = "/2019";
                            if (split.length > 1) {
                                if (monthNum.containsKey(split[0])) {
                                    date = "/" + monthNum.get(split[0]) + date;
                                    if (split[1].length() == 4 || split[1].length() == 2) {
                                        date = split[1].substring(0, 2) + date;
                                    } else {
                                        date = "0" + split[1].substring(0, 1) + date;
                                    }

                                } else {
                                    date = "/" + monthNum.get(split[1]) + date;
                                    if (split[0].length() == 4 || split[0].length() == 2) {
                                        date = split[0].substring(0, 2) + date;
                                    } else {
                                        date = "0" + split[0].substring(0, 1) + date;
                                    }
                                }
                            } else {
                                String split2[] = split[0].split("/");
                                if (split2[1].length() == 2) {
                                    date = "/" + split2[1] + date;
                                } else {
                                    date = "/0" + split2[1] + date;
                                }
                                if (split2[0].length() == 2) {
                                    date = split2[0] + date;
                                } else {
                                    date = "0" + split2[0] + date;
                                }

                            }
                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                            Date finaldate = formatter.parse(date);
                            data.put("valid_till", finaldate);
                            System.out.println("hi Valid Till " + finaldate);
                        }
                        if (found)
                            break;
                    }
                    if (!found) {
                        Date finaldate = null;
                        data.put("valid_till", finaldate);
                    }

                    System.out.println();

                    // Extract Max Discount

                    String maxDiscount = "(\\bMax.? discount Rs.? ?[0-9]+\\b|up ?to Rs.? ?[0-9]+\\b|\\bMax.? discount of Rs.? ?[0-9]+\\b)";
                    p = Pattern.compile(maxDiscount, Pattern.CASE_INSENSITIVE);
                    //System.out.println(expiryPattern+" "+p);
                    m = p.matcher(message);
                    if (m.find()) {
                        String theGroup = m.group(1);
                        String split[] = theGroup.split(" ");
                        String lastword = split[split.length - 1];
                        if (lastword.charAt(0) == 'R') {
                            if (lastword.charAt(2) == '.') {
                                data.put("discount_upto", lastword.substring(3));
                                System.out.println("hi Max Discount " + lastword.substring(3));
                            } else {
                                data.put("discount_upto", lastword.substring(2));
                                System.out.println("hi Max Discount " + lastword.substring(2));
                            }
                        } else {
                            System.out.println("hi Max Discount " + lastword);
                            data.put("discount_upto", lastword);
                        }

                    } else {
                        data.put("discount_upto", "NA");
                    }
                    System.out.println();
                    //ApiFuture<WriteResult> result = docRef.set(data);
                    //System.out.println("Update time : " + result.get().getUpdateTime());
                    return data;
                }
                else
                {
                    return null;
                }

            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }
}
