//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

@JsonDeserialize(
        builder = TimeSeriesProfileList.Builder.class
)
public class TimeSeriesProfileList {
    @JsonProperty("profile-list")
    private final List<TimeSeriesProfile> profileList;
    @JsonProperty("page")
    private final String page;
    @JsonProperty("page-size")
    private final int pageSize;
    @JsonProperty("total")
    private final int total;
    @JsonProperty("next-page")
    private final String nextPage;

    private TimeSeriesProfileList(Builder builder) {
        this.page = builder.page;
        this.pageSize = builder.pageSize;
        this.total = builder.total;
        this.profileList = builder.timeSeriesProfileList;
        this.nextPage = builder.nextPage;
    }

    public List<TimeSeriesProfile> getProfileList() {
        return this.profileList;
    }

    public String getPage() {
        return this.page;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public int getTotal() {
        return this.total;
    }

    public String getNextPage() {
        return this.nextPage;
    }

    public static class Builder {
        @JsonProperty("page")
        private String page;
        @JsonProperty("page-size")
        private int pageSize;
        @JsonProperty("total")
        private int total;
        @JsonProperty("profile-list")
        private List<TimeSeriesProfile> timeSeriesProfileList;
        @JsonProperty("next-page")
        private String nextPage;

        public Builder page(String page) {
            this.page = page;
            return this;
        }

        public Builder pageSize(int pageSize) {
            this.pageSize = pageSize;
            return this;
        }

        public Builder total(int total) {
            this.total = total;
            return this;
        }

        public Builder nextPage(String nextPage) {
            this.nextPage = nextPage;
            return this;
        }

        public Builder timeSeriesProfileList(List<TimeSeriesProfile> timeSeriesProfileList) {
            this.timeSeriesProfileList = timeSeriesProfileList;
            return this;
        }

        public TimeSeriesProfileList build() {
            return new TimeSeriesProfileList(this);
        }
    }
}
