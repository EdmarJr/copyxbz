package nx;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import utils.BancoUtils;
import utils.URLUtils;

public class Main {


	public static void main(String[] args) {
		int contador = 1;
		String url = URLUtils.obterDadosUrl("http://www.xbzbrindes.com.br/");
		Pattern pattern = Pattern.compile("'anylinkmenu.[0-9]?'\\)\"><a href=\"(http://www\\.xbzbrindes\\.com\\.br/produtos\\.asp\\?cat=[0-9]*)\">([éúíóáÉÚÍÓÁèùìòàÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄA-Za-z0-9\\s]*)</a>");
		Matcher matcher = pattern.matcher(url);
		while(matcher.find()) {
			int idCategoriaPai = contador;
			System.out.println("-----------------------");
			System.out.println(matcher.group(2));
			System.out.println(matcher.group(1));
			String sql = "INSERT INTO CATEGORIA(id,endereco,nome) VALUES ("+contador+",'"+matcher.group(1)+"','"+matcher.group(2)+"')";
			contador++;
			try {
				BancoUtils.obterStatement().execute(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("-----------------------");
			String pedacoCodigoCategoriaPai = obterPedacoCodigoCategoriaPai(
					url, matcher);
			Pattern patternTemp = Pattern.compile("<a href=\"(http://www\\.xbzbrindes\\.com\\.br/produtos\\.asp\\?suc=[0-9]*)\">([éúíóáÉÚÍÓÁèùìòàÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄA-Za-z0-9\\s]*)</a>");
			Matcher matcherTemp = patternTemp.matcher(pedacoCodigoCategoriaPai);
			while(matcherTemp.find()) {
				System.out.println(matcherTemp.group(2));
				System.out.println(matcherTemp.group(1));
				String sqlTemp = "INSERT INTO CATEGORIA(id,endereco,id_categoria,nome) VALUES ("+contador+",'"+matcherTemp.group(1)+"',"+idCategoriaPai+",'"+matcherTemp.group(2)+"')";
				contador++;
				try {
					BancoUtils.obterStatement().execute(sqlTemp);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		BancoUtils.comitar();
		BancoUtils.fecharTudo();
	}

	private static String obterPedacoCodigoCategoriaPai(String url,
			Matcher matcher) {
		String urlTemp = url.substring(matcher.end(1),url.length());
		String pedacoCodigoCategoriaPai = urlTemp.substring(0, urlTemp.indexOf("</div>"));
		return pedacoCodigoCategoriaPai;
	}

	

}
