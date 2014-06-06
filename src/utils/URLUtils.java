package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLUtils {
	public static String obterDadosUrl(String endereco) {
		URL oracle;
		String inputLine = null;
		StringBuilder builderString = new StringBuilder();
		try {
			oracle = new URL(endereco);
			BufferedReader in = new BufferedReader(
					new InputStreamReader(oracle.openStream()));
			
			while ((inputLine = in.readLine()) != null) {
				builderString.append(inputLine.trim()+ "");
			}
			in.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return builderString.toString();

	}
}
