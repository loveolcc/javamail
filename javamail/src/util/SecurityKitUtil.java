package util;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Hex;

/**
 * Servlet implementation class SecurityKitUtil
 */
@WebServlet("/sign")
public class SecurityKitUtil extends HttpServlet {
	public static final String TOKEN = "lovecc"; 
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SecurityKitUtil() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 try {  
	            // �������ύ��Ϣ��΢�ŷ�����������GET������д�ķ�������ַURL�ϣ�GET����Я������  
	            String signature = request.getParameter("signature");// ΢�ż���ǩ����token��timestamp��nonce����  
	            String timestamp = request.getParameter("timestamp");// ʱ���  
	            String nonce = request.getParameter("nonce");// �����  
	            String echostr = request.getParameter("echostr");// ����ַ���  
	            PrintWriter out = response.getWriter();  
	            // ��token��timestamp��nonce�������������ֵ�������  
	            String[] params = new String[] { TOKEN, timestamp, nonce };  
	            Arrays.sort(params);  
	            // �����������ַ���ƴ�ӳ�һ���ַ�������sha1����  
	            String clearText = params[0] + params[1] + params[2];  
	            String algorithm = "SHA-1";  
	            String sign = new String(  
	                    Hex.encodeHex(MessageDigest.getInstance(algorithm).digest((clearText).getBytes()), true));  
	            // �����߻�ü��ܺ���ַ�������signature�Աȣ���ʶ��������Դ��΢��  
	            if (signature.equals(sign)) {  
	                response.getWriter().print(echostr);  
	            }  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        } 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
