package naver.aiservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Controller
public class PapagoTranslate {

	@GetMapping("/naver/papago")
	public String papago() {
		return "papago/papagopage";
	}
	
	@PostMapping("/naver/trans")
	@ResponseBody
	public Map<String, String> trans(@RequestParam String message, @RequestParam String lang){

		Map<String, String> map = new HashMap<>();


		String clientId = "d27pqd5pl3";//애플리케이션 클라이언트 아이디값";
		String clientSecret = "jSuVKOT9Dhaz8j2DuT9XsRBiJsWgRTj8OqQQe96N";//애플리케이션 클라이언트 시크릿값";
		try {
			String text = URLEncoder.encode(message, "UTF-8");
			String apiURL = "https://naveropenapi.apigw.ntruss.com/nmt/v1/translation";
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
			// post request
			String postParams = "source=ko&target="+lang+"&text=" + text;
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			wr.writeBytes(postParams);
			wr.flush();
			wr.close();
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if(responseCode==200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {  // 오류 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			System.out.println(response.toString());
			map.put("text", response.toString());
		} catch (Exception e) {
			System.out.println(e);
		}

		

		return map;

	}
}
