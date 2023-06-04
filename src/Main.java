// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws MalformedURLException, IOException {
        String GET_URL = "http://data.fixer.io/api/latest?access_key=a93f7282e66a43e531d5d83fbdf13358";
        URL url = new URL(GET_URL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        int responseCode = httpURLConnection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader((Reader) new InputStreamReader(httpURLConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();

            Scanner input = new Scanner(System.in);

            String fromCurr, toCurr;
            double amount, value;

            fromCurr = "JOD";
            toCurr = "AED";
            amount = 5;

            System.out.print("Enter Currency You Want To Convert From: ");
            fromCurr = input.next();
            System.out.println("");

            System.out.print("Enter Currency You Want To Convert To: ");
            toCurr = input.next();
            System.out.println("");

            System.out.print("Enter Amount: ");
            amount = input.nextDouble();
            System.out.println("");

            // Convert fromCurr Currency to EUR
            JSONObject obj = new JSONObject(response.toString());
            double fromCurrEurRate = obj.getJSONObject("rates").getDouble(fromCurr);
            double eurAmount = amount / fromCurrEurRate;

            // Convert EUR to toCurr Currency
            double eurAedRate = obj.getJSONObject("rates").getDouble(toCurr);
            value = eurAmount * eurAedRate;

            System.out.println(amount + fromCurr + " = " + value + toCurr);

        }
    }
}