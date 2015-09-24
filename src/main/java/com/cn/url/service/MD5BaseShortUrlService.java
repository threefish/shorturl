package com.cn.url.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

public class MD5BaseShortUrlService extends AbstracStoreShortUrlService {

	private static final int BINARY = 0x2;

	private static final int NUMBER_61 = 0x0000003d;

	@Override
	protected String shorten(String longUrl) {
		String shortUrl = shorten(longUrl, 5);
		if (StringUtils.isEmpty(shortUrl)) {
			shortUrl = shorten(longUrl, 6);
		}
		return shortUrl;
	}

	public String shorten(String longUrl, int urlLength) {
		if (urlLength < 0 || urlLength > 6) {
			throw new IllegalArgumentException("the length of url must be between 0 and 6");
		}
		String md5Hex = DigestUtils.md5Hex(longUrl);
		// 6 digit binary can indicate 62 letter & number from 0-9a-zA-Z
		int binaryLength = urlLength * 6;
		long binaryLengthFixer = Long.valueOf(StringUtils.repeat("1", binaryLength), BINARY);
		for (int i = 0; i < 4; i++) {
			String subString = StringUtils.substring(md5Hex, i * 8, (i + 1) * 8);
			subString = Long.toBinaryString(Long.valueOf(subString, 16) & binaryLengthFixer);
			subString = StringUtils.leftPad(subString, binaryLength, "0");
			StringBuilder sbBuilder = new StringBuilder();
			for (int j = 0; j < urlLength; j++) {
				String subString2 = StringUtils.substring(subString, j * 6, (j + 1) * 6);
				int charIndex = Integer.valueOf(subString2, BINARY) & NUMBER_61;
				sbBuilder.append(DIGITS[charIndex]);
			}
			String shortUrl = sbBuilder.toString();
			if (lookupLong(shortUrl) != null) {
				continue;
			} else {
				return shortUrl;
			}
		}
		// if all 4 possibilities are already exists
		return null;
	}

}
