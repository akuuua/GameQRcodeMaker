package GameQRcodeMaker;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.nio.file.Path;
import java.nio.file.Paths;


public class QRCodeGenerater {
    private String str;
    public QRCodeGenerater(){

    }
    public void makeQRCode(String str)throws Exception {
        this.str = str;
        String path = "QR.jpg";
        BitMatrix BM = new MultiFormatWriter().encode(str, BarcodeFormat.QR_CODE,500,500);
        MatrixToImageWriter.writeToPath(BM,"jpg",Paths.get(path));
        System.out.println("QR code is created successfully and named 'QR.jpg'!!!");

    }
}
