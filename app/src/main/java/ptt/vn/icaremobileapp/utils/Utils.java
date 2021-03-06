package ptt.vn.icaremobileapp.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import ptt.vn.icaremobileapp.model.inpatient.InpatientDomain;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class Utils {
    public static final String yyyyMMdd = "yyyy-MM-dd";
    public static final String ddMMyyyy = "dd/MM/yyyy";
    public static final String ddMMyyyyHHmm = "dd/MM/yyyy HH:mm";
    public static final String ddMMyyyyTHHmmss = "yyyy-MM-dd'T'HH:mm:ss";


    public static boolean checkLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static String dateConvert(String date, String requestFormat, String responseFormat) {
        if (date != null) {
            SimpleDateFormat response = new SimpleDateFormat(responseFormat, Locale.ENGLISH);
            SimpleDateFormat request = new SimpleDateFormat(requestFormat, Locale.ENGLISH);

            try {
                Date d = request.parse(date);
                return response.format(d);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static Calendar dateStringConvert(String date, String requestFormat) {
        Calendar cal = Calendar.getInstance();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(requestFormat, Locale.ENGLISH);
            cal.setTime(sdf.parse(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cal;
    }

    public static String getCurrentDate(String responseFormat) {
        Calendar calendar = Calendar.getInstance();
        return dateFormat(calendar, responseFormat);
    }

    public static String dateFormat(Calendar calendar, @NonNull String responseFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(responseFormat, Locale.ENGLISH);

        return sdf.format(calendar.getTime());
    }

    public static String formatCurrency(float currency) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        String current = formatter.format(currency);
        return current.replaceAll("\\.", ",");
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

    public static String newGuid() {
        return "00000000-0000-0000-0000-000000000000";
    }

    /*
     * JsonElement element = new Gson().toJsonTree(result.getData(), new TypeToken<List<InpatientDomain>>() {}.getType());
     * List<InpatientDomain> lst = getList(InpatientDomain[].class, element.toString());
     */
    public static <T> List<T> getList(final Class<T[]> clazz, final String json) {
        final T[] jsonToObject = new Gson().fromJson(json, clazz);

        return Arrays.asList(jsonToObject);
    }

    public static void keyboardClose(Context context, View v) {
        ((InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    public static String convertHexStrToUnicode(String hexString) {
        StringBuilder output = new StringBuilder();
        if (hexString.length() < 2)
            return "";
        for (int i = 0; i < hexString.length(); i += 2) {
            String s = hexString.substring(i, i + 2);
            output.append((char) Integer.parseInt(s, 16));
        }

        String s = "";
        try {
            s = new String(output.toString().getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return s;
    }
}
