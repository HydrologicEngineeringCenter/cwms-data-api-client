/*
 * Copyright (c) 2021
 * United States Army Corps of Engineers - Hydrologic Engineering Center (USACE/HEC)
 * All Rights Reserved.  USACE PROPRIETARY/CONFIDENTIAL.
 * Source may not be released without written approval from HEC
 */

package mil.army.usace.hec.cwms.http.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;

/**
 * List of retrieved blobs
 */
@ApiModel(description = "List of retrieved blobs")
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2021-11-02T12:25:34.578-07:00[America/Los_Angeles]")
public class BlobsBlobs {
    @JsonProperty("office")
    private String office = null;

    @JsonProperty("id")
    private String id = null;

    @JsonProperty("description")
    private String description = null;

    @JsonProperty("mediaTypeId")
    private String mediaTypeId = null;

    @JsonProperty("value")
    @Valid
    private List<byte[]> value = null;

    public BlobsBlobs office(String office) {
        this.office = office;
        return this;
    }

    /**
     * Get office
     *
     * @return office
     **/
    @ApiModelProperty(value = "")

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public BlobsBlobs id(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get id
     *
     * @return id
     **/
    @ApiModelProperty(value = "")

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BlobsBlobs description(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get description
     *
     * @return description
     **/
    @ApiModelProperty(value = "")

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BlobsBlobs mediaTypeId(String mediaTypeId) {
        this.mediaTypeId = mediaTypeId;
        return this;
    }

    /**
     * Get mediaTypeId
     *
     * @return mediaTypeId
     **/
    @ApiModelProperty(value = "")

    public String getMediaTypeId() {
        return mediaTypeId;
    }

    public void setMediaTypeId(String mediaTypeId) {
        this.mediaTypeId = mediaTypeId;
    }

    public BlobsBlobs value(List<byte[]> value) {
        this.value = value;
        return this;
    }

    public BlobsBlobs addValueItem(byte[] valueItem) {
        if (this.value == null) {
            this.value = new ArrayList<byte[]>();
        }
        this.value.add(valueItem);
        return this;
    }

    /**
     * Get value
     *
     * @return value
     **/
    @ApiModelProperty(value = "")

    public List<byte[]> getValue() {
        return value;
    }

    public void setValue(List<byte[]> value) {
        this.value = value;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BlobsBlobs blobsBlobs = (BlobsBlobs) o;
        return Objects.equals(this.office, blobsBlobs.office) &&
            Objects.equals(this.id, blobsBlobs.id) &&
            Objects.equals(this.description, blobsBlobs.description) &&
            Objects.equals(this.mediaTypeId, blobsBlobs.mediaTypeId) &&
            Objects.equals(this.value, blobsBlobs.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(office, id, description, mediaTypeId, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BlobsBlobs {\n");

        sb.append("    office: ").append(toIndentedString(office)).append("\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    description: ").append(toIndentedString(description)).append("\n");
        sb.append("    mediaTypeId: ").append(toIndentedString(mediaTypeId)).append("\n");
        sb.append("    value: ").append(toIndentedString(value)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
