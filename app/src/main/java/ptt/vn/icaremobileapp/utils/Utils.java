package ptt.vn.icaremobileapp.utils;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.widget.DatePicker;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;

public class Utils {
    public static final String yyyyMMdd = "yyyy-MM-dd";
    public static final String ddMMyyyy = "dd/MM/yyyy";

    public static String formatCurrency(int currency) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        String current = formatter.format(currency);
        return current.replaceAll(",", ".");
    }

    public static String dateConvert(String date) {
        if (date != null) {
            SimpleDateFormat response = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            SimpleDateFormat request = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

            try {
                Date d = request.parse(date);
                return response.format(d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "";
    }


    public static Calendar dateStringConvert(String date){
        Calendar cal = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            cal.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }

    public static String dateFormat(Calendar calendar, @NonNull String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);

        return sdf.format(calendar.getTime());
    }

    public static String getCurrentDate(String dateFormat) {
        Calendar calendar = Calendar.getInstance();
        return dateFormat(calendar, dateFormat);
    }

    public static SpannableString spannable(String s, String character) {
        if (s != null && !s.equals("") && character != null && !character.equals("") && character.length() <= s.length()) {
            SpannableString spannableString = new SpannableString(s);
            int start = s.toLowerCase().indexOf(character.toLowerCase());
            int end = start + character.length();
            if (start > -1)
                spannableString.setSpan(new ForegroundColorSpan(Color.RED), start, end, 0);
            return spannableString;
        }

        if (s == null) {
            return SpannableString.valueOf("");
        }

        return SpannableString.valueOf(s);
    }

    /*
     * JsonElement element = new Gson().toJsonTree(result.getData(), new TypeToken<List<InpatientDomain>>() {}.getType());
     * List<InpatientDomain> lst = getList(InpatientDomain[].class, element.toString());
     */
    public static <T> List<T> getList(final Class<T[]> clazz, final String json) {
        final T[] jsonToObject = new Gson().fromJson(json, clazz);

        return Arrays.asList(jsonToObject);
    }
}
