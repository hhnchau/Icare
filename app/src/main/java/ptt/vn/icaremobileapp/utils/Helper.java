package ptt.vn.icaremobileapp.utils;

import android.text.TextUtils;

import java.util.List;

import ptt.vn.icaremobileapp.model.common.CateSharelDomain;
import ptt.vn.icaremobileapp.model.hi.HiCard;

public class Helper {
    public static int getIdByName(List<CateSharelDomain> list, String name) {
        if (list != null && name != null)
            for (CateSharelDomain cate : list)
                if (cate.getName().equals(name))
                    return cate.getIdline();
        return -1;
    }

    public static String[] splitName(String name) {
        if (name != null) {
            String[] namez = name.trim().split(" ");
            StringBuilder firstName = new StringBuilder();
            String lastName = "";
            for (int i = 0; i < namez.length; i++) {
                if (i == namez.length - 1) {
                    lastName = namez[i];
                } else {
                    firstName.append(namez[i]).append(" ");
                }
            }

            return new String[]{firstName.toString().trim(), lastName};
        }
        return null;
    }

    public static String[] splitDate(String date) {
        if (date != null) {
            String[] datez = date.split("/");
            if (datez.length == 3)
                return new String[]{datez[0], datez[1], datez[2]};
            if (datez.length == 2)
                return new String[]{"", datez[0], datez[1]};
            if (datez.length == 1)
                return new String[]{"", "", datez[0]};
        }
        return null;
    }

    public static float convertString2Float(String number) {
        if (TextUtils.isEmpty(number)) {
            return 0;
        }
        String[] num = number.split("/");
        if (num.length > 1) {
            float num1 = Float.parseFloat(num[0]);
            float num2 = Float.parseFloat(num[1]);
            return num1 / num2;
        } else return Float.parseFloat(number);
    }

    public static String calculateDrugTotal(String number, String mor, String aft, String din, String eve) {
        if (!TextUtils.isEmpty(number)) {

            float morning = convertString2Float(mor);
            float after = convertString2Float(aft);
            float dinner = convertString2Float(din);
            float evening = convertString2Float(eve);


            int num = Integer.parseInt(number.split("/")[0]);
            float total = ((morning + after + dinner + evening) * num);
            float residual = total - (int) total;
            if (residual > 0) total = total + 1;

            return String.valueOf((int) total);
        }
        return "";
    }

    public static HiCard parseQr(String qr) {
        String[] dataParse = qr.split("\\|");

        HiCard hiCard = new HiCard();
        if (dataParse.length > 0)
            hiCard.setSn(dataParse[0]);
        if (dataParse.length > 1)
            hiCard.setName(Utils.convertHexStrToUnicode(dataParse[1]));
        if (dataParse.length > 2)
            hiCard.setBirthday(dataParse[2]);
        if (dataParse.length > 3)
            hiCard.setGender(Integer.parseInt(dataParse[3]));
        if (dataParse.length > 4)
            hiCard.setAddress(Utils.convertHexStrToUnicode(dataParse[4]));
        if (dataParse.length > 5)
            hiCard.setFirstRegistration(dataParse[5]);
        if (dataParse.length > 6)
            hiCard.setStartDate(dataParse[6]);
        if (dataParse.length > 7)
            hiCard.setEndDate(dataParse[7]);
        if (dataParse.length > 8)
            hiCard.setReleaseDate(dataParse[8]);
        if (dataParse.length > 9)
            hiCard.setManagerCode(dataParse[9]);
        if (dataParse.length > 10)
            hiCard.setParentName(Utils.convertHexStrToUnicode(dataParse[10]));
        if (dataParse.length > 11)
            hiCard.setObjectCode(dataParse[11]);
        if (dataParse.length > 12)
            hiCard.setTimeOver5Year(dataParse[12]);
        if (dataParse.length > 13)
            hiCard.setStringTest(dataParse[13]);
        if (dataParse.length > 14)
            hiCard.setCharEnd(dataParse[14]);

        return hiCard;
    }
}
