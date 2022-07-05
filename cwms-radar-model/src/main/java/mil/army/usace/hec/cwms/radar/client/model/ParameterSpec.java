package mil.army.usace.hec.cwms.radar.client.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * ParameterSpec
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-06-30T22:13:10.613Z[GMT]")
public class ParameterSpec
{
	@JsonProperty("parameter")
	private String parameter = null;

	@JsonProperty("in-range-method")
	private String inRangeMethod = null;

	@JsonProperty("out-range-low-method")
	private String outRangeLowMethod = null;

	@JsonProperty("out-range-high-method")
	private String outRangeHighMethod = null;

	public ParameterSpec parameter(String parameter)
	{
		this.parameter = parameter;
		return this;
	}

	/**
	 * Get parameter
	 *
	 * @return parameter
	 **/


	public String getParameter()
	{
		return parameter;
	}

	public void setParameter(String parameter)
	{
		this.parameter = parameter;
	}

	public ParameterSpec inRangeMethod(String inRangeMethod)
	{
		this.inRangeMethod = inRangeMethod;
		return this;
	}

	/**
	 * Get inRangeMethod
	 *
	 * @return inRangeMethod
	 **/


	public String getInRangeMethod()
	{
		return inRangeMethod;
	}

	public void setInRangeMethod(String inRangeMethod)
	{
		this.inRangeMethod = inRangeMethod;
	}

	public ParameterSpec outRangeLowMethod(String outRangeLowMethod)
	{
		this.outRangeLowMethod = outRangeLowMethod;
		return this;
	}

	/**
	 * Get outRangeLowMethod
	 *
	 * @return outRangeLowMethod
	 **/


	public String getOutRangeLowMethod()
	{
		return outRangeLowMethod;
	}

	public void setOutRangeLowMethod(String outRangeLowMethod)
	{
		this.outRangeLowMethod = outRangeLowMethod;
	}

	public ParameterSpec outRangeHighMethod(String outRangeHighMethod)
	{
		this.outRangeHighMethod = outRangeHighMethod;
		return this;
	}

	/**
	 * Get outRangeHighMethod
	 *
	 * @return outRangeHighMethod
	 **/


	public String getOutRangeHighMethod()
	{
		return outRangeHighMethod;
	}

	public void setOutRangeHighMethod(String outRangeHighMethod)
	{
		this.outRangeHighMethod = outRangeHighMethod;
	}


	@Override
	public boolean equals(java.lang.Object o)
	{
		if(this == o)
		{
			return true;
		}
		if(o == null || getClass() != o.getClass())
		{
			return false;
		}
		ParameterSpec parameterSpec = (ParameterSpec) o;
		return Objects.equals(this.parameter, parameterSpec.parameter) && Objects.equals(this.inRangeMethod,
				parameterSpec.inRangeMethod) && Objects.equals(this.outRangeLowMethod,
				parameterSpec.outRangeLowMethod) && Objects.equals(this.outRangeHighMethod,
				parameterSpec.outRangeHighMethod);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(parameter, inRangeMethod, outRangeLowMethod, outRangeHighMethod);
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("class ParameterSpec {\n");

		sb.append("    parameter: ").append(toIndentedString(parameter)).append("\n");
		sb.append("    inRangeMethod: ").append(toIndentedString(inRangeMethod)).append("\n");
		sb.append("    outRangeLowMethod: ").append(toIndentedString(outRangeLowMethod)).append("\n");
		sb.append("    outRangeHighMethod: ").append(toIndentedString(outRangeHighMethod)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o)
	{
		if(o == null)
		{
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
