package cn.ucai.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

	// �ƶ���134��135��136��137��138��139��150��151��157(TD)��158��159��187��188
	// ����ͨ��130��131��132��152��155��156��185��186
	// �����ţ�133��153��180��189����1349��ͨ��
	public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";

	public static boolean isMobile(String mobile) {
		return Pattern.matches(REGEX_MOBILE, mobile);
	}

}
