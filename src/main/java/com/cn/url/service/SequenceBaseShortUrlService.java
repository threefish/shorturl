package com.cn.url.service;

import java.util.concurrent.atomic.AtomicLong;

public class SequenceBaseShortUrlService extends AbstracStoreShortUrlService {

	//this sequence count be a real database sequence or others
	//此处应为数据表最大值
	private AtomicLong sequence = new AtomicLong(urlShortService.maxId());

	@Override
	protected String shorten(String longUrl) {
		long myseq = sequence.incrementAndGet();
		String shortUrl = to62RadixString(myseq);
		return shortUrl;
	}

	private String to62RadixString(long seq) {
		StringBuilder sBuilder = new StringBuilder();
		while (true) {
			int remainder = (int) (seq % 62);
			sBuilder.append(DIGITS[remainder]);
			seq = seq / 62;
			if (seq == 0) {
				break;
			}
		}
		return sBuilder.toString();
	}

}
