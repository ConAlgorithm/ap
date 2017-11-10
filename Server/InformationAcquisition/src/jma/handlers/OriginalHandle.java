package jma.handlers;
import java.util.ArrayList;
//import java.util.List;
import java.util.regex.*;//for Regular Expression

import com.google.gson.*;
//import com.google.gson.reflect.*;  // for typetoken

import com.google.gson.reflect.TypeToken;

public class OriginalHandle {
	//private String birthYearAndMon;
	private String originalString;
	protected String[][] questionsAndAnswers=new String[5][5];
	protected int[] digitalLabelForQuestions=new int[5];
	protected String[] timeForQuestions=new String[5]; 
		
	public OriginalHandle(String str){
		originalString=str;
		go();
	}
	
	public void go(){
		//提取问题和答案
		questionsAndAnswersHandle();
		//给问题数字标号
		digitalLabelHandle();
		//提取问题的时间信息
		timeHandle();
	}

	public int[] getDigitalLabelForQuestions(){
		return digitalLabelForQuestions;
	}
	
	public String[][] getQuestionsAndAnswers(){
		return questionsAndAnswers;
	}
	
	public String[] getTimeForQuestions(){
		return timeForQuestions;
	}


	private void timeHandle(){
		for (int i=0;i<5;i++){
			String source=questionsAndAnswers[i][0];
			String regCharSet="([0-9]{4}[\u4E00-\u9FA5][0-9]{2}[\u4E00-\u9FA5]([\u4E00-\u9FA5][0-9]{4}[\u4E00-\u9FA5][0-9]{2}[\u4E00-\u9FA5])?)";
			Pattern p=Pattern.compile(regCharSet);
			Matcher m = p.matcher(source); 
			while(m.find()){
				timeForQuestions[i]=m.group(1);
			}
		}
	}


	/*
	private void birthYearAndMonHandle(Ka ka){
		birthYearAndMon=ka.getCreditNum().substring(6,12);
	}
*/
	private  void questionsAndAnswersHandle(){
		//String json = "{'data1':100,'data2':'hello'}";  
		Gson gson = new Gson();  
		ArrayList<CreditQuestion> obj=gson.fromJson(originalString, new TypeToken<ArrayList<CreditQuestion>>(){}.getType());
		for(int ii=0;ii<5;ii++){
			questionsAndAnswers[ii][0]=obj.get(ii).question;
			questionsAndAnswers[ii][1]=obj.get(ii).answer1;
			questionsAndAnswers[ii][2]=obj.get(ii).answer2;
			questionsAndAnswers[ii][3]=obj.get(ii).answer3;
			questionsAndAnswers[ii][4]=obj.get(ii).answer4;
			
		}
		

	}
	
	private class CreditQuestion{
		private String question;
		private String answer1;
		private String answer2;
		private String answer3;
		private String answer4;
//		private String answer5;
	}


	private void digitalLabelHandle(){
		for (int i=0;i<5;i++){
			if(questionsAndAnswers[i][0].indexOf("办理的贷款每月应还款额（即还款计划表上和银行约定的还款金额）是多少")!=-1)
				digitalLabelForQuestions[i]=1;
			else if(questionsAndAnswers[i][0].indexOf("曾在以下哪家机构办理过信用卡，并且正在使用")!=-1)
				digitalLabelForQuestions[i]=2;
			else if(questionsAndAnswers[i][0].indexOf("通讯地址是哪里")!=-1)
				digitalLabelForQuestions[i]=3;
			else if(questionsAndAnswers[i][0].indexOf("婚姻状况是什么")!=-1)
				digitalLabelForQuestions[i]=4;
			else if(questionsAndAnswers[i][0].indexOf("学历是什么")!=-1)
				digitalLabelForQuestions[i]=5;
			else if(questionsAndAnswers[i][0].indexOf("首次身份证领取地是哪里")!=-1)
				digitalLabelForQuestions[i]=6;
			else if(questionsAndAnswers[i][0].indexOf("在何处办理过贷款")!=-1)
				digitalLabelForQuestions[i]=7;
			else if(questionsAndAnswers[i][0].indexOf("在哪家机构办理过信用卡")!=-1)
				digitalLabelForQuestions[i]=8;
			else if(questionsAndAnswers[i][0].indexOf("在哪家机构办理过贷款")!=-1)
				digitalLabelForQuestions[i]=9;
			else if(questionsAndAnswers[i][0].indexOf("办理的信用卡额度")!=-1)
				digitalLabelForQuestions[i]=10;
			else if(questionsAndAnswers[i][0].indexOf("办理的贷款额度")!=-1)
				digitalLabelForQuestions[i]=11;
			else if(questionsAndAnswers[i][0].indexOf("您办理的所有贷款中，最近办理的贷款是以下哪种类型")!=-1)
				digitalLabelForQuestions[i]=12;
			else if(questionsAndAnswers[i][0].indexOf("办理的所有贷款中，额度最高的贷款是以下哪种类型")!=-1)
				digitalLabelForQuestions[i]=13;
			else if(questionsAndAnswers[i][0].indexOf("所有贷款中，最近办理的一笔贷款额度为多少")!=-1)
				digitalLabelForQuestions[i]=14;
			else if(questionsAndAnswers[i][0].indexOf("办理的所有贷款中，最高一笔贷款额度为多少")!=-1)
				digitalLabelForQuestions[i]=15;
			else if(questionsAndAnswers[i][0].indexOf("所有贷款中，最近办理的一笔贷款的还款频率是什么")!=-1)
				digitalLabelForQuestions[i]=16;
			else if(questionsAndAnswers[i][0].indexOf("所有正在使用的信用卡中，最近办理的一张信用卡的发卡行为哪家")!=-1)
				digitalLabelForQuestions[i]=17;
			else if(questionsAndAnswers[i][0].indexOf("办理的所有正在使用的信用卡中，额度最高的一张信用卡")!=-1)
				digitalLabelForQuestions[i]=18;
			else if(questionsAndAnswers[i][0].indexOf("办理的所有正在使用的信用卡中，最高授信额度为多少")!=-1)
				digitalLabelForQuestions[i]=19;
			else if(questionsAndAnswers[i][0].indexOf("所有尚未还清的贷款中，最近办理的一笔贷款何时到期")!=-1)
				digitalLabelForQuestions[i]=20;
			else if(questionsAndAnswers[i][0].indexOf("办理了何种贷款")!=-1)
				digitalLabelForQuestions[i]=21;
			else if(questionsAndAnswers[i][0].indexOf("办理的所有贷款中，最近一笔贷款是在哪家商业银行办理")!=-1)
				digitalLabelForQuestions[i]=22;
			else if(questionsAndAnswers[i][0].indexOf("按照您和贷款机构的约定")!=-1)
				digitalLabelForQuestions[i]=23;
			else if(questionsAndAnswers[i][0].indexOf("办理的所有贷款中，最早办理的贷款是以下哪种类型")!=-1)
				digitalLabelForQuestions[i]=24;
			else
				digitalLabelForQuestions[i]=0;
		}	

	}
	
	


	public static void main(String[] agrs){
		//String Num="340824199604164625";
//		String StringOri = "[{" "'|':'问题1：您在2012年07月办理的信用卡额度是多少？','answer1':'7001-17000','answer2':'17001-27000','answer3':'27001-37000','answer4':'37001-47000','answer5':'以上都不是'},{'|':'问题2：您曾在以下哪家机构办理过信用卡，并且正在使用？','answer1':'宁夏银行,上海银行','answer2':'浙江民泰商业银行,华夏银行','answer3':'中国银行,中国工商银行','answer4':'江苏银行,江苏省农村信用社联合社','answer5':'以上都不是'},{'|':'问题3：2011年01月至2014年05月期间，您办理的所有正在使用的信用卡中，额度最高的一张信用卡发卡行为哪家？','answer1':'中国银行','answer2':'平安银行','answer3':'恒丰银行','answer4':'台州银行','answer5':'以上都不是'},{'|':'问题4：您2012年07月的婚姻状况是什么？','answer1':'丧偶','answer2':'已婚','answer3':'未婚','answer4':'离婚','answer5':'以上都不是'},{'|':'问题5：您于2008年12月办理了何种贷款？','answer1':'个人住房贷款','answer2':'个人消费贷款','answer3':'个人助学贷款','answer4':'农户贷款','answer5':'以上都不是'}]'";
		//Ka ka=new Ka(Num,StringOri);
		String StringOri="[{'question':'问题1：2011年01月至2014年07月期间，您办理的所有正在使用的信用卡中，最高授信额度为多少？','answer1':'501-1500','answer2':'1501-2500','answer3':'2501-3500','answer4':'3501-4500','answer5':'以上都不是'},{'question':'问题2：2011年01月至2014年07月期间，您办理的所有正在使用的信用卡中，额度最高的一张信用卡发卡行为哪家？','answer1':'浙江民泰商业银行','answer2':'浙江省农村信用社联合社','answer3':'交通银行','answer4':'中国银行','answer5':'以上都不是'},{'question':'问题3：您在2014年09月的通讯地址是哪里？','answer1':'石嘴山市大武口区新世纪花园10-1-302','answer2':'江津市石蟆镇蟆街村','answer3':'苏安新村141幢308','answer4':'湖南省长沙市芙蓉区万家丽中路６８号万家丽家居建材广场一楼长沙','answer5':'以上都不是'},{'question':'问题4：您的首次身份证领取地是哪里？','answer1':'湖南省常德市','answer2':'陕西省','answer3':'香港特别行政区','answer4':'青海省黄南藏族自治州','answer5':'以上都不是'},{'question':'问题5：您在2013年11月办理的贷款每月应还款额（即还款计划表上和银行约定的还款金额）是多少？','answer1':'5001-6000','answer2':'6001-7000','answer3':'7001-8000','answer4':'8001-9000','answer5':'以上都不是'}]";
		OriginalHandle handle=new OriginalHandle(StringOri);
		//handle.go();
		//System.out.println(handle.birthYearAndMon);
		
		
		//问题及答案
		for (int ii=0;ii<5;ii++)
			for (int jj=0;jj<5;jj++)
			System.out.println(handle.questionsAndAnswers[ii][jj]);
		
		//问题数字标号
		for (int ii:handle.digitalLabelForQuestions)
			System.out.println(ii);
		
		//问题的时间点信息
		for (String w:handle.timeForQuestions)
			System.out.println(w);
	}

}
