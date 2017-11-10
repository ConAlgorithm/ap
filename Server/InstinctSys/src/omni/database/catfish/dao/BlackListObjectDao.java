package omni.database.catfish.dao;

import java.util.ArrayList;

import omni.database.catfish.object.BlackListObject;

public interface BlackListObjectDao 
{
	
	ArrayList<BlackListObject> getBlackListObject(int numOfList);

	ArrayList<BlackListObject> getAllIABlackListObject();

	int getNumOfIABlackListObject();

}
