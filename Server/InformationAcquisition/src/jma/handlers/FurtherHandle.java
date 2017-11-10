package jma.handlers;

import java.util.ArrayList;
import java.util.Set;

public class FurtherHandle extends OriginalHandle {

	private static int[] CRED={2,8,10,17,18,19};
	private static int[] LOAN={1,7,9,11,12,13,14,15,16,20,21,22,23,24};


	private int creditCardNumber;
	private int creditCardAverageAmount;
	private int creditCardMaxAmount;
	private int creditCardPreviousTime;
	private int creditCardLaterTime;
	private String creditTime;
	private int loanNumber;
	private int loanAverageAmount;
	private int loanMaxAmount;
	private int loanPreviousTime;
	private int loanLaterTime;
	private String loanTime;

	public FurtherHandle(String originalString){
		super(originalString);
		//super.go();
		
	}

	public void furtherGo(){
		//信用卡处理
		ArrayList<Integer> Cset=intersect(digitalLabelForQuestions,CRED);
		creditCardHandle(Cset);
		//贷款处理
		ArrayList<Integer> Lset=intersect(digitalLabelForQuestions,LOAN);
		LoanHandle(Lset);
		//时间处理
		timeHandle();
		

	}

	
	private void timeHandle(){
		String[]  tempa=creditTime.split("至");
		if(tempa.length==1){
			creditCardPreviousTime=timeToInteger(tempa[0]);
			creditCardLaterTime=timeToInteger(tempa[0]);
		}else if(tempa.length==2){
			creditCardPreviousTime=timeToInteger(timeCmp(tempa[0],tempa[1])<0?tempa[0]:tempa[1]);
			creditCardLaterTime=timeToInteger(timeCmp(tempa[0],tempa[1])<0?tempa[1]:tempa[0]);
		}else if(tempa.length==3){
			creditCardPreviousTime=timeToInteger(timeCmp(tempa[0],tempa[1])<0?(timeCmp(tempa[0],tempa[2])<0?tempa[0]:tempa[2]):(timeCmp(tempa[1],tempa[2])<0?tempa[1]:tempa[2]));
			creditCardLaterTime=timeToInteger(timeCmp(tempa[0],tempa[1])>0?(timeCmp(tempa[0],tempa[2])>0?tempa[0]:tempa[2]):(timeCmp(tempa[1],tempa[2])>0?tempa[1]:tempa[2]));
		}
		String[]  tempb=loanTime.split("至");
		if(tempb.length==1){
			loanPreviousTime=timeToInteger(tempb[0]);
			loanLaterTime=timeToInteger(tempb[0]);
		}else if(tempb.length==2){
			creditCardPreviousTime=timeToInteger(timeCmp(tempb[0],tempb[1])<0?tempb[0]:tempb[1]);
			loanLaterTime=timeToInteger(timeCmp(tempb[0],tempb[1])<0?tempb[1]:tempb[0]);
		}else if(tempb.length==3){
			loanPreviousTime=timeToInteger(timeCmp(tempb[0],tempb[1])<0?(timeCmp(tempb[0],tempb[2])<0?tempb[0]:tempb[2]):(timeCmp(tempb[1],tempb[2])<0?tempb[1]:tempb[2]));
			loanLaterTime=timeToInteger(timeCmp(tempb[0],tempb[1])>0?(timeCmp(tempb[0],tempb[2])>0?tempb[0]:tempb[2]):(timeCmp(tempb[1],tempb[2])>0?tempb[1]:tempb[2]));
		}
	}
	
	
	public int getCreditCardNumber(){
		return creditCardNumber;
	}

	public int getCreditCardAverageAmount(){
		return creditCardAverageAmount;
	}

	public int getCreditCardMaxAmount(){
		return creditCardMaxAmount;
	}

	public int getCreditCardPreviousTime(){
		return creditCardPreviousTime;
	}
	public int getCreditCardLaterTime(){
		return creditCardLaterTime;
	}
	public String getCreditTime(){
		return creditTime;
	}

	public int getLoanNumber(){
		return loanNumber;
	}

	public int getLoanAverageAmount(){
		return loanAverageAmount;
	}

	public int getLoanMaxAmount(){
		return loanMaxAmount;
	}

	public int getLoanPreviousTime(){
		return loanPreviousTime;
	}
	public int getLoanLaterTime(){
		return loanLaterTime;
	}
	public String getLoanTime(){
		return loanTime;
	}


	private void LoanHandle(ArrayList<Integer> Lset){
		int[] odd={7,21,1};
		int[] even={11,9,22,16,14,13,15,12,20,24,23};
		if (Lset.size()==1){
			loanNumber=0;//
			int pos=find(digitalLabelForQuestions,Lset.get(0));
			loanTime=timeForQuestions[pos];
			if(Lset.contains(11)){
				loanAverageAmount=calculateAverageAmount(pos);
				loanMaxAmount=calculateMaxAmount(pos);
			}
		}else if(Lset.size()==2){
			if(intersect(digitalLabelForQuestions,odd).size()!=0 && intersect(digitalLabelForQuestions,even).size()==0){
				int pos1=find(digitalLabelForQuestions,Lset.get(0));
				int pos2=find(digitalLabelForQuestions,Lset.get(1));
				if (timeCmp(timeForQuestions[pos1],timeForQuestions[pos2])==0){
					loanNumber=1;
					loanTime=timeForQuestions[pos2];
				}else{
					loanNumber=0;
					loanTime=timeForQuestions[pos1]+"and"+timeForQuestions[pos2];
				}
			}else if(intersect(digitalLabelForQuestions,odd).size()==1){
				loanNumber=1;
				int tempNum=intersect(digitalLabelForQuestions,even).get(0);
				int pos=find(digitalLabelForQuestions,tempNum);
				loanTime=timeForQuestions[pos];
				if(tempNum==11 || tempNum==14 || tempNum==15){
					loanAverageAmount=calculateAverageAmount(pos);
					loanMaxAmount=calculateMaxAmount(pos);
				}
			}else{
				int pos1=find(digitalLabelForQuestions,Lset.get(0));
				int pos2=find(digitalLabelForQuestions,Lset.get(1));
				if (timeCmp(timeForQuestions[pos1],timeForQuestions[pos2])==0){
					loanNumber=1;
					loanTime=timeForQuestions[pos1];
				}else{
					loanNumber=2;
					loanTime=timeForQuestions[pos1]+"至"+timeForQuestions[pos2];
				}
				if(Lset.get(0)==11 || Lset.get(0)==14 || Lset.get(0)==15  ){
					loanAverageAmount=calculateAverageAmount(pos1);
					loanMaxAmount=calculateMaxAmount(pos1);
				}
				if(Lset.get(1)==11 || Lset.get(1)==14 || Lset.get(1)==15  ){
					loanAverageAmount=calculateAverageAmount(pos2);
					loanMaxAmount=calculateMaxAmount(pos2);
				}
			}

		}else if(Lset.size()>=3){
			ArrayList<Integer> tempSet=intersect(digitalLabelForQuestions,even);
			if(tempSet.size()==1){
				loanNumber=1;
				int pos=find(digitalLabelForQuestions,tempSet.get(0));
				loanTime=timeForQuestions[pos];
				if (tempSet.get(0)==11 ||tempSet.get(0)==14 ||tempSet.get(0)==15){
					loanAverageAmount=calculateAverageAmount(pos);
					loanMaxAmount=calculateMaxAmount(pos);
				}
			}else if(tempSet.size()==2){
				int pos1=find(digitalLabelForQuestions,Lset.get(0));
				int pos2=find(digitalLabelForQuestions,Lset.get(1));
				if (timeCmp(timeForQuestions[pos1],timeForQuestions[pos2])==0){
					loanNumber=1;
					loanTime=timeForQuestions[pos1];
				}else{
					loanNumber=2;
					loanTime=timeForQuestions[pos1]+"至"+timeForQuestions[pos2];
				}
				if(Lset.get(0)==11 || Lset.get(0)==14 || Lset.get(0)==15  ){
					loanAverageAmount=calculateAverageAmount(pos1);
					loanMaxAmount=calculateMaxAmount(pos1);
				}
				if(Lset.get(1)==11 || Lset.get(1)==14 || Lset.get(1)==15  ){
					loanAverageAmount=calculateAverageAmount(pos2);
					loanMaxAmount=calculateMaxAmount(pos2);
				}
			}
		}

	}

	private void creditCardHandle(ArrayList<Integer> Cset){
		if (Cset.size()==1){
			creditCardNumber=1;
			int pos=find(digitalLabelForQuestions,Cset.get(0));
			creditTime=timeForQuestions[pos];
			if(Cset.contains(10)){
				creditCardAverageAmount=calculateAverageAmount(pos);
				creditCardMaxAmount    =calculateMaxAmount(pos);
			}
		}else if(Cset.size()==2){
			if(Cset.contains(8)&&Cset.contains(10)){
				int pos1=find(digitalLabelForQuestions,8);
				int pos2=find(digitalLabelForQuestions,10);
				creditCardAverageAmount=calculateAverageAmount(pos2);
				creditCardMaxAmount    =calculateMaxAmount(pos2);
				if (timeCmp(timeForQuestions[pos1],timeForQuestions[2])==0){
					creditCardNumber=1;
					creditTime=timeForQuestions[pos1];
				}else{
					creditCardNumber=2;
					creditTime=timeForQuestions[pos1]+"至"+timeForQuestions[pos2];
				}
			}else if(Cset.contains(18)&&Cset.contains(10)){
				int pos1=find(digitalLabelForQuestions,18);
				int pos2=find(digitalLabelForQuestions,10);
				creditCardAverageAmount=calculateAverageAmount(pos2);
				creditCardMaxAmount    =calculateMaxAmount(pos2);
				if (timeCmp(timeForQuestions[pos1],timeForQuestions[2])==0){
					creditCardNumber=1;
					creditTime=timeForQuestions[pos1];
				}else{
					creditCardNumber=2;
					creditTime=timeForQuestions[pos1]+"至"+timeForQuestions[pos2];
				}
			}else if (Cset.contains(17)&&Cset.contains(10)){
				int pos1=find(digitalLabelForQuestions,17);
				int pos2=find(digitalLabelForQuestions,10);
				creditCardAverageAmount=calculateAverageAmount(pos2);
				creditCardMaxAmount    =calculateMaxAmount(pos2);
				if(timeCmp(timeForQuestions[pos1],timeForQuestions[2])==0){
					creditCardNumber=1;
					creditTime=timeForQuestions[pos1];
				}else{
					creditCardNumber=2;
					creditTime=timeForQuestions[pos1]+"至"+timeForQuestions[pos2];
				}
			}else if(Cset.contains(2)&& Cset.contains(10)){
				creditCardNumber=1;
				int pos=find(digitalLabelForQuestions,10);
				creditTime=timeForQuestions[pos];
				creditCardAverageAmount=calculateAverageAmount(pos);
				creditCardMaxAmount=calculateMaxAmount(pos);
			}else if(Cset.contains(2)&& Cset.contains(19)){
				creditCardNumber=1;
				int pos=find(digitalLabelForQuestions,19);
				creditTime=timeForQuestions[pos];
				creditCardAverageAmount=calculateAverageAmount(pos);
				creditCardMaxAmount=calculateMaxAmount(pos);
			}else if(Cset.contains(8)&& Cset.contains(19)){
				int pos1=find(digitalLabelForQuestions,19);
				int pos2=find(digitalLabelForQuestions,8);
				creditCardAverageAmount=calculateAverageAmount(pos1);
				creditCardMaxAmount=calculateMaxAmount(pos1);
				if(timeCmp(timeForQuestions[pos1],timeForQuestions[pos2])==0){
					creditCardNumber=2;
					creditTime=timeForQuestions[pos1];
				}else{
					creditCardNumber=2;
					creditTime=timeForQuestions[pos1]+"至"+timeForQuestions[pos2];
				}
			}else if(Cset.contains(18)&& Cset.contains(19)){
				int pos=find(digitalLabelForQuestions,19);
				creditCardAverageAmount=calculateAverageAmount(pos);
				creditCardMaxAmount=calculateMaxAmount(pos);
				creditCardNumber=2;
				creditTime=timeForQuestions[pos];
			}else if (Cset.contains(17)&& Cset.contains(19)){
				int pos=find(digitalLabelForQuestions,19);
				creditCardAverageAmount=calculateAverageAmount(pos);
				creditCardMaxAmount=calculateMaxAmount(pos);
				creditCardNumber=2;
				creditTime=timeForQuestions[pos];
			}else if (Cset.contains(2)&& Cset.contains(8)){
				int pos=find(digitalLabelForQuestions,8);
				creditTime=timeForQuestions[pos];
				creditCardNumber=1;
			}else if (Cset.contains(2)&& Cset.contains(18)){
				int pos=find(digitalLabelForQuestions,18);
				creditTime=timeForQuestions[pos];
				creditCardNumber=1;
			}else if (Cset.contains(2)&& Cset.contains(19)){
				int pos=find(digitalLabelForQuestions,19);
				creditTime=timeForQuestions[pos];
				creditCardNumber=1;
			}else
				System.out.println("error");
		}


		else if (Cset.size()>=3){
			creditCardNumber=3;
			creditTime="...";
			creditCardAverageAmount=1111;
			creditCardMaxAmount=1111;
		}
	}

	private int calculateAverageAmount(int pos){
		String one=questionsAndAnswers[pos][2];
		String two=questionsAndAnswers[pos][3];
		one = one.substring(one.indexOf('-')+1);
		two = two.substring(two.indexOf('-')+1);
		return (Integer.parseInt(one)+Integer.parseInt(two))/2;

	}

	private int calculateMaxAmount(int pos){
		String one =questionsAndAnswers[pos][4];
		one=one.substring(one.indexOf('-')+1);
		return Integer.parseInt(one);
	}

	private int find(int[] A,int x){
		for(int i=0;i<A.length;i++)
			if(A[i]==x)
				return i;
		return -1;
	}

	private ArrayList<Integer> intersect(int[] A,int [] B){
		ArrayList<Integer> C=new ArrayList<Integer>();
		for (int i=0;i<A.length;i++){
			for(int j=0;j<B.length;j++)
				if(A[i]==B[j])
					C.add(A[i]);
		}
		
		return C;
	}

	private int timeCmp(String A,String B){
		//A or B is the form of "****年**月" or "****年**月至****年**月"
		if(A.indexOf("至")==-1 && B.indexOf("至")==-1)//two time point
			return timeToInteger(A)==timeToInteger(B)?0:(timeToInteger(A)<timeToInteger(B)?-1:1);
		if(A.indexOf("至")!=-1 && B.indexOf("至")!=-1)//two time period
			//时间段只有相等和不等，并且在同一个人的时间段应该是相等的，可以做验证，所以只返回等号或者不等
			if(A.equals(B))
				return 0;
			else
				return -1;
		else{
			//先统一成A是time point，B是time period
			if(A.indexOf("至")!=-1){
				String tmp=A;
				A=B;
				B=tmp;
			}
			if(timeToInteger(A)<timeToInteger(B.substring(0,8)))
				return -1;
			else if(timeToInteger(A)<timeToInteger(B.substring(0,8)))
				return 1;
			else
				return 0;
		}
	}

	private int timeToInteger(String A){
		//A is the form of ****年**月 and return ******
		String tmp=A.substring(0,4)+A.substring(5,7);
		return Integer.parseInt(tmp);
	}

	public static void main(String[] args){
		String StringOri="[{'question':'问题1：2011年01月至2014年07月期间，您办理的所有正在使用的信用卡中，最高授信额度为多少？','answer1':'501-1500','answer2':'1501-2500','answer3':'2501-3500','answer4':'3501-4500','answer5':'以上都不是'},{'question':'问题2：2011年01月至2014年07月期间，您办理的所有正在使用的信用卡中，额度最高的一张信用卡发卡行为哪家？','answer1':'浙江民泰商业银行','answer2':'浙江省农村信用社联合社','answer3':'交通银行','answer4':'中国银行','answer5':'以上都不是'},{'question':'问题3：您在2014年09月的通讯地址是哪里？','answer1':'石嘴山市大武口区新世纪花园10-1-302','answer2':'江津市石蟆镇蟆街村','answer3':'苏安新村141幢308','answer4':'湖南省长沙市芙蓉区万家丽中路６８号万家丽家居建材广场一楼长沙','answer5':'以上都不是'},{'question':'问题4：您的首次身份证领取地是哪里？','answer1':'湖南省常德市','answer2':'陕西省','answer3':'香港特别行政区','answer4':'青海省黄南藏族自治州','answer5':'以上都不是'},{'question':'问题5：您在2013年11月办理的贷款每月应还款额（即还款计划表上和银行约定的还款金额）是多少？','answer1':'5001-6000','answer2':'6001-7000','answer3':'7001-8000','answer4':'8001-9000','answer5':'以上都不是'}]";

		FurtherHandle handle=new FurtherHandle(StringOri);
		handle.furtherGo();
		System.out.println("该人有 "+handle.getCreditCardNumber()+" 张信用卡");
		System.out.println(handle.getCreditCardAverageAmount());
		System.out.println(handle.getCreditCardMaxAmount());
		System.out.println(handle.getCreditTime());
		System.out.println(handle.getCreditCardPreviousTime());
		System.out.println(handle.getCreditCardLaterTime());
		//System.out.println(handle.getCreditCardAverageAmount());
		System.out.println("该人贷了 "+handle.getLoanNumber()+" 笔贷款");
		
		//System.out.println(handle.calculateMaxAmount(4));
		//System.out.println(handle.calculateAverageAmount(4));
		//System.out.println(handle.timeCmp("2013年11月","2013年12月至2014年11月"));
	}

}
