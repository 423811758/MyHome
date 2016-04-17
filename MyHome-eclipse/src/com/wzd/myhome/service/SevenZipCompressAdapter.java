package com.wzd.myhome.service;

import java.io.IOException;

import SevenZip.SevenZipHelper;

import com.wzd.common.util.ICompress;

/**
 * 压缩写入文件适配器，在写入压缩文件时被调用
 * 
 * @author tian
 * 
 */
public class SevenZipCompressAdapter implements ICompress {
	
	@Override
	public byte[] compress(byte[] bytes) throws IOException {
		return SevenZipHelper.Compress(bytes);
	}

}
