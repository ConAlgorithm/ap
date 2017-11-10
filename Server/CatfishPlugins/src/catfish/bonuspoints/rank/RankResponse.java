package catfish.bonuspoints.rank;

public class RankResponse {

	public class RankObject implements Comparable<RankObject>{

		public String UserId;

		public int Points;

		public int Rank;

		public int LastRank;

		public RankObject(String UserId, int points) {
			this.UserId = UserId;
			this.Points = points;
			this.Rank = 0;
			this.LastRank = 0;
		}

		@Override
		public int compareTo(RankObject obj) {
			if (Points < obj.Points) return 1;
			if (Points > obj.Points) return -1;
			return 0;
		}

		public void addPoints(int point) {
			this.Points += point;
		}
	}

	public RankObject[] tops;

	public RankObject myRank;

	public String error;
	
	public RankResponse(String error) {
		this.error = error;
		tops = null;
		myRank = null;
	}
	
	public RankResponse() {}
	
	public static RankResponse getErrorResponse(String error) {
		return new RankResponse(error);
	}
	
	public RankObject createRankObject(String UserId, int points) {
		return new RankObject(UserId, points);
	}

    public static int getTopCountByArea(String area) {
      area = area.toLowerCase();
      if (area.equals("city")) {
        return 20;
      }
      else if (area.equals("area")) {
        return 10;
      }
      else if (area.equals("province")) {
        return 30;
      }
      return 10;
    }
}
