package ptt.vn.icaremobileapp.scanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;
import ptt.vn.icaremobileapp.R;
import ptt.vn.icaremobileapp.loading.Loading;
import ptt.vn.icaremobileapp.model.hi.HiCard;
import ptt.vn.icaremobileapp.utils.Utils;


/**
 * Created by kingpes on 8/23/18.
 */

public class Scanner extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView scannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scanner_activity);

        ViewGroup frame = findViewById(R.id.frameScanner);
        scannerView = new ZXingScannerView(this);
        frame.addView(scannerView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        scannerView.setResultHandler(this);
        scannerView.startCamera();

    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
        Loading.getInstance().show(this);
        String qr = result.getText();
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
        onReturn(hiCard);
    }

    private void onReturn(HiCard hiCard) {
        Intent intent = new Intent();
        intent.putExtra("SCANNER", hiCard);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Loading.getInstance().hide();
    }
}
