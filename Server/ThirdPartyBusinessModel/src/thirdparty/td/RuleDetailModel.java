package thirdparty.td;

import com.google.gson.annotations.SerializedName;

public class RuleDetailModel {

        @SerializedName("描述")
        private String description;

        @SerializedName("计算结果")
        private int calculateResult;

        @SerializedName("个数")
        private int count;

        public int getCount() {
                return count;
        }

        public void setCount(int count) {
                this.count = count;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public int getCalculateResult() {
                return calculateResult;
        }

        public void setCalculateResult(int calculateResult) {
                this.calculateResult = calculateResult;
        }


}
