package ptt.vn.icaremobileapp.utils;

import android.text.TextUtils;

import java.util.List;

import ptt.vn.icaremobileapp.model.common.CateSharelDomain;

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
}
