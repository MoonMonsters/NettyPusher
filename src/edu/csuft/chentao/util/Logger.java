/**
 * ��ӡ���
 */
package edu.csuft.chentao.util;

/**
 * @author csuft.chentao
 *
 * 2016��12��8�� ����9:45:32
 */
public class Logger {

	private static boolean isPrint = false;
	
	public static void log(String msg){
		if(isPrint){
			System.out.println(msg);	
		}
	}
}
