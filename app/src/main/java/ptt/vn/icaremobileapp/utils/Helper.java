package ptt.vn.icaremobileapp.utils;

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
}
