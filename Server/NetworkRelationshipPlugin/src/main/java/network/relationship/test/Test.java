package network.relationship.test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import network.relationship.api.StatisticsApi;
import network.relationship.businessdomain.GroupInfo;
import network.relationship.domain.Application;
import catfish.base.CollectionUtils;
import catfish.base.Logger;
import catfish.base.StartupConfig;
import catfish.base.business.dao.EndUserExtentionDao;
import catfish.base.business.object.EndUserExtensionObject;
import catfish.base.dao.Dao;
import catfish.base.database.DatabaseApi;
import catfish.base.httpclient.HttpClientConfig;

import com.google.gson.Gson;

public class Test {

	public static void main( String[] args ){
		StartupConfig.initialize();
		Logger.initialize();
		HttpClientConfig.initialize();
		
		List<Application> apps = new ArrayList<Application>();
		apps.add(new Application("C7071A26-90CB-4230-A10E-16BC9DBAE18B"));
		GroupInfo groupInfo = new GroupInfo();
		StatisticsApi.getStatisticsFromApps(apps, groupInfo);
//		List<String> idNumbersFromRisk = null;
//		try {
//			idNumbersFromRisk = new CsvParser("C:\\Users\\dell\\Desktop\\Copy of 144.csv").getAllIdNumbers();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		List<String> idNumbersFromDev = new JsonParser().getIdNumbers();
//		
//		for (int i = 0; i < idNumbersFromDev.size(); i++) {
//			int count = 0;
//			for(int j = 0; j < idNumbersFromRisk.size(); ++j){
//				if(idNumbersFromDev.get(i).equalsIgnoreCase(idNumbersFromRisk.get(j))){
//					count++;
//					String in = idNumbersFromRisk.remove(j);
//					if(count > 1){
//						Logger.get().error(count);
//						Logger.get().error(in);
//					}
//				}
//			}
//		}
//		
//		for(String s : idNumbersFromRisk){
//			Logger.get().info(s);
//		}
	}
	
	public static class JsonParser {
		public class User {
			private String idName;
			private String idNumber;

			public String getIdNumber() {
				return idNumber;
			}

			public void setIdNumber(String idNumber) {
				this.idNumber = idNumber;
			}

			public String getIdName() {
				return idName;
			}

			public void setIdName(String idName) {
				this.idName = idName;
			}
		}

		private String jsonString = "[{\"idName\":\"王振洋\",\"idNumber\":\"341622199408250910\"},{\"idName\":\"李唐红\",\"idNumber\":\"32012419850721281X\"},{\"idName\":\"李振龙\",\"idNumber\":\"61032319941203211X\"},{\"idName\":\"韩程\",\"idNumber\":\"321281198911092278\"},{\"idName\":\"李强维\",\"idNumber\":\"142731198911244818\"},{\"idName\":\"郭飞飞\",\"idNumber\":\"320322198701115915\"},{\"idName\":\"李轲\",\"idNumber\":\"411327199502160616\"},{\"idName\":\"张国平\",\"idNumber\":\"51080219900722171X\"},{\"idName\":\"李腾\",\"idNumber\":\"610431199205150637\"},{\"idName\":\"权凯\",\"idNumber\":\"610526198805121311\"},{\"idName\":\"李正武\",\"idNumber\":\"433127198810206639\"},{\"idName\":\"牛吉星\",\"idNumber\":\"411423198806252072\"},{\"idName\":\"牛力\",\"idNumber\":\"152201198002070052\"},{\"idName\":\"王红波\",\"idNumber\":\"320724198907193315\"},{\"idName\":\"郭雪健\",\"idNumber\":\"612732199111112212\"},{\"idName\":\"马建校\",\"idNumber\":\"130182198111261410\"},{\"idName\":\"刘焕坤\",\"idNumber\":\"420525199512300810\"},{\"idName\":\"董诗冬\",\"idNumber\":\"320321199103010230\"},{\"idName\":\"高稳荣\",\"idNumber\":\"370406198708051824\"},{\"idName\":\"廖勇\",\"idNumber\":\"362233199212110010\"},{\"idName\":\"黄刚\",\"idNumber\":\"420526199610021619\"},{\"idName\":\"宋鑫\",\"idNumber\":\"422822199309254037\"},{\"idName\":\"李黔川\",\"idNumber\":\"520202199511214454\"},{\"idName\":\"李路凯\",\"idNumber\":\"522702199609304756\"},{\"idName\":\"祝靖\",\"idNumber\":\"422802199609035412\"},{\"idName\":\"王世杰\",\"idNumber\":\"341021198904062253\"},{\"idName\":\"朱士勇\",\"idNumber\":\"370404198701145411\"},{\"idName\":\"刘仁杰\",\"idNumber\":\"522127199607035016\"},{\"idName\":\"肖刘佳\",\"idNumber\":\"320721199412082272\"},{\"idName\":\"贺兴\",\"idNumber\":\"37088219830312371X\"},{\"idName\":\"高杰\",\"idNumber\":\"411425198805301218\"},{\"idName\":\"侯磊\",\"idNumber\":\"411403198701035471\"},{\"idName\":\"李军\",\"idNumber\":\"321283199012184037\"},{\"idName\":\"陈根\",\"idNumber\":\"321283199002010615\"},{\"idName\":\"罗健\",\"idNumber\":\"342626199512054916\"},{\"idName\":\"来红伟\",\"idNumber\":\"62302219960412001X\"},{\"idName\":\"韦黑虎\",\"idNumber\":\"410328198907179690\"},{\"idName\":\"臧天河\",\"idNumber\":\"41132919891217105X\"},{\"idName\":\"寇建波\",\"idNumber\":\"320826199301024253\"},{\"idName\":\"郭强\",\"idNumber\":\"140424199401096416\"},{\"idName\":\"陈光砚\",\"idNumber\":\"321324198801194618\"},{\"idName\":\"汪军\",\"idNumber\":\"51312219860630271X\"},{\"idName\":\"潘伟\",\"idNumber\":\"342425199208202737\"},{\"idName\":\"刘小慧\",\"idNumber\":\"410328199308071025\"},{\"idName\":\"王晓隆\",\"idNumber\":\"410184198912117718\"},{\"idName\":\"李成\",\"idNumber\":\"430502198809125016\"},{\"idName\":\"黄招\",\"idNumber\":\"45092319960121592x\"},{\"idName\":\"杨四元\",\"idNumber\":\"411323199304225313\"},{\"idName\":\"李本顺\",\"idNumber\":\"320321199305172211\"},{\"idName\":\"周磊\",\"idNumber\":\"412725199004017470\"},{\"idName\":\"黄振\",\"idNumber\":\"411524198809220596\"},{\"idName\":\"伍习勇\",\"idNumber\":\"430524198504270974\"},{\"idName\":\"杨帆\",\"idNumber\":\"140522199209190018\"},{\"idName\":\"杨文书\",\"idNumber\":\"511528199102117839\"},{\"idName\":\"吕鑫\",\"idNumber\":\"230126199304160592\"},{\"idName\":\"张其海\",\"idNumber\":\"371327198508185155\"},{\"idName\":\"刘欢\",\"idNumber\":\"340828199506223316\"},{\"idName\":\"闻宇\",\"idNumber\":\"340323199303076097\"},{\"idName\":\"张明翔\",\"idNumber\":\"320381199305070617\"},{\"idName\":\"王睿\",\"idNumber\":\"622427199407015491\"},{\"idName\":\">张祖桐\",\"idNumber\":\"371427198901071010\"},{\"idName\":\"郭立龙\",\"idNumber\":\"371421198712011678\"},{\"idName\":\"张涛\",\"idNumber\":\"652222199505150817\"},{\"idName\":\"方伟\",\"idNumber\":\"430624199002017311\"},{\"idName\":\"张爱\",\"idNumber\":\"320324199011270632\"},{\"idName\":\"于凯宇\",\"idNumber\":\"431225199202130816\"},{\"idName\":\"孙春雷\",\"idNumber\":\"230208198805080054\"},{\"idName\":\"王坚\",\"idNumber\":\"320926198112105813\"},{\"idName\":\"杨娟\",\"idNumber\":\"320525198908262621\"},{\"idName\":\"原天宇\",\"idNumber\":\"622101199605143515\"},{\"idName\":\"孙建伟\",\"idNumber\":\"412825199002196431\"},{\"idName\":\"封蓉\",\"idNumber\":\"321283199110107641\"},{\"idName\":\"温志伟\",\"idNumber\":\"413026198501024211\"},{\"idName\":\"王帅帅\",\"idNumber\":\"142724198810102737\"},{\"idName\":\"唐子源\",\"idNumber\":\"320922199506210092\"},{\"idName\":\"朱莹\",\"idNumber\":\"321283199603143421\"},{\"idName\":\"石举敏\",\"idNumber\":\"612326198411192417\"},{\"idName\":\"曹琪\",\"idNumber\":\"342221199012123109\"},{\"idName\":\"田永望\",\"idNumber\":\"522226198610275234\"},{\"idName\":\"丁龙海\",\"idNumber\":\"321283198707120035\"},{\"idName\":\"蒋昆\",\"idNumber\":\"320321199012151214\"},{\"idName\":\"陈海\",\"idNumber\":\"320923198210224518\"},{\"idName\":\"陈颖许\",\"idNumber\":\"520221199212114890\"},{\"idName\":\"顾海鹏\",\"idNumber\":\"320921199012222476\"},{\"idName\":\"夏金帅\",\"idNumber\":\"320324199407272511\"},{\"idName\":\"黄永魁\",\"idNumber\":\"410728199302064598\"},{\"idName\":\"李毓欣\",\"idNumber\":\"411282199109107041\"},{\"idName\":\"罗文莉\",\"idNumber\":\"342222199101244064\"},{\"idName\":\"李博兴\",\"idNumber\":\"610115199410285294\"},{\"idName\":\"惠拴林\",\"idNumber\":\"610321198601023637\"},{\"idName\":\"刘强\",\"idNumber\":\"420324199512263477\"},{\"idName\":\"黄晶\",\"idNumber\":\"321283198705110415\"},{\"idName\":\"谭情情\",\"idNumber\":\"341223198802042917\"},{\"idName\":\"尹健虎\",\"idNumber\":\"341621198902084910\"},{\"idName\":\"王业全\",\"idNumber\":\"342425198710304435\"},{\"idName\":\"周彦军\",\"idNumber\":\"622621198702012494\"},{\"idName\":\"邵金峰\",\"idNumber\":\"339005198204159033\"},{\"idName\":\"崔浩\",\"idNumber\":\"342224198601041579\"},{\"idName\":\"杨松\",\"idNumber\":\"52232419960606161X\"},{\"idName\":\"田孝强\",\"idNumber\":\"41092719920916701X\"},{\"idName\":\"左政\",\"idNumber\":\"150102199102040137\"},{\"idName\":\"闵敏\",\"idNumber\":\"320830198510082214\"},{\"idName\":\"陈雪建\",\"idNumber\":\"321321198607197410\"},{\"idName\":\"许高祥\",\"idNumber\":\"340122198908123317\"},{\"idName\":\"何丰\",\"idNumber\":\"36230119900417003X\"},{\"idName\":\"于乐\",\"idNumber\":\"320381198807106718\"},{\"idName\":\"王辉\",\"idNumber\":\"36232219920709661X\"},{\"idName\":\"何明坤\",\"idNumber\":\"510723198711094496\"},{\"idName\":\"侯春先\",\"idNumber\":\"341221199304013170\"},{\"idName\":\"缪立聪\",\"idNumber\":\"340123198211227913\"},{\"idName\":\"郑志明\",\"idNumber\":\"61232319821028421X\"},{\"idName\":\"张博\",\"idNumber\":\"372929199507167212\"},{\"idName\":\"姜瑞瑞\",\"idNumber\":\"341221199405027563\"},{\"idName\":\"陈祥\",\"idNumber\":\"320722199510057018\"},{\"idName\":\"郑旭\",\"idNumber\":\"500384199512284274\"},{\"idName\":\"林晓二\",\"idNumber\":\"340823198304227515\"},{\"idName\":\"刘颂宇\",\"idNumber\":\"342401199102114818\"},{\"idName\":\"杨福生\",\"idNumber\":\"510623198105232718\"},{\"idName\":\"王磊\",\"idNumber\":\"340702199302187019\"},{\"idName\":\"陈昌梨\",\"idNumber\":\"320924198109223816\"},{\"idName\":\"刘传祥\",\"idNumber\":\"370830198503152973\"},{\"idName\":\"黄宏\",\"idNumber\":\"230805198205100211\"},{\"idName\":\"武贺阳\",\"idNumber\":\"412824199010176071\"},{\"idName\":\"郑清龙\",\"idNumber\":\"612401199303269715\"},{\"idName\":\"倪延军\",\"idNumber\":\"372924199410075715\"},{\"idName\":\"王安伦\",\"idNumber\":\"340121199311101615\"},{\"idName\":\"陈海龙\",\"idNumber\":\"610327199201064635\"},{\"idName\":\"梁龙\",\"idNumber\":\"410822199007082037\"},{\"idName\":\"薛法洲\",\"idNumber\":\"410927199509078019\"},{\"idName\":\"祝建柳\",\"idNumber\":\"362322198708284251\"},{\"idName\":\"杨斌\",\"idNumber\":\"362226199007292114\"},{\"idName\":\"赵留强\",\"idNumber\":\"412702199308201475\"},{\"idName\":\"代文标\",\"idNumber\":\"342221199607044039\"},{\"idName\":\"肖桂鑫\",\"idNumber\":\"510725198405050017\"},{\"idName\":\"张奇\",\"idNumber\":\"320323198803102214\"},{\"idName\":\"王思可\",\"idNumber\":\"372926198910258612\"},{\"idName\":\"缪小飞\",\"idNumber\":\"320924198302172993\"},{\"idName\":\"吴伟清\",\"idNumber\":\"320583198708284619\"},{\"idName\":\"倪延平\",\"idNumber\":\"372924199410075758\"},{\"idName\":\"张杰\",\"idNumber\":\"341122198708110213\"}]";

		public List<String> getIdNumbers() {
			User[] users = new Gson().fromJson(jsonString, User[].class);
			List<String> idNumbers = new ArrayList<String>();
			
			for(User user : users){
				idNumbers.add(user.idNumber);
			}
			
			return idNumbers;
		}
	}
	
	public static class CsvParser{
		private ArrayList<String> allUserIds = new ArrayList<String>();

		public CsvParser(String filePath) throws IOException {
			parse(filePath);
		}

		public List<String> getAllIdNumbers() {
			ArrayList<String> allIdNumbers = new ArrayList<String>();
			Dao<EndUserExtensionObject> dao = Dao.create(EndUserExtensionObject.class, DatabaseApi.database);
			
			for(String id : allUserIds){
				//id="6B0D1A8E-3D12-E511-98E3-AC853DA49BEC";
				String sql = "SELECT TOP 1 * "
						 + "FROM EndUserExtensionObjects "
						 + "where Id=:userid";
				EndUserExtensionObject eu = dao.getSingle(sql, CollectionUtils.mapOf("userid", id.trim()));
				if(eu == null){
					Logger.get().error("weired user id: " + id);
					continue;
				}
				allIdNumbers.add(eu.IdNumber);
			}
			
			return allIdNumbers;
		}
		
		private void parse(String filePath) throws IOException {
			FileReader fr = new FileReader(filePath);
			BufferedReader br = new BufferedReader(fr);

			String s = "";
			// values
			while ((s = br.readLine()) != null) {
				String[] columns = s.split(",");
				allUserIds.add(columns[1]);
			}

			fr.close();
		}
	}	
}
