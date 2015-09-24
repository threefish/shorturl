package com.cn.url.service;

import com.cn.db.service.UrlShortServiceImpl;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public abstract class AbstracStoreShortUrlService implements ShortUrlService {

	protected UrlShortServiceImpl urlShortService=new UrlShortServiceImpl();

	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	@Override
	public String lookupLong(String shortUrl) {
		String longUrl = null;
		Lock readLock = readWriteLock.readLock();
		try {
			readLock.lock();
			longUrl = urlShortService.fetchLurl(shortUrl);
		} catch (Exception e) {
		} finally {
			readLock.unlock();
		}
		return longUrl;
	}

	@Override
	public String convertShort(String longUrl) {
		String shortUrl = lookupShort(longUrl);
		if (StringUtils.isEmpty(shortUrl)) {
			shortUrl = shorten(longUrl);
			if (StringUtils.isEmpty(shortUrl)) {
				System.err.println("Cannot convert long url to short url");
			} else {
				synchronizedResult(longUrl, shortUrl);
			}
		}
		return new StringBuilder(shortUrl).toString();
	}

	protected String lookupShort(String longUrl) {
		String shortUrl = null;
		Lock readLock = readWriteLock.readLock();
		try {
			readLock.lock();
			shortUrl = urlShortService.fetchSurl(longUrl);
		} catch (Exception e) {
		} finally {
			readLock.unlock();
		}
		return shortUrl;
	}

	protected void synchronizedResult(String longUrl, String shortUrl) {
		Lock writeLock = readWriteLock.writeLock();
		try {
			writeLock.lock();
			urlShortService.add(shortUrl,longUrl);
		} catch (Exception e) {
		} finally {
			writeLock.unlock();
		}
	}
	protected abstract String shorten(String longUrl);

}
