package jma.thirdpartyservices.junyu;

import java.util.LinkedList;
import java.util.Queue;

import jma.Configuration;

public class AlternativeUrlsContainer{
	private Queue<String> urlsContainer = new LinkedList<>();
	public AlternativeUrlsContainer()
	{
		urlsContainer.add(Configuration.getJunyuDXUrl());
		urlsContainer.add(Configuration.getJunyuLTUrl());
	}
	
	public String popUrl()
	{
		return urlsContainer.poll();
	}
}