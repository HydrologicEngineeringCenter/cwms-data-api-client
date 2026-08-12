package mil.army.usace.hec.cwms.data.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Pools
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@jakarta.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2026-05-03T12:34:59.760765100-07:00[America/Los_Angeles]")
public class Pools {

    @JsonProperty("next-page")
    private String nextPage = null;

    @JsonProperty("page")
    private String page = null;

    @JsonProperty("page-size")
    private Integer pageSize = null;

    @JsonProperty("pools")
    @Valid
    private List<Pool> pools = new ArrayList<>();

    @JsonProperty("total")
    private Integer total = null;

    public Pools nextPage(String nextPage) {
        this.nextPage = nextPage;
        return this;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    public Pools page(String page) {
        this.page = page;
        return this;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public Pools pageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Pools pools(List<Pool> pools) {
        this.pools = pools;
        return this;
    }

    public Pools addPoolsItem(Pool poolsItem) {
            if (this.pools == null) {
            this.pools = new ArrayList<>();
            }
        this.pools.add(poolsItem);
        return this;
    }

    public List<Pool> getPools() {
        return pools;
    }

    public void setPools(List<Pool> pools) {
        this.pools = pools;
    }

    public Pools total(Integer total) {
        this.total = total;
        return this;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        } 
        Pools pools = (Pools) o;
        return this.nextPage == null || pools.nextPage == null?Objects.equals(this.nextPage, pools.nextPage):this.nextPage.equalsIgnoreCase(pools.nextPage)
         && this.page == null || pools.page == null?Objects.equals(this.page, pools.page):this.page.equalsIgnoreCase(pools.page)
         && Objects.equals(this.pageSize, pools.pageSize)
         && Objects.equals(this.pools, pools.pools)
         && Objects.equals(this.total, pools.total)
        ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nextPage==null?0:nextPage.toLowerCase(), page==null?0:page.toLowerCase(), pageSize, pools, total);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Pools {\n");
        
        sb.append("    next-page: ").append(toIndentedString(nextPage)).append("\n");
        sb.append("    page: ").append(toIndentedString(page)).append("\n");
        sb.append("    page-size: ").append(toIndentedString(pageSize)).append("\n");
        sb.append("    pools: ").append(toIndentedString(pools)).append("\n");
        sb.append("    total: ").append(toIndentedString(total)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
